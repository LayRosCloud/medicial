package by.betrayal.visitingservice.mapper;

import by.betrayal.visitingservice.dto.institution.InstitutionCreateDto;
import by.betrayal.visitingservice.dto.institution.InstitutionFullDto;
import by.betrayal.visitingservice.dto.institution.InstitutionUpdateDto;
import by.betrayal.visitingservice.entity.InstitutionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstitutionMapper {
    InstitutionFullDto toFullDto(InstitutionEntity item);
    List<InstitutionFullDto> toFullDto(List<InstitutionEntity> items);
    @Mapping(target = "id", ignore = true)
    InstitutionEntity toEntity(InstitutionCreateDto dto);
    default void toEntity(InstitutionEntity entity, InstitutionUpdateDto dto) {
        entity.setName(dto.getName());
    }
}
