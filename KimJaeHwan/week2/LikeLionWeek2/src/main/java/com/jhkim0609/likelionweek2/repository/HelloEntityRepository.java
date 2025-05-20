package com.jhkim0609.likelionweek2.repository;

import com.jhkim0609.likelionweek2.entity.HelloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HelloEntityRepository extends JpaRepository<HelloEntity, Long> {
    Optional<HelloEntity> findByName(String name);
}
