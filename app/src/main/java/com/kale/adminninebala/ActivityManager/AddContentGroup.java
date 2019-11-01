package com.kale.adminninebala.ActivityManager;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kale.adminninebala.ActivityAdmin.CreateGroup;
import com.kale.adminninebala.ActivityAdmin.Server;
import com.kale.adminninebala.App.AppController;
import com.kale.adminninebala.BaseApp;
import com.kale.adminninebala.Helper.RbHelper;
import com.kale.adminninebala.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddContentGroup extends BaseApp {

    ImageView ivPromoImage;
    EditText etPromoName, etPromoDesc;
    TextView tvDatePromo, tvEndDatePromo;
    Button bDate, bAddPromo, bEndDate;
    String promoname, promodesc, promodate, groupimage, groupname, promoenddate;
    int idmanager, groupid;

    private int mYear, mMonth, mDay;

    ConnectivityManager conMgr;

    int success;
    int PICK_IMAGE_REQUEST = 1;
    Bitmap bitmap, decoded;
    int bitmap_size = 60; // range 1 - 100
    private static final String TAG = CreateGroup.class.getSimpleName();

    private String UPLOAD_URL = Server.URL + "AddPromo.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";


    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_content_group);

        getSupportActionBar().setTitle("Add Promo");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        groupname = getIntent().getStringExtra("groupname");
        groupimage = getIntent().getStringExtra("groupimage");
        groupid = getIntent().getIntExtra("groupid",0);
        idmanager = getIntent().getIntExtra("idmanager",0);
        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        ivPromoImage =(ImageView) findViewById(R.id.ivPromoImage);
        etPromoName =(EditText) findViewById(R.id.etPromoName);
        etPromoDesc =(EditText) findViewById(R.id.etPromoDesc);
        tvDatePromo =(TextView) findViewById(R.id.tvDatePromo);
        tvEndDatePromo =(TextView) findViewById(R.id.tvEndDatePromo);
        bDate = (Button) findViewById(R.id.bDate);
        bEndDate = (Button) findViewById(R.id.bEndDate);
        bAddPromo = (Button) findViewById(R.id.bAddPromo);

        ivPromoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage();
            }
        });
        bEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEndDate();
            }
        });
        bDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate();
            }
        });

        bAddPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promoname = etPromoName.getText().toString();
                promodesc = etPromoDesc.getText().toString();


                    if (conMgr.getActiveNetworkInfo() != null
                            && conMgr.getActiveNetworkInfo().isAvailable()
                            && conMgr.getActiveNetworkInfo().isConnected()) {
                        if (promoname.isEmpty()){
                            etPromoName.setError("name is empty");
                        }else if (promodesc.isEmpty()) {
                            etPromoDesc.setError("desc is empty");
                        }else if (promodate == null) {
                            RbHelper.pesan(context, "date is empty");
                        }else if (promoenddate == null) {
                            RbHelper.pesan(context, "date is empty");
                        }else {
                            AddPromo();
                        }
                    } else {
                        Toast.makeText(context, "No Internet Connection",
                                Toast.LENGTH_LONG).show();
                    }

            }
        });

    }

    private void getEndDate() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    String hari, bulan;

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        if (dayOfMonth <10){
                            hari = "0"+dayOfMonth;
                        }else {
                            hari = ""+dayOfMonth;
                        }
                        if (monthOfYear+1 <10){
                            bulan = "0"+(monthOfYear+1);
                        }else {
                            bulan = ""+(monthOfYear+1);
                        }

                        tvEndDatePromo.setText(year + "-" + bulan + "-" + hari);
                        promoenddate = tvEndDatePromo.getText().toString();

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void getDate() {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        String hari, bulan;

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            if (dayOfMonth <10){
                                 hari = "0"+dayOfMonth;
                            }else {
                                hari = ""+dayOfMonth;
                            }
                            if (monthOfYear+1 <10){
                                bulan = "0"+(monthOfYear+1);
                            }else {
                                bulan = ""+(monthOfYear+1);
                            }

                            tvDatePromo.setText(year + "-" + bulan + "-" + hari);
                            promodate = tvDatePromo.getText().toString();

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();

    }

    private void getImage() {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //mengambil fambar dari Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
                setToImageView(getResizedBitmap(bitmap, 512));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void kosong() {
        ivPromoImage.setImageResource(0);
        etPromoName.setText(null);
        etPromoDesc.setText(null);
        tvDatePromo.setText(null);
    }

    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        ivPromoImage.setImageBitmap(decoded);
    }

    // fungsi resize image
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    private void AddPromo() {
        final ProgressDialog loading = ProgressDialog.show(this, "Add Promo..", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "Response: " + response.toString());

                        try {
                            JSONObject jObj = new JSONObject(response);
                            success = jObj.getInt(TAG_SUCCESS);

                            if (success == 1) {
                                Log.e("v Add", jObj.toString());

                                Toast.makeText(context, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                                startActivity(new Intent(context, BerandaManager.class));
                                finish();

                            }else {
                                Toast.makeText(context, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //menghilangkan progress dialog
                        loading.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //menghilangkan progress dialog
                        loading.dismiss();

                        //menampilkan toast
                        Toast.makeText(context, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        Log.e(TAG, error.getMessage().toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                //membuat parameters
                Map<String, String> params = new HashMap<String, String>();

                //menambah parameter yang di kirim ke web servis
                params.put("promoimage", getStringImage(decoded));
                params.put("promoname", promoname.trim());
                params.put("promodesc", promodesc.trim());
                params.put("promodate", promodate.trim());
                params.put("promoenddate", promoenddate.trim());
                params.put("groupid", String.valueOf(groupid).trim());
                params.put("groupname", groupname.trim());
                params.put("groupimage", groupimage.trim());
                params.put("idmanager", String.valueOf(idmanager).trim());
                //kembali ke parameters
                Log.e(TAG, "" + params);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);

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
