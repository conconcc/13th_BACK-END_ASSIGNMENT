package com.conconcc.likelionweek4.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
// 1명이 여러번 확인한게 읽은 상태에 적용되지 않도록 방지
@Table(name = "read_status", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "chat_id"}))
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"chat", "user"})
public class ChatReadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Long read_status_id;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private ChatEntity chat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;


    @Column(name = "isRead")
    private Boolean isRead;



    @Column(name = "read_at")
    private Timestamp readAt;


    public void markAsRead() {
        this.isRead = true;
        this.readAt = new Timestamp(System.currentTimeMillis());

    }
}
