package com.conconcc.LikeLionWeek2.repository;

import com.conconcc.LikeLionWeek2.entity.HelloEntity;
import com.conconcc.LikeLionWeek2.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HelloEntityRepository extends JpaRepository<HelloEntity, Long> {
    Optional<HelloEntity> findByName(String name);

}
