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
import sn.ept.leak.entities.Segment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class SegmentService {
    public Segment getIncident(String documentId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("segment").document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        Segment segment;
        if (document.exists()) {
            segment = document.toObject(Segment.class);
            return segment;
        }
        return null;
    }


    public String updateSegment(Segment segment) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("segment").document(segment.getDocumentId()).set(segment);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public ServiceResponse getAllIncidents() {

        Firestore dbFireStore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReference = dbFireStore.collection("segment").listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

        List<Segment> segmentDetails = new ArrayList<>();
        Segment segment = null;
        int count = 0;
        while(iterator.hasNext()) {
            count++;
            DocumentReference documentReference1 = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference1.get();
            DocumentSnapshot document;
            try {
                document = future.get();
                if (document.exists() && document.toObject(Segment.class).fuite) {
                    segment = document.toObject(Segment.class);
                    segmentDetails.add(segment);
                } else {
                    return new ServiceResponse("Echec", "Pas de données", null);
                }
            } catch (InterruptedException | ExecutionException e) {
                return new ServiceResponse("Echec", "Une exception est levée", "Error : " + e.getLocalizedMessage());
            }

        }
        JSONObject obj = new JSONObject();
        obj.put("segments", segmentDetails);
        return new ServiceResponse("Succès", "Les informations sur tous les incidents ont éte obtenus avec succès",count + " Incidents",obj);
    }

    public ServiceResponse getAllSegments() {

        Firestore dbFireStore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReference = dbFireStore.collection("segment").listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

        List<Segment> segmentDetails = new ArrayList<>();
        Segment segment = null;
        int count = 0;
        while(iterator.hasNext()) {
            count++;
            DocumentReference documentReference1 = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference1.get();
            DocumentSnapshot document;
            try {
                document = future.get();
                if (document.exists()) {
                    segment = document.toObject(Segment.class);
                    segmentDetails.add(segment);
                } else {
                    return new ServiceResponse("Echec", "Pas de données", null);
                }
            } catch (InterruptedException | ExecutionException e) {
                return new ServiceResponse("Echec", "Une exception est levée", "Error : " + e.getLocalizedMessage());
            }

        }
        JSONObject obj = new JSONObject();
        obj.put("segments", segmentDetails);
        return new ServiceResponse("Succès", "Les informations sur tous les incidents ont éte obtenus avec succès",count + " Incidents",obj);
    }
}
