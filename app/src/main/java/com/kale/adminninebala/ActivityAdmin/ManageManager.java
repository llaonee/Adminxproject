package com.kale.adminninebala.ActivityAdmin;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.kale.adminninebala.Adapter.AdapterManager;
import com.kale.adminninebala.BaseApp;
import com.kale.adminninebala.Http.ApiService;
import com.kale.adminninebala.Http.ConfigRetrofit;
import com.kale.adminninebala.Model.DataManager;
import com.kale.adminninebala.Model.ResponseManager;
import com.kale.adminninebala.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageManager extends BaseApp {

    String manager = "manager";
    @BindView(R.id.rvManager)
    RecyclerView rvManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_manager);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Manager List");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AddManager.class);
                startActivity(i);
            }
        });

        getManager();
    }

    private void getManager() {
        ApiService api = ConfigRetrofit.getInstanceRetrofit();
        api.action_manager(manager).enqueue(new Callback<ResponseManager>() {
            @Override
            public void onResponse(Call<ResponseManager> call, Response<ResponseManager> response) {
                Log.d("Response truck : ", response.message());

                if (response.isSuccessful()) {
                    Boolean status = response.body().isStatus();
                    //String pesan = response.body().getPesan();
                    if (status == true) {

                        List<DataManager> dataManagers = response.body().getData();
                        AdapterManager adapterManager  = new AdapterManager(context, dataManagers, manager);
                        rvManager.setAdapter(adapterManager);
                        rvManager.setLayoutManager(new LinearLayoutManager(context));

//                        data = response.body().getData();
                        Log.d("data response :", String.valueOf(dataManagers.size()));
//                        // Log.d("response truck 2 :" ,data.get(1).getNamaTransporter());
//
//                        setSearch(data);

                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseManager> call, Throwable t) {
                Log.d("fail truck : ", t.getCause().toString());
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
