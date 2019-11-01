package com.kale.adminninebala.ActivityAdmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.kale.adminninebala.BaseApp;
import com.kale.adminninebala.Helper.RbHelper;
import com.kale.adminninebala.R;

public class ContentUser extends BaseApp {
    LinearLayout llZipcode, llUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_user);
        getSupportActionBar().setTitle("Content User");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        llZipcode = (LinearLayout) findViewById(R.id.llZipcode);
        llUserList = (LinearLayout) findViewById(R.id.llUserList);
        llZipcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, AddZipcode.class);
                startActivity(i);
            }
        });
        llUserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RbHelper.pesan(context,"belum");
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
