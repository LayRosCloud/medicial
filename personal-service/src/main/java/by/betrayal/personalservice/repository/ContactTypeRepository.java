package by.betrayal.personalservice.repository;

import by.betrayal.personalservice.entity.ContactTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactTypeRepository extends JpaRepository<ContactTypeEntity, Short> {
}
