package com.roebou.openapitest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseObject settings = new ParseObject("Settings") ;
        settings.put("set_name", "test");
        settings.put("set_val", "rohan_test_connection");

        final ProgressDialog progressDialog = new ProgressDialog(this) ;
        progressDialog.setMessage("Saving new setting");
        progressDialog.show();

        settings.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if (e == null) {
                    FancyToast.makeText(getApplicationContext(),"Successful addition!",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                } else {
                    FancyToast.makeText(getApplicationContext(),"Error !",FancyToast.LENGTH_LONG,FancyToast.CONFUSING,true).show();
                }

                progressDialog.dismiss();
            }
        });

    }

    public void goToSignUpAct (View view) {
        Intent signUp = new Intent(this, SignUpActivity.class) ;
        startActivity(signUp);
    }

    public void goToLoginAct (View view) {
        Intent login = new Intent(this, LoginActivty.class) ;
        startActivity(login);
    }


}
