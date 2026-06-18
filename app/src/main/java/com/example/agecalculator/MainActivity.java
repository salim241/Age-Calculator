package com.example.agecalculator;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;

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

    EditText dob;
    public static final String EXTRA_NAME = "package com.example.agecalculator.extra.NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void openActivity(View view){
        Toast.makeText(this, "See Magic Now", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity2.class);
        dob = findViewById(R.id.etdob);
        String dobText = dob.getText().toString();
        intent.putExtra(EXTRA_NAME, dobText);
        startActivity(intent);
    }
}