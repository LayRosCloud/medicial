package by.betrayal.personalservice.service;

import by.betrayal.personalservice.core.utils.creator.PersonCreationUtils;
import by.betrayal.personalservice.core.utils.creator.PolicyCreationUtils;
import by.betrayal.personalservice.core.utils.equals.PolicyEqualsUtils;
import by.betrayal.personalservice.entity.PersonEntity;
import by.betrayal.personalservice.entity.PolicyEntity;
import by.betrayal.personalservice.mapper.PersonMapper;
import by.betrayal.personalservice.mapper.PersonMapperImpl;
import by.betrayal.personalservice.mapper.PolicyMapper;
import by.betrayal.personalservice.mapper.PolicyMapperImpl;
import by.betrayal.personalservice.repository.PersonRepository;
import by.betrayal.personalservice.repository.PolicyRepository;
import by.betrayal.personalservice.service.impl.PersonServiceImpl;
import by.betrayal.personalservice.service.impl.PolicyServiceImpl;
import by.betrayal.personalservice.utils.PageUtils;
import by.betrayal.personalservice.utils.ResponseLimitPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PolicyMapperImpl.class, PersonMapperImpl.class})
public class PolicyServiceTest {

    private final PolicyRepository policyRepository;
    private final PersonRepository personRepository;

    private final PolicyService service;

    @Autowired
    public PolicyServiceTest(PolicyMapper mapper) {
        policyRepository = Mockito.mock(PolicyRepository.class);
        personRepository = Mockito.mock(PersonRepository.class);
        service = new PolicyServiceImpl(mapper, policyRepository, personRepository);
    }

    @Test
    void findAllTest_happyPath() {
        //given
        var list = new ArrayList<PolicyEntity>();
        list.add(PolicyCreationUtils.generatePolicyWithId());
        list.add(PolicyCreationUtils.generatePolicyWithId());

        when(policyRepository.findAll()).thenReturn(list);

        //when
        var actual = service.findAll();

        //then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(list.size(), actual.size());
    }

    @Test
    void findAllByLimitAndPageTest_happyPath() {
        //given
        final var totalCount = 10;
        var list = PolicyCreationUtils.generatePolicies(totalCount);

        final var limit = 5;
        final var page = 0;
        final var offset = limit * page;
        var pageUtils = new PageUtils(limit, page);
        var pageItem = new PageImpl<PolicyEntity>(list.subList(offset, limit + offset), pageUtils, totalCount);
        when(policyRepository.findAll(pageUtils)).thenReturn(pageItem);
        //when
        var actual = service.findAll(limit, page);

        //then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual.totalCount(), totalCount);
        Assertions.assertEquals(limit, actual.items().size());
    }

    @Test
    void findAllByPatientIdTest_happyPath() {
        //given
        final var totalCount = 10;
        var list = PolicyCreationUtils.generatePolicies(totalCount);

        var patientId = 5L;
        var patient = PersonCreationUtils.generatePerson();
        patient.setId(patientId);
        when(personRepository.findById(patientId)).thenReturn(Optional.of(patient));
        when(policyRepository.findAllByPatient(patient)).thenReturn(list);

        //when
        var actual = service.findAll(patientId);

        //then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(list.size(), actual.size());
    }

    @Test
    void create_happyPath() {
        //given
        final var totalCount = 10;
        var list = PolicyCreationUtils.generatePolicies(totalCount);

        var patientId = 5L;
        var patient = PersonCreationUtils.generatePerson();
        patient.setId(patientId);
        when(personRepository.findById(patientId)).thenReturn(Optional.of(patient));
        when(policyRepository.findAllByPatient(patient)).thenReturn(list);

        //when
        var actual = service.findAll(patientId);

        //then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(list.size(), actual.size());
    }
}
