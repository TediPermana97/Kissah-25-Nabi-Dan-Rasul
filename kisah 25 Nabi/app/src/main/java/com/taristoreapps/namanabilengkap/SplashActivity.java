package com.taristoreapps.namanabilengkap;

import static com.taristoreapps.namanabilengkap.config.Settings.JSON_ID;
import static com.taristoreapps.namanabilengkap.config.Settings.ON_OFF_ADS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.widget.Toast;

import com.aliendroid.alienads.AlienOpenAds;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.taristoreapps.namanabilengkap.config.Settings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class SplashActivity extends AppCompatActivity {
    public static File dir;
    private static final int REQUEST = 112;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (ON_OFF_ADS.equals("1")) {
            if (checkConnectivity()) {
                loadUrlData();
            }
        }
        if (Settings.ON_OFF_OPEN_ADS.equals("1")) {
            AlienOpenAds.LoadOpenAds(Settings.ADMOB_OPENADS);
        } else {
            AlienOpenAds.LoadOpenAds("");
        }
        new CountDownTimer(10000, 1000) {
            @Override
            public void onFinish() {
                if (ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity)SplashActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST);
                } else {
                    Intent intent = new Intent(getBaseContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onTick(long millisUntilFinished) {

            }
        }.start();
    }


    private void loadUrlData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "https://drive.google.com/uc?export=download&id="+JSON_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObj = new JSONObject(response);
                    JSONArray contacts = jsonObj.getJSONArray("Ads");
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        Settings.STATUS_APP = c.getString("status_app");
                        Settings.LINK_REDIRECT = c.getString("link_redirect");
                        Settings.SELECT_ADS = c.getString("select_main_ads");
                        Settings.SELECT_BACKUP_ADS = c.getString("select_backup_ads");
                        Settings.MAIN_ADS_BANNER = c.getString("main_ads_banner");
                        Settings.MAIN_ADS_INTER = c.getString("main_ads_intertitial");
                        Settings.BACKUP_ADS_BANNER = c.getString("backup_ads_banner");
                        Settings.BACKUP_ADS_INTER = c.getString("backup_ads_intertitial");
                        Settings.INITIALIZE_SDK = c.getString("initialize_sdk");
                        Settings.INITIALIZE_SDK_BACKUP_ADS = c.getString("initialize_sdk_backup_ads");
                        Settings.INTERVAL = c.getInt("interval_intertitial");
                        Settings.HIGH_PAYING_KEYWORD1 = c.getString("high_paying_keyword_1");
                        Settings.HIGH_PAYING_KEYWORD2 = c.getString("high_paying_keyword_2");
                        Settings.HIGH_PAYING_KEYWORD3 = c.getString("high_paying_keyword_3");
                        Settings.HIGH_PAYING_KEYWORD4 = c.getString("high_paying_keyword_4");
                        Settings.HIGH_PAYING_KEYWORD5 = c.getString("high_paying_keyword_5");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SplashActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(SplashActivity.this);
        requestQueue.add(stringRequest);
    }

    private boolean checkConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return info != null && info.isConnected() && info.isAvailable();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(getBaseContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/" + getString(R.string.app_name));
                    } else {
                        dir = new File(Environment.getExternalStorageDirectory() + "/" + getString(R.string.app_name));
                    }
                    if (!dir.exists()) {
                        if (!dir.mkdirs()) ;
                    }

                } else {
                    //Toast.makeText(mContext, "The app was not allowed to write in your storage", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}