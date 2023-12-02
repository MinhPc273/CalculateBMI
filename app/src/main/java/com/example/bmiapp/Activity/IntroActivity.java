package com.example.bmiapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.bmiapp.Database.DataBase;
import com.example.bmiapp.R;

public class IntroActivity extends AppCompatActivity {

    private static final int DELAY_TIME = 1000;

    private boolean isDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        isDark = DataBase.getValue(IntroActivity.this, "DarkMode", false);
        SettingsActivity.setDarkMode(isDark);
        if(isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
                finish();
            }
        }, DELAY_TIME);
    }
}
