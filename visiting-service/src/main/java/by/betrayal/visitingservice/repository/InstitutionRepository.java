package by.betrayal.visitingservice.repository;

import by.betrayal.visitingservice.entity.InstitutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends JpaRepository<InstitutionEntity, Short> {
}
