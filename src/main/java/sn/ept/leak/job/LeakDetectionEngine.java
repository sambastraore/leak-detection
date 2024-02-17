package sn.ept.leak.job;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.ept.leak.dtos.ServiceResponse;
import sn.ept.leak.entities.Capteur;
import sn.ept.leak.entities.Segment;
import sn.ept.leak.services.CapteurService;
import org.springframework.scheduling.annotation.Scheduled;
import sn.ept.leak.services.SegmentService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
public class LeakDetectionEngine {

    @Autowired
    public CapteurService capteurService;

    @Autowired
    public SegmentService segmentService;

    //@Scheduled(fixedRate = 15000)
    public  ServiceResponse LeakDetectionAlgorithm(Double epsilon) throws ExecutionException, InterruptedException {
        ServiceResponse serviceResponse = segmentService.getAllSegments();
        String code = serviceResponse.getMessage();
        List<Segment> segmentDetails = new ArrayList<>();
        List<String> segmentsIds = new ArrayList<>();
        List<String> fuitesDetectees = new ArrayList<>();

        if (Objects.equals(code, "Succès")) {
            JSONObject segmentsJson = (JSONObject) serviceResponse.getData();
            segmentDetails = (List<Segment>) segmentsJson.get("segments");

        }

        for (Segment segment : segmentDetails) {
            String segmentId = segment.getDocumentId();
            segmentsIds.add(segmentId);
            double pression_1 = capteurService.getCapteur(segment.getId_1()).getPression();
            double pression_2 = capteurService.getCapteur(segment.getId_2()).getPression();
            double perteDeChargeNormale = segment.getPerteDeChargeNormale();
            double perteDeCharge = pression_1 - pression_2;

            if ((perteDeCharge - perteDeChargeNormale) > epsilon ) {
                segment.setFuite(true);
                segment.setVerification(new Date());
                fuitesDetectees.add(segment.documentId);
                segmentService.updateSegment(segment);
            }

            else {
                segment.setFuite(false);
                segment.setVerification(new Date());
                segmentService.updateSegment(segment);
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



    // il reste à gérer les cas de ramification. Discuter aussi de comment on compte disposer les capteurs.
    // faire que la fonction renvoie quelque chose s'il y a fuite (renvoyer un service response)
}
