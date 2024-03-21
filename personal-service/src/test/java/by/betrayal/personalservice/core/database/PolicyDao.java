package by.betrayal.personalservice.core.database;

import by.betrayal.personalservice.core.utils.creator.PolicyCreationUtils;
import by.betrayal.personalservice.entity.PolicyEntity;
import by.betrayal.personalservice.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PolicyDao {

    private final PolicyRepository repository;

    @Autowired
    public PolicyDao(PolicyRepository repository) {
        this.repository = repository;
    }

    public PolicyEntity save() {
        return repository.save(PolicyCreationUtils.generatePolicy());
    }
}
