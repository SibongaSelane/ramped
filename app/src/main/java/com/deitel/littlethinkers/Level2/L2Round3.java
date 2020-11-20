package com.deitel.littlethinkers.Level2;

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
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.deitel.littlethinkers.ActivityPage;
import com.deitel.littlethinkers.Level3.Level3Activity;
import com.deitel.littlethinkers.R;
import com.deitel.littlethinkers.UserDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class L2Round3 extends AppCompatActivity {

    Button btnBack, btnNextLevel;
    Button mButtonChoice1;
    Button mButtonChoice2;
    Button mButtonChoice3;

    ImageView imageHint;
    private SoundPool soundPool;
    private int hintSound;

    MediaPlayer mediaPlayer;


    // Firebase
    // Realtime database
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l2_round3);
        btnBack = findViewById(R.id.quit);
        btnNextLevel = findViewById(R.id.btnNextLevel);

        mButtonChoice1 = findViewById(R.id.choice1);
        imageHint = findViewById(R.id.imageHint);

        mButtonChoice2 = (Button) findViewById(R.id.choice2);
        mButtonChoice3 = (Button) findViewById(R.id.choice3);

        //Start of Button Listener for Button1
        choiceOne();
        choiceTwo();
        choiceThree();

        BackButton();
        NextLevel();
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

        hintSound = soundPool.load(this, R.raw.countinghands,1);

        mediaPlayer = MediaPlayer.create(this,R.raw.countinghands);

    }

    public void choiceOne() {
        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mButtonChoice1.getText() == "1") {
                    Toast.makeText(L2Round3.this, "Well done!", Toast.LENGTH_SHORT).show();
                    // Intent loginInt = new Intent(Level1_Activity.this, roundTwoActivity.class);
                    //startActivity(loginInt);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(L2Round3.this);

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

    public void choiceTwo() {
        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mButtonChoice2.getText() == "1") {
                    Toast.makeText(L2Round3.this, "Well done!", Toast.LENGTH_SHORT).show();
                    // Intent loginInt = new Intent(Level1_Activity.this, roundTwoActivity.class);
                    //startActivity(loginInt);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(L2Round3.this);

                    builder.setMessage("Oops! Try Again");


                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();                  }
            }
        });

    }

    public void choiceThree() {
        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(L2Round3.this);

                builder.setMessage("Well done. You have completed the level \nYou can now move to the next level or stop playing");


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btnNextLevel.setEnabled(true);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(L2Round3.this);

                builder.setTitle("HOW TO PLAY THE GAME");
                builder.setMessage("STEP 1: Count the hands" +
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

    public void BackButton() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(L2Round3.this, ActivityPage.class);
                startActivity(back);
            }
        });
    }

    public void NextLevel() {
        btnNextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(L2Round3.this, Level3Activity.class);
                startActivity(back);
                updateUser();
            }
        });
    }

    private void updateUser() {

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Registration Information");


        reference.child(UserDetails.usernameInDB_Student)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        Map<String, Object> postValues = new HashMap<String,Object>();
                                                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                            postValues.put(snapshot.getKey(),snapshot.getValue());
                                                        }

                                                        String numberLevel = "3";
                                                        postValues.put("number_level", numberLevel);
                                                        reference.child(UserDetails.usernameInDB_Student).updateChildren(postValues);
                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {}
                                                }
                );
    }
}