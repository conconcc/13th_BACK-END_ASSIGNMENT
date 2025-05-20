package com.conconcc.likelionweek4.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_chatroom", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "chatroom_id"})
})
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"user", "chatRoom"})
public class UserChatRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id", nullable = false)
    private ChatRoomEntity chatRoom;


    //required: no arguments
    //  found:    String
    //  reason: actual and formal argument lists differ in length
    // 각 서비스에서 위와 같은 오류 발생으로 기본 생성자 이외 추가 생성자 추가
    public UserChatRoomEntity(UserEntity user, ChatRoomEntity room) {
        this.user = user;
        this.chatRoom = room;
    }
}
