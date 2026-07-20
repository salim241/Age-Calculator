package com.example.agecalculator;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import androidx.core.content.FileProvider;
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

    private void shareAgeCard() {

        LayoutInflater inflater = getLayoutInflater();

        View shareView = inflater.inflate(R.layout.share_layout, null);

        TextView years = shareView.findViewById(R.id.txtShareYears);
        TextView summary = shareView.findViewById(R.id.txtShareSummary);
        TextView birthday = shareView.findViewById(R.id.txtShareBirthday);
        TextView countdown = shareView.findViewById(R.id.txtShareCountdown);

        years.setText(String.valueOf(age.getYears()));

        summary.setText(
                age.getYears() + " Years • " +
                        age.getMonths() + " Months\n •" +
                        age.getDays() + " Days"
        );

        birthday.setText(
                nextBirthday.format(
                        DateTimeFormatter.ofPattern("dd MMMM yyyy")
                )
        );

        if (daysRemaining == 0) {
            countdown.setText("🎉 Happy Birthday!");
        } else {
            countdown.setText(daysRemaining + " Days Remaining");
        }

        shareView.measure(
                View.MeasureSpec.makeMeasureSpec(1080, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        );

        shareView.layout(
                0,
                0,
                shareView.getMeasuredWidth(),
                shareView.getMeasuredHeight()
        );

        Bitmap bitmap = Bitmap.createBitmap(
                shareView.getMeasuredWidth(),
                shareView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888
        );

        Canvas canvas = new Canvas(bitmap);

        shareView.draw(canvas);

        try {

            File file = new File(getCacheDir(), "age_result.png");

            FileOutputStream outputStream = new FileOutputStream(file);
            Uri uri = FileProvider.getUriForFile(
                    this,
                    getPackageName() + ".provider",
                    file
            );

            bitmap.compress(
                    Bitmap.CompressFormat.PNG,
                    100,
                    outputStream
            );

            outputStream.flush();
            outputStream.close();

            Intent shareIntent = new Intent(Intent.ACTION_SEND);

            shareIntent.setType("image/png");

            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);

            shareIntent.addFlags(
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
            );

            startActivity(
                    Intent.createChooser(
                            shareIntent,
                            "Share Age Card"
                    )
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

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
            if (item.getItemId() == R.id.action_share) {
                shareAgeCard();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
    public void calculateAgain(View view) {
        finish();
    }
}