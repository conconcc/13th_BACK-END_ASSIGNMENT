package com.acycycy.LikeLionWeek2.repository;

import com.acycycy.LikeLionWeek2.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}