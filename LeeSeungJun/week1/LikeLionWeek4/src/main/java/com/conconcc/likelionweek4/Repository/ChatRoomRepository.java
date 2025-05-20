package com.conconcc.likelionweek4.Repository;

import com.conconcc.likelionweek4.Entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long> {
    Optional<ChatRoomEntity> findByRoomName(String roomName);

    List<ChatRoomEntity> findByUserChatRoomsUserUsername(String username);
}
