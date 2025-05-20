package com.conconcc.likelionweek4.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.conconcc.likelionweek4.Entity.ChatReadEntity;

import java.util.Optional;

public interface ChatReadRepository extends JpaRepository<ChatReadEntity, Long> {
    //사용자가 읽지 않은 메시지 수 확인
    long countByChatChatRoomIdAndUserIdAndIsReadFalse(Long chatRoomId, Long userId);
    //해당 사용자의 읽음 상태 조회
    Optional<ChatReadEntity> findByChatIdAndUserId(Long chatId, Long userId);

}