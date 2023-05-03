package com.safar.bloodbankmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import java.util.HashMap;
import java.util.Map;

public class BloodInfoActivity extends AppCompatActivity {

    private static final String TAG = "BloodInfoActivity";
    String id;
    private TextView tvName, tvLat, tvLang;

    private MaterialCardView btnReceive, btnDonate;
    private TextView AP1, AP2, AP3, AP4, AP5, AP6, AP7, AP8;

    private TableLayout tlData;
    private FirebaseFirestore firebaseFirestore;
//    Map<String, String> bloodGroup;

    private void init() {
        initialize();
        listener();

        getData();
    }

    private void getData() {
        firebaseFirestore
                .collection("Bank")
                .document(id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String name = documentSnapshot.get("name").toString();
                        GeoPoint geoPoint = documentSnapshot.getGeoPoint("geoPoint");
//                        bloodGroup = (Map<String, String>) documentSnapshot.get("bloodGroup");

                        tvName.setText("Name : " + name);
                        tvLat.setText("Lat : " + Double.toString(geoPoint.getLatitude()));
                        tvLang.setText("Lang : " + Double.toString(geoPoint.getLongitude()));

                        AP1.setText(documentSnapshot.get("A+").toString());
                        AP2.setText(documentSnapshot.get("A-").toString());
                        AP3.setText(documentSnapshot.get("B+").toString());
                        AP4.setText(documentSnapshot.get("B-").toString());
                        AP5.setText(documentSnapshot.get("O+").toString());
                        AP6.setText(documentSnapshot.get("O-").toString());
                        AP7.setText(documentSnapshot.get("AB+").toString());
                        AP8.setText(documentSnapshot.get("AB-").toString());

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

        btnReceive = findViewById(R.id.btnReceive);
        btnDonate = findViewById(R.id.btnDonate);

        tvName = findViewById(R.id.tvName);
        tvLat = findViewById(R.id.tvLat);
        tvLang = findViewById(R.id.tvLang);

        AP1 = findViewById(R.id.AP1);
        AP2 = findViewById(R.id.AP2);
        AP3 = findViewById(R.id.AP3);
        AP4 = findViewById(R.id.AP4);
        AP5 = findViewById(R.id.AP5);
        AP6 = findViewById(R.id.AP6);
        AP7 = findViewById(R.id.AP7);
        AP8 = findViewById(R.id.AP8);

    }

    private void listener() {

        btnReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(BloodInfoActivity.this);

                dialog.setContentView(R.layout.blood_receive_donate_form_layout);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                EditText etBloodGroup = dialog.findViewById(R.id.etBloodGroup);
                EditText etPackets = dialog.findViewById(R.id.etPackets);
                Button btnSubmit = dialog.findViewById(R.id.btnSubmit);

                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String bloodGroup = etBloodGroup.getText().toString().trim();
                        int receive = Integer.valueOf(etPackets.getText().toString().trim());

                        if (bloodGroup.equals("A+")) {
                            int cal = Integer.parseInt(AP1.getText().toString()) - receive;
                            if (cal < 0) {
                                Toast.makeText(BloodInfoActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            } else {
                                updateField(bloodGroup, Integer.toString(cal));
                            }

                        } else if (bloodGroup.equals("A-")) {
                            int cal = Integer.parseInt(AP2.getText().toString()) - receive;
                            if (cal < 0) {
                                Toast.makeText(BloodInfoActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            } else {
                                updateField(bloodGroup, Integer.toString(cal));
                            }
                        } else if (bloodGroup.equals("B+")) {
                            int cal = Integer.parseInt(AP3.getText().toString()) - receive;
                            if (cal < 0) {
                                Toast.makeText(BloodInfoActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            } else {
                                updateField(bloodGroup, Integer.toString(cal));
                            }
                        } else if (bloodGroup.equals("B-")) {
                            int cal = Integer.parseInt(AP4.getText().toString()) - receive;
                            if (cal < 0) {
                                Toast.makeText(BloodInfoActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            } else {
                                updateField(bloodGroup, Integer.toString(cal));
                            }
                        } else if (bloodGroup.equals("O+")) {
                            int cal = Integer.parseInt(AP5.getText().toString()) - receive;
                            if (cal < 0) {
                                Toast.makeText(BloodInfoActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            } else {
                                updateField(bloodGroup, Integer.toString(cal));
                            }
                        } else if (bloodGroup.equals("O-")) {
                            int cal = Integer.parseInt(AP6.getText().toString()) - receive;
                            if (cal < 0) {
                                Toast.makeText(BloodInfoActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            } else {
                                updateField(bloodGroup, Integer.toString(cal));
                            }
                        } else if (bloodGroup.equals("AB+")) {
                            int cal = Integer.parseInt(AP7.getText().toString()) - receive;
                            if (cal < 0) {
                                Toast.makeText(BloodInfoActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            } else {
                                updateField(bloodGroup, Integer.toString(cal));
                            }
                        } else if (bloodGroup.equals("AB-")) {
                            int cal = Integer.parseInt(AP8.getText().toString()) - receive;
                            if (cal < 0) {
                                Toast.makeText(BloodInfoActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            } else {
                                updateField(bloodGroup, Integer.toString(cal));
                            }
                        }

                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(BloodInfoActivity.this);

                dialog.setContentView(R.layout.blood_receive_donate_form_layout);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                EditText etBloodGroup = dialog.findViewById(R.id.etBloodGroup);
                EditText etPackets = dialog.findViewById(R.id.etPackets);
                Button btnSubmit = dialog.findViewById(R.id.btnSubmit);

                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String bloodGroup = etBloodGroup.getText().toString().trim();
                        int donate = Integer.valueOf(etPackets.getText().toString().trim());

                        if (bloodGroup.equals("A+")) {
                            int cal = Integer.parseInt(AP1.getText().toString()) + donate;
                            updateField(bloodGroup, Integer.toString(cal));

                        } else if (bloodGroup.equals("A-")) {
                            int cal = Integer.parseInt(AP2.getText().toString()) + donate;
                            updateField(bloodGroup, Integer.toString(cal));
                        } else if (bloodGroup.equals("B+")) {
                            int cal = Integer.parseInt(AP3.getText().toString()) + donate;
                            updateField(bloodGroup, Integer.toString(cal));
                        } else if (bloodGroup.equals("B-")) {
                            int cal = Integer.parseInt(AP4.getText().toString()) + donate;
                            updateField(bloodGroup, Integer.toString(cal));
                        } else if (bloodGroup.equals("O+")) {
                            int cal = Integer.parseInt(AP5.getText().toString()) + donate;
                            updateField(bloodGroup, Integer.toString(cal));
                        } else if (bloodGroup.equals("O-")) {
                            int cal = Integer.parseInt(AP6.getText().toString()) + donate;
                            updateField(bloodGroup, Integer.toString(cal));
                        } else if (bloodGroup.equals("AB+")) {
                            int cal = Integer.parseInt(AP7.getText().toString()) + donate;
                            updateField(bloodGroup, Integer.toString(cal));
                        } else if (bloodGroup.equals("AB-")) {
                            int cal = Integer.parseInt(AP8.getText().toString()) + donate;
                            updateField(bloodGroup, Integer.toString(cal));
                        }

                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    private void updateField(String bloodGroup, String packets) {
        Map<String, Object> update = new HashMap<>();
        update.put(bloodGroup, packets);

        firebaseFirestore
                .collection("Bank")
                .document(id)
                .update(update)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(BloodInfoActivity.this, "Transaction Completed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_info);
        getSupportActionBar().setTitle("Blood Info");

        init();
    }
}