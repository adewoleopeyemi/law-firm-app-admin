package com.example.mylawyerappadmin.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mylawyerappadmin.fragments.AdvertsFragment;
import com.example.mylawyerappadmin.fragments.FormsFragment;
import com.example.mylawyerappadmin.fragments.ServiceProvidersFragment;

import org.jetbrains.annotations.NotNull;

public class MainPagerAdapter extends FragmentPagerAdapter {
    public MainPagerAdapter(@NonNull @NotNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new ServiceProvidersFragment();
        }
        else if (position == 1)
        {
            fragment = new FormsFragment();
        }
        else if (position == 2)
        {
            fragment = new AdvertsFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Sevice Providers";
        }
        else if (position == 1)
        {
            title = "Forms";
        }
        else if (position == 2)
        {
            title = "Adverts";
        }
        return title;
    }
}
