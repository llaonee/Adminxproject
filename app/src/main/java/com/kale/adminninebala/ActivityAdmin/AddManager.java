package com.kale.adminninebala.ActivityAdmin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.kale.adminninebala.Adapter.AdapterGroupManager;
import com.kale.adminninebala.App.AppController;
import com.kale.adminninebala.BaseApp;
import com.kale.adminninebala.Model.DataGroupManager;
import com.kale.adminninebala.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddManager extends BaseApp {
    EditText etUsername, etPassword, etManagername;
    Spinner spGroup;
    Button bAddmanager;
    String username, password, group, managername;
    int success;
    ProgressDialog pDialog;
    ConnectivityManager conMgr;
    AdapterGroupManager adapter;
    List<DataGroupManager> listGroup = new ArrayList<DataGroupManager>();

    public static final String urlgetgroup = Server.URL + "GetGroup.php";
    public static final String urladdmanager = Server.URL + "AddManager.php";

    private static final String TAG = AddManager.class.getSimpleName();

    public static final String TAG_GROUPID = "group_id" ;
    public static final String TAG_GROUPNAME = "group_name";
    public static final String TAG_GROUPDESC = "group_desc";
    public static final String TAG_GROUPIMAGE = "group_image";
    public static final String TAG_STATUSMANAGER = "status_manager";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_manager);

        getSupportActionBar().setTitle("Add Manager");
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
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etManagername =(EditText) findViewById(R.id.etManagername);
        spGroup = (Spinner) findViewById(R.id.spGroup);
        bAddmanager = (Button) findViewById(R.id.bAddmanager);

        spGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
               // txt_hasil.setText("Pendidikan Terakhir : " + listPendidikan.get(position).getPendidikan());
                group = listGroup.get(position).getGroup_name();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        adapter = new AdapterGroupManager(AddManager.this, listGroup);
        spGroup.setAdapter(adapter);

        callData();

        bAddmanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                managername = etManagername.getText().toString();
                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    //Toast.makeText(getApplicationContext(), dayy, Toast.LENGTH_SHORT).show();
                    addmanager();
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void callData() {
        listGroup.clear();

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

                                DataGroupManager item = new DataGroupManager();

                                item.setGroup_id(obj.getString(TAG_GROUPID));
                                item.setGroup_name(obj.getString(TAG_GROUPNAME));
                                item.setGroup_desc(obj.getString(TAG_GROUPDESC));
                                item.setGroup_image(obj.getString(TAG_GROUPIMAGE));
                                item.setStatus_manager(obj.getString(TAG_STATUSMANAGER));

                                listGroup.add(item);
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


    private void addmanager() {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Register ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, urladdmanager, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Successfully Register!", jObj.toString());

                        Intent i = new Intent(context,ManageManager.class);
                        startActivity(i);
                        finish();

                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();


                    } else {
                        etUsername.setError("Username is already taken");

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "LoginAdmin Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("managername", managername);
                params.put("username", username);
                params.put("password", password);
                params.put("group", group);
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }


    /*@Override
    public void onBackPressed() {
        Intent intent = new Intent(context, LoginAdmin.class);
        finish();
        startActivity(intent);
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
