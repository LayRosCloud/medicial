package by.betrayal.personalservice.repository;

import by.betrayal.personalservice.entity.MedicalCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalCardRepository extends JpaRepository<MedicalCardEntity, Long> {
    List<MedicalCardEntity> findAllByPatientId(Long patientId);
}
