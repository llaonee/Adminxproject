package com.kale.adminninebala.ActivityManager;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kale.adminninebala.BaseApp;
import com.kale.adminninebala.Helper.RbHelper;
import com.kale.adminninebala.Http.ApiService;
import com.kale.adminninebala.Http.ConfigRetrofit;
import com.kale.adminninebala.Model.DataManager;
import com.kale.adminninebala.Model.ResponseLoginManager;
import com.kale.adminninebala.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginManager extends BaseApp {

    @BindView(R.id.etUsername)
    EditText loginUsername;
    @BindView(R.id.etPassword)
    EditText loginPassword;
    @BindView(R.id.bLogin)
    Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_manager);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Manager");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @OnClick(R.id.bLogin)
    public void onViewClicked() {

        String username = loginUsername.getText().toString();
        String password = loginPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {

            RbHelper.pesan(context, "tidak boleh kosong");
        } else {
            ApiService api = ConfigRetrofit.getInstanceRetrofit();
            Call<ResponseLoginManager> call = api.action_loginManager(username, password);
            call.enqueue(new Callback<ResponseLoginManager>() {
                @Override
                public void onResponse(Call<ResponseLoginManager> call, Response<ResponseLoginManager> response) {
                    Log.d("response login : ", response.message());
                    if (response.isSuccessful()) {
                        boolean result = response.body().isStatus();
                        String pesan = response.body().getPesan();
                        if (result == true) {
                            sesi.createLoginSession("2");
                            List<DataManager> data = response.body().getData();
                            //sesi.setNama(data.get(0).getNamaUser());
                            sesi.setIdManager(data.get(0).getIdManager());
                            sesi.setManagerName(data.get(0).getManagerName());
                            sesi.setManagerGroup(data.get(0).getManagerGroup());

                            Log.d("login manager :" , String.valueOf(sesi.getIdManager()));
                            startActivity(new Intent(context, BerandaManager.class));
                            finish();


                        } else {
                            //RbHelper.pesan(context, pesan);
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginManager.this);
                            builder.setMessage(pesan)
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseLoginManager> call, Throwable t) {

//                    Log.d("error login : ", t.getCause().toString());


                }
            });

        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

