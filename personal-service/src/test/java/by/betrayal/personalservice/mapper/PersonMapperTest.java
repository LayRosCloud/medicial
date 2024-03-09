package by.betrayal.personalservice.mapper;

import by.betrayal.personalservice.core.utils.creator.PersonCreationUtils;
import by.betrayal.personalservice.core.utils.equals.PersonEqualsUtils;
import by.betrayal.personalservice.entity.PersonEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PersonMapperTest {
    private final PersonMapper mapper = Mappers.getMapper(PersonMapper.class);

    @Test
    void convertToListFullDto_happyPath() {
        var items = new ArrayList<PersonEntity>();
        items.add(PersonCreationUtils.generatePersonWithId());
        items.add(PersonCreationUtils.generatePersonWithId());

        var dtoItems = mapper.toFullDto(items);

        assertNotNull(dtoItems);
        assertEquals(items.size(), dtoItems.size());
        for (int index = 0; index < items.size(); index++) {
            var dtoItem = dtoItems.get(index);
            var item = items.get(index);
            PersonEqualsUtils.assertEqualsDto(item, dtoItem);
        }
    }

    @Test
    void convertToOneFullDto_happyPath() {
        var item = PersonCreationUtils.generatePersonWithId();

        var dto = mapper.toFullDto(item);

        assertNotNull(dto);
        PersonEqualsUtils.assertEqualsDto(item, dto);
    }

    @Test
    void convertToCreateDto_happyPath() {
        var dto = PersonCreationUtils.generateCreateDto();

        var item = mapper.toEntity(dto);

        assertNotNull(dto);
        PersonEqualsUtils.assertEqualsDto(item, dto);
    }

    @Test
    void convertToUpdateDto_happyPath() {
        var user = PersonCreationUtils.generatePersonWithId();
        var dto = PersonCreationUtils.generateUpdateDto(user.getId());

        mapper.toEntity(user, dto);

        assertNotNull(dto);
        PersonEqualsUtils.assertEqualsDto(user, dto);
    }
}
