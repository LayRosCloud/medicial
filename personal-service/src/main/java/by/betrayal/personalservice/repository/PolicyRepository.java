package by.betrayal.personalservice.repository;

import by.betrayal.personalservice.entity.PersonEntity;
import by.betrayal.personalservice.entity.PolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyRepository extends JpaRepository<PolicyEntity, Long> {
    List<PolicyEntity> findAllByPatient(PersonEntity patient);
}
