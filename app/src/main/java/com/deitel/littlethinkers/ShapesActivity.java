package com.deitel.littlethinkers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.deitel.littlethinkers.Adapter.ImageAdapter;
import com.deitel.littlethinkers.Level4.Level4Activity;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

public class ShapesActivity extends AppCompatActivity {

    Button btnMenu, btnHelp, btnStart;
    TextView txtTimer;
    CountDownTimer countDownTimer;
    private static final long COUNTDOWN_TIMER_IN_MILLI = 120000;
    private long timeLeft;

    ImageView iv_11, iv_12,iv_13,iv_14,iv_21,iv_22,iv_23,iv_24,iv_31,iv_32,iv_33,iv_34;

    Integer [] cardArray = {101,102,103,104,105,106,201,202,203,204,205,206};

    int image101,image102,image103,image104,image105,image106,
            image201,image202,image203,image204,image205,image206;

    int firstCard, secondCard;
    int clickedFirst, clickedSecond;

    int cardNumber = 1;

    ImageView imageHint;
    private SoundPool soundPool;
    private int hintSound;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shapes);

        iv_11 = findViewById(R.id.iv_11);
        iv_12 = findViewById(R.id.iv_12);
        iv_13 = findViewById(R.id.iv_13);
        iv_14 = findViewById(R.id.iv_14);
        iv_21 = findViewById(R.id.iv_21);
        iv_22 = findViewById(R.id.iv_22);
        iv_23 = findViewById(R.id.iv_23);
        iv_24 = findViewById(R.id.iv_24);
        iv_31 = findViewById(R.id.iv_31);
        iv_32 = findViewById(R.id.iv_32);
        iv_33 = findViewById(R.id.iv_33);
        iv_34 = findViewById(R.id.iv_34);

        txtTimer = findViewById(R.id.timer);

        btnMenu = findViewById(R.id.quit);
        imageHint = findViewById(R.id.imageHint);
        btnStart = findViewById(R.id.btnStart);

        iv_11.setTag("0");
        iv_12.setTag("1");
        iv_13.setTag("2");
        iv_14.setTag("3");
        iv_21.setTag("4");
        iv_22.setTag("5");
        iv_23.setTag("6");
        iv_24.setTag("7");
        iv_31.setTag("8");
        iv_32.setTag("9");
        iv_33.setTag("10");
        iv_34.setTag("11");

        frontOfCardResource();

        // Shuffles the images
        Collections.shuffle(Arrays.asList(cardArray));

        iv_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_11, theCard);
            }
        });

        iv_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_12, theCard);
            }
        });

        iv_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_13, theCard);
            }
        });

        iv_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_14, theCard);
            }
        });

        iv_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_21, theCard);
            }
        });

        iv_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_22, theCard);
            }
        });

        iv_23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_23, theCard);
            }
        });

        iv_24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_24, theCard);
            }
        });

        iv_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_31, theCard);
            }
        });

        iv_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_32, theCard);
            }
        });

        iv_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_33, theCard);
            }
        });

        iv_34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_34, theCard);
            }
        });

        timeLeft = COUNTDOWN_TIMER_IN_MILLI;
        startGame();
        Help();
        BackToMainMenu();

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

        hintSound = soundPool.load(this, R.raw.shapesgame,1);

        mediaPlayer = MediaPlayer.create(this,R.raw.shapesgame);
    }
public void startGame(){
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer  = new CountDownTimer(timeLeft, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        timeLeft = millisUntilFinished;
                        updateCountDownText();
                    }

                    @Override
                    public void onFinish() {
                        timeLeft = 0;
                        updateCountDownText();

                        AlertDialog.Builder builder = new AlertDialog.Builder(ShapesActivity.this);

                        builder.setTitle("SHAPES GAME");
                        builder.setMessage("Oops! You are out of time. Try again");


                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent loginInt = new Intent(ShapesActivity.this, StudentHomeActivity.class);
                                startActivity(loginInt);
                            }
                        });
                        builder.show();
                    }
                }.start();
            }
        });
}

    public void updateCountDownText(){
        int minutes =(int) (timeLeft/1000) / 60;
        int seconds =(int) (timeLeft/1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        txtTimer.setText(timeFormatted);
    }

    private void doStuff(ImageView image, int card){
        if(cardArray[card] == 101){
            image.setImageResource(image101);
        }
        else if(cardArray[card] == 102){
            image.setImageResource(image102);
        }
        else if(cardArray[card] == 103){
            image.setImageResource(image103);
        }
        else if(cardArray[card] == 104){
            image.setImageResource(image104);
        }
        else if(cardArray[card] == 105){
            image.setImageResource(image105);
        }
        else if(cardArray[card] == 106){
            image.setImageResource(image106);
        }
        else if(cardArray[card] == 201){
            image.setImageResource(image101);
        }
        else if(cardArray[card] == 202){
            image.setImageResource(image102);
        }
        else if(cardArray[card] == 203){
            image.setImageResource(image103);
        }
        else if(cardArray[card] == 204){
            image.setImageResource(image104);
        }
        else if(cardArray[card] == 205){
            image.setImageResource(image105);
        }
        else if(cardArray[card] == 206){
            image.setImageResource(image106);
        }

        if(cardNumber == 1){
            firstCard = cardArray[card];
            if(firstCard > 200){
                firstCard = firstCard - 100;
            }
            cardNumber = 2;
            clickedFirst = card;

            image.setEnabled(false);
        }
        else if(cardNumber == 2) {
            secondCard = cardArray[card];
            if(secondCard > 200){
                secondCard = secondCard - 100;
            }
            cardNumber = 1;
            clickedSecond = card;

            iv_11.setEnabled(false);
            iv_12.setEnabled(false);
            iv_13.setEnabled(false);
            iv_14.setEnabled(false);
            iv_21.setEnabled(false);
            iv_22.setEnabled(false);
            iv_23.setEnabled(false);
            iv_24.setEnabled(false);
            iv_31.setEnabled(false);
            iv_32.setEnabled(false);
            iv_33.setEnabled(false);
            iv_34.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    calculate();
                }
            }, 1000);
        }
    }


    private void calculate(){
        if (firstCard == secondCard){
            if (clickedFirst == 0){
                iv_11.setVisibility(View.INVISIBLE);
            }
            else if (clickedFirst == 1){
                iv_12.setVisibility(View.INVISIBLE);
            }
            else if (clickedFirst == 2){
                iv_13.setVisibility(View.INVISIBLE);
            }
            else if (clickedFirst == 3){
                iv_14.setVisibility(View.INVISIBLE);
            }
            else if (clickedFirst == 4){
                iv_21.setVisibility(View.INVISIBLE);
            }
            else if (clickedFirst == 5){
                iv_22.setVisibility(View.INVISIBLE);
            }
            else if (clickedFirst == 6){
                iv_23.setVisibility(View.INVISIBLE);
            }
            else if (clickedFirst == 7){
                iv_24.setVisibility(View.INVISIBLE);
            }
            else if (clickedFirst == 8){
                iv_31.setVisibility(View.INVISIBLE);
            }
            else if (clickedFirst == 9){
                iv_32.setVisibility(View.INVISIBLE);
            }
            else if (clickedFirst == 10){
                iv_33.setVisibility(View.INVISIBLE);
            }
            else if (clickedFirst == 11){
                iv_34.setVisibility(View.INVISIBLE);
            }

            if (clickedSecond == 0){
                iv_11.setVisibility(View.INVISIBLE);
            }
            else if (clickedSecond == 1){
                iv_12.setVisibility(View.INVISIBLE);
            }
            else if (clickedSecond == 2){
                iv_13.setVisibility(View.INVISIBLE);
            }
            else if (clickedSecond == 3){
                iv_14.setVisibility(View.INVISIBLE);
            }
            else if (clickedSecond == 4){
                iv_21.setVisibility(View.INVISIBLE);
            }
            else if (clickedSecond == 5){
                iv_22.setVisibility(View.INVISIBLE);
            }
            else if (clickedSecond == 6){
                iv_23.setVisibility(View.INVISIBLE);
            }
            else if (clickedSecond == 7){
                iv_24.setVisibility(View.INVISIBLE);
            }
            else if (clickedSecond == 8){
                iv_31.setVisibility(View.INVISIBLE);
            }
            else if (clickedSecond == 9){
                iv_32.setVisibility(View.INVISIBLE);
            }
            else if (clickedSecond == 10){
                iv_33.setVisibility(View.INVISIBLE);
            }
            else if (clickedSecond == 11){
                iv_34.setVisibility(View.INVISIBLE);
            }
        }
        else {
            iv_11.setImageResource(R.drawable.questionm);
            iv_12.setImageResource(R.drawable.questionm);
            iv_13.setImageResource(R.drawable.questionm);
            iv_14.setImageResource(R.drawable.questionm);
            iv_21.setImageResource(R.drawable.questionm);
            iv_22.setImageResource(R.drawable.questionm);
            iv_23.setImageResource(R.drawable.questionm);
            iv_24.setImageResource(R.drawable.questionm);
            iv_31.setImageResource(R.drawable.questionm);
            iv_32.setImageResource(R.drawable.questionm);
            iv_33.setImageResource(R.drawable.questionm);
            iv_34.setImageResource(R.drawable.questionm);
        }

        iv_11.setEnabled(true);
        iv_12.setEnabled(true);
        iv_13.setEnabled(true);
        iv_14.setEnabled(true);
        iv_21.setEnabled(true);
        iv_22.setEnabled(true);
        iv_23.setEnabled(true);
        iv_24.setEnabled(true);
        iv_31.setEnabled(true);
        iv_32.setEnabled(true);
        iv_33.setEnabled(true);
        iv_34.setEnabled(true);

        checkEnd();

    }

    private void checkEnd(){
        if(iv_11.getVisibility() == View.INVISIBLE &&
                iv_12.getVisibility() == View.INVISIBLE &&
                iv_13.getVisibility() == View.INVISIBLE &&
                iv_14.getVisibility() == View.INVISIBLE &&
                iv_21.getVisibility() == View.INVISIBLE &&
                iv_22.getVisibility() == View.INVISIBLE &&
                iv_23.getVisibility() == View.INVISIBLE &&
                iv_24.getVisibility() == View.INVISIBLE &&
                iv_31.getVisibility() == View.INVISIBLE &&
                iv_32.getVisibility() == View.INVISIBLE &&
                iv_33.getVisibility() == View.INVISIBLE &&
                iv_34.getVisibility() == View.INVISIBLE){

            AlertDialog.Builder builder = new AlertDialog.Builder(ShapesActivity.this);

            builder.setMessage("GAME OVER")
            .setCancelable(false)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent loginInt = new Intent(ShapesActivity.this, StudentHomeActivity.class);
                    startActivity(loginInt);
                    finish();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    // Adding images
    public void frontOfCardResource(){
        image101 = R.drawable.img1;
        image102 = R.drawable.img2;
        image103 = R.drawable.img3;
        image104 = R.drawable.img4;
        image105 = R.drawable.img5;
        image106 = R.drawable.img6;
        image201 = R.drawable.img1;
        image202 = R.drawable.img2;
        image203 = R.drawable.img3;
        image204 = R.drawable.img4;
        image205 = R.drawable.img5;
        image206 = R.drawable.img6;
    }

    public void Help(){

        imageHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShapesActivity.this);

                //builder.setTitle("LEARNING NUMBERS");
                builder.setMessage("Welcome to the shapes concentration game. " +
                        "\n This game consists of eight shapes.\n" +
                        "Click on \"start game\" to begin.\n" +
                        "All the cards will be laid in rows, face down.\n" +
                        "A player will select any two cards of their choice:\n" +
                        "If the cards don’t match, the game turns them back over.\n" +
                        "If the two cards match, it’s a pair! the cards will be removed from the screen.\n" +
                        "Once all the cards disappear, the game is over.\n" +
                        "Players given two minutes to complete the game.");

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

    public void BackToMainMenu(){

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu = new Intent(ShapesActivity.this, StudentHomeActivity.class);
                startActivity(menu);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }
}