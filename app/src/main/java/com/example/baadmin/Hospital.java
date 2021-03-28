package com.example.baadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baadmin.databinding.ActivityHospitalBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Hospital extends AppCompatActivity {

    ActivityHospitalBinding binding;
    FirebaseFirestore db;
    String hp,date,location,special,time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHospitalBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        db = FirebaseFirestore.getInstance();

        binding.date.setOnClickListener(v -> showDateDialog(binding.date));

        binding.button.setOnClickListener(v -> {

            hp=binding.hospital.getSelectedItem().toString();
            special=binding.special.getSelectedItem().toString();
            date=binding.date.getText().toString();
            location=binding.location.getSelectedItem().toString();
            time=binding.time.getSelectedItem().toString();

            Map<String, String> user = new HashMap<>();
            user.put("name", hp);
            user.put("date", date);
            user.put("time", time);
            user.put("location",location);
            user.put("special",special);
            user.put("type","Hospital");

            db.collection("Hospital").document(hp+date+time+location+special)
                    .set(user)
                    .addOnSuccessListener(documentReference -> {

                        Toast.makeText(this, "added", Toast.LENGTH_SHORT).show();

                    })
                    .addOnFailureListener(e -> Toast.makeText(Hospital.this, "error", Toast.LENGTH_SHORT).show());

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

        DatePickerDialog datePickerDialog = new DatePickerDialog(Hospital.this,dateSetListener,calendar.get(calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent=new Intent(Hospital.this,HpHome.class);
        startActivity(intent);
        finish();

    }
}