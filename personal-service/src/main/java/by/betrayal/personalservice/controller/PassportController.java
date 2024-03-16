package by.betrayal.personalservice.controller;

import by.betrayal.personalservice.dto.passport.PassportCreateDto;
import by.betrayal.personalservice.dto.passport.PassportFullDto;
import by.betrayal.personalservice.dto.passport.PassportUpdateDto;
import by.betrayal.personalservice.service.PassportService;
import by.betrayal.personalservice.utils.LoggerUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PassportController {

    private final PassportService service;

    public static final String ENDPOINT = "v1/passports";
    public static final String ENDPOINT_BY_ID = "v1/passports/{id}";
    public static final String ENDPOINT_BY_PATIENT_ID = "v1/people/{patientId}/passports";
    private static final String CONTROLLER_NAME = "Passport";

    @GetMapping(ENDPOINT)
    public ResponseEntity<List<PassportFullDto>> findAll() {
        LoggerUtils.createLogFindAllStart(CONTROLLER_NAME, "", "");
        var list = service.findAll();
        LoggerUtils.createLogFindAllEnd(CONTROLLER_NAME, "", list.size());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_BY_PATIENT_ID)
    public ResponseEntity<List<PassportFullDto>> findAll(@PathVariable Long patientId) {
        LoggerUtils.createLogFindAllStart(CONTROLLER_NAME, "patientId", "patientId=" + patientId);
        var list = service.findAll(patientId);
        LoggerUtils.createLogFindAllEnd(CONTROLLER_NAME, "patientId", list.size());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_BY_ID)
    public ResponseEntity<PassportFullDto> findById(@PathVariable Long id) {
        LoggerUtils.createLogFindByIdStart(CONTROLLER_NAME, id);
        var item = service.findById(id);
        LoggerUtils.createLogFindByIdEnd(CONTROLLER_NAME, item.toString());
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping(ENDPOINT)
    public ResponseEntity<PassportFullDto> create(@Valid @RequestBody PassportCreateDto dto) {
        LoggerUtils.createLogCreateStart(CONTROLLER_NAME, dto.toString());
        var item = service.create(dto);
        LoggerUtils.createLogCreateEnd(CONTROLLER_NAME, item.toString());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping(ENDPOINT)
    public ResponseEntity<PassportFullDto> update(@Valid @RequestBody PassportUpdateDto dto) {
        LoggerUtils.createLogUpdateStart(CONTROLLER_NAME, dto.toString());
        var item = service.update(dto);
        LoggerUtils.createLogUpdateEnd(CONTROLLER_NAME, item.toString());
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PutMapping(ENDPOINT_BY_ID)
    public ResponseEntity<PassportFullDto> delete(@PathVariable Long id) {
        LoggerUtils.createLogDeleteStart(CONTROLLER_NAME, id);
        var item = service.delete(id);
        LoggerUtils.createLogDeleteEnd(CONTROLLER_NAME, item.toString());
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
