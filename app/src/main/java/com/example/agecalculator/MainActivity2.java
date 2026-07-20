package com.example.agecalculator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;

public class MainActivity2 extends AppCompatActivity {

    private TextView txtHeroTitle;
    private TextView txtHeroYears;
    private TextView txtHeroSummary;
    private TextView txtYears;
    private TextView txtMonths;
    private TextView txtDays;
    private TextView txtTotalMonths;
    private TextView txtTotalWeeks;
    private TextView txtTotalDays;
    private TextView txtTotalHours;
    private TextView txtTotalMinutes;
    private TextView txtTotalSeconds;
    private TextView txtNextBirthdayDate;
    private TextView txtBirthdayCountdown;
    private Period age;

    private long totalMonths;
    private long totalWeeks;
    private long totalDays;

    private LocalDate nextBirthday;
    private long daysRemaining;

    // Animatio variables
    private MaterialCardView heroCard;
    private MaterialCardView breakdownCard;
    private MaterialCardView totalAgeCard;
    private MaterialCardView nextBirthdayCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        Intent intent = getIntent();
        String dob = intent.getStringExtra(MainActivity.EXTRA_DOB);
        String currentDate = intent.getStringExtra(MainActivity.EXTRA_CURRENT_DATE);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate birthDate = LocalDate.parse(dob, formatter);

        LocalDate today = LocalDate.parse(currentDate, formatter);

        nextBirthday = birthDate.withYear(today.getYear());

        if (nextBirthday.isBefore(today)) {
            nextBirthday = nextBirthday.plusYears(1);
        }

        daysRemaining = ChronoUnit.DAYS.between(today, nextBirthday);

        DateTimeFormatter birthdayFormatter =
                DateTimeFormatter.ofPattern("dd MMMM yyyy");


        age = Period.between(birthDate, today);

        totalMonths = ChronoUnit.MONTHS.between(birthDate, today);
        totalWeeks = ChronoUnit.WEEKS.between(birthDate, today);
        totalDays = ChronoUnit.DAYS.between(birthDate, today);

        long totalHours = totalDays * 24;
        long totalMinutes = totalHours * 60;
        long totalSeconds = totalMinutes * 60;

        txtHeroTitle = findViewById(R.id.txtHeroTitle);
        txtHeroYears = findViewById(R.id.txtHeroYears);
        txtHeroSummary = findViewById(R.id.txtHeroSummary);
        txtYears = findViewById(R.id.txtYears);
        txtMonths = findViewById(R.id.txtMonths);
        txtDays = findViewById(R.id.txtDays);
        txtNextBirthdayDate = findViewById(R.id.txtNextBirthdayDate);
        txtBirthdayCountdown = findViewById(R.id.txtBirthdayCountdown);

//        Animation
        heroCard = findViewById(R.id.heroCard);
        heroCard = findViewById(R.id.heroCard);
        breakdownCard = findViewById(R.id.breakdownCard);
        totalAgeCard = findViewById(R.id.totalAgeCard);
        nextBirthdayCard = findViewById(R.id.nextBirthdayCard);
        heroCard.setAlpha(0f);
        breakdownCard.setAlpha(0f);
        totalAgeCard.setAlpha(0f);
        nextBirthdayCard.setAlpha(0f);


        txtYears.setText(String.valueOf(age.getYears()));
        txtMonths.setText(String.valueOf(age.getMonths()));
        txtDays.setText(String.valueOf(age.getDays()));


        txtTotalMonths = findViewById(R.id.txtTotalMonths);
        txtTotalWeeks = findViewById(R.id.txtTotalWeeks);
        txtTotalDays = findViewById(R.id.txtTotalDays);
        txtTotalHours = findViewById(R.id.txtTotalHours);
        txtTotalMinutes = findViewById(R.id.txtTotalMinutes);
        txtTotalSeconds = findViewById(R.id.txtTotalSeconds);


        txtHeroTitle.setText("Your Age");

        txtHeroYears.setText(String.valueOf(age.getYears()));

        txtHeroSummary.setText(
                age.getYears() + " years • "
                        + age.getMonths() + " months •"
                        + age.getDays() + " days"
        );

        txtTotalMonths.setText(String.format("%,d", totalMonths));
        txtTotalWeeks.setText(String.format("%,d", totalWeeks));
        txtTotalDays.setText(String.format("%,d", totalDays));
        txtTotalHours.setText(String.format("%,d", totalHours));
        txtTotalMinutes.setText(String.format("%,d", totalMinutes));
        txtTotalSeconds.setText(String.format("%,d", totalSeconds));

        txtNextBirthdayDate.setText(
                nextBirthday.format(birthdayFormatter)
        );

        if (daysRemaining == 0) {
            txtBirthdayCountdown.setText("🎉 Happy Birthday!");
        } else if (daysRemaining == 1) {
            txtBirthdayCountdown.setText("1 day remaining");
        } else {
            txtBirthdayCountdown.setText(daysRemaining + " days remaining");
        }


        Animation heroAnimation =
                AnimationUtils.loadAnimation(this, R.anim.hero_card_enter);

        heroCard.setAlpha(0f);
        heroCard.setTranslationY(40);

        breakdownCard.setAlpha(0f);
        breakdownCard.setTranslationY(40);

        totalAgeCard.setAlpha(0f);
        totalAgeCard.setTranslationY(40);

        nextBirthdayCard.setAlpha(0f);
        nextBirthdayCard.setTranslationY(40);

        heroCard.startAnimation(heroAnimation);

        heroCard.animate()
                .alpha(1f)
                .translationY(0)
                .setDuration(500)
                .start();

        breakdownCard.setTranslationY(40);

        breakdownCard.animate()
                .alpha(1f)
                .translationY(0)
                .setStartDelay(150)
                .setDuration(500)
                .start();

        totalAgeCard.setTranslationY(40);

        totalAgeCard.animate()
                .alpha(1f)
                .translationY(0)
                .setStartDelay(300)
                .setDuration(500)
                .start();

        nextBirthdayCard.setTranslationY(40);

        nextBirthdayCard.animate()
                .alpha(1f)
                .translationY(0)
                .setStartDelay(450)
                .setDuration(500)
                .start();
    }

    private void shareAgeDetails() {

        String message =
                "🎉 My Age Details\n\n" +

                        "👤 Age\n" +
                        age.getYears() + " Years, " +
                        age.getMonths() + " Months, " +
                        age.getDays() + " Days\n\n" +

                        "📅 Total Age\n" +
                        "• " + String.format("%,d", totalMonths) + " Months\n" +
                        "• " + String.format("%,d", totalWeeks) + " Weeks\n" +
                        "• " + String.format("%,d", totalDays) + " Days\n\n" +

                        "🎂 Next Birthday\n" +
                        nextBirthday.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")) +
                        "\n" +
                        daysRemaining + " days remaining\n\n" +

                        "📱 Generated using Age Calculator";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, message);

        startActivity(Intent.createChooser(intent, "Share via"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        if (item.getItemId() == R.id.action_share) {

            shareAgeDetails();
        }

        return super.onOptionsItemSelected(item);
    }
    public void calculateAgain(View view) {
        finish();
    }
}