package sn.ept.leak.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sn.ept.leak.dtos.ServiceResponse;
import sn.ept.leak.entities.Capteur;
import sn.ept.leak.services.CapteurService;
import org.springframework.http.HttpStatus;

import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/api")
public class CapteurController {

    @Autowired
    public CapteurService capteurService;

    @GetMapping(value = "/getCapteurs")
    public ResponseEntity<ServiceResponse> getAllCapteurs() throws InterruptedException {
        ServiceResponse response = capteurService.getAllCapteurs();
        return new ResponseEntity<ServiceResponse>(response, new HttpHeaders(), HttpStatus.OK);

    }

    @GetMapping("/getCapteur")
    public Capteur getCapteur(@RequestParam String documentId) throws InterruptedException, ExecutionException {
        return capteurService.getCapteur(documentId);
    }




}
