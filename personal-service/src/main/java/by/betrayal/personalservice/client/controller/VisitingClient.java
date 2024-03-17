package by.betrayal.personalservice.client.controller;

import by.betrayal.personalservice.client.dto.VisitFullDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "visiting-client")
public interface VisitingClient {
    @DeleteMapping("v1/people/{patientId}/visits")
    List<VisitFullDto> deleteAllVisitsByPatientId(@PathVariable Long patientId);
    @DeleteMapping("v1/people/{patientId}/diagnostics")
    List<VisitFullDto> deleteAllDiagnosticsByPatientId(@PathVariable Long patientId);
}
