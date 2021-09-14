package com.example.mylawyerappadmin.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;

import com.example.mylawyerappadmin.R;
import com.example.mylawyerappadmin.adapters.AdapterSingleUser;
import com.example.mylawyerappadmin.databinding.ActivityAllUsersBinding;
import com.example.mylawyerappadmin.models.ModelServiceProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import okhttp3.internal.cache.DiskLruCache;

public class AllUsersActivity extends BaseActivity {
    ActivityAllUsersBinding binding;
    String category;
    ArrayList<ModelServiceProvider> allUsers = new ArrayList<>();
    ProgressDialog pd;
    Boolean forAssignment = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_users);
        category = getIntent().getExtras().getString("category");
        try{
            forAssignment = getIntent().getExtras().getBoolean("forAssigment");
        }
        catch (Exception e){

        }
        if (forAssignment){

        }
        pd = new ProgressDialog(this);
        pd.setMessage("Loading please wait....");
        pd.setCancelable(false);
        pd.show();
        fetchAllUsers(category);

    }

    private void fetchAllUsers(String category) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("ServiceProvider").child(category).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot data: snapshot.getChildren()){
                    Log.d("AllUsersActivity", "XonDataChange: "+data.getValue().toString());
                        ModelServiceProvider user = data.getValue(ModelServiceProvider.class);
                        allUsers.add(user);
                }
                setUpAllUsersRv();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private void setUpAllUsersRv() {
        AdapterSingleUser adapterSingleUser = new AdapterSingleUser(getApplicationContext(), allUsers, forAssignment);
        binding.allUsersRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.allUsersRv.setAdapter(adapterSingleUser);
        pd.dismiss();
    }
}