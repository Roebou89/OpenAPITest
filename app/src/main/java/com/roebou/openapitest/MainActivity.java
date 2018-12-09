package com.roebou.openapitest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
