package com.conconcc.likelionweek4.Repository;

import com.conconcc.likelionweek4.Entity.UserChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserChatRoomRepository extends JpaRepository<UserChatRoomEntity, Long> {
    Optional<UserChatRoomEntity> findByUserIdAndChatRoomId(Long userId, Long chatRoomId);
    }
