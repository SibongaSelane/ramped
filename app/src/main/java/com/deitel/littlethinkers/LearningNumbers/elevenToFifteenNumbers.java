package com.deitel.littlethinkers.LearningNumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.deitel.littlethinkers.BigNumbers.NumberEleven;
import com.deitel.littlethinkers.BigNumbers.NumberFifteen;
import com.deitel.littlethinkers.BigNumbers.NumberFourteen;
import com.deitel.littlethinkers.BigNumbers.NumberTwelve;
import com.deitel.littlethinkers.BigNumbers.NumberThirteen;
import com.deitel.littlethinkers.NumbersActivity;
import com.deitel.littlethinkers.R;

public class elevenToFifteenNumbers extends AppCompatActivity {

    private Button btnEleven, btnTwelve, btnThirteen, btnFourteen, btnFifteen, btnNext, btnBack, btnMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleven_to_fifteen_numbers);

        btnEleven = findViewById(R.id.btnEleven);
        btnTwelve = findViewById(R.id.btnTwelve);
        btnThirteen = findViewById(R.id.btnThirteen);
        btnFourteen = findViewById(R.id.btnFourteen);
        btnFifteen = findViewById(R.id.btnFifteen);

        btnNext = findViewById(R.id.btnNext);
        btnMenu = findViewById(R.id.btnMenu);
        btnBack = findViewById(R.id.btnBack);

        DisplayEleven();
        DisplayTwelve();
        DisplayThirteen();
        DisplayFourteen();
        DisplayFifteen();
        Next();
        Back();
        BackToMenu();

    }

    private void BackToMenu() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu = new Intent(elevenToFifteenNumbers.this, NumbersActivity.class);
                startActivity(menu);
            }
        });
    }

    private void DisplayEleven() {
        btnEleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent number = new Intent(elevenToFifteenNumbers.this, NumberEleven.class);
                startActivity(number);
            }
        });
    }

    private void DisplayTwelve() {
        btnTwelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent number = new Intent(elevenToFifteenNumbers.this, NumberTwelve.class);
                startActivity(number);
            }
        });
    }

    private void DisplayThirteen() {
        btnThirteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent number = new Intent(elevenToFifteenNumbers.this, NumberThirteen.class);
                startActivity(number);
            }
        });
    }

    private void DisplayFourteen() {
        btnFourteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent number = new Intent(elevenToFifteenNumbers.this, NumberFourteen.class);
                startActivity(number);
            }
        });
    }
    private void DisplayFifteen() {
        btnFifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent number = new Intent(elevenToFifteenNumbers.this, NumberFifteen.class);
                startActivity(number);
            }
        });
    }

    private void Next() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(elevenToFifteenNumbers.this, SixteenToTwentyNumbers.class);
                startActivity(next);
            }
        });
    }

    private void Back() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(elevenToFifteenNumbers.this, sixToTenNumbers.class);
                startActivity(next);
            }
        });
    }

}