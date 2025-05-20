package com.LimChanHyeok.LikeLionWeek2.repository;

import com.LimChanHyeok.LikeLionWeek2.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity,Long> {

    @Query(
            value = "SELECT t.* " +
                    "FROM todo t" +
                    "JOIN users u ON t.user_id = u.id" +
                    "WHERE u.username = :username",
            nativeQuery = true
    )
    List<TodoEntity> findTodoByUsername(@Param("username") String username);

    @Query(
            value = "SELECT t.* " +
                    "FROM todo t " +
                    "JOIN users u ON t.user_id = u.id " +
                    "WHERE u.username = :username AND t.completed = :completed",
            nativeQuery = true
    )
    List<TodoEntity> findTodoByUsernameAndCompleted(
            @Param("username") String username,
            @Param("completed") boolean completed);


}
