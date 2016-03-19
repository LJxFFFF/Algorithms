package com.mycompany.activitydemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    private static final String TAG = "ActivityDemo";
    private LinearLayout mainLayout;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View buttonLaout = layoutInflater.inflate(R.layout.button, null);
        mainLayout.addView(buttonLaout);

        Log.e(TAG, "start onCreaate~~~");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "start onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "start onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "start onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "start onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "start onDestroy");
    }
}
