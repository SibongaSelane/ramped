package com.deitel.littlethinkers;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.deitel.littlethinkers.Frozen.SlidingPuzzleFrozen;
import com.deitel.littlethinkers.PawPatrol.SlidingPuzzlePawPatrol;
import com.deitel.littlethinkers.Princesses.SlidingPuzzlePrincesses;
import com.deitel.littlethinkers.SlidingPuzzles.SlidingPuzzle1;

public class PuzzleMenu extends AppCompatActivity {

    ImageView imgMickeyFriends, imgFrozen, imgPawPatrol, imgPrincesses;

    ImageView imageHint;

    private SoundPool soundPool;
    private int hintSound;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_menu);

        imgMickeyFriends = findViewById(R.id.btnMickey);
        imgFrozen = findViewById(R.id.btnFrozen);
        imgPawPatrol = findViewById(R.id.btnPawPatrol);
        imgPrincesses = findViewById(R.id.btnPrincesses);
        imageHint = findViewById(R.id.imageHint);

        MickeyFriendsPuzzle();
        FrozenPuzzle();
        PawPatrolPuzzle();
        PrincessesPuzzle();

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

        hintSound = soundPool.load(this, R.raw.studenthome,1);

        mediaPlayer = MediaPlayer.create(this,R.raw.puzzlemenu);

        Hint();

    }

    private void Hint() {
        imageHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PuzzleMenu.this);

                builder.setMessage("Click on a picture to start the puzzle. Have fun!");

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


    // Method - redirects to the mickey puzzle
    public void MickeyFriendsPuzzle(){

        // When the user clicks on the text -- the login page will open
        imgMickeyFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mickeyFriends = new Intent(PuzzleMenu.this, SlidingPuzzle1.class);
                startActivity(mickeyFriends);
            }
        });
    }

    // Method - redirects to the frozen puzzle
    public void FrozenPuzzle(){

        // When the user clicks on the text -- the login page will open
        imgFrozen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent frozen = new Intent(PuzzleMenu.this, SlidingPuzzleFrozen.class);
                startActivity(frozen);
            }
        });
    }

    // Method - redirects to the paw patrol puzzle
    public void PawPatrolPuzzle(){

        // When the user clicks on the text -- the login page will open
        imgPawPatrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pawPatrol = new Intent(PuzzleMenu.this, SlidingPuzzlePawPatrol.class);
                startActivity(pawPatrol);
            }
        });
    }

    // Method - redirects to the princesses puzzle
    public void PrincessesPuzzle(){

        // When the user clicks on the text -- the login page will open
        imgPrincesses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent princesses = new Intent(PuzzleMenu.this, SlidingPuzzlePrincesses.class);
                startActivity(princesses);
            }
        });
    }
}