package com.deitel.littlethinkers.Level1;

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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.deitel.littlethinkers.ActivityPage;
import com.deitel.littlethinkers.R;

public class L1Round2 extends AppCompatActivity {
    Button btnBack;
    Button mButtonChoice1;
    Button mButtonChoice2;
    Button mButtonChoice3;

    ImageView imageHint;


    private SoundPool soundPool;
    private int hintSound;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l1_round2);


        btnBack = findViewById(R.id.quit);
        imageHint = findViewById(R.id.imageHint);


        mButtonChoice1 = findViewById(R.id.choice1);
        mButtonChoice2 = (Button)findViewById(R.id.choice2);
        mButtonChoice3 = (Button)findViewById(R.id.choice3);

        //Start of Button Listener for Button1
        choiceOne();
        choiceTwo();
        choiceThree();

        BackButton();
        HelpButton();

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

        hintSound = soundPool.load(this, R.raw.countingcars,1);

        mediaPlayer = MediaPlayer.create(this,R.raw.countingcars);
    }



    public void choiceOne(){
        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        if(mButtonChoice1.getText()=="1"){
                            Toast.makeText(L1Round2.this,"Well done!",Toast.LENGTH_SHORT).show();
                            // Intent loginInt = new Intent(Level1_Activity.this, roundTwoActivity.class);
                            //startActivity(loginInt);
                        }else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(L1Round2.this);

                            builder.setMessage("Oops! Try Again");


                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder.show();
                        }
                    }
        });
    }

    public void choiceTwo(){
        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mButtonChoice2.getText()=="1"){
                    Toast.makeText(L1Round2.this,"Well done!",Toast.LENGTH_SHORT).show();
                    // Intent loginInt = new Intent(Level1_Activity.this, roundTwoActivity.class);
                    //startActivity(loginInt);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(L1Round2.this);

                    builder.setMessage("Oops! Try Again");


                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }
            }
        });
    }

    public void choiceThree(){
        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(L1Round2.this);

                builder.setMessage("Well done");


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent loginInt = new Intent(L1Round2.this, L1Round3.class);
                        startActivity(loginInt);
                    }
                });
                builder.show();


            }
        });


    }

    public void HelpButton(){
        imageHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(L1Round2.this);

                builder.setTitle("HOW TO PLAY THE GAME");
                builder.setMessage("STEP 1: Count the cars" +
                        "\n\nSTEP 2: Click the right number \n\nSTEP 3: If your answer is correct!, well done. Click okay to move on " +
                        "\n\nSTEP 4: If your answer is incorrect, oops! \nTry Again");

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


    public void BackButton(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(L1Round2.this, ActivityPage.class);
                startActivity(back);
            }
        });
    }

}