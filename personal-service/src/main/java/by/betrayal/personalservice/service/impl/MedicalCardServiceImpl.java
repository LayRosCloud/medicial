package by.betrayal.personalservice.service.impl;

import by.betrayal.personalservice.dto.medicalcard.MedicalCardCreateDto;
import by.betrayal.personalservice.dto.medicalcard.MedicalCardFullDto;
import by.betrayal.personalservice.dto.medicalcard.MedicalCardUpdateDto;
import by.betrayal.personalservice.entity.MedicalCardEntity;
import by.betrayal.personalservice.entity.PersonEntity;
import by.betrayal.personalservice.mapper.MedicalCardMapper;
import by.betrayal.personalservice.repository.MedicalCardRepository;
import by.betrayal.personalservice.repository.PersonRepository;
import by.betrayal.personalservice.service.MedicalCardService;
import by.betrayal.personalservice.utils.ThrowableUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalCardServiceImpl implements MedicalCardService {

    private final MedicalCardRepository cardRepository;
    private final PersonRepository personRepository;
    private final MedicalCardMapper mapper;

    @Override
    @Transactional
    public List<MedicalCardFullDto> findAll() {
        var list = cardRepository.findAll();
        return mapper.toFullDto(list);
    }

    @Override
    public List<MedicalCardFullDto> findAll(Long patientId) {
        var list = cardRepository.findAllByPatientId(patientId);

        return mapper.toFullDto(list);
    }

    @Override
    @Transactional
    public MedicalCardFullDto findById(Long id) {
        var item = findByIdOrThrowNotFoundException(id);

        return mapper.toFullDto(item);
    }

    @Override
    @Transactional
    public MedicalCardFullDto create(MedicalCardCreateDto dto) {
        var patient = findByIdPersonOrThrowNotFoundException(dto.getPatientId());

        var item = mapper.toEntity(dto);
        item.setPatient(patient);

        var result = cardRepository.saveAndFlush(item);

        return mapper.toFullDto(result);
    }

    @Override
    @Transactional
    public MedicalCardFullDto update(MedicalCardUpdateDto dto) {
        var item = findByIdOrThrowNotFoundException(dto.getId());
        var patient = findByIdPersonOrThrowNotFoundException(dto.getPatientId());

        mapper.toEntity(item, dto);
        item.setPatient(patient);

        var result = cardRepository.saveAndFlush(item);

        return mapper.toFullDto(result);
    }

    @Override
    @Transactional
    public MedicalCardFullDto delete(Long id) {
        var item = findByIdOrThrowNotFoundException(id);

        cardRepository.delete(item);

        return mapper.toFullDto(item);
    }

    private MedicalCardEntity findByIdOrThrowNotFoundException(Long id) {
        return cardRepository.findById(id).orElseThrow(() ->
                ThrowableUtils.throwNotFoundException(id)
        );
    }

    private PersonEntity findByIdPersonOrThrowNotFoundException(Long id) {
        return personRepository.findById(id).orElseThrow(() ->
                ThrowableUtils.throwNotFoundException(id)
        );
    }
}
