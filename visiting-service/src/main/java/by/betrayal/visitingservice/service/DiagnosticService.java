package by.betrayal.visitingservice.service;

import by.betrayal.visitingservice.dto.diagnostic.DiagnosticCreateDto;
import by.betrayal.visitingservice.dto.diagnostic.DiagnosticFullDto;
import by.betrayal.visitingservice.dto.diagnostic.DiagnosticUpdateDto;
import by.betrayal.visitingservice.dto.visit.VisitCreateDto;
import by.betrayal.visitingservice.dto.visit.VisitFullDto;
import by.betrayal.visitingservice.dto.visit.VisitUpdateDto;

import java.util.List;

public interface DiagnosticService {
    List<DiagnosticFullDto> findAllByPatientId(Long patientId);
    DiagnosticFullDto findById(Long id);
    DiagnosticFullDto create(DiagnosticCreateDto dto);
    DiagnosticFullDto update(DiagnosticUpdateDto dto);
    DiagnosticFullDto delete(Long id);
    List<DiagnosticFullDto> deleteAllByPatientId(Long patientId);
}
