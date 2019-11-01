package com.kale.adminninebala;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kale.adminninebala.Helper.SessionManager;

/**
 * Created by nandoseptianhusni on 11/13/17.
 */

public class BaseApp extends AppCompatActivity {

    protected Context context ;
    protected SessionManager sesi ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this ;
        sesi = new SessionManager(context);

    }
}
