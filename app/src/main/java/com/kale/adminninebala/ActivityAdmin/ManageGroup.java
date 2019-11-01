package com.kale.adminninebala.ActivityAdmin;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.kale.adminninebala.Adapter.AdapterGroup;
import com.kale.adminninebala.BaseApp;
import com.kale.adminninebala.Http.ApiService;
import com.kale.adminninebala.Http.ConfigRetrofit;
import com.kale.adminninebala.Model.DataGroup;
import com.kale.adminninebala.Model.ResponseGroup;
import com.kale.adminninebala.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageGroup extends BaseApp {

    String group = "group";
    @BindView(R.id.rvGroup)
    RecyclerView rvGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_group);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Group List");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, CreateGroup.class);
                startActivity(i);
            }
        });

        getGroup();
    }

    private void getGroup() {
        ApiService api = ConfigRetrofit.getInstanceRetrofit();
        api.action_group(group).enqueue(new Callback<ResponseGroup>() {
            @Override
            public void onResponse(Call<ResponseGroup> call, Response<ResponseGroup> response) {
                Log.d("Response truck : ", response.message());

                if (response.isSuccessful()) {
                    Boolean status = response.body().isStatus();
                    //String pesan = response.body().getPesan();
                    if (status == true) {

                        List<DataGroup> dataGroups = response.body().getData();
                        AdapterGroup adapterGroup  = new AdapterGroup(context, dataGroups, group);
                        rvGroup.setAdapter(adapterGroup);
                        rvGroup.setLayoutManager(new LinearLayoutManager(context));

//                        data = response.body().getData();
                        Log.d("data response :", String.valueOf(dataGroups.size()));
//                        // Log.d("response truck 2 :" ,data.get(1).getNamaTransporter());
//
//                        setSearch(data);

                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseGroup> call, Throwable t) {
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