package com.example.mylawyerappadmin.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mylawyerappadmin.R;
import com.example.mylawyerappadmin.databinding.ActivityDetailsAndApprovalBinding;
import com.example.mylawyerappadmin.models.ModelServiceProvider;
import com.example.mylawyerappadmin.utils.Constants;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailsAndApprovalActivity extends BaseActivity {
    ActivityDetailsAndApprovalBinding binding;
    ModelServiceProvider user;
    DatabaseReference ref;
    Boolean userApproved;
    String forWhat;
    int EMAIL_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details_and_approval);
        user = (ModelServiceProvider) getIntent().getExtras().get("user");
        ref = FirebaseDatabase.getInstance().getReference();
        try{
            forWhat = getIntent().getExtras().getString("forwhat");
            if (forWhat.equals("Assignment")){
                initViewsForAssignment();
            }
        }
        catch (Exception e){
            initViews();
        }
    }

    private void initViewsForAssignment() {
        userApproved = user.getApproved();
        if (userApproved){
            binding.approveBtn.setText("Delegate job to this user");
        }
        binding.name.setText(user.getFullname());
        binding.addressTv.setText(user.getAddress());
        binding.genderTv.setText(user.getGender());
        binding.chamberTv.setText(user.getChamber());
        binding.rollCallNumberTv.setText(user.getRoll_call_number());
        binding.stateJustificationTv.setText(user.getState_justification());
        binding.yearsOfExperienceTv.setText(""+user.getYears_of_exp());
        binding.phoneNumberTv.setText(user.getPhone_number());
        Glide.with(getApplicationContext()).load(user.getCor_profile()).into(binding.ivProfile);

        binding.approveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email= new Intent(Intent.ACTION_SENDTO);
                email.setData(Uri.parse("mailto:"+user.getEmail()));
                email.putExtra(Intent.EXTRA_SUBJECT, "Assignment of task from MyLawyerApp");
                email.putExtra(Intent.EXTRA_TEXT, "Congrats!!! you have been assigned a new task. Please check your notfications \n" +
                        "on our application to accept or reject the task. We wish you best of lucks.\n"
                +"Best Regards\n"+
                "MyLaywerApp team");
                //email.setType("text/html");
                //email.setPackage("com.google.android.gm");
                startActivityForResult(email, EMAIL_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        if (resultCode == RESULT_CANCELED){
            if (requestCode == EMAIL_CODE){
                sendNotificationToUser();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void sendNotificationToUser() {
        Log.d("DetailsAcitivity", "XXXsendNotificationToUser: ");
    }

    private void initViews() {
        userApproved = user.getApproved();
        if (userApproved){
            binding.approveBtn.setText("Disapprove");
        }
        binding.name.setText(user.getFullname());
        binding.addressTv.setText(user.getAddress());
        binding.genderTv.setText(user.getGender());
        binding.chamberTv.setText(user.getChamber());
        binding.rollCallNumberTv.setText(user.getRoll_call_number());
        binding.stateJustificationTv.setText(user.getState_justification());
        binding.yearsOfExperienceTv.setText(""+user.getYears_of_exp());
        binding.phoneNumberTv.setText(user.getPhone_number());
        Glide.with(getApplicationContext()).load(user.getCor_profile()).into(binding.ivProfile);

        binding.approveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userApproved = !userApproved;
                if (userApproved){
                    Toast.makeText(DetailsAndApprovalActivity.this, "Approving user....", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(DetailsAndApprovalActivity.this, "Disapproving user", Toast.LENGTH_SHORT).show();
                }
                user.setApproved(userApproved);
                ref.child("ServiceProvider").child(user.getCategory()).child(user.getUid()).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        if (userApproved){
                            Toast.makeText(DetailsAndApprovalActivity.this, "User Approved!!", Toast.LENGTH_SHORT).show();
                            binding.approveBtn.setText("Disapprove");
                        }
                        else{
                            Toast.makeText(DetailsAndApprovalActivity.this, "User Disapproved!!", Toast.LENGTH_SHORT).show();
                            binding.approveBtn.setText("Approve");
                        }
                    }
                });
            }
        });
    }


}