package com.jhkim0609.todolist.repository;

import com.jhkim0609.todolist.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoEntityRepository extends JpaRepository<TodoEntity, Long> {

}
