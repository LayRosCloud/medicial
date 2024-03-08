package by.betrayal.personalservice.core.database;

import by.betrayal.personalservice.entity.PersonEntity;
import by.betrayal.personalservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonDao {

    @Autowired
    private PersonRepository repository;

    public void clearDatabase() {
        repository.deleteAll();
    }

    public  PersonEntity save(PersonEntity person) {
        return repository.save(person);
    }
}
