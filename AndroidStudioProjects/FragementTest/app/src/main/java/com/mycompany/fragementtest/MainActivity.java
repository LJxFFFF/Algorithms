package com.mycompany.fragementtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(findViewById(R.id.fragment_container) != null) {

            if(savedInstanceState != null) {
                return;
            }

            Fragment firstFragment = new Fragment();

            firstFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(
                    R.id.fragment_container, firstFragment).commit();
        }


    }
}
