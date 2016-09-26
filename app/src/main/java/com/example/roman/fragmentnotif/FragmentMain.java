package com.example.roman.fragmentnotif;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Roman on 22.09.2016.
 */

public class FragmentMain extends Fragment {
    String data = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            data = getArguments().getString("data");
        }
        View v = inflater.inflate(R.layout.fragment_main, null);
        Button btnNextFragment = (Button) v.findViewById(R.id.btnNextFragment);
        Button btnSendNotification = (Button) v.findViewById(R.id.btnSendNotification);

        final TextView txt = (TextView) v.findViewById(R.id.receiveFromFrag);
        txt.setText(data);

        btnSendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager = (NotificationManager)getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);

                Intent intent = new Intent(getContext(), MainActivity.class);
//                intent.addFlags((Intent.FLAG_ACTIVITY_CLEAR_TOP |
//                        Intent.FLAG_ACTIVITY_SINGLE_TOP |
//                        Intent.FLAG_ACTIVITY_NEW_TASK));
                intent.putExtra("from", "true");
                PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

                Notification notification = new Notification.Builder(getContext())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pendingIntent)
                        .setContentTitle("Change Activity?")
                        .setContentText("Go to second app")
                        .build();
                notificationManager.notify(0, notification);
            }
        });

        btnNextFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fr = new FragmentSecond();
                data = txt.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("data", data);
                fr.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .addToBackStack("main")
                        .replace(R.id.activity_main, fr)
                        .commit();
            }
        });

        return v;
    }


}
