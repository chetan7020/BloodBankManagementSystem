package com.safar.bloodbankmanagementsystem.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.safar.bloodbankmanagementsystem.databinding.FragmentFeedbackBinding;
import com.safar.bloodbankmanagementsystem.databinding.FragmentProfileBinding;

public class FeedbackFragment extends Fragment {

    private FragmentFeedbackBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFeedbackBinding.inflate(getLayoutInflater());


        return binding.getRoot();
    }

}