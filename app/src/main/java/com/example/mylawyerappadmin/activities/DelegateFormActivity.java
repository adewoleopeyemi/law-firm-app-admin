package com.example.mylawyerappadmin.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.mylawyerappadmin.R;
import com.example.mylawyerappadmin.databinding.ActivityDelegateFormBinding;
import com.example.mylawyerappadmin.databinding.ActivityDelegateFormBindingImpl;
import com.example.mylawyerappadmin.models.ModelForm;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class DelegateFormActivity extends BaseActivity {
    ActivityDelegateFormBinding binding;
    ModelForm form;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_delegate_form);
        form = (ModelForm) getIntent().getExtras().get("form");
        populateViews(form);
    }


    private void populateViews(ModelForm form){
        binding.name.setText(""+form.getFirst_name()+" "+form.getSurname());
        binding.countryTv.setText(""+form.getLocation());
        binding.stateTv.setText(form.getState());
        binding.emailTv.setText(form.getEmail_address());
        binding.phoneNumberTv.setText(form.getPhone_number());
        binding.typeServiceTv.setText(form.getType_of_service());
        binding.bulletPointsTv.setText(form.getBullet_point());
        binding.willingToPayTv.setText(form.getWilling_to_pay());
        binding.budget.setText(form.getBudget());
        binding.statusTv.setText(form.getStatus());
        binding.intentOfPaymentTv.setText(form.getIntend_to_pay());
        //binding.consentVariationsTv.setText(form.getCon);
        binding.readyToDepositTv.setText(form.getReady_to_deposit());
        binding.assignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFormsApprovalActivity(form);
            }
        });
        loadProfilePicture(form.getUid());

    }
    private void loadProfilePicture(String uid){
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
        ref.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot user: snapshot.getChildren()){
                    if (user.getKey() == uid){
                        ModelForm form = user.getValue(ModelForm.class);
                        Glide.with(getApplicationContext()).load(form.getUid()).placeholder(getResources().getDrawable(R.drawable.ic_baseline_person_24)).into(binding.ivProfile);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
    private void startFormsApprovalActivity(ModelForm form) {
        Intent i = new Intent(getApplicationContext(), AssignmentActivity.class);
        i.putExtra("form", form);
        startActivity(i);
    }
}