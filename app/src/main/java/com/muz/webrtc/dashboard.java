package com.muz.webrtc;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.muz.webrtc.databinding.ActivityDashboardBinding;

public class dashboard extends AppCompatActivity {

    BottomNavigationView bnv;
    ProfileFragment profileFragment = new ProfileFragment();
    CallFragment callFragment = new CallFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        bnv = findViewById(R.id.bottomNavigationView);
        //getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,profileFragment).commit();
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.profile) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,profileFragment).commit();
                    return true;
                } else if (id == R.id.call) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,callFragment).commit();
                    return true;
                }

                return false;
            }
        });

    }
}