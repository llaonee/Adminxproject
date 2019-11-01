package com.kale.adminninebala.ActivityAdmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kale.adminninebala.BaseApp;
import com.kale.adminninebala.Helper.RbHelper;
import com.kale.adminninebala.Http.ApiService;
import com.kale.adminninebala.Http.ConfigRetrofit;
import com.kale.adminninebala.Model.ResponseLoginAdmin;
import com.kale.adminninebala.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddZipcode extends BaseApp {
    EditText etZipcode, etDesc;
    Button  bAddZipcode;
    String zipcode, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_zipcode);
        etZipcode = (EditText) findViewById(R.id.etZipcode);
        etDesc = (EditText) findViewById(R.id.etDesc);
        bAddZipcode = (Button) findViewById(R.id.bAddZipcode);
        getSupportActionBar().setTitle("Add Zipcode");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bAddZipcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zipcode = etZipcode.getText().toString();
                description = etDesc.getText().toString();
                if (zipcode.length()!=5 ){
                    etZipcode.setError("zipcode salah");
                }else if(zipcode.isEmpty()) {
                    etZipcode.setError("ndak boleh kosong");
                }else if (description.isEmpty()){
                    etDesc.setError("ndak boleh kosong");
                }else {
                    addzipcode();
                }

            }

            private void addzipcode() {
                ApiService api = ConfigRetrofit.getInstanceRetrofit();
                api.action_addZipcode(zipcode, description).enqueue(new Callback<ResponseLoginAdmin>() {


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
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
