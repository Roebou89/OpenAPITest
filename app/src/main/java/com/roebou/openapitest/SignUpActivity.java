package com.roebou.openapitest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpActivity extends AppCompatActivity {

    private EditText username ;
    private EditText password ;
    private EditText emailAddress ;
    private Button suButton ;
    private Button suLoginButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("OpenAPI - Sign Up");

        username = findViewById(R.id.suUsernameEditText) ;
        password = findViewById(R.id.suPasswordEditText) ;
        emailAddress = findViewById(R.id.suEmailEditText) ;
        suButton = findViewById(R.id.suButton) ;
        suLoginButton = findViewById(R.id.suLoginButton) ;

        if (ParseUser.getCurrentUser() != null) {

            goToHomeActivity() ;

        }
    }


    public void signUpLoginPressed (View view) {
        Log.i("DEMO", "Back To Login Activity") ;

        Intent login = new Intent(this, LoginActivty.class) ;
        startActivity(login);
    }

    public void signUpPressed(View view) {
        Log.i("DEMO", "Signing Up");

        ParseUser user = new ParseUser();

        try {
            if (!username.getText().toString().equals("") && !password.getText().toString().equals("") && !emailAddress.getText().toString().equals("")) {

                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                user.setEmail(emailAddress.getText().toString());

                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Signing Up...");
                progressDialog.show();

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {

                        if (e == null) {

                            FancyToast.makeText(getApplicationContext(), "User " + username.getText().toString() + " successfully signed up!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            progressDialog.dismiss();

                            goToHomeActivity() ;

                        } else {

                            if (e.getMessage().equals("Account already exists for this username.")) {

                                FancyToast.makeText(getApplicationContext(), "Username already in use.", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                            } else if (e.getMessage().equals("Account already exists for this email address.")) {

                                FancyToast.makeText(getApplicationContext(), "Email already in use.", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                            } else {

                                e.printStackTrace();
                                FancyToast.makeText(getApplicationContext(), "Error signing up user. Please try again later.", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                            }

                            progressDialog.dismiss();

                        }

                    }
                });

            } else {

                FancyToast.makeText(getApplicationContext(), "Please fill in all fields before attempting to create your user.", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goToHomeActivity () {

        Intent homeAct = new Intent(SignUpActivity.this, HomeActivity.class) ;
        startActivity(homeAct);
    }
}