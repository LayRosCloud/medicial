package by.betrayal.visitingservice.service;

import by.betrayal.visitingservice.dto.visit.VisitCreateDto;
import by.betrayal.visitingservice.dto.visit.VisitFullDto;
import by.betrayal.visitingservice.dto.visit.VisitUpdateDto;

import java.util.List;

public interface VisitService {

    List<VisitFullDto> findAllByPatientId(Long patientId);
    VisitFullDto findById(Long id);
    VisitFullDto create(VisitCreateDto dto);
    VisitFullDto update(VisitUpdateDto dto);
    VisitFullDto delete(Long id);
    List<VisitFullDto> deleteAllByPatientId(Long patientId);
}
