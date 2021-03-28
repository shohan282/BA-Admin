package com.example.baadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.baadmin.databinding.ActivityHpHomeBinding;
import com.example.baadmin.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.HashMap;
import java.util.Map;

public class HpHome extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    ActivityHpHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHpHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.floatingActionButton.setOnClickListener(v -> {

            Intent intent = new Intent(HpHome.this,Hospital.class);
            startActivity(intent);

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