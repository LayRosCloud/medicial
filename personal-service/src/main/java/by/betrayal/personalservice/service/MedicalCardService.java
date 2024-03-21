package by.betrayal.personalservice.service;

import by.betrayal.personalservice.dto.medicalcard.MedicalCardCreateDto;
import by.betrayal.personalservice.dto.medicalcard.MedicalCardFullDto;
import by.betrayal.personalservice.dto.medicalcard.MedicalCardUpdateDto;

import java.util.List;

public interface MedicalCardService {

    List<MedicalCardFullDto> findAll();
    List<MedicalCardFullDto> findAll(Long patientId);
    MedicalCardFullDto findById(Long id);
    MedicalCardFullDto create(MedicalCardCreateDto dto);
    MedicalCardFullDto update(MedicalCardUpdateDto dto);
    MedicalCardFullDto delete(Long id);
}
