package com.roebou.openapitest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class HomeActivity extends AppCompatActivity {

    private Button homeLogoutButton ;
    private Button homeAddOrgButton ;
    private Button homeAddTransactionButton ;
    private Button homeMyTransactionsButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("OpenAPI - Home");

        homeLogoutButton = findViewById(R.id.homeLogoutButton) ;
        homeAddOrgButton = findViewById(R.id.homeAddOrgButton) ;
        homeAddTransactionButton = findViewById(R.id.homeAddTransactionButton) ;
        homeMyTransactionsButton = findViewById(R.id.homeMyTransactionsButton) ;

    }

    public void addOrgPressed (View view) {

        goToAddOrganisation();

    }

    public void addTransactionPressed (View view) {
        goToAddTransaction();
    }

    public void myTransactionsPressed (View view) {
        //goToActivity (MyTransactionsActivty.class);
    }

    public void settingsPressed (View view) {

        goToSettingsActivity();

    }

    public void logoutPressed (View view) {

        ParseUser user = ParseUser.getCurrentUser() ;

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Logging Out...");
            progressDialog.show();

            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {

                    if (e == null) {

                        FancyToast.makeText(getApplicationContext(), "User successfully logged out!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                        goToMainActivity();

                    } else {

                        e.printStackTrace();
                        FancyToast.makeText(getApplicationContext(), "Something went wrong. Please try again later.", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                    }

                    progressDialog.dismiss();

                }
            });

    }

    private void goToMainActivity() {

        Intent activity = new Intent(HomeActivity.this, MainActivity.class) ;
        startActivity(activity);
    }

    private void goToAddOrganisation() {

        Intent activity = new Intent(HomeActivity.this, AddOrganisationActivty.class) ;
        startActivity(activity);
    }

    private void goToAddTransaction() {

        Intent activity = new Intent(HomeActivity.this, AddTransactionActivity.class) ;
        startActivity(activity);
    }

    private void goToMyTransactions() {

        Intent activity = new Intent(HomeActivity.this, AddOrganisationActivty.class) ;
        startActivity(activity);
    }

    private void goToSettingsActivity() {

        Intent activity = new Intent(HomeActivity.this, SettingsActivity.class) ;
        startActivity(activity);
    }

}
