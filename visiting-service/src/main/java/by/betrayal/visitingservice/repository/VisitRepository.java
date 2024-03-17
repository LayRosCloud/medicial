package by.betrayal.visitingservice.repository;

import by.betrayal.visitingservice.entity.VisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<VisitEntity, Long> {
    List<VisitEntity> findAllByPatientId(Long patientId);
}
