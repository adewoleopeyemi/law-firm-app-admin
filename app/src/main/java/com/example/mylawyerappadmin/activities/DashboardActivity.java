package com.example.mylawyerappadmin.activities;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.mylawyerappadmin.R;
import com.example.mylawyerappadmin.activities.BaseActivity;
import com.example.mylawyerappadmin.adapters.MainPagerAdapter;
import com.example.mylawyerappadmin.databinding.ActivityDashboardBinding;

public class DashboardActivity extends BaseActivity {
    ActivityDashboardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        binding.mainPager.setAdapter(adapter);
        binding.tablayout.setupWithViewPager(binding.mainPager);
    }
}