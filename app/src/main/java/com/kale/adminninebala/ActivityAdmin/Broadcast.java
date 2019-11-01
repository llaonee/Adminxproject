package com.kale.adminninebala.ActivityAdmin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.kale.adminninebala.Adapter.AdapterGroupManager;
import com.kale.adminninebala.Adapter.AdapterReligion;
import com.kale.adminninebala.App.AppController;
import com.kale.adminninebala.BaseApp;
import com.kale.adminninebala.Model.DataGroupManager;
import com.kale.adminninebala.Model.DataReligion;
import com.kale.adminninebala.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Broadcast extends BaseApp {
    EditText smsBody;
    Button smsManagerBtn;

    ProgressDialog pDialog;

    ConnectivityManager conMgr;
    String age, gender, married, contentSMS, noku, religion;
    int broadcastchoice;
    private Spinner spGender, spMarital, spAge, spReligion;

    AdapterReligion adapter;
    List<DataReligion> listReligion = new ArrayList<DataReligion>();



    public static final String urlgetgroup = Server.URL + "GetReligion.php";
    private static final String TAG = AddManager.class.getSimpleName();

    public static final String TAG_RELIGIONID = "religion_id" ;
    public static final String TAG_RELIGION = "religion";
    public static final String TAG_ALLRELIGION = "all_religion";
    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        getSupportActionBar().setTitle("Broadcast");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }
              smsBody = (EditText) findViewById(R.id.smsBody);
              smsManagerBtn = (Button) findViewById(R.id.smsManager);
              broadcastchoice = getIntent().getIntExtra("broadcast",0);

            /*smsManagerBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    sendSmsByManager();
                }
            });*/
            spGender = (Spinner) findViewById(R.id.spGender);
            spMarital = (Spinner) findViewById(R.id.spMarital);
            spAge = (Spinner) findViewById(R.id.spAge);
            spReligion = (Spinner) findViewById(R.id.spReligion);
            smsManagerBtn = (Button) findViewById(R.id.smsManager);

        spReligion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                // txt_hasil.setText("Pendidikan Terakhir : " + listPendidikan.get(position).getPendidikan());
                religion = listReligion.get(position).getReligion();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        adapter = new AdapterReligion(Broadcast.this, listReligion);

        spReligion.setAdapter(adapter);



            smsManagerBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    contentSMS = smsBody.getText().toString();
                    age = String.valueOf(spAge.getSelectedItem());
                    gender = String.valueOf(spGender.getSelectedItem());
                    married = String.valueOf(spMarital.getSelectedItem());

                   // Toast.makeText(context, age + gender + married + contentSMS, Toast.LENGTH_SHORT).show();
                    //getSend();

                    /*if (age == "All age" && gender =="All Gender" && married == "All Status"){
                        getAllBroadcast();
                    }else {
                        getBroadcast();
                    }*/
                    Intent i = new Intent(context, DetailBroadcast.class);
                    i.putExtra("age", age);
                    i.putExtra("gender",gender);
                    i.putExtra("status",married);
                    i.putExtra("sms",contentSMS);
                    i.putExtra("broadcast", broadcastchoice);
                    i.putExtra("religion", religion);

                    context.startActivity(i);

                }

            });

            getspGender();
            getspMarital();
            getAge();
            getReligion();

        }

    private void getReligion() {
        listReligion.clear();
        DataReligion itemall = new DataReligion();
        itemall.setReligion("All Religion");
        listReligion.add(itemall);
        pDialog = new ProgressDialog(context);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading...");
        showDialog();

        // Creating volley request obj
        JsonArrayRequest jArr = new JsonArrayRequest(urlgetgroup,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, response.toString());

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);

                                DataReligion item = new DataReligion();

                                item.setReligion_id(obj.getString(TAG_RELIGIONID));
                                item.setReligion(obj.getString(TAG_RELIGION));
                                listReligion.add(item);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();

                        hideDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jArr);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public void getSend() {
        // menambahkan phone number ke URI data
        Uri uri = Uri.parse("smsto:" + noku);
        // membuat intent baru dengan ACTION_SENDTO
        Intent smsSIntent = new Intent(Intent.ACTION_SENDTO, uri);
        // menambahkan isi SMS pada field sms_body
        smsSIntent.putExtra("sms_body", contentSMS);
        try{
            startActivity(smsSIntent);
        } catch (Exception ex) {
            Toast.makeText(context, "Pengiriman SMS Gagal...",
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
        public void getspGender(){
            spGender = (Spinner) findViewById(R.id.spGender);
            List<String> list = new ArrayList<String>();
            list.add("All Gender");
            list.add("Male");
            list.add("Female");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spGender.setAdapter(dataAdapter);
        }
        public void getspMarital(){
            spMarital= (Spinner) findViewById(R.id.spMarital);
            List<String> list = new ArrayList<String>();
            list.add("All Status");
            list.add("Single");
            list.add("Married");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spMarital.setAdapter(dataAdapter);
        }
        public void getAge(){
            spAge= (Spinner) findViewById(R.id.spAge);
            List<String> list = new ArrayList<String>();
            list.add("All Age");
            list.add("anak-anak");
            list.add("remaja");
            list.add("dewasa");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spAge.setAdapter(dataAdapter);
        }
        private void getAllBroadcast(){

        }
/*

    public void sendSmsByManager() {
        try {
            // Get the default instance of the SmsManager
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber.getText().toString(),
                    null,
                    smsBody.getText().toString(),
                    null,
                    null);
            Toast.makeText(getApplicationContext(), "SMS Berhasil Dikirim!",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),"Pengiriman SMS Gagal...",
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}