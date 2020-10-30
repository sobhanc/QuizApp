package com.example.project2_chohss;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText firstNameField, lastNameField, dobField, emailField, passwordField;
    private Button regButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstNameField = (EditText) findViewById(R.id.firstNameField);
        lastNameField = (EditText) findViewById(R.id.lastNameField);
        dobField = (EditText) findViewById(R.id.dobField);
        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        regButton = (Button) findViewById(R.id.regButton);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fName = firstNameField.getText().toString().trim();
                String lName = lastNameField.getText().toString().trim();
                String dob = dobField.getText().toString().trim();
                String email = emailField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();

                if(TextUtils.isEmpty(fName) || TextUtils.isEmpty(lName) || TextUtils.isEmpty(dob) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
                {
                    Toast.makeText(RegisterActivity.this, "All fields are mandatory!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // check whether first and last name length is between 3 and 30 characters
                if(fName.length() < 3 || fName.length() > 30)
                {
                    Toast.makeText(RegisterActivity.this, "First name must be 3-30 characters long!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(lName.length() < 3 || lName.length() > 30)
                {
                    Toast.makeText(RegisterActivity.this, "Last name must be 3-30 characters long!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // email validation
                String emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if(!email.matches(emailRegex))
                {
                    Toast.makeText(RegisterActivity.this, "Invalid email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // display the details in a dialog and return to the previous screen
                String userDetails = "Name: " + fName + " " + lName + "\n" +
                        "Date of birth: " + dob + "\n" +
                        "Email: " + email + "\n" +
                        "Password: " + password;
                AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();
                alertDialog.setMessage(userDetails);
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(RegisterActivity.this, LoginRegisterActivity.class));
                        finish();
                    }
                });
                alertDialog.show();
            }
        });
    }
}
