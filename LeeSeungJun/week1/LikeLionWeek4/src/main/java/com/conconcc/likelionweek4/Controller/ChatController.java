package com.conconcc.likelionweek4.Controller;

import com.conconcc.likelionweek4.Dto.ChatMessageRequestDto;
import com.conconcc.likelionweek4.Dto.ChatMessageResponseDto;
import com.conconcc.likelionweek4.Dto.UnreadMessageCountResponseDto;
import com.conconcc.likelionweek4.Service.ChatReadStatusService;
import com.conconcc.likelionweek4.Service.ChatService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {
    private final ChatService chatService;
    private final ChatReadStatusService chatReadStatusService;

    public ChatController(ChatService chatService, ChatReadStatusService chatReadStatusService) {
        this.chatService = chatService;
        this.chatReadStatusService = chatReadStatusService;
    }

    @PostMapping
    public ResponseEntity<ChatMessageResponseDto> sendMessage(@RequestBody ChatMessageRequestDto chatDto) {
        try{
            ChatMessageResponseDto sentMessage = chatService.sendMessage(chatDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(sentMessage);
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/room{roomId}")
    public ResponseEntity<List<ChatMessageResponseDto>>getMessagesByChatRoom(@PathVariable Long roomId) {
        try{
            List<ChatMessageResponseDto> messages = chatService.getMessagesByChatRoom(roomId);
            return ResponseEntity.ok(messages);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{chatId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long chatId) {
        try{
            chatService.deleteMessage(chatId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{chatId}/read")
    public ResponseEntity<Void>MarkAsRead(@PathVariable Long chatId, @RequestParam Long userId, @RequestParam Long roomId) {
        try{
            chatReadStatusService.markMessageAsRead(chatId, userId, roomId);
            return ResponseEntity.ok().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/rooms/{roomId}/users/{userId}/count_unread")
    public ResponseEntity<UnreadMessageCountResponseDto> getUnreadMessageCount(@PathVariable Long roomId, @PathVariable Long userId) {
        try{
            UnreadMessageCountResponseDto responseDto = chatReadStatusService.getUnreadMessageCount(roomId, userId);
            return ResponseEntity.ok(responseDto);
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
