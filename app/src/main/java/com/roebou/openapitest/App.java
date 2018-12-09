package com.roebou.openapitest;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("iPl1Vn9PF3wdKEGmbJtKlDrEV3VI00bGbmB8qIS0")
                .clientKey("YQ6ZssjTfKXAe24YnVoNgCCUufKuIv2Xhy5SqF60")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
