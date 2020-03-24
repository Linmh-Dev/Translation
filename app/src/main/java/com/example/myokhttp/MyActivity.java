package com.example.myokhttp;

import android.app.Application;
import android.content.Context;

public class MyActivity extends Application {
    private static Context mcontext;

    @Override
    public void onCreate() {
        mcontext=getApplicationContext();
        super.onCreate();
    }

    public static Context getMcontext() {
        return mcontext;
    }
}
