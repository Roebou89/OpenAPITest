package com.roebou.openapitest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivty extends AppCompatActivity {

    private EditText username ;
    private EditText password ;
    private Button loginButton ;
    private Button loginCreateNewAccButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activty);
        setTitle("OpenAPI - Login");

        username = findViewById(R.id.loginUsernameEditText) ;
        password = findViewById(R.id.loginPasswordEditText) ;
        loginButton = findViewById(R.id.loginLoginButton) ;
        loginCreateNewAccButton = findViewById(R.id.loginCreateNewAccButton) ;

    }

    public void loginCreateNewAccIntent (View view) {
        Log.i("DEMO", "Back To Sign Up Activity") ;

        Intent signUp = new Intent(this, SignUpActivity.class) ;
        startActivity(signUp);
    }

    public void loginAct(View view) {
        Log.i("DEMO", "Logging In") ;

        try {
            if (!username.getText().toString().equals("") && !password.getText().toString().equals("")) {

                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Logging In...");
                progressDialog.show();

                ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if (user != null) {

                            FancyToast.makeText(getApplicationContext(), "Login successful!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                        } else {

                                if (e.getMessage().equals("Account already exists for this username.")) {

                                    FancyToast.makeText(getApplicationContext(), "Username already in use.", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                                } else if (e.getMessage().equals("Account already exists for this email address.")) {

                                    FancyToast.makeText(getApplicationContext(), "Email already in use.", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                                } else {

                                    e.printStackTrace();
                                    FancyToast.makeText(getApplicationContext(), "Error signing up user. Please try again later.", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                                }
                        }

                        progressDialog.dismiss();
                    }
                });

                ParseUser user = ParseUser.getCurrentUser() ;

                if (user != null) {

                    Intent homeActivity = new Intent(this, HomeActivity.class) ;
                    startActivity(homeActivity);
                }

            } else {

                FancyToast.makeText(getApplicationContext(), "Please fill in all fields before attempting to login.", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
