package com.example.mylawyerappadmin.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylawyerappadmin.R;
import com.example.mylawyerappadmin.activities.AddAndRemoveAdsActivity;
import com.example.mylawyerappadmin.adapters.AdapterSingleAd;
import com.example.mylawyerappadmin.databinding.FragmentAdvertsBinding;
import com.example.mylawyerappadmin.models.ModelAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdvertsFragment extends Fragment {
    FragmentAdvertsBinding binding;
    ArrayList<ModelAds> allAds= new ArrayList();
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_adverts, container, false);
        binding.addAdverts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCreateAdActivity();
            }
        });
        initRv();
        return binding.getRoot();
    }

    private void initRv() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                ref.child("Adverts")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        allAds.clear();
                        for (DataSnapshot ad: snapshot.getChildren()){
                            ModelAds singleAd = ad.getValue(ModelAds.class);
                            allAds.add(singleAd);
                        }

                        AdapterSingleAd adapterSingleAd = new AdapterSingleAd(getContext(), allAds);
                        binding.adsRv.setLayoutManager(new LinearLayoutManager(getContext()));
                        binding.adsRv.setAdapter(adapterSingleAd);
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });
    }

    @Override
    public void onDetach() {
        allAds.clear();
        super.onDetach();
    }

    private void startCreateAdActivity() {
        startActivity(new Intent(getActivity(), AddAndRemoveAdsActivity.class));
    }

}