package com.deitel.littlethinkers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.deitel.littlethinkers.Database.UserHelperClass;
import com.deitel.littlethinkers.Level1.Level1Activity;
import com.deitel.littlethinkers.Level2.Level2Activity;
import com.deitel.littlethinkers.Level3.Level3Activity;
import com.deitel.littlethinkers.Level4.Level4Activity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityPage extends AppCompatActivity {

    Button btnLevel1, btnLevel2, btnLevel3, btnLevel4, btnMenu;
    TextView txtUsername;

   String username = UserDetails.usernameInDB_Student;

    // Firebase
    // Realtime database
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        btnLevel1 = findViewById(R.id.btnLevel1);
        btnLevel2 = findViewById(R.id.btnLevel2);
        btnLevel3 = findViewById(R.id.btnLevel3);
        btnLevel4 = findViewById(R.id.btnLevel4);
        btnMenu = findViewById(R.id.btnMenu);

        txtUsername = findViewById(R.id.txtUsername);

        txtUsername.setText(UserDetails.numberLevel_Student);

        btnLevel2.setEnabled(false);
        btnLevel3.setEnabled(false);
        btnLevel4.setEnabled(false);

            Level1();
            Level3();
            Level4();
            Menu();

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Registration Information/" + username + "/number_level");

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                UserHelperClass numb = dataSnapshot.getValue(UserHelperClass.class);
                txtUsername.setText(numb.getNumber_level());
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

int numberLevel = 1; // mistake
            if (numberLevel == 2) {
                btnLevel2.setEnabled(true);
                btnLevel2.setClickable(true);
                Level2();
            }
                else if(numberLevel ==3){
                    btnLevel3.setEnabled(true);
                    btnLevel3.setClickable(true);
                    Level3();

                btnLevel2.setEnabled(true);
                btnLevel2.setClickable(true);
                Level2();

                }
            else if(numberLevel ==4){
                btnLevel2.setEnabled(true);
                btnLevel2.setClickable(true);
                Level2();

                btnLevel3.setEnabled(true);
                btnLevel3.setClickable(true);
                Level3();

                btnLevel4.setEnabled(true);
                btnLevel4.setClickable(true);
                Level4();
            }
            else{
                Toast.makeText(ActivityPage.this, "error", Toast.LENGTH_SHORT).show();
                btnLevel2.setClickable(false);
                btnLevel2.setEnabled(false);

                btnLevel3.setClickable(false);
                btnLevel3.setEnabled(false);

                btnLevel4.setClickable(false);
                btnLevel4.setEnabled(false);
            }
    }

    private void Menu() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu = new Intent(ActivityPage.this, NumbersActivity.class);
                startActivity(menu);
            }
        });

    }

    public void Level1(){
        btnLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityPage.this);

                builder.setTitle("HOW TO PLAY THE GAME");
                builder.setMessage("STEP 1: Count the cars" +
                        "\n\nSTEP 2: Click the right number \n\nSTEP 3: If your answer is correct!, well done. Click okay to move on " +
                        "\n\nSTEP 4: If your answer is incorrect, oops! \nTry Again");


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intLevel1 = new Intent(ActivityPage.this, Level1Activity.class);
                        startActivity(intLevel1);
                    }
                });
                builder.show();

            }
        });
    }

    public void Level2(){
        btnLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityPage.this);

                    builder.setTitle("HOW TO PLAY THE GAME");
                    builder.setMessage("STEP 1: Count the hands" +
                            "\n\nSTEP 2: Click the right number \n\nSTEP 3: If your answer is correct!, well done. Click okay to move on " +
                            "\n\nSTEP 4: If your answer is incorrect, oops! \nTry Again");


                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intLevel2 = new Intent(ActivityPage.this, Level2Activity.class);
                            startActivity(intLevel2);
                        }
                    });
                    builder.show();

            }
        });

    }

    public void Level3() {
        btnLevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityPage.this);

                builder.setTitle("HOW TO PLAY THE GAME");
                builder.setMessage("STEP 1: Count the spots on the ladybugs" +
                        "\n\nSTEP 2: Click the right number \n\nSTEP 3: If your answer is correct!, well done. Click okay to move on " +
                        "\n\nSTEP 4: If your answer is incorrect, oops! \nTry Again");


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intLevel3 = new Intent(ActivityPage.this, Level3Activity.class);
                        startActivity(intLevel3);
                    }
                });
                builder.show();

            }
        });
    }

        public void Level4(){
            btnLevel4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityPage.this);

                    builder.setTitle("HOW TO PLAY THE GAME");
                    builder.setMessage("STEP 1: Count the fruits on the trees" +
                            "\n\nSTEP 2: Click the right number \n\nSTEP 3: If your answer is correct!, well done. Click okay to move on " +
                            "\n\nSTEP 4: If your answer is incorrect, oops! \nTry Again");


                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intLevel4 = new Intent(ActivityPage.this, Level4Activity.class);
                            startActivity(intLevel4);
                        }
                    });
                    builder.show();

                }
            });
        }
    }
