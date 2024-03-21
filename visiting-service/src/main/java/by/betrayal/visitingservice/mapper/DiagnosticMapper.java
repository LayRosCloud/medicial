package by.betrayal.visitingservice.mapper;

import by.betrayal.visitingservice.dto.diagnostic.DiagnosticCreateDto;
import by.betrayal.visitingservice.dto.diagnostic.DiagnosticFullDto;
import by.betrayal.visitingservice.dto.diagnostic.DiagnosticUpdateDto;
import by.betrayal.visitingservice.entity.DiagnosticEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiagnosticMapper {
    DiagnosticFullDto toFullDto(DiagnosticEntity item);
    List<DiagnosticFullDto> toFullDto(List<DiagnosticEntity> items);
    @Mapping(target = "id", ignore = true)
    DiagnosticEntity toEntity(DiagnosticCreateDto dto);
    default void toEntity(DiagnosticEntity entity, DiagnosticUpdateDto dto) {
        entity.setDate(dto.getDate());
        entity.setValue(dto.getValue());
        entity.setIsHealed(dto.getIsHealed());
        entity.setPatientId(dto.getPatientId());
    }
}
