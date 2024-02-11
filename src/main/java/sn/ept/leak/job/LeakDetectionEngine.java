package sn.ept.leak.job;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.ept.leak.dtos.ServiceResponse;
import sn.ept.leak.entities.Capteur;
import sn.ept.leak.services.CapteurService;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
public class LeakDetectionEngine {

    @Autowired
    public CapteurService capteurService;

    //@Scheduled(fixedRate = 15000)
    public  ServiceResponse LeakDetectionAlgorithm(Double epsilon) throws ExecutionException, InterruptedException {
        ServiceResponse serviceResponse = capteurService.getAllCapteurs();
        String code = serviceResponse.getMessage();
        List<Capteur> capteurDetails = new ArrayList<>();
        List<String> capteurIds = new ArrayList<>();
        List<String> fuitesDetectees = new ArrayList<>();

        if (Objects.equals(code, "Succès")) {
            JSONObject capteursJson = (JSONObject) serviceResponse.getData();
            capteurDetails = (List<Capteur>) capteursJson.get("capteurs");

        }

        for (Capteur capteur : capteurDetails) {
            String capteurId = capteur.getDocumentId();
            capteurIds.add(capteurId);
        }

        for (String capteurId : capteurIds) {
            int tuyauNumero = getTuyauNumero(capteurId);
            int capteurNumero = getCapteurNumero(capteurId);

            if (capteurNumero > 1) {
                String capteurPrecedentId = "capteur_" + tuyauNumero + "." + (capteurNumero - 1);
                double pressionCapteur = capteurService.getCapteur(capteurId).getPression();
                double pressionCapteurPrecedent = capteurService.getCapteur(capteurPrecedentId).getPression();

                double differencePression = pressionCapteurPrecedent - pressionCapteur;
                // double limiteEpsilon = 0.1;

                if (differencePression > epsilon) {
                    fuitesDetectees.add(capteurPrecedentId + " et " + capteurId);
                    System.out.println("Fuite détectée entre " + capteurPrecedentId + " et " + capteurId);
                }
            }
        }
        JSONObject data = new JSONObject();
        data.put("fuitesDetectees", fuitesDetectees);

        if (!fuitesDetectees.isEmpty()) {
            return new ServiceResponse("Fuite(s) détectée(s)", data);
        } else {
            return new ServiceResponse("Aucune fuite détectée",  data);
        }
    }

    public static int getTuyauNumero(String capteurId) {
        String[] capteurIdParts = capteurId.split("_");
        return Integer.parseInt(capteurIdParts[1].split("\\.")[0]);
    }

    private static int getCapteurNumero(String capteurId) {
        String[] capteurIdParts = capteurId.split("_");
        return Integer.parseInt(capteurIdParts[1].split("\\.")[1]);
    }

    // il reste à gérer les cas de ramification. Discuter aussi de comment on compte disposer les capteurs.
    // faire que la fonction renvoie quelque chose s'il y a fuite (renvoyer un service response)
}
