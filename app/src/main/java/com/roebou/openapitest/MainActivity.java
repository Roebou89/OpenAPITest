package com.roebou.openapitest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.parse.GetCallback;
import com.parse.LogOutCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main Menu");

    }

    public void goToSignUpAct (View view) {

        Intent signUp = new Intent(this, SignUpActivity.class) ;
        startActivity(signUp);

    }

    public void goToLoginAct (View view) {

            Intent login = new Intent(this, LoginActivty.class) ;
            startActivity(login);

    }

    public void logoutUser (View view) {

        final ParseUser user = ParseUser.getCurrentUser() ;

        if (user != null) {

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Logging Out...");
            progressDialog.show();

            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {

                    if (e == null || e.getMessage().equals("Invalid session token")) {

                        FancyToast.makeText(getApplicationContext(), "User " + user.getUsername() + " successfully logged out!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                    } else {

                            e.printStackTrace();
                            FancyToast.makeText(getApplicationContext(), "Something went wrong. Please try again later.", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                    }

                    progressDialog.dismiss();

                }
            });

        } else {

            FancyToast.makeText(getApplicationContext(), "No user to sign out!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

        }

    }

}
