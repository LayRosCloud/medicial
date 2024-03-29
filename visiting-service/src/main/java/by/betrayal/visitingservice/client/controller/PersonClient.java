package by.betrayal.visitingservice.client.controller;

import by.betrayal.visitingservice.client.dto.PersonFullDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "personal-client"
)
public interface PersonClient {

    @GetMapping("v1/people/{id}")
    PersonFullDto findById(@PathVariable Long id);
}
