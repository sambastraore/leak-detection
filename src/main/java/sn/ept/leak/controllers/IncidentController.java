package sn.ept.leak.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.ept.leak.dtos.ServiceResponse;
import sn.ept.leak.services.IncidentService;

@RestController
@RequestMapping("/api")
public class IncidentController {


    @Autowired
    public IncidentService incidentService;
    @GetMapping(value = "/getIncidents")
    public ResponseEntity<ServiceResponse> getAllIncidents() throws InterruptedException {
        ServiceResponse response = incidentService.getAllIncidents();
        return new ResponseEntity<ServiceResponse>(response, new HttpHeaders(), HttpStatus.OK);

    }
}
