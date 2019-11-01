package com.kale.adminninebala.Adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.kale.adminninebala.Model.DataUser;
import com.kale.adminninebala.R;

import java.util.List;


/**
 * Created by Kale on 12/16/2018.
 */

public class AdapterBroadcast extends RecyclerView.Adapter<AdapterBroadcast.MyHolder> {
    List<DataUser> data;
    Context context;
    String age, married, gender, contentSMS;
    Button bSend;



    public AdapterBroadcast(Context context,List<DataUser> data, String age, String married, String gender, String contentSMS, Button bSend) {
        this.data = data;
        this.context = context;
        this.age = age;
        this.gender = gender;
        this.married = married;
        this.contentSMS = contentSMS;
        this.bSend = bSend;
    }

    @Override
    public AdapterBroadcast.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.list_nohp, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterBroadcast.MyHolder holder, final int position) {
        holder.tvNohp.setText(": "+data.get(position).getPhoneNumber());
        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    // request permission (see result in onRequestPermissionsResult() method)
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.SEND_SMS},
                            1);
                }
                getSend();


            }
            public void getSend() {

                    // permission already granted run sms send
                   // sendSms(phone, message);

                try {

                    SmsManager smsManager = SmsManager.getDefault();
                    //ArrayList<String> msgArray = smsManager.divideMessage(contentSMS);

                    smsManager.sendTextMessage(data.get(position).getPhoneNumber(), null, contentSMS, null, null);
                    Toast.makeText(context, "Message Sent", Toast.LENGTH_LONG).show();
                } catch (Exception ex) {
                    Toast.makeText(context, ex.getMessage().toString(), Toast.LENGTH_LONG).show();
                    ex.printStackTrace();
                }
            }


        });

    }
    @Override
    public int getItemCount() {
        return data.size();
        }
    public static class MyHolder extends RecyclerView.ViewHolder {
        TextView tvNohp;

        public MyHolder(View itemView) {
            super(itemView);
            tvNohp = itemView.findViewById(R.id.tvNohp);

        }
    }
    }

