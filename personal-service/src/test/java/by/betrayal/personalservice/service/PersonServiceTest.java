package by.betrayal.personalservice.service;

import by.betrayal.personalservice.core.utils.creator.PersonCreationUtils;
import by.betrayal.personalservice.core.utils.equals.PersonEqualsUtils;
import by.betrayal.personalservice.entity.PersonEntity;
import by.betrayal.personalservice.exception.NotFoundException;
import by.betrayal.personalservice.mapper.PersonMapper;
import by.betrayal.personalservice.repository.PersonRepository;
import by.betrayal.personalservice.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository repository;

    @Spy
    private PersonMapper mapper = Mappers.getMapper(PersonMapper.class);

    @InjectMocks
    private PersonServiceImpl service;

    @Test
    void findAll_happyPath() {
        var expected = new ArrayList<PersonEntity>();
        expected.add(PersonCreationUtils.generatePersonWithId());
        expected.add(PersonCreationUtils.generatePersonWithId());

        when(repository.findAll()).thenReturn(expected);

        var actual = service.findAll();

        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());

        for (int index = 0; index < expected.size(); index++) {
            var itemDto = actual.get(index);
            var item = expected.get(index);

            PersonEqualsUtils.assertEqualsDto(item, itemDto);
        }
    }


    @Test
    void findByIdTestValid_happyPath() {
        var expected = PersonCreationUtils.generatePersonWithId();
        when(repository.findById(expected.getId())).thenReturn(Optional.of(expected));

        var actual = service.findById(expected.getId());

        PersonEqualsUtils.assertEqualsDto(expected, actual);
    }

    @Test
    void findByIdTestInvalid_happyPath() {
        var id = 3L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.findById(id));
    }

    @Test
    void createTest_happyPath() {
        var dto = PersonCreationUtils.generateCreateDto();

        when(repository.saveAndFlush(Mockito.any(PersonEntity.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        var actual = service.create(dto);

        assertNotNull(actual);
        PersonEqualsUtils.assertEqualsDto(dto, actual);
    }

    @Test
    void updateTest_happyPath() {
        var findDto = PersonCreationUtils.generatePersonWithId();
        var dto = PersonCreationUtils.generateUpdateDto(findDto.getId());
        findDto.setId(dto.getId());
        when(repository.findById(dto.getId())).thenReturn(Optional.of(
                findDto
        ));

        when(repository.saveAndFlush(Mockito.any(PersonEntity.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        var actual = service.update(dto);

        assertNotNull(actual);
        PersonEqualsUtils.assertEqualsDto(dto, actual);
    }

    @Test
    void updateTestInvalidId_happyPath() {
        var dto = PersonCreationUtils.generateUpdateDto(20L);
        when(repository.findById(dto.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.update(dto));
    }

    @Test
    void deleteTestInvalidId_happyPath() {
        var id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.delete(id));
    }

    @Test
    void deleteTestValidId_happyPath() {
        var item = PersonCreationUtils.generatePersonWithId();
        when(repository.findById(item.getId())).thenReturn(Optional.of(item));
        var actual = service.delete(item.getId());

        assertNotNull(actual);
        PersonEqualsUtils.assertEqualsDto(item, actual);
    }
}
