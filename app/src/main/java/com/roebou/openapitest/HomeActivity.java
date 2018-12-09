package com.roebou.openapitest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("OpenAPI - Home");

    }

    public void logoutUser (View view) {

        ParseUser user = ParseUser.getCurrentUser() ;

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Logging Out...");
            progressDialog.show();

            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {

                    if (e == null) {

                        FancyToast.makeText(getApplicationContext(), "User successfully logged out!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                    } else {

                        e.printStackTrace();
                        FancyToast.makeText(getApplicationContext(), "Something went wrong. Please try again later.", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                    }

                    progressDialog.dismiss();

                }
            });

        user = ParseUser.getCurrentUser() ;

        if (user == null) {
            Intent loginActivity = new Intent(this, LoginActivty.class) ;
            startActivity(loginActivity);
        }
    }
}
