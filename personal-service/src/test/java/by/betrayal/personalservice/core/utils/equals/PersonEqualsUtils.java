package by.betrayal.personalservice.core.utils.equals;

import by.betrayal.personalservice.dto.person.PersonCreateDto;
import by.betrayal.personalservice.dto.person.PersonFullDto;
import by.betrayal.personalservice.dto.person.PersonUpdateDto;
import by.betrayal.personalservice.entity.PersonEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonEqualsUtils {

    public static void assertEqualsEntity(PersonEntity firstItem, PersonEntity secondItem) {
        assertEquals(firstItem.getId(), secondItem.getId());
        assertEquals(firstItem.getFirstName(), secondItem.getFirstName());
        assertEquals(firstItem.getLastName(), secondItem.getLastName());
        assertEquals(firstItem.getPatronymic(), secondItem.getPatronymic());
        assertEquals(firstItem.getSex(), secondItem.getSex());
        assertEquals(firstItem.getImage(), secondItem.getImage());
        assertEquals(firstItem.getBirthday(), secondItem.getBirthday());
    }

    public static void assertEqualsDto(PersonEntity firstItem, PersonFullDto secondItem) {
        assertEquals(firstItem.getId(), secondItem.getId());
        assertEquals(firstItem.getFirstName(), secondItem.getFirstName());
        assertEquals(firstItem.getLastName(), secondItem.getLastName());
        assertEquals(firstItem.getPatronymic(), secondItem.getPatronymic());
        assertEquals(firstItem.getSex(), secondItem.getSex());
        assertEquals(firstItem.getImage(), secondItem.getImage());
        assertEquals(firstItem.getBirthday(), secondItem.getBirthday());
    }

    public static void assertEqualsDto(PersonEntity firstItem, PersonCreateDto secondItem) {
        assertEquals(firstItem.getFirstName(), secondItem.getFirstName());
        assertEquals(firstItem.getLastName(), secondItem.getLastName());
        assertEquals(firstItem.getPatronymic(), secondItem.getPatronymic());
        assertEquals(firstItem.getSex(), secondItem.getSex());

        assertEquals(firstItem.getBirthday(), secondItem.getBirthday());
    }

    public static void assertEqualsDto(PersonEntity firstItem, PersonUpdateDto secondItem) {
        assertEquals(firstItem.getId(), secondItem.getId());
        assertEquals(firstItem.getFirstName(), secondItem.getFirstName());
        assertEquals(firstItem.getLastName(), secondItem.getLastName());
        assertEquals(firstItem.getPatronymic(), secondItem.getPatronymic());
        assertEquals(firstItem.getSex(), secondItem.getSex());
        assertEquals(firstItem.getBirthday(), secondItem.getBirthday());
    }
}
