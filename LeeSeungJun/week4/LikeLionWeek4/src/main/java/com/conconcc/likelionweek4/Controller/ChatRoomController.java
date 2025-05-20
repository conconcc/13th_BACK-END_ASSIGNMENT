package com.conconcc.likelionweek4.Controller;

import com.conconcc.likelionweek4.Dto.ChatRoomCreationRequestDto;
import com.conconcc.likelionweek4.Dto.ChatRoomDetailsResponseDto;
import com.conconcc.likelionweek4.Dto.ChatRoomResponseDto;
import com.conconcc.likelionweek4.Service.ChatRoomService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatrooms")
public class ChatRoomController{
    private final ChatRoomService chatRoomService;
    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @PostMapping
    public ResponseEntity<ChatRoomResponseDto> createChatRoom(@RequestBody ChatRoomCreationRequestDto roomDto){
        try{
            ChatRoomResponseDto createdRoom= chatRoomService.createChatRoom(roomDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRoom);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ChatRoomResponseDto>> getAllChatRooms(){
        List<ChatRoomResponseDto> rooms = chatRoomService.getAllChatRooms();
        return ResponseEntity.ok(rooms);
    }
    //자신의 채팅방 조회 추가
    @GetMapping("/rooms/{userId}")
    public ResponseEntity<List<ChatRoomResponseDto>> getAllChatRoomsByUserId(@PathVariable Long userId){
        List<ChatRoomResponseDto> rooms = chatRoomService.getAllChatRoomsByUserId(userId);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<ChatRoomDetailsResponseDto> getChatRoomDetails(@PathVariable Long roomId){
        try{
            ChatRoomDetailsResponseDto roomDetails = chatRoomService.getChatRoomDetails(roomId);
            return ResponseEntity.ok(roomDetails);
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteChatRoom(@PathVariable Long roomId){
        try{
            chatRoomService.deleteChatRoom(roomId);
            return ResponseEntity.noContent().build();
        } catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{roomId}/users/{userId}")
    public ResponseEntity<Void> addUserToChatRoom(@PathVariable Long roomId, @PathVariable Long userId){
        try{
            chatRoomService.addUserToChatRoom(roomId, userId);
            return ResponseEntity.ok().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}