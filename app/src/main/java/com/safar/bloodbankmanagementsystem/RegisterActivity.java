package com.safar.bloodbankmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.safar.bloodbankmanagementsystem.model.User;

import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName, etMobileNumber, etBloodGroup, etEmail, etPassword, etLat, etLang;
    private Button btnRegister;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    private void init() {
        initialize();
        listener();
    }

    private void initialize() {
        etName = findViewById(R.id.etName);
        etMobileNumber = findViewById(R.id.etMobileNumber);
        etBloodGroup = findViewById(R.id.etBloodGroup);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etLat = findViewById(R.id.etLat);
        etLang = findViewById(R.id.etLang);
        btnRegister = findViewById(R.id.btnRegister);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void listener() {
        btnRegister
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String id = UUID.randomUUID().toString();
                        String name = etName.getText().toString().trim();
                        String mobileNumber = etMobileNumber.getText().toString().trim();
                        String bloodGroup = etBloodGroup.getText().toString().trim();
                        String email = etEmail.getText().toString().trim();
                        String pass = etPassword.getText().toString().trim();
                        double lat = Double.parseDouble(etLat.getText().toString().trim());
                        double lang = Double.parseDouble(etLang.getText().toString().trim());
                        long time = System.currentTimeMillis();

                        firebaseAuth
                                .createUserWithEmailAndPassword(email, pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        firebaseFirestore
                                                .collection("User")
                                                .document(email)
                                                .set(new User(id, name, mobileNumber, bloodGroup, email, time, lat, lang))
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                                        finish();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }
}