package by.betrayal.personalservice.controller;

import by.betrayal.personalservice.dto.passport.PassportCreateDto;
import by.betrayal.personalservice.dto.passport.PassportFullDto;
import by.betrayal.personalservice.dto.passport.PassportUpdateDto;
import by.betrayal.personalservice.service.PassportService;
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

    @GetMapping(ENDPOINT)
    public ResponseEntity<List<PassportFullDto>> findAll() {
        var list = service.findAll();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_BY_PATIENT_ID)
    public ResponseEntity<List<PassportFullDto>> findAll(@PathVariable Long patientId) {
        var list = service.findAll(patientId);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_BY_ID)
    public ResponseEntity<PassportFullDto> findById(@PathVariable Long id) {
        var item = service.findById(id);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping(ENDPOINT)
    public ResponseEntity<PassportFullDto> create(@Valid @RequestBody PassportCreateDto dto) {
        var list = service.create(dto);

        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

    @PutMapping(ENDPOINT)
    public ResponseEntity<PassportFullDto> update(@Valid @RequestBody PassportUpdateDto dto) {
        var list = service.update(dto);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping(ENDPOINT_BY_ID)
    public ResponseEntity<PassportFullDto> delete(@PathVariable Long id) {
        var list = service.delete(id);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
