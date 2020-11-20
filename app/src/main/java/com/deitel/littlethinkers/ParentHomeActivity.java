package com.deitel.littlethinkers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ParentHomeActivity extends AppCompatActivity {

    // Declaration of variables
    Button btnLogout, btnAddChild;

    TextView txtUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_home);

        btnLogout = findViewById(R.id.btnLogout);
        btnAddChild = findViewById(R.id.btnAddChild);
        txtUsername = findViewById(R.id.txtUsername);


        // Methods
        Logout();
        AddChild();

        String name, surname;
        name = UserDetails.nameInDB_Parent;
        surname = UserDetails.surnameInDB_Parent;
        txtUsername.setText(name + " " + surname);

    }


    // Method - redirects the user to the login screen
    public void Logout(){

        // When the user clicks on the text -- the login page will open
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginInt = new Intent(ParentHomeActivity.this, LoginActivity.class);
                startActivity(loginInt);
            }
        });
    }

    // Method - redirects the user to the login screen
    public void AddChild(){

        // When the user clicks on the button -- the student registration page will open
        btnAddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regInt = new Intent(ParentHomeActivity.this, StudRegistrationActivity.class);
                startActivity(regInt);
            }
        });
    }
}