package com.example.agecalculator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    private TextView AgeYears, AgeMonths, AgeDays;
    private TextView AgeTotalYears, AgeTotalMonths, AgeTotalWeeks;
    private TextView AgeTotalDays, AgeTotalHours;
    private TextView AgeTotalMinutes, AgeTotalSeconds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();

        String dob = intent.getStringExtra(MainActivity.EXTRA_DOB);
        String currentDate = intent.getStringExtra(MainActivity.EXTRA_CURRENT_DATE);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate birthDate = LocalDate.parse(dob, formatter);

        LocalDate today = LocalDate.parse(currentDate, formatter);

        Period age = Period.between(birthDate, today);

        long totalMonths = ChronoUnit.MONTHS.between(birthDate, today);
        long totalWeeks = ChronoUnit.WEEKS.between(birthDate, today);
        long totalDays = ChronoUnit.DAYS.between(birthDate, today);

        long totalHours = totalDays * 24;
        long totalMinutes = totalHours * 60;
        long totalSeconds = totalMinutes * 60;

        AgeYears = findViewById(R.id.AgeYears);
        AgeMonths = findViewById(R.id.AgeMonths);
        AgeDays = findViewById(R.id.AgeDays);

        AgeTotalYears = findViewById(R.id.AgeTotalYears);
        AgeTotalMonths = findViewById(R.id.AgeTotalMonths);
        AgeTotalWeeks = findViewById(R.id.AgeTotalWeeks);
        AgeTotalDays = findViewById(R.id.AgeTotalDays);
        AgeTotalHours = findViewById(R.id.AgeTotalHours);
        AgeTotalMinutes = findViewById(R.id.AgeTotalMinutes);
        AgeTotalSeconds = findViewById(R.id.AgeTotalSeconds);

        AgeYears.setText(String.valueOf(age.getYears()));
        AgeMonths.setText(String.valueOf(age.getMonths()));
        AgeDays.setText(String.valueOf(age.getDays()));

        AgeTotalYears.setText(String.valueOf(age.getYears()));
        AgeTotalMonths.setText(String.valueOf(totalMonths));
        AgeTotalWeeks.setText(String.valueOf(totalWeeks));
        AgeTotalDays.setText(String.valueOf(totalDays));
        AgeTotalHours.setText(String.valueOf(totalHours));
        AgeTotalMinutes.setText(String.valueOf(totalMinutes));
        AgeTotalSeconds.setText(String.valueOf(totalSeconds));
    }

    public void calculateAgain(View view) {
        finish();
    }
}