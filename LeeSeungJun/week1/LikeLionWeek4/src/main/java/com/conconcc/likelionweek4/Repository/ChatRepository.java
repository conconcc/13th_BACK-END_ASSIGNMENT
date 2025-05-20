package com.conconcc.likelionweek4.Repository;

import com.conconcc.likelionweek4.Entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatEntity, Long> {
    List<ChatEntity> findByChatRoomId(Long chatRoomId);

}
