package sn.ept.leak.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import sn.ept.leak.dtos.ServiceResponse;
import sn.ept.leak.entities.Incident;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class IncidentService {
    public Incident getIncident(String documentId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("incident").document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        Incident incident;
        if (document.exists()) {
            incident = document.toObject(Incident.class);
            return incident;
        }
        return null;
    }


    public String createIncident(Incident incident) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("incident").document(incident.getDocumentId()).set(incident);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public ServiceResponse getAllIncidents() {

        Firestore dbFireStore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReference = dbFireStore.collection("incident").listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

        List<Incident> incidentDetails = new ArrayList<>();
        Incident incident = null;
        int count = 0;
        while(iterator.hasNext()) {
            count++;
            DocumentReference documentReference1 = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference1.get();
            DocumentSnapshot document;
            try {
                document = future.get();
                if (document.exists()) {
                    incident = document.toObject(Incident.class);
                    incidentDetails.add(incident);
                } else {
                    return new ServiceResponse("Echec", "Pas de données", null);
                }
            } catch (InterruptedException | ExecutionException e) {
                return new ServiceResponse("Echec", "Une exception est levée", "Error : " + e.getLocalizedMessage());
            }

        }
        JSONObject obj = new JSONObject();
        obj.put("incidents", incidentDetails);
        return new ServiceResponse("Succès", "Les informations sur tous les incidents ont éte obtenus avec succès",count + " Incidents",obj);
    }
}
