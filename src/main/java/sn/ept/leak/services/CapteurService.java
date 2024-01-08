package sn.ept.leak.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import sn.ept.leak.entities.Capteur;
import sn.ept.leak.dtos.ServiceResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CapteurService {

    public Capteur getCapteur(String documentId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("capteurs").document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        Capteur capteur;
        if (document.exists()) {
            capteur = document.toObject(Capteur.class);
            return capteur;
        }
        return null;
    }

    public ServiceResponse getAllCapteurs() {

        Firestore dbFireStore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReference = dbFireStore.collection("capteurs").listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

        List<Capteur> capteurDetails = new ArrayList<>();
        Capteur capteur = null;
        int count = 0;
        while(iterator.hasNext()) {
            count++;
            DocumentReference documentReference1 = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference1.get();
            DocumentSnapshot document;
            try {
                document = future.get();
                if (document.exists()) {
                    capteur = document.toObject(Capteur.class);
                    capteurDetails.add(capteur);
                } else {
                    return new ServiceResponse("Echec", "Pas de données", null);
                }
            } catch (InterruptedException | ExecutionException e) {
                return new ServiceResponse("Echec", "Une exception est levée", "Error : " + e.getLocalizedMessage());
            }

        }
        JSONObject obj = new JSONObject();
        obj.put("capteurs", capteurDetails);
        return new ServiceResponse("Succès", "Les données de tous les capteurs ont éte obtenus avec succès",count + " Capteurs",obj);
    }



}
