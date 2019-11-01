package com.kale.adminninebala.ActivityAdmin;

import android.app.ProgressDialog;
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
import com.kale.adminninebala.Model.DataAdmin;
import com.kale.adminninebala.Model.ResponseLoginAdmin;
import com.kale.adminninebala.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginAdmin extends BaseApp {
    @BindView(R.id.etUsername)
    EditText loginUsername;
    @BindView(R.id.etPassword)
    EditText loginPassword;
    @BindView(R.id.bLogin)
    Button btnSignIn;

    ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Super Admin");
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
            loading = ProgressDialog.show(this,"", "Loading Please wait..", true);
            loading.show();
            ApiService api = ConfigRetrofit.getInstanceRetrofit();
            Call<ResponseLoginAdmin> call = api.action_loginAdmin(username, password);
            Log.d("tes", username+" "+ password);
            call.enqueue(new Callback<ResponseLoginAdmin>() {
                @Override
                public void onResponse(Call<ResponseLoginAdmin> call, Response<ResponseLoginAdmin> response) {
                    Log.d("response login : ", response.message());
                    if (response.isSuccessful()) {
                        boolean result = response.body().isStatus();
                        String pesan = response.body().getPesan();
                        if (result == true) {
                            loading.dismiss();
                            sesi.createLoginSession("1");
                            List<DataAdmin> data = response.body().getData();
                            //sesi.setNama(data.get(0).getNamaUser());
                            sesi.setAdminId(data.get(0).getAdminId());
                            sesi.setAdminName(data.get(0).getAdminName());
                            sesi.setAdminEmail(data.get(0).getAdminEmail());
                            sesi.setAdminNohp(data.get(0).getAdminNohp());

                            Log.d("login user :" , String.valueOf(sesi.getAdminId()));
                            startActivity(new Intent(context, BerandaAdmin.class));
                            finish();

                        } else {
                            loading.dismiss();
                            //RbHelper.pesan(context, pesan);
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginAdmin.this);
                            builder.setMessage("Username atau Password salah")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseLoginAdmin> call, Throwable t) {

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
