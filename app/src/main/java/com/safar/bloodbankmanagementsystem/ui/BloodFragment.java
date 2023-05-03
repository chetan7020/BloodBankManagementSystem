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
import com.google.firebase.firestore.QuerySnapshot;
import com.safar.bloodbankmanagementsystem.R;
import com.safar.bloodbankmanagementsystem.databinding.FragmentBloodBinding;


public class BloodFragment extends Fragment {

    private FragmentBloodBinding binding;
    private static final String TAG = "BloodFragment";
    private FirebaseFirestore firebaseFirestore;

    private void init() {
        initialize();
        listener();
    }

    private void initialize() {
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    private void listener() {
        firebaseFirestore
                .collection("Bank")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                            Log.d(TAG, "onSuccess: " + documentSnapshot);

                            String name = documentSnapshot.get("name").toString();
                            GeoPoint geoPoint = documentSnapshot.getGeoPoint("geoPoint");

                            double lat = geoPoint.getLatitude();
                            double lang = geoPoint.getLongitude();

                            addBank(documentSnapshot.getId(), name, lat, lang);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void addBank(String id, String name, double lat, double lang) {
        View view = getLayoutInflater().inflate(R.layout.blood_bank_card_layout, null, false);

        TextView tvID = view.findViewById(R.id.tvID);
        TextView tvBankName = view.findViewById(R.id.tvBankName);
        TextView tvLat = view.findViewById(R.id.tvLat);
        TextView tvLang = view.findViewById(R.id.tvLang);

        tvID.setText(id);
        tvBankName.setText(name);
        tvLat.setText(Double.toString(lat));
        tvLang.setText(Double.toString(lang));

        binding.llData.addView(view);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBloodBinding.inflate(getLayoutInflater());

        init();

        return binding.getRoot();
    }
}