package com.roebou.openapitest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class AddOrganisationActivty extends AppCompatActivity {

    private Button addOrgAddButton ;
    private EditText addOrgOrgNameEditText ;
    private EditText addOrgOrgShortcodeEditText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_organisation_activty);
        setTitle("OpenAPI - Add Organisation");

        addOrgAddButton = findViewById(R.id.addOrgAddButton) ;
        addOrgOrgNameEditText = findViewById(R.id.addOrgOrgNameEditText) ;
        addOrgOrgShortcodeEditText = findViewById(R.id.addOrgOrgShortcodeEditText) ;
    }

    public void addOrgPressed (View view) {

        try {

            if (!addOrgOrgNameEditText.getText().toString().equals("") && !addOrgOrgShortcodeEditText.getText().toString().equals("")) {

                if (addOrgOrgShortcodeEditText.getText().toString().length() != 6 ) {

                    FancyToast.makeText(getApplicationContext(), "Please enter a valid 6 digit Shortcode", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                } else {

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Saving Organisation...");
                    progressDialog.show();

                    ParseObject organisation = new ParseObject("Organisations");
                    organisation.put("Org_Name", addOrgOrgNameEditText.getText().toString());
                    organisation.put("Org_ShortCode", Integer.parseInt(addOrgOrgShortcodeEditText.getText().toString()));

                    organisation.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {

                                FancyToast.makeText(getApplicationContext(), "Saved organisation successfully!", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();

                                goToActivity();

                            } else {

                                FancyToast.makeText(getApplicationContext(), "Unable to add organisation. Please try again later.", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                            }
                        }
                    });
                }

            } else {

                FancyToast.makeText(getApplicationContext(), "Please fill in all fields before attempting to save a new organisation.", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    private void goToActivity () {

        Intent homeAct = new Intent(AddOrganisationActivty.this, HomeActivity.class) ;
        startActivity(homeAct);

    }
}
