package com.example.mylawyerappadmin.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mylawyerappadmin.R;
import com.example.mylawyerappadmin.activities.AllUsersActivity;
import com.example.mylawyerappadmin.databinding.FragmentServiceProvidersBinding;

public class ServiceProvidersFragment extends Fragment {
    FragmentServiceProvidersBinding binding;
    public ServiceProvidersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_service_providers, container, false);
        setOnclickListeners();
        return binding.getRoot();
    }

    /*""Criminal Law, "Civil Law", "Human Rights", "Maritime", "Corporate Affairs",
                    "International and Cross Border Legal", "Debt  Recoveries", "Litigation Services", "Offshore Legal Representation",
            "Real Estate Solutions", "Title Documents Registration", "Cross Border Real Estate Services", "Purchase of land for Nigerians in the Diasporas",
            "Company Secretarial Services", "Maritime and International Trade Laws", "Legal drafting and contract documentations", "Family Law",
            "Matrimonial Courses and Child Right Laws", "Human Rights matter", "Intellectual Property Law", "Legal Opinions on all areas of law",
            "pro bono services", "Entertainment law", "Sports law"*/
    private void startAllUsersListActivity(String category){
        Intent i = new Intent(getActivity(), AllUsersActivity.class);
        i.putExtra("category", category);
        startActivity(i);
    }

    public void setOnclickListeners() {
        binding.criminalLaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllUsersListActivity("Criminal Law");
            }
        });
        binding.humanRights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllUsersListActivity("Human Rights");
            }
        });
        binding.maritime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllUsersListActivity("Maritime");
            }
        });
        binding.cororateAffairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllUsersListActivity("Corporate Affairs");
            }
        });
        binding.internationalCrossBoroder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllUsersListActivity("International and Cross Border Legal");
            }
        });
        binding.debtRecoveries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllUsersListActivity("Debt  Recoveries");
            }
        });
        binding.litigationServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllUsersListActivity("Litigation Services");
            }
        });
        binding.offshoreLegalRepresentation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllUsersListActivity("Offshore Legal Representation");
            }
        });
        binding.companySecretarialServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllUsersListActivity("Company Secretarial Services");
            }
        });
        binding.realEstateSolutions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllUsersListActivity("Real Estate Solutions");
            }
        });
        binding.titleDocumentsRegistrations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllUsersListActivity("Title Documents Registration");
            }
        });
        binding.crossBorderRealEstateServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllUsersListActivity("Cross Border Real Estate Services");
            }
        });
        binding.purchaseOfLandForNigeriansInTheDiasporas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllUsersListActivity("Purchase of land for Nigerians in the Diasporas");
            }
        });
        binding.maritimeAndInternationalTradeLaws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllUsersListActivity("Maritime and International Trade Laws");
            }
        });
        binding.legalDraftingAndContractDoocumentation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllUsersListActivity("Legal drafting and contract documentations");
            }
        });
        binding.familyLaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllUsersListActivity("Family Law");
            }
        });
        binding.matrimonialCoursesAndChildRightLaws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllUsersListActivity("Matrimonial Courses and Child Right Laws");
            }
        });
        binding.humanRightsMatters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllUsersListActivity("Human Rights matter");
            }
        });
        binding.intellectualPropertyLaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllUsersListActivity("Intellectual Property Law");
            }
        });
        binding.legalOpinionsOnAllAreasOfLaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllUsersListActivity("Legal Opinions on all areas of law");
            }
        });
        binding.proBonoServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllUsersListActivity("pro bono services");
            }
        });
        binding.entertainmentLaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllUsersListActivity("Entertainment law");
            }
        });
        binding.sportsLaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllUsersListActivity("Sports law");
            }
        });
    }
}