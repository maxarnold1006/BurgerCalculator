package com.missouristate.arnold.burgercaloriecalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private RadioGroup pattyRG;
    private CheckBox prosciuttoCBX;
    private RadioGroup cheeseRG;
    private SeekBar sauceSBR;
    private TextView caloriesTV;

    private Burger burger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        burger = new Burger();
        initialize();

        registerChangeListener();

    }

    private void initialize() {
        pattyRG = findViewById(R.id.radioGroup1);
        prosciuttoCBX = findViewById(R.id.checkBox);
        cheeseRG = findViewById(R.id.radioGroup2);
        sauceSBR = findViewById(R.id.seekBar);
        caloriesTV = findViewById(R.id.textView2);

        displayCalories();
    }

    private void registerChangeListener() {
        pattyRG.setOnCheckedChangeListener(foodListener);
        prosciuttoCBX.setOnClickListener(baconListener);
        cheeseRG.setOnCheckedChangeListener(foodListener);
        sauceSBR.setOnSeekBarChangeListener(sauceListener);
    }

    private OnCheckedChangeListener foodListener = new OnCheckedChangeListener() {
        public void onCheckedChanged(RadioGroup rbGroup, int radioId) {
            RadioButton rb = findViewById(radioId);
            String btn = rb.getText().toString();

            switch (btn) {
                case "Beef Patty": // BEEF PATTY
                    burger.setPattyCalories(Burger.BEEF);
                    break;
                case "Lamb Patty": // LAMB PATTY
                    burger.setPattyCalories(Burger.LAMB);
                    break;
                case "Ostrich Patty": // OSTRICH PATTY
                    burger.setPattyCalories(Burger.OSTRICH);
                    break;
                case "Asiago Cheese": // ASIAGO CHEESE
                    burger.setCheeseCalories(Burger.ASIAGO);
                    break;
                case "Creme Fraiche": // CREME FRAICHE
                    burger.setCheeseCalories(Burger.CREME_FRAICHE);
            }
            displayCalories();
        }
    };

    private OnClickListener baconListener = new OnClickListener() {
        public void onClick(View v) {
            if (((CheckBox) v).isChecked())
                burger.setProsciuttoCalories(Burger.PROSCIUTTO);
            else
                burger.clearProsciuttoCalories();

            displayCalories();
        }
    };

    private OnSeekBarChangeListener sauceListener = new OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            burger.setSauceCalories(seekBar.getProgress());
            displayCalories();
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };

    private void displayCalories() {
        String calorieText = "Calories: " + burger.getTotalCalories();
        caloriesTV.setText(calorieText);
    }
}



