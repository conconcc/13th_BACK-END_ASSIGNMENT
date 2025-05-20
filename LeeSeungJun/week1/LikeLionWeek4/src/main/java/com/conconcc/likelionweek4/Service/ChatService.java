package com.conconcc.likelionweek4.Service;

import com.conconcc.likelionweek4.Dto.ChatMessageRequestDto;
import com.conconcc.likelionweek4.Dto.ChatMessageResponseDto;
import com.conconcc.likelionweek4.Entity.*;
import com.conconcc.likelionweek4.Repository.ChatReadRepository;
import com.conconcc.likelionweek4.Repository.ChatRepository;
import com.conconcc.likelionweek4.Repository.ChatRoomRepository;
import com.conconcc.likelionweek4.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final ChatReadRepository chatReadRepository;

    public ChatService(ChatRoomRepository chatRoomRepository, ChatRepository chatRepository, UserRepository userRepository, ChatReadRepository chatReadRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
        this.chatReadRepository = chatReadRepository;
    }

    public ChatMessageResponseDto sendMessage(ChatMessageRequestDto chatDto) {
        UserEntity sender = userRepository.findById(chatDto.getSenderId()).orElseThrow(()->new EntityNotFoundException("사용자 없음"+chatDto.getSenderId()));
        ChatRoomEntity chatRoom = chatRoomRepository.findById((chatDto.getChatRoomId())).orElseThrow(() -> new EntityNotFoundException("채팅방 없음:" + chatDto.getChatRoomId()));

        ChatEntity newChat = new ChatEntity(chatDto.getContent(), sender, chatRoom);
        ChatEntity savedChat = chatRepository.save(newChat);

        List<UserEntity> chatRoomUsers = chatRoom.getUserChatRooms().stream()
                .map(UserChatRoomEntity::getUser)
                .toList();

        for(UserEntity user : chatRoomUsers) {
            boolean isSender = user.getId().equals(savedChat.getSender().getId());
            Optional<ChatReadEntity> readStatusOptional = chatReadRepository.findByChatIdAndUserId(savedChat.getId(), user.getId());

            ChatReadEntity chatRead;
            if(readStatusOptional.isPresent()) {
                chatRead = readStatusOptional.get();
                if(isSender&&!chatRead.getIsRead()) {
                    chatRead.setIsRead(true);
                    chatRead.setReadAt(Timestamp.valueOf(LocalDateTime.now()));
                    chatReadRepository.save(chatRead);
                }
            }else {
                chatRead = new ChatReadEntity();
                chatRead.setChat(savedChat);
                chatRead.setUser(user);
                if (isSender){
                    chatRead.setIsRead(true);
                    chatRead.setReadAt(Timestamp.valueOf(LocalDateTime.now()));
                }else {
                    chatRead.setIsRead(false);
                    chatRead.setReadAt(null);
                }
                chatReadRepository.save(chatRead);
            }

        }

        return new ChatMessageResponseDto(
                savedChat.getId(), savedChat.getSender().getId(),
                savedChat.getSender().getUsername(), savedChat.getChatRoom().getId(),
                savedChat.getContent(), savedChat.getTimestamp()
        );
    }
    @Transactional(readOnly = true)
    public List<ChatMessageResponseDto> getMessagesByChatRoom(Long chatRoomId){
        List<ChatEntity> chats = chatRepository.findByChatRoomId(chatRoomId);
        return chats.stream().map(chat -> new ChatMessageResponseDto(
                chat.getId(), chat.getSender().getId(), chat.getSender().getUsername(),
                chat.getChatRoom().getId(), chat.getContent(), chat.getTimestamp()
        )).collect(Collectors.toList());
    }
    public void deleteMessage(Long chatId){
        if(!chatRepository.existsById(chatId))
            throw new EntityNotFoundException("채팅 없음: " + chatId);

        chatRepository.deleteById(chatId);
    }
}
