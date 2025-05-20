package com.yusungyeon.like_lion_.repository;

import com.yusungyeon.like_lion_.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Integer>{
}
