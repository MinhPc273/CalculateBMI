package com.example.bmiapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.example.bmiapp.Database.DataBase;
import com.example.bmiapp.R;

public class SettingsActivity extends AppCompatActivity {

    private RelativeLayout homeBtn, dataBtn;

    private RelativeLayout aboutUsDetail, aboutBtn;

    private ImageView closeBtn;
    private Switch darkModeSW;
    private static boolean isDark;

    private boolean isAbout = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        homeBtn = findViewById(R.id.homeBtn);
        dataBtn = findViewById(R.id.dataBtn);
        darkModeSW = findViewById(R.id.darkmodeSw);
        aboutUsDetail = findViewById(R.id.detailaboutus);
        aboutBtn = findViewById(R.id.aboutusBtn);
        closeBtn = findViewById(R.id.closeBtn);

        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAbout = true;
                aboutUsDetail.setVisibility(View.VISIBLE);
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAbout = false;
                aboutUsDetail.setVisibility(View.GONE);
            }
        });

        if(isDark) {
            darkModeSW.setChecked(isDark);
        }
        else {
            darkModeSW.setChecked(isDark);
        }

        darkModeSW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAbout) {
                    darkModeSW.setChecked(isDark);
                    return;
                }

                if(isDark) {
                    isDark = false;
                    darkModeSW.setChecked(isDark);
                    DataBase.saveValue(SettingsActivity.this, "DarkMode", isDark);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                else {
                    isDark = true;
                    darkModeSW.setChecked(isDark);
                    DataBase.saveValue(SettingsActivity.this, "DarkMode", isDark);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAbout) return;
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
                finish();
            }
        });

        dataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAbout) return;
                startActivity(new Intent(SettingsActivity.this, DataActivity.class));
                finish();
            }
        });
    }

    public static boolean isDarkMode() {
        return isDark;
    }

    public static void setDarkMode(boolean tf) {
        isDark = tf;
    }
}