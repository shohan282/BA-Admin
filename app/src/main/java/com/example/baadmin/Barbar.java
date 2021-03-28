package com.example.baadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baadmin.databinding.ActivityBarbarBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Barbar extends AppCompatActivity {

    ActivityBarbarBinding binding;
    FirebaseFirestore db;
    String bb,date,location,time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBarbarBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        db = FirebaseFirestore.getInstance();

        binding.date.setOnClickListener(v -> showDateDialog(binding.date));

        binding.button5.setOnClickListener(v -> {

            bb=binding.barbar.getSelectedItem().toString();
            date=binding.date.getText().toString();
            location=binding.location.getSelectedItem().toString();
            time=binding.time.getSelectedItem().toString();

            Map<String, String> user = new HashMap<>();
            user.put("name", bb);
            user.put("date", date);
            user.put("time", time);
            user.put("location",location);
            user.put("type","Barbar");

            db.collection("Barbar").document(bb+date+time+location)
                    .set(user)
                    .addOnSuccessListener(documentReference -> {

                        Toast.makeText(this, "added", Toast.LENGTH_SHORT).show();

                    })
                    .addOnFailureListener(e -> Toast.makeText(Barbar.this, "error", Toast.LENGTH_SHORT).show());

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

        DatePickerDialog datePickerDialog = new DatePickerDialog(Barbar.this,dateSetListener,calendar.get(calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent=new Intent(Barbar.this,BrHome.class);
        startActivity(intent);
        finish();

    }
}