package com.deitel.littlethinkers;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class StudentHomeActivity extends AppCompatActivity {

    Button btnLogout, btnArrow, btnArrow1;
    ImageView imageHint;

    ConstraintLayout expandableView, expandableView1;

    CardView Shapes, Numbers, cardView, cardView1, TakePicture, puzzles;

    TextView txtUsername;
    private SoundPool soundPool;
    private int hintSound;

    MediaPlayer mediaPlayer;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        btnLogout = findViewById(R.id.btnLogout);

        btnArrow = findViewById(R.id.btnArrow);
        btnArrow1 = findViewById(R.id.btnArrow1);

        expandableView = findViewById(R.id.expandable);
        expandableView1 = findViewById(R.id.expandable1);

        Numbers = findViewById(R.id.btnNumbers);
        Shapes = findViewById(R.id.btnShapes);
        TakePicture = findViewById(R.id.btnTakePictures);
        puzzles = findViewById(R.id.puzzles);

        cardView = findViewById(R.id.cardView);
        cardView1 = findViewById(R.id.cardView1);

        txtUsername = findViewById(R.id.txtUsername);
        imageHint = findViewById(R.id.imageHint);

        // Method
        Logout();
        ArrowClickEM();
        ArrowClickMD();
        MoveToNumbersPage();
        MoveToShapesPage();
        MoveToTakePicturesPage();
        MoveToPuzzlePage();

        Hint();

        String name, surname;
        name = UserDetails.nameInDB_Student;
        surname = UserDetails.surnameInDB_Student;
        txtUsername.setText(name + " " + surname);

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

        mediaPlayer = MediaPlayer.create(this,R.raw.studenthome);
    }

    private void Hint() {
        imageHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(StudentHomeActivity.this);

                builder.setMessage("Hi! \nWelcome to the main screen." +
                        "\nChoose a difficulty, then click a picture to start learning. \nHave Fun!");

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



    public void ArrowClickEM(){
        btnArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(expandableView.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.VISIBLE);
                    btnArrow.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                }
                else{
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.GONE);
                    btnArrow.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

    }

    public void ArrowClickMD(){
        btnArrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(expandableView1.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
                    expandableView1.setVisibility(View.VISIBLE);
                    btnArrow1.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                }
                else{
                    TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
                    expandableView1.setVisibility(View.GONE);
                    btnArrow1.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

    }

    // Method - redirects the user to the login screen
    public void Logout(){

        // When the user clicks on the text -- the login page will open
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginInt = new Intent(StudentHomeActivity.this, LoginActivity.class);
                startActivity(loginInt);
            }
        });
    }


    // Method - redirects the user to numbers page
    public void MoveToNumbersPage() {
        // When the user clicks on the text -- the login page will open
        Numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginInt = new Intent(StudentHomeActivity.this, NumbersActivity.class);
                startActivity(loginInt);
            }
        });
    }


    // Method - redirects the user to shapes page
    public void MoveToShapesPage() {
        // When the user clicks on the text -- the login page will open
        Shapes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginInt = new Intent(StudentHomeActivity.this, ShapesActivity.class);
                startActivity(loginInt);
            }
        });
    }

    // Method - redirects the user to take picture page
    public void MoveToTakePicturesPage() {
        // When the user clicks on the text -- the login page will open
        TakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pictures = new Intent(StudentHomeActivity.this, TakePicturesActivity.class);
                startActivity(pictures);
            }
        });
    }

    // Method - redirects the user to puzzles page
    public void MoveToPuzzlePage() {
        // When the user clicks on the text -- the login page will open
        puzzles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent puzzles = new Intent(StudentHomeActivity.this, PuzzleMenu.class);
                startActivity(puzzles);
            }
        });
    }
}