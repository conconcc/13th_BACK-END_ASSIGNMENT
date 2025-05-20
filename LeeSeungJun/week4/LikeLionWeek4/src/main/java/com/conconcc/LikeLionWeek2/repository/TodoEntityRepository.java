package com.conconcc.LikeLionWeek2.repository;


import com.conconcc.LikeLionWeek2.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoEntityRepository extends JpaRepository<TodoEntity, Long> {
    Optional<TodoEntity> findByTitle(String title);
}
