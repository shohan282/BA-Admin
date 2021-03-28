package com.example.baadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.baadmin.databinding.ActivityMainBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        db = FirebaseFirestore.getInstance();

        binding.hospital.setOnClickListener(v -> {

            Map<String, String> user = new HashMap<>();
            user.put("cname", "Hospital");

            db.collection("check").document("check").set(user).addOnSuccessListener(documentReference -> {

                Intent intent = new Intent(MainActivity.this,HpHome.class);
                startActivity(intent);

            }).addOnFailureListener(e -> Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show());

        });

        binding.library.setOnClickListener(v -> {

            Map<String, String> user = new HashMap<>();
            user.put("cname", "Library");

            db.collection("check").document("check").set(user).addOnSuccessListener(documentReference -> {

                Intent intent = new Intent(MainActivity.this,LibHome.class);
                startActivity(intent);

            }).addOnFailureListener(e -> Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show());

        });

        binding.movie.setOnClickListener(v -> {

            Map<String, String> user = new HashMap<>();
            user.put("cname", "Movie");

            db.collection("check").document("check").set(user).addOnSuccessListener(documentReference -> {

                Intent intent = new Intent(MainActivity.this,MovHome.class);
                startActivity(intent);

            }).addOnFailureListener(e -> Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show());

        });

        binding.barbar.setOnClickListener(v -> {

            Map<String, String> user = new HashMap<>();
            user.put("cname", "Barbar");

            db.collection("check").document("check").set(user).addOnSuccessListener(documentReference -> {

                Intent intent = new Intent(MainActivity.this,BrHome.class);
                startActivity(intent);

            }).addOnFailureListener(e -> Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show());

        });

    }
}