package com.example.mylawyerappadmin.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mylawyerappadmin.R;
import com.example.mylawyerappadmin.databinding.ActivityAssignmentBinding;

public class AssignmentActivity extends BaseActivity {
    ActivityAssignmentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_assignment);
        setOnclickListeners();
    }

    private void startAllUsersListActivity(String category){
        Intent i = new Intent(getApplicationContext(), AllUsersActivity.class);
        i.putExtra("category", category);
        i.putExtra("forAssigment", true);
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