package com.deitel.littlethinkers.LearningNumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.deitel.littlethinkers.BigNumbers.NumberEight;
import com.deitel.littlethinkers.BigNumbers.NumberEighteen;
import com.deitel.littlethinkers.BigNumbers.NumberNine;
import com.deitel.littlethinkers.BigNumbers.NumberNineteen;
import com.deitel.littlethinkers.BigNumbers.NumberSeven;
import com.deitel.littlethinkers.BigNumbers.NumberSeventeen;
import com.deitel.littlethinkers.BigNumbers.NumberSix;
import com.deitel.littlethinkers.BigNumbers.NumberSixteen;
import com.deitel.littlethinkers.BigNumbers.NumberTen;
import com.deitel.littlethinkers.BigNumbers.NumberTwenty;
import com.deitel.littlethinkers.NumbersActivity;
import com.deitel.littlethinkers.R;

public class SixteenToTwentyNumbers extends AppCompatActivity {

    private Button btnSixteen, btnSeventeen, btnEighteen, btnNineteen, btnTwenty, btnNext, btnBack, btnMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixteen_to_twenty_numbers);

        btnSixteen = findViewById(R.id.btnSixteen);
        btnSeventeen = findViewById(R.id.btnSeventeen);
        btnEighteen = findViewById(R.id.btnEighteen);
        btnNineteen = findViewById(R.id.btnNineteen);
        btnTwenty = findViewById(R.id.btnTwenty);

        btnNext = findViewById(R.id.btnNext);
        btnMenu = findViewById(R.id.btnMenu);
        btnBack = findViewById(R.id.btnBack);

        DisplaySixteen();
        DisplaySeventeen();
        DisplayEighteen();
        DisplayNineteen();
        DisplayTwenty();
        BackToMainMenu();
        Back();
        BackToMainMenu();
    }

    private void BackToMenu() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu = new Intent(SixteenToTwentyNumbers.this, NumbersActivity.class);
                startActivity(menu);
            }
        });
    }

    private void DisplaySixteen() {
        btnSixteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent number = new Intent(SixteenToTwentyNumbers.this, NumberSixteen.class);
                startActivity(number);
            }
        });
    }

    private void DisplaySeventeen() {
        btnSeventeen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent number = new Intent(SixteenToTwentyNumbers.this, NumberSeventeen.class);
                startActivity(number);
            }
        });
    }

    private void DisplayEighteen() {
        btnEighteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent number = new Intent(SixteenToTwentyNumbers.this, NumberEighteen.class);
                startActivity(number);
            }
        });
    }

    private void DisplayNineteen() {
        btnNineteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent number = new Intent(SixteenToTwentyNumbers.this, NumberNineteen.class);
                startActivity(number);
            }
        });
    }
    private void DisplayTwenty() {
        btnTwenty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent number = new Intent(SixteenToTwentyNumbers.this, NumberTwenty.class);
                startActivity(number);
            }
        });
    }

    private void BackToMainMenu() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(SixteenToTwentyNumbers.this, NumbersActivity.class);
                startActivity(next);
            }
        });
    }

    private void Back() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(SixteenToTwentyNumbers.this, elevenToFifteenNumbers.class);
                startActivity(next);
            }
        });
    }
}