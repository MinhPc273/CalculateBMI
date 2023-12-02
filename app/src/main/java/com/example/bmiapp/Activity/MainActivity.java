package com.example.bmiapp.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bmiapp.Database.DataBase;
import com.example.bmiapp.R;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout dataBtn, settingsBtn;
    private Button caculatorBtn;
    private RelativeLayout male, female;
    private EditText currentHeight, currentWeight, currentAge;
    private ImageView incWeight, decWeight;
    private ImageView incAge, decAge;
    private SeekBar seekBar;
    private int intHeight, intWeight, intAge;
    private String sex;
    private final int minHeight = 50, maxHeight = 300;
    private final int minWeight = 10, maxWeight = 300;
    private final int minAge = 10, maxWAge = 100;

    private final String nofHeight = "Height must be " + minHeight + " - " + maxHeight;
    private final String nofWeight = "Weight must be " + minWeight + " - " + maxWeight;
    private final String nofAge = "Age must be " + minAge + " - " + maxWAge;

    private static boolean ok;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBtn = findViewById(R.id.dataBtn);
        settingsBtn = findViewById(R.id.settingsBtn);
        caculatorBtn = findViewById(R.id.caculatorBtn);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        seekBar = findViewById(R.id.seekbarforheight);
        currentHeight = findViewById(R.id.currentheight);
        currentWeight = findViewById(R.id.currentweight);
        currentAge = findViewById(R.id.currentage);
        incWeight = findViewById(R.id.incweight);
        decWeight = findViewById(R.id.decweight);
        incAge = findViewById(R.id.incage);
        decAge = findViewById(R.id.decage);

        intHeight = DataBase.getValue(MainActivity.this, "currentHeight", 170);
        intWeight = DataBase.getValue(MainActivity.this, "currentWeight", 70);
        intAge = DataBase.getValue(MainActivity.this, "currentAge", 20);
        sex = DataBase.getValue(MainActivity.this, "sex", "Male");

        currentHeight.setText(String.valueOf(intHeight));
        currentWeight.setText(String.valueOf(intWeight));
        currentAge.setText(String.valueOf(intAge));


        dataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DataActivity.class));
                finish();
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                finish();
            }
        });

        if(sex.equals("Male")) {
            changeSex(male,female);
        }
        else {
            changeSex(female, male);
        }

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSex(male, female);
                sex = "Male";
                DataBase.saveValue(MainActivity.this, "sex", sex);
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSex(female, male);
                sex = "Female";
                DataBase.saveValue(MainActivity.this, "sex", sex);
            }
        });

        seekBar.setMin(50);
        seekBar.setMax(300);
        seekBar.setProgress(intHeight);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                intHeight = progress;
                currentHeight.setText(String.valueOf(intHeight));
                DataBase.saveValue(MainActivity.this, "currentHeight",intHeight);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        currentHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    intHeight = Integer.parseInt(s.toString());
                    intHeight = checkValidValue(intHeight,minHeight,maxHeight,nofHeight);
                    seekBar.setProgress(intHeight);
                    DataBase.saveValue(MainActivity.this, "currentHeight", intHeight);
                }catch (Exception e) {

                }
            }
        });

        currentWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    intWeight = Integer.parseInt(s.toString());
                    intWeight = checkValidValue(intWeight,minWeight,maxWeight,nofWeight);
                    DataBase.saveValue(MainActivity.this, "currentWeight", intWeight);
                }catch (Exception e) {

                }
            }
        });

        currentAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    intAge = Integer.parseInt(s.toString());
                    intAge = checkValidValue(intAge,minAge,maxWAge,nofAge);
                    DataBase.saveValue(MainActivity.this, "currentAge", intAge);
                }catch (Exception e) {

                }
            }
        });


        incWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intWeight++;
                intWeight = checkValidValue(intWeight,minWeight,maxWeight,nofWeight);
                currentWeight.setText(String.valueOf(intWeight));
                DataBase.saveValue(MainActivity.this, "currentWeight", intWeight);
            }
        });

        decWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intWeight--;
                intWeight = checkValidValue(intWeight,minWeight,maxWeight,nofWeight);
                currentWeight.setText(String.valueOf(intWeight));
                DataBase.saveValue(MainActivity.this, "currentWeight", intWeight);
            }
        });

        incAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intAge++;
                intAge = checkValidValue(intAge,minAge,maxWAge,nofAge);
                currentAge.setText(String.valueOf(intAge));
                DataBase.saveValue(MainActivity.this, "currentAge", intAge);
            }
        });

        decAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intAge--;
                intAge = checkValidValue(intAge,minAge,maxWAge,nofAge);
                currentAge.setText(String.valueOf(intAge));
                DataBase.saveValue(MainActivity.this, "currentAge", intAge);
            }
        });

        caculatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DataActivity.class);

                ok = true;

                intHeight = Integer.parseInt(currentHeight.getText().toString());
                intHeight = checkValidValue(intHeight,minHeight,maxHeight,nofHeight);
                currentHeight.setText(String.valueOf(intHeight));
                seekBar.setProgress(intHeight);

                intWeight = Integer.parseInt(currentWeight.getText().toString());
                intWeight = checkValidValue(intWeight,minWeight,maxWeight,nofWeight);
                currentWeight.setText(String.valueOf(intWeight));

                intAge = Integer.parseInt(currentAge.getText().toString());
                intAge = checkValidValue(intAge,minAge,maxWAge,nofAge);
                currentAge.setText(String.valueOf(intAge));

                DataBase.saveValue(MainActivity.this, "sex", sex);
                DataBase.saveValue(MainActivity.this, "currentAge", intAge);
                DataBase.saveValue(MainActivity.this, "currentHeight", intHeight);
                DataBase.saveValue(MainActivity.this, "currentWeight", intWeight);

                System.out.println(ok);

                if(!ok) {
                    return;
                }

                startActivity(intent);
            }
        });
    }

    private void changeSex(RelativeLayout s1, RelativeLayout s2) {
        if(SettingsActivity.isDarkMode()) {
            s1.setBackgroundResource(R.drawable.cardbackgroundboxdark);
            s2.setBackgroundResource(R.drawable.cardbackgrounddark);
        }
        else {
            s1.setBackgroundResource(R.drawable.cardbackgroundbox);
            s2.setBackgroundResource(R.drawable.cardbackground);
        }
    }

    private void setFalseForOK() {
        ok = false;
    }

    private int checkValidValue(int v, int vMin, int vMax, String s) {
        if(v < vMin || v > vMax) {
            MainActivity.ok = false;
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            if(v < vMin)
                v = vMin;
            if(v > vMax)
                v = vMax;
        }
        return v;
    }
}

