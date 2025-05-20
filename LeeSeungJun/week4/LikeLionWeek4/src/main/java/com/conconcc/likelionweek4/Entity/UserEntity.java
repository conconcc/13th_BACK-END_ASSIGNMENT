package com.conconcc.likelionweek4.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"userChatRooms", "chats"})

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name="user_id")
    private Long id;

    @Column(nullable = false, name="user_password")
    private String password;

    @Column(nullable = false, unique = true)
    private String username;
//    @ManyToMany
//    @JoinTable(
//            name="user_chatrooms",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name="chatroom_id")
//    )
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserChatRoomEntity> userChatRooms = new HashSet<>();


    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ChatEntity> chats = new HashSet<>();

    //required: no arguments
    //  found:    String
    //  reason: actual and formal argument lists differ in length
    // 각 서비스에서 위와 같은 오류 발생으로 기본 생성자 이외 추가 생성자 추가
    public UserEntity(String username) {
        this.username = username;
    }


    public UserEntity(String username, String password) {
        this.password=password;
        this.username = username;
    }
}
