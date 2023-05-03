package com.safar.bloodbankmanagementsystem.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.safar.bloodbankmanagementsystem.databinding.FragmentFeedbackBinding;
import com.safar.bloodbankmanagementsystem.databinding.FragmentProfileBinding;

import java.util.HashMap;
import java.util.Map;

public class FeedbackFragment extends Fragment {

    private FragmentFeedbackBinding binding;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    private void init() {
        initialize();
        listener();
    }

    private void initialize() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
    }
    
    private void listener(){
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> data = new HashMap<>();
                data.put("q1", binding.etQ1.getText().toString());
                data.put("q2", binding.etQ2.getText().toString());
                data.put("q3", binding.etQ3.getText().toString());
                data.put("q4", binding.etQ4.getText().toString());
                data.put("q5", binding.etQ5.getText().toString());
                data.put("q6", binding.etQ6.getText().toString());
                data.put("q7", binding.etQ7.getText().toString());
                data.put("q8", binding.etQ8.getText().toString());
                
                firebaseFirestore
                        .collection("Feedback")
                        .document(firebaseAuth.getCurrentUser().getEmail())
                        .set(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                binding.etQ1.setText("");
                                binding.etQ2.setText("");
                                binding.etQ3.setText("");
                                binding.etQ4.setText("");
                                binding.etQ5.setText("");
                                binding.etQ6.setText("");
                                binding.etQ7.setText("");
                                binding.etQ8.setText("");
                                Toast.makeText(getActivity(), "Form Submitted", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFeedbackBinding.inflate(getLayoutInflater());

        init();

        return binding.getRoot();
    }

}