package com.example.roman.fragmentnotif;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Roman on 22.09.2016.
 */

public class FragmentSecond extends Fragment {
    String data = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_second, null);
        Button btnBack = (Button) v.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });

        Button btnBackData = (Button) v.findViewById(R.id.btnBackData);
        final EditText editText = (EditText) v.findViewById(R.id.editText);
        if (getArguments() != null) {
            data = getArguments().getString("data");
        }
        editText.setText(data);
        final Fragment current = this;

        btnBackData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
                Fragment main = new FragmentMain();
                Bundle bundle = new Bundle();
                data = editText.getText().toString();
                bundle.putString("data", data);
                main.setArguments(bundle);
                fm.beginTransaction()
                        .replace(R.id.activity_main, main)
                        .remove(current)
                        .commit()
                ;
            }
        });
        return v;
    }
}
