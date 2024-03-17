package by.betrayal.visitingservice.controller;

import by.betrayal.visitingservice.dto.diagnostic.DiagnosticCreateDto;
import by.betrayal.visitingservice.dto.diagnostic.DiagnosticFullDto;
import by.betrayal.visitingservice.dto.diagnostic.DiagnosticUpdateDto;
import by.betrayal.visitingservice.service.DiagnosticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiagnosticController {

    private final DiagnosticService service;

    private static final String ENDPOINT_BY_PATIENT_ID = "v1/people/{patientId}/diagnostics";
    private static final String ENDPOINT_BY_ID = "v1/diagnostics/{id}";
    private static final String ENDPOINT = "v1/diagnostics";

    @GetMapping(ENDPOINT_BY_PATIENT_ID)
    public ResponseEntity<List<DiagnosticFullDto>> findAllByPatientId(@PathVariable Long patientId) {
        var items = service.findAllByPatientId(patientId);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_BY_ID)
    public ResponseEntity<DiagnosticFullDto> findById(@PathVariable Long id) {
        var item = service.findById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping(ENDPOINT)
    public ResponseEntity<DiagnosticFullDto> create(@RequestBody DiagnosticCreateDto dto) {
        var item = service.create(dto);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping(ENDPOINT)
    public ResponseEntity<DiagnosticFullDto> update(@RequestBody DiagnosticUpdateDto dto) {
        var item = service.update(dto);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping(ENDPOINT)
    public ResponseEntity<DiagnosticFullDto> delete(@RequestBody Long id) {
        var item = service.delete(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping(ENDPOINT_BY_PATIENT_ID)
    public ResponseEntity<List<DiagnosticFullDto>> deleteAllByPatientId(@PathVariable Long patientId) {
        var items = service.deleteAllByPatientId(patientId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
