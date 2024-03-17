package by.betrayal.visitingservice.service;

import by.betrayal.visitingservice.dto.institution.InstitutionCreateDto;
import by.betrayal.visitingservice.dto.institution.InstitutionFullDto;
import by.betrayal.visitingservice.dto.institution.InstitutionUpdateDto;

import java.util.List;

public interface InstitutionService {

    List<InstitutionFullDto> findAll();
    InstitutionFullDto findById(Short id);
    InstitutionFullDto create(InstitutionCreateDto dto);
    InstitutionFullDto update(InstitutionUpdateDto dto);
    InstitutionFullDto delete(Short id);
}
