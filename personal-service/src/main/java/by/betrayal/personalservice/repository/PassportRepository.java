package by.betrayal.personalservice.repository;

import by.betrayal.personalservice.entity.PassportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassportRepository extends JpaRepository<PassportEntity, Long> {
    List<PassportEntity> findAllByPatientId(Long patientId);
}
