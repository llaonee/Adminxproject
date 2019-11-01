package com.kale.adminninebala.ActivityManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.kale.adminninebala.ActivityAdmin.LoginOption;
import com.kale.adminninebala.Adapter.AdapterGroup;
import com.kale.adminninebala.Adapter.AdapterManagerGroup;
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

public class BerandaManager extends BaseApp
        implements NavigationView.OnNavigationItemSelectedListener {

    String group;
    int idmanager;
    @BindView(R.id.rvManagergroup)
    RecyclerView rvManagergroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        group = sesi.getManagerGroup();
        idmanager = sesi.getIdManager();
        getSupportActionBar().setTitle("X project");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getGroupManager();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);

        try {

            View header = ((NavigationView) findViewById(R.id.nav_view)).getHeaderView(0);

            TextView tvProfileName = header.findViewById(R.id.tvName);
            TextView tvProfileEmail = header.findViewById(R.id.tvEmail);

            tvProfileName.setText(sesi.getManagerName());
            tvProfileEmail.setText(sesi.getManagerGroup());


            //kontak Profile


        } catch (Exception e) {
            e.printStackTrace();
        }

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void getGroupManager() {
        ApiService api = ConfigRetrofit.getInstanceRetrofit();
        api.action_managergroup(group).enqueue(new Callback<ResponseGroup>() {
            @Override
            public void onResponse(Call<ResponseGroup> call, Response<ResponseGroup> response) {
                Log.d("Response truck : ", response.message());

                if (response.isSuccessful()) {
                    Boolean status = response.body().isStatus();
                    //String pesan = response.body().getPesan();
                    if (status == true) {

                        List<DataGroup> dataGroups = response.body().getData();
                        AdapterManagerGroup adapterManagerGroup = new AdapterManagerGroup(context, dataGroups, group, idmanager);
                        rvManagergroup.setAdapter(adapterManagerGroup);
                        rvManagergroup.setLayoutManager(new LinearLayoutManager(context));

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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.beranda_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {


        } else if (id == R.id.nav_share) {

            startActivity(new Intent(context, LoginOption.class));
            sesi.logout();
            finish();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
