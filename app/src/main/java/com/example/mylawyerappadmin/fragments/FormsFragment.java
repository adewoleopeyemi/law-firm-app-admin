package com.example.mylawyerappadmin.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mylawyerappadmin.R;
import com.example.mylawyerappadmin.adapters.AdapterSingleForm;
import com.example.mylawyerappadmin.databinding.FragmentFormsBinding;
import com.example.mylawyerappadmin.models.ModelForm;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class FormsFragment extends Fragment {
    FragmentFormsBinding binding;
    ArrayList<ModelForm> forms = new ArrayList();
    ProgressDialog pd;

    public FormsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forms, container, false);
        fetchAllForms();
        return binding.getRoot();
    }

    private void fetchAllForms() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Forms").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot form: snapshot.getChildren()){
                    ModelForm singleForm = form.getValue(ModelForm.class);
                    forms.add(singleForm);
                }
                AdapterSingleForm adapter = new AdapterSingleForm(getContext(), forms);
                binding.formsRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.formsRecyclerview.setAdapter(adapter);
                binding.loading.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}