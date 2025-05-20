package com.conconcc.likelionweek4.Controller;

import com.conconcc.likelionweek4.Dto.*;
import com.conconcc.likelionweek4.Entity.UserEntity;
import com.conconcc.likelionweek4.Repository.UserRepository;
import com.conconcc.likelionweek4.Service.ChatRoomService;
import com.conconcc.likelionweek4.Service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final ChatRoomService chatRoomService;

    public UserController(UserService userService, ChatRoomService chatRoomService) {
        this.userService = userService;
        this.chatRoomService = chatRoomService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserCreationRequestDto userDto) {
        try{
            UserResponseDto createdUser = userService.createUser(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping("/signup")
    public ResponseEntity<UserRegisterResponseDto> signUp(@RequestBody UserRegisterRequestDto userDto) {
        try{
            UserRegisterResponseDto signupUser=userService.signUpUser(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(signupUser);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getChatRoomById(@PathVariable Long userId) {
        try{
            UserResponseDto user=userService.getUserById(userId);
            return ResponseEntity.ok(user);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@RequestParam String password, @PathVariable Long userId) {
        try{
            boolean isAuthenticated = userService.authenticateUser(userId, password);
            if (!isAuthenticated) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            userService.deleteUser(userId);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping("/chatrooms/{userId}")
    public ResponseEntity<List<ChatRoomResponseDto>> getChatRoomsByUserIdAndPassword(@PathVariable Long userId,@RequestParam String password) {
        try{
            boolean isAuthenticated = userService.authenticateUser(userId, password);
            if (!isAuthenticated) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            List<ChatRoomResponseDto> userChatRooms = chatRoomService.getAllChatRoomsByUserId(userId);
            return ResponseEntity.ok(userChatRooms);
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
}
