package by.betrayal.personalservice.controller;

import by.betrayal.personalservice.dto.policy.PolicyCreateDto;
import by.betrayal.personalservice.dto.policy.PolicyFullDto;
import by.betrayal.personalservice.dto.policy.PolicyUpdateDto;
import by.betrayal.personalservice.service.PolicyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/policies")
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService service;

    @GetMapping
    public ResponseEntity<List<PolicyFullDto>> findAll(@RequestParam(required = false) Integer limit,
                                                       @RequestParam(defaultValue = "0") Integer page
    ) {
        if(limit == null) {
            return ResponseEntity.ok(service.findAll());
        }

        var result = service.findAll(limit, page);

        var header = new HttpHeaders();
        header.set("x-total-count", String.valueOf(result.totalCount()));

        return new ResponseEntity<>(result.items(), header, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PolicyFullDto> findById(@PathVariable(value = "id") Long id) {
        var item = service.findById(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public ResponseEntity<PolicyFullDto> create(@Valid @RequestBody PolicyCreateDto dto) {
        var item = service.create(dto);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PolicyFullDto> update(@Valid @RequestBody PolicyUpdateDto dto) {
        var item = service.update(dto);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PolicyFullDto> delete(@PathVariable(value = "id") Long id) {
        var item = service.delete(id);
        return ResponseEntity.ok(item);
    }
}