package com.roebou.openapitest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivty extends AppCompatActivity {

    private EditText username ;
    private EditText password ;
    private Button loginButton ;
    private Button loginCreateNewAccButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activty);

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

    public void loginAct (View view) {
        Log.i("DEMO", "Logging In") ;
    }
}
