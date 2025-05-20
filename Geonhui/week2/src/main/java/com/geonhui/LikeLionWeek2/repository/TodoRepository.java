package com.geonhui.LikeLionWeek2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geonhui.LikeLionWeek2.entity.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

}
