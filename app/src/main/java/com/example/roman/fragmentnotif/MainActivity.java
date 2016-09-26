package com.example.roman.fragmentnotif;

import android.support.v4.app.Fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    static boolean isOpen;
    String receive = "";


    @Override
    protected void onResume() {
        super.onResume();
        receive = getIntent().getStringExtra("from");
        if (isOpen && receive.equals("true")) {
            Fragment frSecond = new FragmentSecond();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_main, frSecond)
                    .commit();
            receive = "false";
        } else if (!isOpen) {
            Fragment frMain = new FragmentMain();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_main, frMain)
                    .commit();
        }
        isOpen = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //isOpen = false;
    }
}
