package com.example.roman.fragmentnotif;

import android.support.v4.app.Fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    boolean isOpen = false;
    String receive = "";


    @Override
    protected void onResume() {
        super.onResume();
            receive = getIntent().getStringExtra("from");
            if (isOpen && receive.equals("true")) {
                Fragment fr = new FragmentSecond();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.activity_main, fr)
                        .commit();
                receive = "false";
            }
        isOpen = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fr = new FragmentMain();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_main, fr)
                .commit();
    }
}
