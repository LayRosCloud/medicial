package by.betrayal.visitingservice.repository;

import by.betrayal.visitingservice.entity.DiagnosticEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosticRepository extends JpaRepository<DiagnosticEntity, Long> {
    List<DiagnosticEntity> findAllByPatientId(Long patientId);
}
