package com.example.agecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        editText = findViewById(R.id.editTextText);
//        Intent intent = getIntent();
//        String name = intent.getStringExtra(MainActivity.EXTRA_NAME);
//        editText.setText("Your DOB is : " + name);

    }

    public void calculateAgain(View view) {
        finish();
    }
}