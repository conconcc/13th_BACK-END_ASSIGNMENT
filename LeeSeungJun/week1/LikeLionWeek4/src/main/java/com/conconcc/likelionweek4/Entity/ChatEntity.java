package com.conconcc.likelionweek4.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;

import java.util.Set;

@Entity
@Table(name = "chats")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"sender", "chatRoom"})
public class ChatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private UserEntity sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id", nullable = false)
    private ChatRoomEntity chatRoom;


    @OneToMany(mappedBy = "chat")
    private Set<ChatReadEntity> readStatuses = new HashSet<>();


    //required: no arguments
    //  found:    String
    //  reason: actual and formal argument lists differ in length
    // 각 서비스에서 위와 같은 오류 발생으로 기본 생성자 이외 추가 생성자 추가
    public ChatEntity(String content, UserEntity sender, ChatRoomEntity chatRoom) {
        this.content = content;
        this.sender = sender;
        this.chatRoom = chatRoom;
    }
}
