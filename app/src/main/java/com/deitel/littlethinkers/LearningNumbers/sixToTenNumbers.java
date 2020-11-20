package com.deitel.littlethinkers.LearningNumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.deitel.littlethinkers.BigNumbers.NumberEight;
import com.deitel.littlethinkers.BigNumbers.NumberNine;
import com.deitel.littlethinkers.BigNumbers.NumberOne;
import com.deitel.littlethinkers.BigNumbers.NumberSeven;
import com.deitel.littlethinkers.BigNumbers.NumberSix;
import com.deitel.littlethinkers.BigNumbers.NumberTen;
import com.deitel.littlethinkers.NumbersActivity;
import com.deitel.littlethinkers.R;

import java.nio.channels.NetworkChannel;

public class sixToTenNumbers extends AppCompatActivity {

    private Button btnSix, btnSeven, btnEight, btnNine, btnTen, btnNext, btnBack, btnMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six_to_ten_numbers);

        btnSix = findViewById(R.id.btnSix);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnNine);
        btnTen = findViewById(R.id.btnTen);

        btnNext = findViewById(R.id.btnNext);
        btnMenu = findViewById(R.id.btnMenu);
        btnBack = findViewById(R.id.btnBack);

        DisplaySix();
        DisplaySeven();
        DisplayEight();
        DisplayNine();
        DisplayTen();
        Next();
        Back();
        BackToMenu();

    }

    private void BackToMenu() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu = new Intent(sixToTenNumbers.this, NumbersActivity.class);
                startActivity(menu);
            }
        });
    }


    private void DisplaySix() {
        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent number = new Intent(sixToTenNumbers.this, NumberSix.class);
                startActivity(number);
            }
        });
    }

    private void DisplaySeven() {
        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent number = new Intent(sixToTenNumbers.this, NumberSeven.class);
                startActivity(number);
            }
        });
    }

    private void DisplayEight() {
        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent number = new Intent(sixToTenNumbers.this, NumberEight.class);
                startActivity(number);
            }
        });
    }

    private void DisplayNine() {
        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent number = new Intent(sixToTenNumbers.this, NumberNine.class);
                startActivity(number);
            }
        });
    }
    private void DisplayTen() {
        btnTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent number = new Intent(sixToTenNumbers.this, NumberTen.class);
                startActivity(number);
            }
        });
    }

    private void Next() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(sixToTenNumbers.this, elevenToFifteenNumbers.class);
                startActivity(next);
            }
        });
    }

    private void Back() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(sixToTenNumbers.this, zeroToSixNumbers.class);
                startActivity(next);
            }
        });
    }

}