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
//        ParseObject settings = new ParseObject("Settings") ;
//        settings.put("set_name", "test");
//        settings.put("set_val", "rohan_test_connection");
//
//        final ProgressDialog progressDialog = new ProgressDialog(this) ;
//        progressDialog.setMessage("Saving new setting");
//        progressDialog.show();
//
//        settings.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//
//                if (e == null) {
//                    FancyToast.makeText(getApplicationContext(),"Successful addition!",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
//                } else {
//                    FancyToast.makeText(getApplicationContext(),"Error !",FancyToast.LENGTH_LONG,FancyToast.CONFUSING,true).show();
//                }
//
//                progressDialog.dismiss();
//            }
//        });

    }

    public void goToSignUpAct (View view) {

        final ParseUser user = ParseUser.getCurrentUser() ;

        if (user != null) {

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Logging user out first...");
            progressDialog.show();

            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {

                    if (e == null) {

                        FancyToast.makeText(getApplicationContext(), "User " + user.getUsername() + " successfully logged out!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                    } else {

                        e.printStackTrace();
                        FancyToast.makeText(getApplicationContext(), "Something went wrong. Please try again later.", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                    }

                    progressDialog.dismiss();

                }
            });
        }

        Intent signUp = new Intent(this, SignUpActivity.class) ;
        startActivity(signUp);

    }

    public void goToLoginAct (View view) {

        ParseUser user = ParseUser.getCurrentUser() ;

        if (user == null) {
            Intent login = new Intent(this, LoginActivty.class) ;
            startActivity(login);
        } else {
            FancyToast.makeText(getApplicationContext(), "User already signed in. Please sign out first.", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
        }

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

                    if (e == null) {

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
