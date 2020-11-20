package com.deitel.littlethinkers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TeacherHomeActvity extends AppCompatActivity {

    Button btnLogout;

    TextView txtUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home_actvity);

        btnLogout = findViewById(R.id.btnLogout);
        txtUsername = findViewById(R.id.txtUsername);


        // Method
        Logout();

        String name, surname;
        name = UserDetails.nameInDB_Teacher;
        surname = UserDetails.surnameInDB_Teacher;
        txtUsername.setText(name + " " + surname);


    }


    // Method - redirects the user to the login screen
    public void Logout(){

        // When the user clicks on the text -- the login page will open
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginInt = new Intent(TeacherHomeActvity.this, LoginActivity.class);
                startActivity(loginInt);
            }
        });
    }
}