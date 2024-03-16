package by.betrayal.visitingservice.controller;

import by.betrayal.visitingservice.dto.visit.VisitCreateDto;
import by.betrayal.visitingservice.dto.visit.VisitFullDto;
import by.betrayal.visitingservice.dto.visit.VisitUpdateDto;
import by.betrayal.visitingservice.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VisitController {

    public static final String ENDPOINT_BY_PATIENT_ID = "v1/people/{patientId}/visits";
    public static final String ENDPOINT_BY_ID = "v1/visits/{id}";
    public static final String ENDPOINT = "v1/visits";

    private final VisitService service;


    @GetMapping(ENDPOINT_BY_PATIENT_ID)
    public ResponseEntity<List<VisitFullDto>> findAllByPatientId(@PathVariable Long patientId) {

        var list = service.findAllByPatientId(patientId);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_BY_ID)
    public ResponseEntity<VisitFullDto> findById(@PathVariable Long id) {
        var item = service.findById(id);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping(ENDPOINT)
    public ResponseEntity<VisitFullDto> create(@RequestBody VisitCreateDto dto) {
        var item = service.create(dto);

        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping(ENDPOINT)
    public ResponseEntity<VisitFullDto> update(@RequestBody VisitUpdateDto dto) {
        var item = service.update(dto);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping(ENDPOINT_BY_ID)
    public ResponseEntity<VisitFullDto> delete(@PathVariable Long id) {
        var item = service.delete(id);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping(ENDPOINT_BY_PATIENT_ID)
    public ResponseEntity<List<VisitFullDto>> deleteAllByPatientId(@PathVariable Long patientId) {

        var list = service.deleteAllByPatientId(patientId);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
