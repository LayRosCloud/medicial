package by.betrayal.personalservice.core.utils.creator;

import by.betrayal.personalservice.core.utils.FakerUtils;
import by.betrayal.personalservice.dto.policy.PolicyCreateDto;
import by.betrayal.personalservice.dto.policy.PolicyUpdateDto;
import by.betrayal.personalservice.entity.PolicyEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PolicyCreationUtils {

    public static List<PolicyEntity> generatePolicies(int count) {
        final var list = new ArrayList<PolicyEntity>(count);

        for (int i = 0; i < count; i++) {
            list.add(generatePolicy());
        }

        return list;
    }

    public static PolicyEntity generatePolicy() {
        var faker = FakerUtils.FAKER;

        var item = PolicyEntity.builder()
                .dateEnd(faker.date().future(10, TimeUnit.DAYS))
                .patient(PersonCreationUtils.generatePersonWithId())
                .number(UUID.randomUUID().toString())
                .build();

        return item;
    }

    public static PolicyEntity generatePolicyWithId() {
        var faker = FakerUtils.FAKER;

        var item = generatePolicy();
        item.setId(faker.number().randomNumber());

        return item;
    }

    public static PolicyCreateDto generateCreatePolicy() {
        var faker = FakerUtils.FAKER;

        var item = new PolicyCreateDto();
        item.setNumber(UUID.randomUUID().toString());
        item.setPatientId(faker.number().numberBetween(1, Long.MAX_VALUE));
        item.setDateEnd(faker.date().future(10, TimeUnit.DAYS));

        return item;
    }

    public static PolicyUpdateDto generateUpdatePolicy(Long id) {
        var faker = FakerUtils.FAKER;

        var item = new PolicyUpdateDto();
        item.setId(id);
        item.setNumber(UUID.randomUUID().toString());
        item.setPatientId(faker.number().numberBetween(1, Long.MAX_VALUE));
        item.setDateEnd(faker.date().future(10, TimeUnit.DAYS));

        return item;
    }

}
