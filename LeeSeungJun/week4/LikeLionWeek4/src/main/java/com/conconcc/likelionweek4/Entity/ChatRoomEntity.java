package com.conconcc.likelionweek4.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "chatrooms")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"userChatRooms", "chats"})

public class ChatRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id")
    private Long id;

    @Column(nullable = false)
    private String roomName;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserChatRoomEntity> userChatRooms = new HashSet<>();

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ChatEntity> chats = new HashSet<>();

    //required: no arguments
    //  found:    String
    //  reason: actual and formal argument lists differ in length
    // 각 서비스에서 위와 같은 오류 발생으로 기본 생성자 이외 추가 생성자 추가
    public ChatRoomEntity(String roomName) {
        this.roomName = roomName;
    }
    public Set<UserEntity> getUsers() {
        return userChatRooms.stream()
                .map(UserChatRoomEntity::getUser)
                .collect(Collectors.toSet());
    }

}
