package sn.ept.leak.job;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sn.ept.leak.dtos.ServiceResponse;
import sn.ept.leak.entities.Incident;
import sn.ept.leak.services.CapteurService;
import sn.ept.leak.services.IncidentService;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class LeakDetectionJob {

    @Autowired
    private LeakDetectionEngine leakDetectionEngine;

    @Autowired
    private IncidentService incidentService;

    @Autowired
    private CapteurService capteurService;

    @Scheduled(fixedRate = 15000)
    public void executeDetectionAndCreateIncidents() throws ExecutionException, InterruptedException {
        // Penser à comment gérer le cycle de vie des incidents

        Double epsilon = 0.1;
        List<String> fuitesDetectees = new ArrayList<>();
        ServiceResponse response = leakDetectionEngine.LeakDetectionAlgorithm(epsilon);

        if (response.getMessage().equals("Fuite(s) détectée(s)")) {
            JSONObject data = (JSONObject) response.getData();
            fuitesDetectees = (List<String>) data.get("fuitesDetectees");

            // Crée un incident pour chaque fuite détectée
            for (String fuite : fuitesDetectees) {
                String[] capteurs = fuite.split(" et ");
                String capteur1Id = capteurs[0];
                String capteur2Id = capteurs[1];

                // Crée un nouvel objet Incident avec les détails des capteurs et de la fuite
                Incident incident = new Incident();
                incident.setId_1(capteur1Id);
                incident.setId_2(capteur2Id);
                incident.setPression_1(capteurService.getCapteur(capteur1Id).getPression());
                incident.setPression_2(capteurService.getCapteur(capteur2Id).getPression());
                Date creationDate = new Date();
                incident.setDate(creationDate);
                incident.setDocumentId(String.valueOf(creationDate).replace(" ","").replace(":",""));
                //la gravité est à revoir
                incident.setGravite("moyen");
                String incidentId = incidentService.createIncident(incident);
                System.out.println("Incident " + incidentId + " créé  avec succès" );
            }
        } else {
            System.out.println("Aucune fuite détectée.");
        }
    }
}
