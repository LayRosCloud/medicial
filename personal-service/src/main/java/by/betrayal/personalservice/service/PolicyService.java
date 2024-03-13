package by.betrayal.personalservice.service;

import by.betrayal.personalservice.dto.policy.PolicyCreateDto;
import by.betrayal.personalservice.dto.policy.PolicyFullDto;
import by.betrayal.personalservice.dto.policy.PolicyUpdateDto;
import by.betrayal.personalservice.utils.ResponseLimitPage;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PolicyService {

    ResponseLimitPage<PolicyFullDto> findAll(Integer limit, Integer page);
    List<PolicyFullDto> findAll(Long patientId);
    List<PolicyFullDto> findAll();
    PolicyFullDto findById(Long id);
    PolicyFullDto create(PolicyCreateDto dto);
    PolicyFullDto update(PolicyUpdateDto dto);
    PolicyFullDto delete(Long id);
}
