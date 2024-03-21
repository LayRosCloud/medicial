package by.betrayal.visitingservice.service.impl;

import by.betrayal.visitingservice.client.controller.PersonClient;
import by.betrayal.visitingservice.client.dto.PersonFullDto;
import by.betrayal.visitingservice.dto.visit.VisitCreateDto;
import by.betrayal.visitingservice.dto.visit.VisitFullDto;
import by.betrayal.visitingservice.dto.visit.VisitUpdateDto;
import by.betrayal.visitingservice.entity.VisitEntity;
import by.betrayal.visitingservice.exception.NotFoundException;
import by.betrayal.visitingservice.mapper.VisitMapper;
import by.betrayal.visitingservice.repository.InstitutionRepository;
import by.betrayal.visitingservice.repository.VisitRepository;
import by.betrayal.visitingservice.service.VisitService;
import by.betrayal.visitingservice.utils.ThrowableUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final PersonClient client;
    private final VisitRepository visitRepository;
    private final InstitutionRepository institutionRepository;
    private final VisitMapper mapper;

    @Override
    @Transactional
    public List<VisitFullDto> findAllByPatientId(Long patientId) {
        var list = visitRepository.findAllByPatientId(patientId);
        return mapper.toFullDto(list);
    }

    @Override
    @Transactional
    public VisitFullDto findById(Long id) {
        var item = findByIdOrThrowNotFoundException(id);

        return mapper.toFullDto(item);
    }

    @Override
    @Transactional
    public VisitFullDto create(VisitCreateDto dto) {
        var institution = institutionRepository.findById(dto.getInstitutionId()).orElseThrow(NotFoundException::new);
        findPersonOrThrowNotFoundException(dto.getPatientId());
        var item = mapper.toEntity(dto);
        item.setInstitution(institution);

        var result = visitRepository.saveAndFlush(item);

        return mapper.toFullDto(result);
    }

    @Override
    @Transactional
    public VisitFullDto update(VisitUpdateDto dto) {
        var institution = institutionRepository.findById(dto.getInstitutionId()).orElseThrow(NotFoundException::new);
        findPersonOrThrowNotFoundException(dto.getPatientId());
        var item = findByIdOrThrowNotFoundException(dto.getId());

        mapper.toEntity(item, dto);
        item.setInstitution(institution);

        var result = visitRepository.saveAndFlush(item);

        return mapper.toFullDto(result);
    }

    @Override
    @Transactional
    public VisitFullDto delete(Long id) {
        var item = findByIdOrThrowNotFoundException(id);

        visitRepository.delete(item);

        return mapper.toFullDto(item);
    }

    @Override
    @Transactional
    public List<VisitFullDto> deleteAllByPatientId(Long patientId) {
        var list = visitRepository.findAllByPatientId(patientId);
        visitRepository.deleteAll(list);
        return mapper.toFullDto(list);
    }

    private VisitEntity findByIdOrThrowNotFoundException(Long id) {
        return visitRepository.findById(id).orElseThrow(() ->
                ThrowableUtils.throwNotFoundException(id)
        );
    }

    private PersonFullDto findPersonOrThrowNotFoundException(Long patientId) {
        try{
            return client.findById(patientId);
        } catch (Exception ex) {
            throw ThrowableUtils.throwNotFoundException(patientId);
        }
    }
}
