package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            ContentFragment contentFragment = new ContentFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, contentFragment)
                    .commit();
        }
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Remove the default behavior of recreating the fragments
    }
}