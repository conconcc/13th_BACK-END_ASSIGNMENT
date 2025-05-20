package com.jhkim0609.likelionweek3.repository;

import com.jhkim0609.likelionweek3.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoEntityRepository extends JpaRepository<TodoEntity, Long> {
    @Query(
        value = "SELECT t.* " +
                "FROM todo t " +
                "JOIN users u ON t.user_id = u.id " +
                "WHERE u.username = :username",
        nativeQuery = true
    )
    List<TodoEntity> findTodosByUsername(@Param("username") String username);

    @Query(
            value = "SELECT t.* " +
                    "FROM todo t " +
                    "JOIN users u On t.user_id = u.id " +
                    "WHERE u.sername = :username AND t.completed = :completed",
            nativeQuery = true
    )
    List<TodoEntity> findTodosByUsernameAndCompletetion(
            @Param("username") String username,
            @Param("Completed") boolean completed
    );
}
