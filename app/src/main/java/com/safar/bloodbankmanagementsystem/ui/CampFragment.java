package com.safar.bloodbankmanagementsystem.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.safar.bloodbankmanagementsystem.R;
import com.safar.bloodbankmanagementsystem.databinding.FragmentCampBinding;
import com.safar.bloodbankmanagementsystem.databinding.FragmentFeedbackBinding;

import java.util.Date;

public class CampFragment extends Fragment {

    private static final String TAG = "CampFragment";
    private FirebaseFirestore firebaseFirestore;
    private FragmentCampBinding binding;

    private void init() {
        initialize();
        listener();
    }

    private void initialize() {
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    private void listener() {
        firebaseFirestore
                .collection("Camps")
                .orderBy("geoHash", Query.Direction.ASCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                            View view = getLayoutInflater().inflate(R.layout.blood_camp_card_layout, null, false);

                            TextView tvName = view.findViewById(R.id.tvName);
                            TextView tvLat = view.findViewById(R.id.tvLat);
                            TextView tvLang = view.findViewById(R.id.tvLang);
                            TextView tvEndDate = view.findViewById(R.id.tvEndDate);

                            GeoPoint geoPoint = documentSnapshot.getGeoPoint("geoPoint");

                            tvName.setText("Name : " + documentSnapshot.get("name").toString());
                            tvLat.setText("Lat : " + Double.toString(geoPoint.getLatitude()));
                            tvLang.setText("Lang : " + Double.toString(geoPoint.getLongitude()));
                            tvEndDate.setText("End Date : " + new Date((long)documentSnapshot.get("end")).toString());

                            binding.llData.addView(view);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCampBinding.inflate(getLayoutInflater());

        init();

        return binding.getRoot();
    }
}