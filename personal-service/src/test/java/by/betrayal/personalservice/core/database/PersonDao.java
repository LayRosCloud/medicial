package by.betrayal.personalservice.core.database;

import by.betrayal.personalservice.core.utils.creator.PersonCreationUtils;
import by.betrayal.personalservice.entity.PersonEntity;
import by.betrayal.personalservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonDao {

    @Autowired
    private PersonRepository repository;

    public void clearDataTable() {
        repository.deleteAll();
    }

    public PersonEntity save() {
        return repository.save(PersonCreationUtils.generatePerson());
    }
}
