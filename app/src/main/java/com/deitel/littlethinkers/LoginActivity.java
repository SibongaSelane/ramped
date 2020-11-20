package com.deitel.littlethinkers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    // Textview
    TextView txtViewReg;

    // Input fields
    TextInputLayout txtUsername, txtPassword;

    // Button
    Button btnLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtViewReg = findViewById(R.id.txtViewReg);

        txtUsername = findViewById(R.id.username);
        txtPassword = findViewById(R.id.password);

        btnLogin = findViewById(R.id.btnLogin);

        // Calling of methods
        MoveToReg();
        LoginUser();

    }

    // Method - redirects the user to the login screen
    public void MoveToReg(){

        // When the user clicks on the text -- the registration page will open
        txtViewReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regInt = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(regInt);
            }
        });
    }


    /**
     * REFERENCE
     * Video - https://youtu.be/o9Y7HDkopHg
     **/

    // Username validation
    private Boolean validateUsername(){
        String val = txtUsername.getEditText().getText().toString();

        // If the user does not enter data for following message is displayed
        if (val.isEmpty()) {
            txtUsername.setError("Field required");
            return false;
        }
        else {
            // clears the fields once the user enters data
            txtUsername.setError(null);
            txtUsername.setErrorEnabled(false);
            return true;
        }
    }

    // Password validation
    private Boolean validatePassword(){
        String val = txtPassword.getEditText().getText().toString();

        // If the user does not enter data for following message is displayed
        if (val.isEmpty()) {
            txtPassword.setError("Field required");
            return false;
        }
        else {

            // clears the fields once the user enters data
            txtPassword.setError(null);
            txtPassword.setErrorEnabled(false);
            return true;
        }
    }


    public void LoginUser(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // IF -- the fields are empty the validation method will be executed
                if (!validateUsername() | !validatePassword()){
                    return;
                }
                // IF --  the fields contain input then the user data is checked
                else {
                    // Method -- checks user details entered
                    CheckLoginDetails();
                }
            }
        });
    }

    /**
     * REFERENCE
     * Video - https://youtu.be/eGWu0-0TWFI
     **/
    public void  CheckLoginDetails(){

        // Gets data entered
        final String EnteredUsername = txtUsername.getEditText().getText().toString().trim();
        final String EnteredPassword = txtPassword.getEditText().getText().toString().trim();

        // Calls the firebase database
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Registration Information");

        // Query - checks if the username matches the entered username
        Query checkUser = reference.orderByChild("username").equalTo(EnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // IF statement - username exists continue by checking password and user type
                if(dataSnapshot.exists()){

                    txtUsername.setError(null);
                    txtUsername.setErrorEnabled(false);

                    // Retrieving password for a specific username and storing it in a variable
                    String passwordInDB = dataSnapshot.child(EnteredUsername).child("password").getValue(String.class);

                    // IF statement - password entered equals password in database continue
                    if(passwordInDB.equals(EnteredPassword)){

                        txtUsername.setError(null);
                        txtUsername.setErrorEnabled(false);

                        // Checks the user type stored for the specific username
                        String userTypeInDB = dataSnapshot.child(EnteredUsername).child("user_role").getValue(String.class);

                        // If the user is a parent, the following will be executed
                        if(userTypeInDB.equals("Parent")){

                            UserDetails.nameInDB_Parent = dataSnapshot.child(EnteredUsername).child("name").getValue(String.class);
                            UserDetails.surnameInDB_Parent = dataSnapshot.child(EnteredUsername).child("surname").getValue(String.class);
                            UserDetails.DOBInDB_Parent = dataSnapshot.child(EnteredUsername).child("date_of_birth").getValue(String.class);
                            UserDetails.contactNoInDB_Parent = dataSnapshot.child(EnteredUsername).child("contact_no").getValue(String.class);
                            UserDetails.emailInDB_Parent = dataSnapshot.child(EnteredUsername).child("email").getValue(String.class);
                            UserDetails.usernameInDB_Parent = dataSnapshot.child(EnteredUsername).child("username").getValue(String.class);

                            // Redirected the user to the parent home page
                            Intent intParent = new Intent(getApplicationContext(), ParentHomeActivity.class);

                            intParent.putExtra("name", UserDetails.nameInDB_Parent);
                            intParent.putExtra("surname", UserDetails.surnameInDB_Parent);
                            intParent.putExtra("date_of_birth", UserDetails.DOBInDB_Parent);
                            intParent.putExtra("contact_no", UserDetails.contactNoInDB_Parent);
                            intParent.putExtra("email", UserDetails.emailInDB_Parent);
                            intParent.putExtra("username", UserDetails.usernameInDB_Parent);
                            intParent.putExtra("password", passwordInDB);
                            intParent.putExtra("user_role", userTypeInDB);

                            startActivity(intParent);
                        }

                        // If the user is a teacher, the following will be executed
                        else if(userTypeInDB.equals("Teacher")){

                            UserDetails.nameInDB_Teacher = dataSnapshot.child(EnteredUsername).child("name").getValue(String.class);
                            UserDetails.surnameInDB_Teacher = dataSnapshot.child(EnteredUsername).child("surname").getValue(String.class);
                            UserDetails.DOBInDB_Teacher = dataSnapshot.child(EnteredUsername).child("date_of_birth").getValue(String.class);
                            UserDetails.contactNoInDB_Teacher = dataSnapshot.child(EnteredUsername).child("contact_no").getValue(String.class);
                            UserDetails.emailInDB_Teacher = dataSnapshot.child(EnteredUsername).child("email").getValue(String.class);
                            UserDetails.usernameInDB_Teacher = dataSnapshot.child(EnteredUsername).child("username").getValue(String.class);

                            // Redirects the user to the teacher home page
                            Intent intTeacher = new Intent(getApplicationContext(), TeacherHomeActvity.class);

                            intTeacher.putExtra("name", UserDetails.nameInDB_Teacher);
                            intTeacher.putExtra("surname", UserDetails.surnameInDB_Teacher);
                            intTeacher.putExtra("date_of_birth", UserDetails.DOBInDB_Teacher);
                            intTeacher.putExtra("contact_no", UserDetails.contactNoInDB_Teacher);
                            intTeacher.putExtra("email", UserDetails.emailInDB_Teacher);
                            intTeacher.putExtra("username", UserDetails.usernameInDB_Teacher);
                            intTeacher.putExtra("password", passwordInDB);
                            intTeacher.putExtra("user_role", userTypeInDB);

                            startActivity(intTeacher);
                        }

                        // If the user is a student, the following will be executed
                        else{
                            UserDetails.nameInDB_Student = dataSnapshot.child(EnteredUsername).child("name").getValue(String.class);
                            UserDetails.surnameInDB_Student = dataSnapshot.child(EnteredUsername).child("surname").getValue(String.class);
                            UserDetails.DOBInDB_Student = dataSnapshot.child(EnteredUsername).child("date_of_birth").getValue(String.class);
                            UserDetails.contactNoInDB_Student = dataSnapshot.child(EnteredUsername).child("contact_no").getValue(String.class);
                            UserDetails.emailInDB_Student = dataSnapshot.child(EnteredUsername).child("email").getValue(String.class);
                            UserDetails.usernameInDB_Student = dataSnapshot.child(EnteredUsername).child("username").getValue(String.class);
                            UserDetails.numberLevel_Student = dataSnapshot.child(EnteredUsername).child("number_level").getValue(String.class);
                            UserDetails.passwordInDB_Student = dataSnapshot.child(EnteredUsername).child("password").getValue(String.class);
                            UserDetails.userTypeInDB_Student = dataSnapshot.child(EnteredUsername).child("user_role").getValue(String.class);

                            // Redirects the user to the student home page
                            Intent intStudent = new Intent(getApplicationContext(), StudentHomeActivity.class);

                            intStudent.putExtra("name", UserDetails.nameInDB_Student);
                            intStudent.putExtra("surname", UserDetails.surnameInDB_Student);
                            intStudent.putExtra("date_of_birth", UserDetails.DOBInDB_Student);
                            intStudent.putExtra("contact_no", UserDetails.contactNoInDB_Student);
                            intStudent.putExtra("email", UserDetails.emailInDB_Student);
                            intStudent.putExtra("username", UserDetails.usernameInDB_Student);
                            intStudent.putExtra("password", UserDetails.passwordInDB_Student);
                            intStudent.putExtra("user_role", UserDetails.userTypeInDB_Student);
                            intStudent.putExtra("number_level", UserDetails.numberLevel_Student);

                            startActivity(intStudent);

                        }
                    }

                    // If the password is incorrect the following message will be displayed
                    else {
                        txtPassword.setError("Incorrect password");
                        txtPassword.requestFocus();
                    }
                }

                // If the username does not exist in the database then the following message will be displayed
                else {
                    txtUsername.setError("User does not exist");
                    txtPassword.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}