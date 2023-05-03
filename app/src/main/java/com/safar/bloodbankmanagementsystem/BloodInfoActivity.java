package com.safar.bloodbankmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class BloodInfoActivity extends AppCompatActivity {

    private static final String TAG = "BloodInfoActivity";
    String id;
    private TextView tvName, tvLat, tvLang;

    private TableLayout tlData;
    private FirebaseFirestore firebaseFirestore;

    private void init() {
        initialize();
        listener();

        Log.d(TAG, "init: " + id);

        firebaseFirestore
                .collection("Bank")
                .document(id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String name = documentSnapshot.get("name").toString();
                        GeoPoint geoPoint = documentSnapshot.getGeoPoint("geoPoint");
                        Map<String, String> bloodGroup = (Map<String, String>) documentSnapshot.get("bloodGroup");

                        tvName.setText("Name : " + name);
                        tvLat.setText("Lat : " + Double.toString(geoPoint.getLatitude()));
                        tvLang.setText("Lang : " + Double.toString(geoPoint.getLongitude()));

                        Log.d(TAG, "onSuccess: "+bloodGroup);

                        for (Map.Entry<String, String> entry : bloodGroup.entrySet()) {
                            View view = getLayoutInflater().inflate(R.layout.blood_group_data_row_layout, null, false);

                            TextView tvBloodType = view.findViewById(R.id.tvBloodType);
                            TextView tvPackets = view.findViewById(R.id.tvPackets);

                            tvBloodType.setText(entry.getKey());
                            tvPackets.setText(entry.getValue());
                            Log.d(TAG, "onSuccess: "+entry.getKey());
                            Log.d(TAG, "onSuccess: "+entry.getValue());

                            tlData.addView(view);
                        }


                    }
                }).

                addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(BloodInfoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void initialize() {
        id = getIntent().getStringExtra("id");

        firebaseFirestore = FirebaseFirestore.getInstance();

        tvName = findViewById(R.id.tvName);
        tvLat = findViewById(R.id.tvLat);
        tvLang = findViewById(R.id.tvLang);

        tlData = findViewById(R.id.tlData);
    }

    private void listener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_info);

        init();
    }
}