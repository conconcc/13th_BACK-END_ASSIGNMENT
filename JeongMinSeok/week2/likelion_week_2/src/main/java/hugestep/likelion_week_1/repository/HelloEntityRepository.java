package hugestep.likelion_week_1.repository;

import hugestep.likelion_week_1.entity.HelloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HelloEntityRepository extends JpaRepository<HelloEntity, Long> {
    @Override
    Optional<HelloEntity> findById(Long aLong);
    HelloEntity findHelloEntityById(Long id);
}
