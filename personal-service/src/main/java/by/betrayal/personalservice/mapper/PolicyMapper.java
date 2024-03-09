package by.betrayal.personalservice.mapper;

import by.betrayal.personalservice.dto.policy.PolicyCreateDto;
import by.betrayal.personalservice.dto.policy.PolicyFullDto;
import by.betrayal.personalservice.dto.policy.PolicyUpdateDto;
import by.betrayal.personalservice.entity.PolicyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface PolicyMapper {
    PolicyFullDto toFullDto(PolicyEntity item);
    List<PolicyFullDto> toFullDto(List<PolicyEntity> items);

    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "id", ignore = true)
    PolicyEntity toEntity(PolicyCreateDto item);

    default void toEntity(PolicyEntity item, PolicyUpdateDto dto) {
        item.setNumber(dto.getNumber());
        item.setDateEnd(dto.getDateEnd());
    }
}
