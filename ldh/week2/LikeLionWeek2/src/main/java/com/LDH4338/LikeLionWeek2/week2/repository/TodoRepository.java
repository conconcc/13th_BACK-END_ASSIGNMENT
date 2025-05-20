package com.LDH4338.LikeLionWeek2.week2.repository;

import com.LDH4338.LikeLionWeek2.week2.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
