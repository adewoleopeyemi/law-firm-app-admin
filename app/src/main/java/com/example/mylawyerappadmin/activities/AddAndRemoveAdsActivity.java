package com.example.mylawyerappadmin.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mylawyerappadmin.R;
import com.example.mylawyerappadmin.databinding.ActivityAddAdsBinding;
import com.example.mylawyerappadmin.models.ModelAds;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

public class AddAndRemoveAdsActivity extends AppCompatActivity {
    ActivityAddAdsBinding binding;
    public static final int SELECT_IMAGE = 100;
    Uri imageUri;
    ArrayList<ModelAds> allAds = new ArrayList();
    String forWhat;
    ModelAds ad;
    boolean adRemoved = false;
    //boolean

    //TODO Work on update ad logic well test it oout throughly
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_ads);
        try{
            forWhat = getIntent().getExtras().getString("for");
            ad = (ModelAds) getIntent().getExtras().get("ad");
            initViews();
        }
        catch (Exception e){
            initOnClickListeners();
        }
    }

    private void initViews() {
        binding.orgnizationName.setFocusable(false);
        binding.orgnizationEmail.setText(ad.getOffical_email_of_organization());
        binding.orgnizationPhoneNumber.setText(ad.getOfficial_phone_number());
        binding.description.setText(ad.getDescription());
        Glide.with(getApplicationContext()).load(ad.getImagePath()).into(binding.ivAds);
        binding.orgnizationName.setText(ad.getName_of_organization());
        binding.pushAd.setText("Remove Advert");
        binding.updateAd.setVisibility(View.VISIBLE);
        binding.ivAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_IMAGE);
            }
        });
        binding.pushAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adRemoved){
                    if(imageUri == null){
                        Toast.makeText(AddAndRemoveAdsActivity.this, "Please select an image", Toast.LENGTH_SHORT).show();
                    }
                    else if (binding.description.getText().toString().isEmpty()){
                        Toast.makeText(AddAndRemoveAdsActivity.this, "Please enter a descrption about advert", Toast.LENGTH_SHORT).show();
                    }
                    else if (binding.orgnizationEmail.getText().toString().isEmpty()){
                        Toast.makeText(AddAndRemoveAdsActivity.this, "Please enter the organization's email", Toast.LENGTH_SHORT).show();
                    }
                    else if (binding.orgnizationName.getText().toString().isEmpty()){
                        Toast.makeText(AddAndRemoveAdsActivity.this, "Please enter the organization's name", Toast.LENGTH_SHORT).show();
                    }
                    else if (binding.orgnizationPhoneNumber.getText().toString().isEmpty()){
                        Toast.makeText(AddAndRemoveAdsActivity.this, "Please enter the organization's phone number", Toast.LENGTH_SHORT).show();
                    }

                    else{
                        Toast.makeText(AddAndRemoveAdsActivity.this, "Pushing ad", Toast.LENGTH_SHORT).show();
                        uploadAdvert(imageUri);
                    }
                }
                else{
                    Toast.makeText(AddAndRemoveAdsActivity.this, "Removing Advert", Toast.LENGTH_SHORT).show();
                    removeAd();
                }
            }
        });
        binding.updateAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAd();
            }
        });
    }

    private void removeAd(){
        FirebaseDatabase.getInstance().getReference()
                .child("Adverts")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        for (DataSnapshot data: snapshot.getChildren()){
                            ModelAds ads = data.getValue(ModelAds.class);
                            if (ads.getName_of_organization().equals(ad.getName_of_organization())){
                                String key = data.getKey();
                                ads.setExpired(true);
                                FirebaseDatabase.getInstance().getReference().child("Adverts")
                                        .child(key)
                                        .setValue(ads)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                adRemoved = true;
                                                binding.pushAd.setText("Push Advert");
                                                Toast.makeText(AddAndRemoveAdsActivity.this, "Ad removed", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                        adRemoved =false;
                    }
                });
    }


    private void initOnClickListeners() {
        binding.ivAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_IMAGE);
            }
        });

        binding.pushAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUri == null){
                    Toast.makeText(AddAndRemoveAdsActivity.this, "Please select an image", Toast.LENGTH_SHORT).show();
                }
                else if (binding.description.getText().toString().isEmpty()){
                    Toast.makeText(AddAndRemoveAdsActivity.this, "Please enter a descrption about advert", Toast.LENGTH_SHORT).show();
                }
                else if (binding.orgnizationEmail.getText().toString().isEmpty()){
                    Toast.makeText(AddAndRemoveAdsActivity.this, "Please enter the organization's email", Toast.LENGTH_SHORT).show();
                }
                else if (binding.orgnizationName.getText().toString().isEmpty()){
                    Toast.makeText(AddAndRemoveAdsActivity.this, "Please enter the organization's name", Toast.LENGTH_SHORT).show();
                }
                else if (binding.orgnizationPhoneNumber.getText().toString().isEmpty()){
                    Toast.makeText(AddAndRemoveAdsActivity.this, "Please enter the organization's phone number", Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(AddAndRemoveAdsActivity.this, "Pushing ad", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void uploadAdvert(Uri imageUri) {
        long UploadTime = System.currentTimeMillis();
        ModelAds ad = new ModelAds();
        ad.setDescription(binding.description.getText().toString());
        ad.setName_of_organization(binding.orgnizationName.getText().toString());
        ad.setOffical_email_of_organization(binding.orgnizationEmail.getText().toString());
        ad.setOfficial_phone_number(binding.orgnizationPhoneNumber.getText().toString());
        ad.setExpired(false);
        StorageReference ref = FirebaseStorage.getInstance().getReference();

        ref.child("Adverts").child(""+UploadTime).putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                       FirebaseStorage.getInstance().getReference().child("Adverts").child(""+UploadTime)
                               .getDownloadUrl()
                               .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                   @Override
                                   public void onSuccess(Uri uri) {
                                       ad.setImagePath(uri.toString());
                                       DatabaseReference dbref =FirebaseDatabase.getInstance().getReference();
                                       dbref.child("Adverts").child(""+UploadTime)
                                               .setValue(ad)
                                               .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                   @Override
                                                   public void onSuccess(Void unused) {
                                                       Toast.makeText(AddAndRemoveAdsActivity.this, "Advert pushed successfully", Toast.LENGTH_SHORT).show();
                                                        adRemoved = false;
                                                        binding.pushAd.setText("Remove Ad");
                                                   }
                                               })
                                       .addOnFailureListener(new OnFailureListener() {
                                           @Override
                                           public void onFailure(@NonNull @NotNull Exception e) {
                                               Toast.makeText(AddAndRemoveAdsActivity.this, "error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                                adRemoved = true;
                                           }
                                       });
                                   }
                               });
                    }
                });
    }

    private void updateAd(){
        ad.setDescription(binding.description.getText().toString());
        ad.setOffical_email_of_organization(binding.orgnizationEmail.getText().toString());
        ad.setOfficial_phone_number(binding.orgnizationPhoneNumber.getText().toString());
        FirebaseDatabase.getInstance().getReference()
                .child("Adverts")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        for (DataSnapshot data: snapshot.getChildren()){
                            ModelAds ads = data.getValue(ModelAds.class);
                            if (ads.getName_of_organization().equals(ad.getName_of_organization())){
                                String key = data.getKey();
                                if (imageUri != null){
                                    FirebaseStorage.getInstance().getReference()
                                            .child("Adverts")
                                            .child(key)
                                            .putFile(imageUri)
                                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                @Override
                                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                    FirebaseStorage.getInstance().getReference()
                                                    .child("Adverts")
                                                    .child(key)
                                                    .getDownloadUrl()
                                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                        @Override
                                                        public void onSuccess(Uri uri) {
                                                            ad.setImagePath(uri.toString());
                                                            FirebaseDatabase.getInstance().getReference().child("Adverts")
                                                                    .child(key)
                                                                    .setValue(ad)
                                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void unused) {
                                                                            Toast.makeText(AddAndRemoveAdsActivity.this, "Ad updated", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    });
                                                        }
                                                    });
                                                }
                                            });
                                }
                                else{
                                    FirebaseDatabase.getInstance().getReference().child("Adverts")
                                            .child(key)
                                            .setValue(ad)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(AddAndRemoveAdsActivity.this, "Ad updated", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                                //uploadAdvert(imageUri);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                        adRemoved =false;
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                        binding.ivAds.setImageBitmap(bitmap);
                        imageUri = data.getData();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED)  {
                Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}