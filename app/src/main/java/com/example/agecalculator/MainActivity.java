package com.example.agecalculator;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import android.app.DatePickerDialog;
import java.util.Calendar;

import com.google.android.material.appbar.MaterialToolbar;

//import androidx.activity.EdgeToEdge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText dob;
    private EditText currentDate;
    public static final String EXTRA_DOB = "package.com.example.agecalculator.extra.DOB";

    public static final String EXTRA_CURRENT_DATE = "package.com.example.agecalculator.extra.CURRENT_DATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dob = findViewById(R.id.etdob);
        currentDate = findViewById(R.id.etcurrdate);
        setTodayDate();
        dob.setOnClickListener(v -> showDatePicker());
        currentDate.setOnClickListener(v -> showCurrentDatePicker());
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar_menu);
//        getSupportActionBar().setTitle("");

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(
                        this,
                        drawerLayout,
                        toolbar,
                        R.string.open_drawer,
                        R.string.close_drawer
                );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void showDatePicker() {

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {

                    String selectedDate = String.format(
                            "%02d/%02d/%04d",
                            selectedDay,
                            selectedMonth + 1,
                            selectedYear
                    );

                    dob.setText(selectedDate);

                },
                year,
                month,
                day
        );

        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void showCurrentDatePicker() {

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {

                    String selectedDate = String.format(
                            "%02d/%02d/%04d",
                            selectedDay,
                            selectedMonth + 1,
                            selectedYear
                    );

                    currentDate.setText(selectedDate);

                },
                year,
                month,
                day
        );

        datePickerDialog.show();
    }

    private void setTodayDate() {

        Calendar calendar = Calendar.getInstance();

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        String today = String.format("%02d/%02d/%04d", day, month, year);

        currentDate.setText(today);
    }
    public void openActivity(View view){
        Toast.makeText(this, "See Magic Now", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity2.class);
        dob = findViewById(R.id.etdob);
        EditText currentDate = findViewById(R.id.etcurrdate);
        String dobText = dob.getText().toString();
        String currentDateText = currentDate.getText().toString();
        if (dobText.isEmpty()) {
            dob.setError("Please select your Date of Birth");
            return;
        }

        if (currentDateText.isEmpty()) {
            currentDate.setError("Please select Current Date");
            return;
        }
        intent.putExtra(EXTRA_DOB, dobText);
        intent.putExtra(EXTRA_CURRENT_DATE, currentDateText);
        startActivity(intent);
    }
}