package by.betrayal.visitingservice.controller;

import by.betrayal.visitingservice.dto.institution.InstitutionCreateDto;
import by.betrayal.visitingservice.dto.institution.InstitutionFullDto;
import by.betrayal.visitingservice.dto.institution.InstitutionUpdateDto;
import by.betrayal.visitingservice.service.InstitutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InstitutionController {

    private final InstitutionService service;
    private static final String ENDPOINT = "v1/institutions";
    private static final String ENDPOINT_BY_ID = "v1/institutions/{id}";

    @GetMapping(ENDPOINT)
    public ResponseEntity<List<InstitutionFullDto>> findAll() {
        var items = service.findAll();

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_BY_ID)
    public ResponseEntity<InstitutionFullDto> findById(@PathVariable Short id) {
        var item = service.findById(id);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping(ENDPOINT)
    public ResponseEntity<InstitutionFullDto> create(@RequestBody InstitutionCreateDto dto) {
        var item = service.create(dto);

        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping(ENDPOINT)
    public ResponseEntity<InstitutionFullDto> update(@RequestBody InstitutionUpdateDto dto) {
        var item = service.update(dto);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping(ENDPOINT_BY_ID)
    public ResponseEntity<InstitutionFullDto> delete(@PathVariable Short id) {
        var item = service.delete(id);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
