package by.betrayal.visitingservice.service.impl;

import by.betrayal.visitingservice.client.controller.PersonClient;
import by.betrayal.visitingservice.dto.diagnostic.DiagnosticCreateDto;
import by.betrayal.visitingservice.dto.diagnostic.DiagnosticFullDto;
import by.betrayal.visitingservice.dto.diagnostic.DiagnosticUpdateDto;
import by.betrayal.visitingservice.entity.DiagnosticEntity;
import by.betrayal.visitingservice.exception.NotFoundException;
import by.betrayal.visitingservice.mapper.DiagnosticMapper;
import by.betrayal.visitingservice.repository.DiagnosticRepository;
import by.betrayal.visitingservice.service.DiagnosticService;
import by.betrayal.visitingservice.utils.ThrowableUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnosticServiceImpl implements DiagnosticService {

    private final PersonClient client;
    private final DiagnosticRepository repository;
    private final DiagnosticMapper mapper;

    @Override
    @Transactional
    public List<DiagnosticFullDto> findAllByPatientId(Long patientId) {
        var list = repository.findAllByPatientId(patientId);
        return mapper.toFullDto(list);
    }

    @Override
    @Transactional
    public DiagnosticFullDto findById(Long id) {
        var list = findByIdOrThrowNotFoundException(id);
        return mapper.toFullDto(list);
    }

    @Override
    @Transactional
    public DiagnosticFullDto create(DiagnosticCreateDto dto) {
        client.findById(dto.getPatientId());
        var item = mapper.toEntity(dto);
        var result = repository.saveAndFlush(item);

        return mapper.toFullDto(result);
    }

    @Override
    @Transactional
    public DiagnosticFullDto update(DiagnosticUpdateDto dto) {
        client.findById(dto.getPatientId());

        var item = findByIdOrThrowNotFoundException(dto.getId());
        mapper.toEntity(item, dto);

        var result = repository.saveAndFlush(item);

        return mapper.toFullDto(result);
    }

    @Override
    @Transactional
    public DiagnosticFullDto delete(Long id) {
        var item = findByIdOrThrowNotFoundException(id);

        repository.delete(item);

        return mapper.toFullDto(item);
    }

    @Override
    @Transactional
    public List<DiagnosticFullDto> deleteAllByPatientId(Long patientId) {
        var list = repository.findAllByPatientId(patientId);
        repository.deleteAll(list);
        return mapper.toFullDto(list);
    }

    private DiagnosticEntity findByIdOrThrowNotFoundException(Long id) {
        return repository.findById(id).orElseThrow(() ->
                ThrowableUtils.throwNotFoundException(id)
        );
    }
}
