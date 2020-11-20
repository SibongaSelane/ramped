package com.deitel.littlethinkers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.deitel.littlethinkers.LearningNumbers.zeroToSixNumbers;

public class NumbersActivity extends AppCompatActivity {

    Button btnLearnNumbers, btnActivity, btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        btnLearnNumbers = findViewById(R.id.btnLearnNumbers);
        btnActivity = findViewById(R.id.btnActivity);
        btnMenu= findViewById(R.id.btnMenu);

        LearningNumbers();
        NumbersGame();
        Menu();
    }

    public void LearningNumbers(){
        btnLearnNumbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        Intent loginInt = new Intent(NumbersActivity.this, zeroToSixNumbers.class);
                        startActivity(loginInt);

            }
        });
    }

    public void NumbersGame(){
        btnActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent actNumbers = new Intent(NumbersActivity.this, ActivityPage.class);
                        startActivity(actNumbers);
            }
        });
    }

    public void Menu(){
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu = new Intent(NumbersActivity.this, StudentHomeActivity.class);
                startActivity(menu);
            }
        });
    }
}
