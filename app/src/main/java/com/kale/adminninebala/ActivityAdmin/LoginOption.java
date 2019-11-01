package com.kale.adminninebala.ActivityAdmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.kale.adminninebala.ActivityManager.LoginManager;
import com.kale.adminninebala.BaseApp;
import com.kale.adminninebala.R;

public class LoginOption extends BaseApp {

    ImageView ivManager, ivAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_option);
        ivAdmin = (ImageView) findViewById(R.id.ivAdmin);
        ivManager = (ImageView) findViewById(R.id.ivManager);

        ivAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, LoginAdmin.class);
                startActivity(i);
            }
        });
        ivManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, LoginManager.class);
                startActivity(i);
            }
        });
    }
}
