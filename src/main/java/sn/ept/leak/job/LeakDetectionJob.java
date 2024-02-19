package sn.ept.leak.job;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sn.ept.leak.dtos.ServiceResponse;
import sn.ept.leak.email.EmailService;
import sn.ept.leak.entities.Segment;
import sn.ept.leak.services.CapteurService;
import sn.ept.leak.services.SegmentService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class LeakDetectionJob {

    @Autowired
    private LeakDetectionEngine leakDetectionEngine;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SegmentService segmentService;

    @Autowired
    private CapteurService capteurService;

    @Scheduled(fixedRate = 30000)
    public void executeDetectionAndCreateIncidents() throws ExecutionException, InterruptedException {

        Double epsilon = 0.1;
        List<String> fuitesDetectees = new ArrayList<>();
        ServiceResponse response = leakDetectionEngine.LeakDetectionAlgorithm(epsilon);

        if (response.getMessage().equals("Fuite(s) détectée(s)")) {
            JSONObject data = (JSONObject) response.getData();
            fuitesDetectees = (List<String>) data.get("fuitesDetectees");

            // Crée un incident pour chaque fuite détectée
            for (String id : fuitesDetectees) {
                emailService.sendEmail("metzosmith@gmail.com","Fuite","Il y a une fuite sur " + id + ".");
                System.out.println("Il y a une fuite sur " + id + "." );
            }
        } else {
            System.out.println("Aucune fuite détectée.");
        }
    }
}
