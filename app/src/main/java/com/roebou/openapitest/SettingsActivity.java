package com.roebou.openapitest;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("OpenAPI - Settings");

        final ListView settingsListView = findViewById(R.id.settingsListView) ;
        final TextView apiKeyHeaderTextView = findViewById(R.id.apiKeyHeaderTextView) ;
        final TextView apiKeyTextView = findViewById(R.id.apiKeyTextView) ;
        final ArrayList<String> arrayList = new ArrayList<>() ;
        final ArrayAdapter settingsArrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList) ;

        ParseQuery<ParseObject> settings = new ParseQuery<ParseObject>("Settings") ;

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Grabbing settings...");
        progressDialog.show();

        settings.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (objects.size() > 0 && e == null) {

                    for (ParseObject parseObject : objects) {

                        String settingName = parseObject.get("set_name").toString() ;
                        String settingValue = parseObject.get("set_val").toString() ;

                        if (settingName.contains("APIKey")) {

                            apiKeyTextView.setText(settingValue);

                        } else {

                            arrayList.add(settingName + "\n" + settingValue + "\n");

                        }
                    }

                    progressDialog.dismiss();
                    settingsListView.setAdapter(settingsArrayAdapter);

                } else {
                    e.printStackTrace();
                    FancyToast.makeText(getApplicationContext(), "Error getting settings. Please try again later.", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    progressDialog.dismiss();
                }
            }
        });

    }
}
