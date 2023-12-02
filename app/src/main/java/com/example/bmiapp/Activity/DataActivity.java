package com.example.bmiapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bmiapp.Database.DataBase;
import com.example.bmiapp.R;

import java.net.URL;

public class DataActivity extends AppCompatActivity {

    private android.widget.Button caculatorAgain, goWed;
    private RelativeLayout homeBtn, settingsBtn;
    private TextView bmiValue, textInfo, textReview;
    private Intent intent;
    private String sex;
    private int height, weight, age;
    private float floatHeight, floatWeight, floatBMI;
    private String info, review, colorReview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        caculatorAgain = findViewById(R.id.caculatorBtn);
        homeBtn = findViewById(R.id.homeBtn);
        settingsBtn = findViewById(R.id.settingsBtn);
        bmiValue = findViewById(R.id.valuebmi);
        textInfo = findViewById(R.id.info);
        textReview = findViewById(R.id.textreview);
        goWed = findViewById(R.id.wed);


        height = DataBase.getValue(DataActivity.this, "currentHeight", 170);
        weight = DataBase.getValue(DataActivity.this, "currentWeight", 70);
        age = DataBase.getValue(DataActivity.this, "currentAge", 20);
        sex = DataBase.getValue(DataActivity.this, "sex", "Male");

        floatBMI = DataBase.getValue(DataActivity.this, "valueBMI", 24.22f);
        info = DataBase.getValue(DataActivity.this, "info", "Female | 20y | 170Cm | 70Kg");
        review = DataBase.getValue(DataActivity.this, "review", "Normal");

        floatHeight = Float.parseFloat(String.valueOf(height));
        floatWeight = Float.parseFloat(String.valueOf(weight));
        floatHeight/=100;
        floatBMI = floatWeight/(floatHeight*floatHeight);
        info = sex + " | " + age + "y | " + height + "Cm | " + weight +"Kg";
        DataBase.saveValue(DataActivity.this,"valueBMI",floatBMI);
        DataBase.saveValue(DataActivity.this,"info",info);
        setReview();
        bmiValue.setText(String.format("%.2f",floatBMI));
        textInfo.setText(info);
        textReview.setText(review);
        textReview.setTextColor(Color.parseColor(colorReview));


        caculatorAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DataActivity.this, MainActivity.class));
                finish();
            }
        });

        goWed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "https://www.calculator.net/bmi-calculator.html";
                try {
                    Uri uri = Uri.parse(link);
                    startActivity(new Intent(Intent.ACTION_VIEW,uri));
                }
                catch (Exception e) {}
            }
        });


        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DataActivity.this, MainActivity.class));
                finish();
            }
        });


        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DataActivity.this, SettingsActivity.class));
                finish();
            }
        });
    }

    private void setReview() {
        if(floatBMI < 16f) {
            review = "Severe Thinness";
            colorReview = "#3333FF";
        }
        else if (floatBMI<17) {
            review = "Moderate Thinness";
            colorReview = "#3333FF";
        }
        else if (floatBMI<18.5) {
            review = "Mild Thinness";
            colorReview = "#3333FF";
        }
        else if (floatBMI<25) {
            review = "Normal";
            colorReview = "#00EE00";
        }
        else if (floatBMI<30) {
            review = "Overweight";
            colorReview = "#FFA500";
        }
        else if (floatBMI<35) {
            review = "Obese Class I";
            colorReview = "#FFA500";
        }
        else if (floatBMI<40) {
            review = "Obese Class II";
            colorReview = "#FFA500";
        }
        else {
            review = "Obese Class III";
            colorReview = "#FFA500";
        }

    }
}