package com.conconcc.likelionweek4.Service;

import com.conconcc.likelionweek4.Dto.UnreadMessageCountResponseDto;
import com.conconcc.likelionweek4.Entity.*;
import com.conconcc.likelionweek4.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChatReadStatusService {
    private final ChatReadRepository chatReadRepository;
    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;


    public ChatReadStatusService(ChatReadRepository chatReadRepository, ChatRepository chatRepository, ChatRoomRepository chatRoomRepository, UserRepository userRepository) {
        this.chatReadRepository = chatReadRepository;
        this.chatRepository = chatRepository;
        this.chatRoomRepository =chatRoomRepository;
        this.userRepository = userRepository;
    }
    //읽으면 읽음 상태로 변경
    @Transactional
    public ChatReadEntity findOrCreateChatReadStatus(Long chatId, Long userId, Long roomId) {
        //엔티티 조회 및 여부 확인 (chatroom, chat, user)
        ChatRoomEntity chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("방을 찾을 수 없습니다: " + roomId));
        UserEntity user = chatRoom.getUserChatRooms().stream()
                .map(UserChatRoomEntity::getUser)
                .filter(uUser -> uUser.getId().equals(userId)).findFirst().orElseThrow(()-> new EntityNotFoundException("사용자가"+userId + "해당 방에 존재하지 않습니다."+roomId));

        ChatEntity chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new EntityNotFoundException("채팅을 찾을 수 없습니다. " + chatId));
        if (!chat.getChatRoom().getId().equals(roomId)) {
            throw new IllegalArgumentException("채팅이 " + chatId + " 해당 방에서 찾을 수 없습니다 " + roomId);
        }

        return chatReadRepository.findByChatIdAndUserId(chatId, userId)
                .orElseGet(() -> {
                    ChatReadEntity newStatus = new ChatReadEntity();
                    newStatus.setChat(chat);
                    newStatus.setUser(user);
                    newStatus.setReadAt(null);
                    newStatus.setIsRead(false);
                    return chatReadRepository.save(newStatus);
                });
    }

    @Transactional(readOnly = true)
    public UnreadMessageCountResponseDto getUnreadMessageCount(Long chatRoomId, Long userId) {
        chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new EntityNotFoundException("채팅방을 찾을 수 없습니다: " + chatRoomId));
        userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다: " + userId));
        long unreadCount = chatReadRepository.countByChatChatRoomIdAndUserIdAndIsReadFalse(chatRoomId, userId);
        return new UnreadMessageCountResponseDto(chatRoomId, userId, unreadCount);
    }

    @Transactional
    public void createReadStatusForNewChat(ChatEntity newChat, List<UserEntity> chatRoomUsers) {
        for(UserEntity user : chatRoomUsers) {
            boolean isSender =user.getId().equals(newChat.getSender().getId());
            Optional<ChatReadEntity> readStatus = chatReadRepository.findByChatIdAndUserId(newChat.getId(), user.getId());
            ChatReadEntity chatRead;
            if(readStatus.isPresent()) {
                chatRead = readStatus.get();
                if(!chatRead.getIsRead()&&isSender) {
                    chatRead.markAsRead();
                    chatReadRepository.save(chatRead);
                }
            }else{
                chatRead = new ChatReadEntity();
                chatRead.setChat(newChat);
                chatRead.setUser(user);
                if(isSender) {
                    chatRead.markAsRead();
                }else{
                    chatRead.setIsRead(false);
                    chatRead.setReadAt(null);
                }
                chatReadRepository.save(chatRead);
            }


        }
    }
    @Transactional
    public void markMessageAsRead(Long chatId, Long userId, Long roomId) {
        ChatReadEntity chatRead = findOrCreateChatReadStatus(chatId, userId, roomId);
        if(chatRead.getIsRead()){
            chatRead.markAsRead();
            chatReadRepository.save(chatRead);
        }

    }
}
