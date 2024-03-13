package by.betrayal.personalservice.controller;

import by.betrayal.personalservice.dto.medicalcard.MedicalCardCreateDto;
import by.betrayal.personalservice.dto.medicalcard.MedicalCardFullDto;
import by.betrayal.personalservice.dto.medicalcard.MedicalCardUpdateDto;
import by.betrayal.personalservice.service.MedicalCardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MedicalCardController {

    private final MedicalCardService service;

    public static final String ENDPOINT = "v1/medical/cards";
    public static final String ENDPOINT_BY_ID = "v1/medical/cards/{id}";
    public static final String ENDPOINT_BY_PATIENT_ID = "v1/people/{patientId}/medical/cards";

    @GetMapping(ENDPOINT)
    public ResponseEntity<List<MedicalCardFullDto>> findAll() {
        var list = service.findAll();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_BY_PATIENT_ID)
    public ResponseEntity<List<MedicalCardFullDto>> findAll(@PathVariable Long patientId) {
        var list = service.findAll(patientId);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_BY_ID)
    public ResponseEntity<MedicalCardFullDto> findById(@PathVariable Long id) {
        var item = service.findById(id);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping(ENDPOINT)
    public ResponseEntity<MedicalCardFullDto> create(@Valid @RequestBody MedicalCardCreateDto dto) {
        var item = service.create(dto);

        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping(ENDPOINT)
    public ResponseEntity<MedicalCardFullDto> update(@Valid @RequestBody MedicalCardUpdateDto dto) {
        var item = service.update(dto);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping(ENDPOINT_BY_ID)
    public ResponseEntity<MedicalCardFullDto> delete(@PathVariable Long id) {
        var item = service.delete(id);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
