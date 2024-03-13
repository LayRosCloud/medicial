package by.betrayal.personalservice.service.impl;

import by.betrayal.personalservice.dto.passport.PassportCreateDto;
import by.betrayal.personalservice.dto.passport.PassportFullDto;
import by.betrayal.personalservice.dto.passport.PassportUpdateDto;
import by.betrayal.personalservice.entity.PassportEntity;
import by.betrayal.personalservice.entity.PersonEntity;
import by.betrayal.personalservice.mapper.PassportMapper;
import by.betrayal.personalservice.repository.PassportRepository;
import by.betrayal.personalservice.repository.PersonRepository;
import by.betrayal.personalservice.service.PassportService;
import by.betrayal.personalservice.utils.ThrowableUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService {

    private final PassportRepository passportRepository;
    private final PersonRepository personRepository;
    private final PassportMapper mapper;

    @Override
    @Transactional
    public List<PassportFullDto> findAll() {
        var list = passportRepository.findAll();

        return mapper.toFullDto(list);
    }

    @Override
    @Transactional
    public List<PassportFullDto> findAll(Long patientId) {
        var list = passportRepository.findAllByPatientId(patientId);

        return mapper.toFullDto(list);
    }

    @Override
    @Transactional
    public PassportFullDto findById(Long id) {
        var item = findByIdOrThrowNotFoundException(id);

        return mapper.toFullDto(item);
    }

    @Override
    @Transactional
    public PassportFullDto create(PassportCreateDto dto) {
        var patient = findByIdPersonOrThrowNotFoundException(dto.getPatientId());
        var passport = mapper.toEntity(dto);
        passport.setPatient(patient);

        var result = passportRepository.saveAndFlush(passport);

        return mapper.toFullDto(result);
    }

    @Override
    @Transactional
    public PassportFullDto update(PassportUpdateDto dto) {
        var patient = findByIdPersonOrThrowNotFoundException(dto.getPatientId());
        var passport = findByIdOrThrowNotFoundException(dto.getId());

        mapper.toEntity(passport, dto);
        passport.setPatient(patient);

        var result = passportRepository.saveAndFlush(passport);

        return mapper.toFullDto(result);
    }

    @Override
    @Transactional
    public PassportFullDto delete(Long id) {
        var passport = findByIdOrThrowNotFoundException(id);

        passportRepository.delete(passport);

        return mapper.toFullDto(passport);
    }

    private PassportEntity findByIdOrThrowNotFoundException(Long id) {
        return passportRepository.findById(id).orElseThrow(() ->
                ThrowableUtils.throwNotFoundException(id)
        );
    }

    private PersonEntity findByIdPersonOrThrowNotFoundException(Long id) {
        return personRepository.findById(id).orElseThrow(() ->
                ThrowableUtils.throwNotFoundException(id)
        );
    }
}
