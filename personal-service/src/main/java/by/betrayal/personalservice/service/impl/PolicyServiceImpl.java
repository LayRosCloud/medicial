package by.betrayal.personalservice.service.impl;

import by.betrayal.personalservice.dto.person.PersonCreateDto;
import by.betrayal.personalservice.dto.policy.PolicyCreateDto;
import by.betrayal.personalservice.dto.policy.PolicyFullDto;
import by.betrayal.personalservice.dto.policy.PolicyUpdateDto;
import by.betrayal.personalservice.entity.PersonEntity;
import by.betrayal.personalservice.entity.PolicyEntity;
import by.betrayal.personalservice.mapper.PolicyMapper;
import by.betrayal.personalservice.repository.PersonRepository;
import by.betrayal.personalservice.repository.PolicyRepository;
import by.betrayal.personalservice.service.PolicyService;
import by.betrayal.personalservice.utils.PageUtils;
import by.betrayal.personalservice.utils.ResponseLimitPage;
import by.betrayal.personalservice.utils.ThrowableUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService {

    private final PolicyMapper mapper;
    private final PolicyRepository policyRepository;
    private final PersonRepository personRepository;

    @Override
    @Transactional
    public ResponseLimitPage<PolicyFullDto> findAll(Integer limit, Integer page) {

        var pageable = new PageUtils(limit, page);

        var pageItem = policyRepository.findAll(pageable);
        var list = pageItem.getContent();

        var mappingItems = mapper.toFullDto(list);
        return new ResponseLimitPage<PolicyFullDto>(mappingItems, pageItem.getTotalElements());
    }

    @Override
    public List<PolicyFullDto> findAll(Long patientId) {
        var patient = findByIdPersonOrThrowNotFoundException(patientId);
        var list = policyRepository.findAllByPatient(patient);
        return mapper.toFullDto(list);
    }

    @Override
    public List<PolicyFullDto> findAll() {
        var list = policyRepository.findAll();
        return mapper.toFullDto(list);
    }

    @Override
    @Transactional
    public PolicyFullDto findById(Long id) {
        var item = findByIdOrThrowNotFoundException(id);
        return mapper.toFullDto(item);
    }

    @Override
    @Transactional
    public PolicyFullDto create(PolicyCreateDto dto) {
        var patient = findByIdPersonOrThrowNotFoundException(dto.getPatientId());

        var item = mapper.toEntity(dto);
        item.setPatient(patient);

        var result = policyRepository.saveAndFlush(item);

        return mapper.toFullDto(result);
    }

    @Override
    @Transactional
    public PolicyFullDto update(PolicyUpdateDto dto) {
        var policy = findByIdOrThrowNotFoundException(dto.getId());
        var patient = findByIdPersonOrThrowNotFoundException(dto.getPatientId());

        mapper.toEntity(policy, dto);
        policy.setPatient(patient);

        var result = policyRepository.saveAndFlush(policy);

        return mapper.toFullDto(result);
    }

    @Override
    @Transactional
    public PolicyFullDto delete(Long id) {
        var policy = findByIdOrThrowNotFoundException(id);
        policyRepository.delete(policy);
        return mapper.toFullDto(policy);
    }

    private PolicyEntity findByIdOrThrowNotFoundException(Long id) {
        return policyRepository.findById(id).orElseThrow(() ->
                ThrowableUtils.throwNotFoundException(id)
        );
    }

    private PersonEntity findByIdPersonOrThrowNotFoundException(Long id) {
        return personRepository.findById(id).orElseThrow(() ->
                ThrowableUtils.throwNotFoundException(id)
        );
    }
}
