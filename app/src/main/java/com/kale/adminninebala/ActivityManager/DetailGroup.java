package com.kale.adminninebala.ActivityManager;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kale.adminninebala.Adapter.AdapterManagerGroup;
import com.kale.adminninebala.Adapter.AdapterPromo;
import com.kale.adminninebala.BaseApp;
import com.kale.adminninebala.Helper.RbHelper;
import com.kale.adminninebala.Http.ApiService;
import com.kale.adminninebala.Http.ConfigRetrofit;
import com.kale.adminninebala.Model.DataGroup;
import com.kale.adminninebala.Model.DataPromo;
import com.kale.adminninebala.Model.ResponseGroup;
import com.kale.adminninebala.Model.ResponsePromo;
import com.kale.adminninebala.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailGroup extends BaseApp {
    ImageView ivGroup;
    String groupimage, groupname;
    int idmanager, groupid;
    ConnectivityManager conMgr;
    @BindView(R.id.rvPromo)
    RecyclerView rvPromo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_group);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        groupname = getIntent().getStringExtra("groupname");
        groupimage = getIntent().getStringExtra("groupimage");
        groupid = getIntent().getIntExtra("groupid",0);
        idmanager = getIntent().getIntExtra("idmanager",0);

        getSupportActionBar().setTitle(groupname);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        ivGroup =(ImageView) findViewById(R.id.ivGroup);

        Glide.with(context)
                .load(groupimage)
                .into(ivGroup);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AddContentGroup.class);
                i.putExtra("groupname", groupname);
                i.putExtra("groupimage",groupimage);
                i.putExtra("groupid",groupid);
                i.putExtra("idmanager", idmanager);
                startActivity(i);
            }
        });
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {
            getPromo();
        } else {
            Toast.makeText(context, "No Internet Connection",
                    Toast.LENGTH_LONG).show();
        }

    }

    private void getPromo() {
        ApiService api = ConfigRetrofit.getInstanceRetrofit();
        api.action_managerpromo(groupid).enqueue(new Callback<ResponsePromo>() {
            @Override
            public void onResponse(Call<ResponsePromo> call, Response<ResponsePromo> response) {
                Log.d("Response truck : ", response.message());

                if (response.isSuccessful()) {
                    Boolean status = response.body().isStatus();
                    //String pesan = response.body().getPesan();
                    if (status == true) {

                        List<DataPromo> dataPromos = response.body().getData();
                        AdapterPromo adapterPromo = new AdapterPromo(context, dataPromos, groupid);
                        rvPromo.setAdapter(adapterPromo);
                        rvPromo.setLayoutManager(new LinearLayoutManager(context));

//                        data = response.body().getData();
                        Log.d("data response :", String.valueOf(dataPromos.size()));
//                        // Log.d("response truck 2 :" ,data.get(1).getNamaTransporter());
//
//                        setSearch(data);

                    }
                }
            }
            @Override
            public void onFailure(Call<ResponsePromo> call, Throwable t) {
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
