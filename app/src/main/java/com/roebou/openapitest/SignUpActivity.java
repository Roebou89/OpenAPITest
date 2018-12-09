package com.roebou.openapitest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        username = findViewById(R.id.suUsernameEditText) ;
        password = findViewById(R.id.suPasswordEditText) ;
        emailAddress = findViewById(R.id.suEmailEditText) ;
        suButton = findViewById(R.id.suButton) ;
        suLoginButton = findViewById(R.id.suLoginButton) ;
    }


    public void signUpLoginIntent (View view) {
        Log.i("DEMO", "Back To Login Activity") ;

        Intent login = new Intent(this, LoginActivty.class) ;
        startActivity(login);
    }

    public void signUpAct(View view) {
        Log.i("DEMO", "Signing Up") ;
    }
}
