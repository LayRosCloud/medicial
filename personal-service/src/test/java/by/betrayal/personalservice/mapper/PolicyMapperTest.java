package by.betrayal.personalservice.mapper;

import by.betrayal.personalservice.core.utils.creator.PolicyCreationUtils;
import by.betrayal.personalservice.core.utils.equals.PolicyEqualsUtils;
import by.betrayal.personalservice.entity.PolicyEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PolicyMapperImpl.class, PersonMapperImpl.class})
public class PolicyMapperTest {
    private final PolicyMapper mapper;

    @Autowired
    public PolicyMapperTest(PolicyMapper mapper) {
        this.mapper = mapper;
    }

    @Test
    void convertToListFullDto_happyPath() {
        var list = new ArrayList<PolicyEntity>();
        list.add(PolicyCreationUtils.generatePolicyWithId());
        list.add(PolicyCreationUtils.generatePolicyWithId());

        var dtoList = mapper.toFullDto(list);

        assertNotNull(dtoList);
        assertEquals(list.size(), dtoList.size());

        for (int index = 0; index < dtoList.size(); index++) {
           var dtoItem = dtoList.get(index);
           var item = list.get(index);

            PolicyEqualsUtils.assertEqualsDto(item, dtoItem);
        }
    }

    @Test
    void convertToFullDto_happyPath() {
        var item = PolicyCreationUtils.generatePolicyWithId();

        var dto = mapper.toFullDto(item);

        assertNotNull(dto);
        PolicyEqualsUtils.assertEqualsDto(item, dto);
    }

    @Test
    void convertToEntityForCreateDto_happyPath() {
        var dto = PolicyCreationUtils.generateCreatePolicy();

        var item = mapper.toEntity(dto);

        assertNotNull(item);
        PolicyEqualsUtils.assertEqualsDto(item, dto);
    }

    @Test
    void convertToEntityForUpdateDto_happyPath() {
        var item = PolicyCreationUtils.generatePolicyWithId();
        var dto = PolicyCreationUtils.generateUpdatePolicy(item.getId());

        mapper.toEntity(item, dto);

        assertNotNull(item);
        PolicyEqualsUtils.assertEqualsDto(item, dto);
    }
}
