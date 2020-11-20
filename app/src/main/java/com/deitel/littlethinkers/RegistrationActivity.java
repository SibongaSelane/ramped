package com.deitel.littlethinkers;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.deitel.littlethinkers.Database.UserHelperClass;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RegistrationActivity extends AppCompatActivity {

    // Declaring of variables

    TextView txtViewLogin, txtDOB;

    // Button
    Button btnReg;

    // Storing the selected date in this variable
    String date;

    // Input fields
    TextInputLayout txtName, txtSurname, txtContactNo, txtEmail, txtUsername, txtPassword;

    // Radio group & button
    RadioGroup radioUserType;
    RadioButton radioUserPicked;

    // Email
    String sEmail, sPassword;


    // Date picker
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    // Firebase
    // Realtime database
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    // Authentication
    FirebaseAuth firebaseAuth;

    private static final String TAG = "Registration";
    String userID;

    /**
     * Generating a random number
     * final Random random = new Random();
     **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firebaseAuth = FirebaseAuth.getInstance();

        txtName = findViewById(R.id.txtName);
        txtSurname = findViewById(R.id.txtSurname);
        txtContactNo = findViewById(R.id.txtContactNo);
        txtEmail = findViewById(R.id.txtEmail);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);

        radioUserType = findViewById(R.id.radioUserType);

        btnReg = findViewById(R.id.btnReg);

        // Calling of methods
        MoveToLogin();
        SelectDOB();
        RegisteringUser();

        // Sender email credentials
        sEmail = "nataleapillay2308@gmail.com";
        sPassword = "14019005NatP14";


    }

    // Method - redirects the user to the login screen
    public void MoveToLogin() {
        txtViewLogin = findViewById(R.id.txtViewLogin);

        // When the user clicks on the text -- the login page will open
        txtViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginInt = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(loginInt);
            }
        });
    }

    /**
     * REFERENCE
     * Video - https://youtu.be/o9Y7HDkopHg
     * **/

    // Name validation
    private Boolean validateName() {
        final String val = txtName.getEditText().getText().toString();

        if (val.isEmpty()) {
            txtName.setError("Field required");
            return false;
        } else {
            txtName.setError(null);
            txtName.setErrorEnabled(false);
            return true;
        }
    }

    // Surname validation
    private Boolean validateSurname() {
        final String val = txtSurname.getEditText().getText().toString();

        if (val.isEmpty()) {
            txtSurname.setError("Field required");
            return false;
        } else {
            txtSurname.setError(null);
            txtSurname.setErrorEnabled(false);
            return true;
        }
    }

    // Date of birth validation
    private Boolean validateDOB() {
        final String val = txtDOB.getText().toString();

        if (val.isEmpty()) {
            txtDOB.setError("Field required");
            return false;
        } else {
            txtDOB.setError(null);
            txtDOB.clearComposingText();
            return true;
        }
    }

    // Contact number validation
    private Boolean validateContact() {
        final String val = txtContactNo.getEditText().getText().toString();

        // If empty the following message will be displayed
        if (val.isEmpty()) {
            txtContactNo.setError("Field required");
            return false;
        }

        // Once the user enters data the errors will disappear
        else {
            txtContactNo.setError(null);
            txtContactNo.setErrorEnabled(false);
            return true;
        }
    }

    // Email validation
    private Boolean validateEmail() {
        String val = txtEmail.getEditText().getText().toString();
        String RegExpress = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            txtEmail.setError("Invalid email address");
            return false;
        } else if (!val.matches(RegExpress)) {
            txtEmail.setError("Field required");
            return false;
        } else {
            txtEmail.setError(null);
            txtEmail.setErrorEnabled(false);
            return true;
        }
    }

    // Username validation
    private Boolean validateUsername() {
        String val = txtUsername.getEditText().getText().toString();
        String noWhiteSpaces = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            txtUsername.setError("Field required");
            return false;
        } else if (val.length() >= 15) {
            txtUsername.setError("Username max 15 characters");
            return false;
        } else if (!val.matches(noWhiteSpaces)) {
            txtUsername.setError("White spaces are not allowed");
            return false;
        } else {
            txtUsername.setError(null);
            txtUsername.setErrorEnabled(false);
            return true;
        }
    }

    // Password validation
    private Boolean validatePassword() {
        String val = txtPassword.getEditText().getText().toString();

        /**
         * Password needs to contain:
         * at least 1 number
         * letters
         * at least 8 characters
         **/
        String passwordValidate = "^" + "(?=.*[0-9])" + "(?=.*[a-zA-Z])" + ".{8,}" + "$";

        if (val.isEmpty()) {
            txtPassword.setError("Field required");
            return false;
        } else if (!val.matches(passwordValidate)) {
            txtPassword.setError("Password weak");
            return false;
        } else {
            txtPassword.setError(null);
            txtPassword.setErrorEnabled(false);
            return true;
        }
    }


    /**
     * Method - registering the user and saving the data in realtime database
     * REFERENCE
     * Video - https://youtu.be/wa8OrQ_e76M
     **/
    public void RegisteringUser() {

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateName() | !validateSurname() | !validateDOB() | !validateContact() | !validateEmail() | !validateUsername() | !validatePassword()) {
                    return;
                }

                firebaseDatabase = FirebaseDatabase.getInstance();
                reference = firebaseDatabase.getReference("Registration Information");

                // Get values from the input fields
                final String name = txtName.getEditText().getText().toString();
                final String surname = txtSurname.getEditText().getText().toString();
                final String date_of_birth = date;
                final String contact_no = txtContactNo.getEditText().getText().toString();
                final String email = txtEmail.getEditText().getText().toString();
                final String username = txtUsername.getEditText().getText().toString();
                final String password = txtPassword.getEditText().getText().toString();

                /** Generating id -- combination of randomly generated number + username
                 String randomNo = Integer.toString(random.nextInt(96) + 32);
                 final String id = username + randomNo;**/

                // Getting the selected user
                int selectedUserType = radioUserType.getCheckedRadioButtonId();
                radioUserPicked = findViewById(selectedUserType);

                final String user_role = radioUserPicked.getText().toString();
                final String number_level = "0";


                // Register user in firebase
                UserHelperClass helperClass = new UserHelperClass(name, surname, date_of_birth, contact_no, email, username, password, user_role, number_level);

                // Saving the user data to the database
                reference.child(username).setValue(helperClass);

                Properties properties = new Properties();
                properties.put("mail.smtp.auth","true");
                properties.put("mail.smtp.starttls.enable","true");
                properties.put("mail.smtp.host","smtp.gmail.com");
                properties.put("mail.smtp.port","587");

                Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sEmail, sPassword);
                    }
                });


                try {
                    Message message = new MimeMessage(session);

                    message.setFrom(new InternetAddress(sEmail));

                    // Recipient email
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(txtEmail.getEditText().getText().toString().trim()));

                    //Email subject
                    message.setSubject("Little Thinkers - Successful registration");

                    // Email message
                    message.setText(txtName.getEditText().getText().toString().trim() + ", "
                    + "you have been successfully registered as a " + user_role + "\n"
                    + "Your Username: " + username + "\n" + "Your Password: " + password);

                    new SendMail().execute(message);

                } catch (MessagingException e) {
                    e.printStackTrace();
                }

                // If successful a notification will be displayed indicating the registration was successful
                                    Toast.makeText(RegistrationActivity.this, "User Account has been created. Please verify email", Toast.LENGTH_SHORT).show();

                                    // Redirects the user to the login screen
                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));





            }
        });
    }

// Method - allows user to selected Date [day, month and year]
    /**
     * REFERENCE
     * Video -
     * **/
    public void SelectDOB() {
        txtDOB = findViewById(R.id.txtDOB);

        txtDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // Displays a dialog box to allow the user to select date
                DatePickerDialog dialog = new DatePickerDialog(
                        RegistrationActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener,
                        year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });


        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: yyyy-mm-dd" + year + "-" + month + "-" + dayOfMonth);

                // The format shown
                date = year + "-" + month + "-" + dayOfMonth;

                // Displays the selected date in the textbox
                txtDOB.setText(date);
            }
        };

    }

    private class SendMail extends AsyncTask<Message,String,String> {

        // Initialise progress dialog
        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "success";
            }
            catch (MessagingException e){
                e.printStackTrace();
                return "Error";

            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //
        }
    }

}