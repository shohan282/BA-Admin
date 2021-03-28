package com.example.baadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.baadmin.databinding.ActivityHpHomeBinding;
import com.example.baadmin.databinding.ActivityLibHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LibHome extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    ActivityLibHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLibHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.floatingActionButton.setOnClickListener(v -> {

            Intent intent = new Intent(LibHome.this,Library.class);
            startActivity(intent);
            finish();

        });

        binding.navView.setOnNavigationItemSelectedListener(this);
        loadFragment(new AvailableFragment());

    }

    private boolean loadFragment (Fragment fragment) {
        if(fragment != null) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()) {

            case R.id.navigation_available:
                fragment = new AvailableFragment();
                break;

            case R.id.navigation_book:
                fragment = new BookedFragment();
                break;

        }

        return loadFragment(fragment);
    }

}