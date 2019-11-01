package com.kale.adminninebala.ActivityAdmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.kale.adminninebala.BaseApp;
import com.kale.adminninebala.R;

public class BroadcastChoice extends BaseApp {
    LinearLayout llAplication, llSMS;
    int broadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_choice);
        llAplication = (LinearLayout) findViewById(R.id.llAplication);
        llSMS = (LinearLayout) findViewById(R.id.llSMS);
        llAplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Broadcast.class);
                i.putExtra("broadcast", broadcast = 1);
                startActivity(i);

            }
        });
        llSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Broadcast.class);
                i.putExtra("broadcast", broadcast = 2);
                startActivity(i);
            }
        });
    }
}
