package com.domain.likelionweek2.repository;

import com.domain.likelionweek2.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}
