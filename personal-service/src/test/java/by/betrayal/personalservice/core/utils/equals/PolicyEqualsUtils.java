package by.betrayal.personalservice.core.utils.equals;

import by.betrayal.personalservice.dto.policy.PolicyCreateDto;
import by.betrayal.personalservice.dto.policy.PolicyFullDto;
import by.betrayal.personalservice.dto.policy.PolicyUpdateDto;
import by.betrayal.personalservice.entity.PolicyEntity;
import static org.junit.jupiter.api.Assertions.*;


public class PolicyEqualsUtils {

    public static void assertEqualsDto(PolicyEntity item, PolicyFullDto dto) {
        assertEquals(item.getId(), dto.getId());
        assertEquals(item.getNumber(), dto.getNumber());
        assertEquals(item.getDateEnd(), dto.getDateEnd());
        assertEquals(item.getPatient().getId(), dto.getPatient().getId());
    }

    public static void assertEqualsDto(PolicyEntity item, PolicyCreateDto dto) {
        assertEquals(item.getNumber(), dto.getNumber());
        assertEquals(item.getDateEnd(), dto.getDateEnd());
    }

    public static void assertEqualsDto(PolicyEntity item, PolicyUpdateDto dto) {
        assertEquals(item.getId(), dto.getId());
        assertEquals(item.getNumber(), dto.getNumber());
        assertEquals(item.getDateEnd(), dto.getDateEnd());
    }

    public static void assertEqualsEntities(PolicyEntity item, PolicyEntity item1) {
        assertEquals(item.getId(), item1.getId());
        assertEquals(item.getNumber(), item1.getNumber());
        assertEquals(item.getDateEnd(), item1.getDateEnd());
        assertEquals(item.getPatient(), item1.getPatient());
    }
}
