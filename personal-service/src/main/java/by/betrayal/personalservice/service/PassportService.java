package by.betrayal.personalservice.service;

import by.betrayal.personalservice.dto.passport.PassportCreateDto;
import by.betrayal.personalservice.dto.passport.PassportFullDto;
import by.betrayal.personalservice.dto.passport.PassportUpdateDto;

import java.util.List;

public interface PassportService {

    List<PassportFullDto> findAll();
    List<PassportFullDto> findAll(Long patientId);
    PassportFullDto findById(Long id);
    PassportFullDto create(PassportCreateDto dto);
    PassportFullDto update(PassportUpdateDto dto);
    PassportFullDto delete(Long id);

}
