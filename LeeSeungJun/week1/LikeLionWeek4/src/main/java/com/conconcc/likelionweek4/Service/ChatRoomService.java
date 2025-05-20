package com.conconcc.likelionweek4.Service;

import com.conconcc.likelionweek4.Dto.ChatMessageResponseDto;
import com.conconcc.likelionweek4.Dto.ChatRoomCreationRequestDto;
import com.conconcc.likelionweek4.Dto.ChatRoomDetailsResponseDto;
import com.conconcc.likelionweek4.Dto.ChatRoomResponseDto;
import com.conconcc.likelionweek4.Entity.ChatRoomEntity;
import com.conconcc.likelionweek4.Entity.UserChatRoomEntity;
import com.conconcc.likelionweek4.Entity.UserEntity;
import com.conconcc.likelionweek4.Repository.ChatRoomRepository;
import com.conconcc.likelionweek4.Repository.UserChatRoomRepository;
import com.conconcc.likelionweek4.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final UserChatRoomRepository userChatRoomRepository;

    public ChatRoomService(ChatRoomRepository chatRoomRepository, UserRepository userRepository, UserChatRoomRepository userChatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
        this.userChatRoomRepository = userChatRoomRepository;
    }

    public ChatRoomResponseDto createChatRoom(ChatRoomCreationRequestDto roomDto) {
        ChatRoomEntity newRoom = new ChatRoomEntity(roomDto.getRoomName());
        ChatRoomEntity savedRoom = chatRoomRepository.save(newRoom);

        return new ChatRoomResponseDto(savedRoom.getId(), savedRoom.getRoomName());
    }

    @Transactional(readOnly = true)
    public List<ChatRoomResponseDto> getAllChatRooms() {
        List<ChatRoomEntity> rooms = chatRoomRepository.findAll();
        return rooms.stream().map(room -> new ChatRoomResponseDto(room.getId(), room.getRoomName())).collect(Collectors.toList());
    }
    //id 에 맞는 유저가 있는 모든 채팅방 조회
    @Transactional
    public List<ChatRoomResponseDto> getAllChatRoomsByUserId(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("사용자 찾지 못함: "+userId));
        List<ChatRoomEntity> rooms = chatRoomRepository.findByUserChatRoomsUserUsername(user.getUsername());
        return rooms.stream().map(room -> new ChatRoomResponseDto(room.getId(), room.getRoomName())).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public ChatRoomDetailsResponseDto getChatRoomDetails(Long roomId) {
        ChatRoomEntity room = chatRoomRepository.findById(roomId).orElseThrow(() -> new EntityNotFoundException("채팅방 없음" + roomId));

        List<ChatMessageResponseDto> messages = room.getChats().stream()
                .map(chat -> new ChatMessageResponseDto(
                        chat.getId(), chat.getSender().getId(), chat.getSender().getUsername(), chat.getChatRoom().getId(), chat.getContent(), chat.getTimestamp()
                ))
                .collect(Collectors.toList());
    return new ChatRoomDetailsResponseDto(room.getId(), room.getRoomName(), messages);
    }
    public void deleteChatRoom(Long roomId) {
        if(!chatRoomRepository.existsById(roomId)){
            throw new EntityNotFoundException("채팅방 없음 : " + roomId);
        }
        chatRoomRepository.deleteById(roomId);
    }
    public void addUserToChatRoom(Long roomId, Long userId) {
        ChatRoomEntity room=chatRoomRepository.findById(roomId)
                .orElseThrow(()->new EntityNotFoundException("채팅방 없음"+roomId));
        UserEntity user= userRepository.findById(userId)
                .orElseThrow(()->new EntityNotFoundException("사용자 없음"+userId));

        userChatRoomRepository.findByUserIdAndChatRoomId(userId, roomId).ifPresent(ucr -> {
            throw new IllegalArgumentException("사용자" + userId + "는 이미 " + roomId + "에 있습니다.");
        });
        UserChatRoomEntity userChatRoom=new UserChatRoomEntity(user, room);
        userChatRoomRepository.save(userChatRoom);
    }
    public void removeUserFromChatRoom(Long roomId, Long userId) {
        UserChatRoomEntity userChatRoom = userChatRoomRepository.findByUserIdAndChatRoomId(userId, roomId).orElseThrow(()->new EntityNotFoundException("사용자" + userId + "는" + roomId + "에 없습니다."));
        userChatRoomRepository.delete(userChatRoom);
    }

}
