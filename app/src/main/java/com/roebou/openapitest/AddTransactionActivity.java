package com.roebou.openapitest;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

public class AddTransactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        setTitle("OpenAPI - New Transaction");

        final ListView apiListView = findViewById(R.id.apiListView) ;
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

                        if (!settingName.contains("APIKey")) {

                            arrayList.add(settingName + "\n");

                            if (settingName.contains("Reversal")) {

                                ArrayList<String> myArrayList= new ArrayList(parseObject.getList("paramNames")) ;
                                for (String apiParam : myArrayList) {
                                    Log.i("DEMO", apiParam) ;

                                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
                                    EditText editTextView = new EditText(getApplicationContext());
                                    editTextView.setGravity(Gravity.CENTER);

                                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                            LinearLayout.LayoutParams.WRAP_CONTENT, 1);

                                    editTextView.setLayoutParams(params);
                                    editTextView.setHint(apiParam.substring(6));
                                    linearLayout.addView(editTextView);

                                }

                            }
                        }

                    }

                    progressDialog.dismiss();
                    apiListView.setAdapter(settingsArrayAdapter);

                } else {
                    e.printStackTrace();
                    FancyToast.makeText(getApplicationContext(), "Error getting settings. Please try again later.", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    progressDialog.dismiss();
                }
            }
        });

    }

    public void submitPressed (View view) {

        Log.i("DEMO", "Submit Pressed") ;

    }
}
