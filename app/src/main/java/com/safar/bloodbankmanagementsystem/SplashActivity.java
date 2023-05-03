package com.safar.bloodbankmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        } else {
            Thread background = new Thread() {
                public void run() {
                    try {
                        sleep(1000);
                        Intent i = new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(i);
                        finish();
                    } catch (Exception e) {
                        Toast.makeText(SplashActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            };
            background.start();
        }
    }
}
