package com.example.baadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baadmin.databinding.ActivityLibraryBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Library extends AppCompatActivity {

    ActivityLibraryBinding binding;
    FirebaseFirestore db;
    String lb,date,location,time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLibraryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        db = FirebaseFirestore.getInstance();

        binding.date.setOnClickListener(v -> showDateDialog(binding.date));

        binding.button5.setOnClickListener(v -> {

            lb=binding.library.getSelectedItem().toString();
            date=binding.date.getText().toString();
            location=binding.location.getSelectedItem().toString();
            time=binding.time.getSelectedItem().toString();

            Map<String, String> user = new HashMap<>();
            user.put("name", lb);
            user.put("date", date);
            user.put("time", time);
            user.put("location",location);
            user.put("type","Library");

            db.collection("Library").document(lb+date+time+location)
                    .set(user)
                    .addOnSuccessListener(documentReference -> {

                        Toast.makeText(this, "added", Toast.LENGTH_SHORT).show();

                    })
                    .addOnFailureListener(e -> Toast.makeText(Library.this, "error", Toast.LENGTH_SHORT).show());

        });

    }

    private void showDateDialog(TextView date) {

        Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener= (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yy");
            date.setText(simpleDateFormat.format(calendar.getTime()));

        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(Library.this,dateSetListener,calendar.get(calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent=new Intent(Library.this,LibHome.class);
        startActivity(intent);
        finish();

    }
}