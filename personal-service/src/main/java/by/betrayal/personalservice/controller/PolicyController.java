package by.betrayal.personalservice.controller;

import by.betrayal.personalservice.dto.policy.PolicyCreateDto;
import by.betrayal.personalservice.dto.policy.PolicyFullDto;
import by.betrayal.personalservice.dto.policy.PolicyUpdateDto;
import by.betrayal.personalservice.service.PolicyService;
import by.betrayal.personalservice.utils.LoggerUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService service;

    public static final String ENDPOINT = "v1/policies";
    public static final String ENDPOINT_WITH_ID = "v1/policies/{id}";
    public static final String ENDPOINT_GET_BY_PATIENT_ID = "v1/people/{patientId}/policies";

    private static final String CONTROLLER_NAME = "Policy";

    @GetMapping(ENDPOINT)
    public ResponseEntity<List<PolicyFullDto>> findAll(@RequestParam(required = false) Integer limit,
                                                       @RequestParam(defaultValue = "0") Integer page
    ) {
        if(limit == null) {
            LoggerUtils.createLogFindAllStart(CONTROLLER_NAME, "", "");
            var list = service.findAll();
            LoggerUtils.createLogFindAllEnd(CONTROLLER_NAME, "", list.size());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }

        LoggerUtils.createLogFindAllStart(CONTROLLER_NAME, "limit, page", String.format("limit=%d, page=%d", limit, page));

        var result = service.findAll(limit, page);

        var headers = new HttpHeaders();
        headers.set("x-total-count", String.valueOf(result.totalCount()));
        LoggerUtils.createLogFindAllEnd(CONTROLLER_NAME, "limit, page", result.items().size());
        return new ResponseEntity<>(result.items(), headers, HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_GET_BY_PATIENT_ID)
    public ResponseEntity<List<PolicyFullDto>> findAllByPatientId(@PathVariable Long patientId) {
        LoggerUtils.createLogFindAllStart(CONTROLLER_NAME, "patientId", "patientId=" + patientId);
        var list = service.findAll(patientId);
        LoggerUtils.createLogFindAllEnd(CONTROLLER_NAME, "patientId", list.size());
        return ResponseEntity.ok(list);
    }

    @GetMapping(ENDPOINT_WITH_ID)
    public ResponseEntity<PolicyFullDto> findById(@PathVariable(value = "id") Long id) {
        LoggerUtils.createLogFindByIdStart(CONTROLLER_NAME, id);
        var item = service.findById(id);
        LoggerUtils.createLogFindByIdEnd(CONTROLLER_NAME, item.toString());
        return ResponseEntity.ok(item);
    }

    @PostMapping(ENDPOINT)
    public ResponseEntity<PolicyFullDto> create(@Valid @RequestBody PolicyCreateDto dto) {
        LoggerUtils.createLogCreateStart(CONTROLLER_NAME, dto.toString());
        var item = service.create(dto);
        LoggerUtils.createLogCreateEnd(CONTROLLER_NAME, item.toString());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping(ENDPOINT)
    public ResponseEntity<PolicyFullDto> update(@Valid @RequestBody PolicyUpdateDto dto) {
        LoggerUtils.createLogUpdateStart(CONTROLLER_NAME, dto.toString());
        var item = service.update(dto);
        LoggerUtils.createLogUpdateEnd(CONTROLLER_NAME, dto.toString());
        return ResponseEntity.ok(item);
    }

    @DeleteMapping(ENDPOINT_WITH_ID)
    public ResponseEntity<PolicyFullDto> delete(@PathVariable(value = "id") Long id) {
        LoggerUtils.createLogDeleteStart(CONTROLLER_NAME, id);
        var item = service.delete(id);
        LoggerUtils.createLogDeleteEnd(CONTROLLER_NAME, item.toString());
        return ResponseEntity.ok(item);
    }
}