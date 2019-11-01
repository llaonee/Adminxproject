package com.kale.adminninebala.ActivityAdmin;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kale.adminninebala.Adapter.AdapterBroadcast;
import com.kale.adminninebala.BaseApp;
import com.kale.adminninebala.Helper.RbHelper;
import com.kale.adminninebala.Http.ApiService;
import com.kale.adminninebala.Http.ConfigRetrofit;
import com.kale.adminninebala.Model.DataUser;
import com.kale.adminninebala.Model.ResponseLoginAdmin;
import com.kale.adminninebala.Model.ResponseUser;
import com.kale.adminninebala.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBroadcast extends BaseApp {
    @BindView(R.id.rvUser)
    RecyclerView rvUser;
    String age, gender, married,contentSMS, religion;
    TextView tvGender, tvStatus, tvAge, tvContenSMS, tvReligion;
    Button bSend;
    int broadcastchoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_broadcast);
        ButterKnife.bind(this);
        age = getIntent().getStringExtra("age");
        gender=getIntent().getStringExtra("gender");
        married=getIntent().getStringExtra("status");
        contentSMS=getIntent().getStringExtra("sms");
        religion = getIntent().getStringExtra("religion");

        broadcastchoice = getIntent().getIntExtra("broadcast", 0);
        tvGender = (TextView) findViewById(R.id.tvGender);
        tvStatus = (TextView) findViewById(R.id.tvStatus);
        tvAge = (TextView) findViewById(R.id.tvAge);
        tvContenSMS = (TextView) findViewById(R.id.tvContenSMS);
        tvReligion = (TextView) findViewById(R.id.tvReligion);
        bSend = (Button) findViewById(R.id.bSend);
        tvGender.setText(": "+gender);
        tvStatus.setText(": "+married);
        tvAge.setText(": "+age);
        tvReligion.setText(": "+religion);
        tvContenSMS.setText(" : "+contentSMS);

                if (broadcastchoice == 1){
                    bSend.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getBroadcastAplication();
                        }
                    });
                }else {
                    //getBroadcast();
                    getBroadcastSMS();
                }
    }
    private void getBroadcastAplication() {
        ApiService api = ConfigRetrofit.getInstanceRetrofit();
        api.action_broadcastApp(contentSMS, gender, married, religion, age).enqueue(new Callback<ResponseLoginAdmin>() {


            @Override
            public void onResponse(Call<ResponseLoginAdmin> call, Response<ResponseLoginAdmin> response) {
                Log.d("response checklist 1 :", response.message());

                if (response.isSuccessful()) {
                    boolean status = response.body().isStatus();
                    String pesan = response.body().getPesan();
                    if (status == true) {
                        RbHelper.pesan(context, pesan);

                    } else {
                        RbHelper.pesan(context, pesan);
                    }

                }

            }
            @Override
            public void onFailure(Call<ResponseLoginAdmin> call, Throwable t) {
                Log.d("error input : ", t.getCause().toString());

            }
        });
    }
    private void getBroadcastSMS() {
        ApiService api = ConfigRetrofit.getInstanceRetrofit();
        api.action_broadcast(age, married, gender).enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                Log.d("Response truck : ", response.message());

                if (response.isSuccessful()) {
                    Boolean status = response.body().isStatus();
                    //String pesan = response.body().getPesan();
                    if (status == true) {

                        List<DataUser> dataUsers = response.body().getData();
                        AdapterBroadcast adapterBroadcast  = new AdapterBroadcast(context, dataUsers, age, married, gender, contentSMS, bSend);
                        rvUser.setAdapter(adapterBroadcast);
                        rvUser.setLayoutManager(new LinearLayoutManager(context));

//                        data = response.body().getData();
                        Log.d("data response :", String.valueOf(dataUsers.size()));
//                        // Log.d("response truck 2 :" ,data.get(1).getNamaTransporter());
//
//                        setSearch(data);

                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                Log.d("fail truck : ", t.getCause().toString());
            }
        });
    }
}
