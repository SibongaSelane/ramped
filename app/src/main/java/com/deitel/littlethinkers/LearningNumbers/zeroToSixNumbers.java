package com.deitel.littlethinkers.LearningNumbers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.WithHint;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.deitel.littlethinkers.BigNumbers.NumberFive;
import com.deitel.littlethinkers.BigNumbers.NumberFour;
import com.deitel.littlethinkers.BigNumbers.NumberOne;
import com.deitel.littlethinkers.BigNumbers.NumberSix;
import com.deitel.littlethinkers.BigNumbers.NumberThree;
import com.deitel.littlethinkers.BigNumbers.NumberTwo;
import com.deitel.littlethinkers.BigNumbers.NumberZero;
import com.deitel.littlethinkers.NumbersActivity;
import com.deitel.littlethinkers.R;

public class zeroToSixNumbers extends AppCompatActivity {

    private Button btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnNext, btnMenu;
    ImageView hint;
    private SoundPool soundPool;
    private int hintSound;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zero_to_six_numbers);

        btnZero = findViewById(R.id.btnZero);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);

        btnNext = findViewById(R.id.btnNext);
        btnMenu = findViewById(R.id.btnMenu);
        hint = findViewById(R.id.imageHint);

        AlertDialog.Builder builder = new AlertDialog.Builder(zeroToSixNumbers.this);

        builder.setTitle("LEARNING NUMBERS");
        builder.setMessage("Click on a number to start learning.\nClick next to move to the next set of numbers");


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();

        DisplayZero();
       DisplayOne();
       DisplayTwo();
       DisplayThree();
       DisplayFour();
       DisplayFive();

       Next();
       Hint();
       BackToMenu();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build();

        } else {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }

        hintSound = soundPool.load(this, R.raw.clickonnumber,1);

        mediaPlayer = MediaPlayer.create(this,R.raw.clickonnumber);
    }

    private void BackToMenu() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu = new Intent(zeroToSixNumbers.this, NumbersActivity.class);
                startActivity(menu);
            }
        });
    }

    private void Hint(){
        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(zeroToSixNumbers.this);

                builder.setTitle("LEARNING NUMBERS");
                builder.setMessage("Click on a number to start learning.\nClick next to move to the next set of numbers");

                mediaPlayer.start();


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        });
    }

    private void DisplayZero() {
        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent number = new Intent(zeroToSixNumbers.this, NumberZero.class);
                startActivity(number);
            }
        });
    }

    private void DisplayOne() {
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent number = new Intent(zeroToSixNumbers.this, NumberOne.class);
                startActivity(number);
            }
        });
    }

        private void DisplayTwo(){
            btnTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent number = new Intent(zeroToSixNumbers.this, NumberTwo.class);
                    startActivity(number);
                }
            });
    }

    private void DisplayThree(){
        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent number = new Intent(zeroToSixNumbers.this, NumberThree.class);
                startActivity(number);
            }
        });
    }

    private void DisplayFour(){
        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent number = new Intent(zeroToSixNumbers.this, NumberFour.class);
                startActivity(number);
            }
        });
    }

    private void DisplayFive(){
        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent number = new Intent(zeroToSixNumbers.this, NumberFive.class);
                startActivity(number);
            }
        });
    }


    private void Next(){
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(zeroToSixNumbers.this, sixToTenNumbers.class);
                startActivity(next);
            }
        });
    }
}