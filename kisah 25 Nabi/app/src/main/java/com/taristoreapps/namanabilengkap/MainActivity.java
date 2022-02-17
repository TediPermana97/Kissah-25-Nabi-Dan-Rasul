package com.taristoreapps.namanabilengkap;

import static com.google.android.play.core.install.model.AppUpdateType.FLEXIBLE;
import static com.taristoreapps.namanabilengkap.config.Settings.API_KEY;
import static com.taristoreapps.namanabilengkap.config.Settings.BACKUP_ADS_BANNER;
import static com.taristoreapps.namanabilengkap.config.Settings.BACKUP_ADS_INTER;
import static com.taristoreapps.namanabilengkap.config.Settings.CHILD_DIRECT_GDPR;
import static com.taristoreapps.namanabilengkap.config.Settings.FOLDER_ID;
import static com.taristoreapps.namanabilengkap.config.Settings.HIGH_PAYING_KEYWORD1;
import static com.taristoreapps.namanabilengkap.config.Settings.HIGH_PAYING_KEYWORD2;
import static com.taristoreapps.namanabilengkap.config.Settings.HIGH_PAYING_KEYWORD3;
import static com.taristoreapps.namanabilengkap.config.Settings.HIGH_PAYING_KEYWORD4;
import static com.taristoreapps.namanabilengkap.config.Settings.HIGH_PAYING_KEYWORD5;
import static com.taristoreapps.namanabilengkap.config.Settings.INITIALIZE_SDK;
import static com.taristoreapps.namanabilengkap.config.Settings.INITIALIZE_SDK_BACKUP_ADS;
import static com.taristoreapps.namanabilengkap.config.Settings.LINK_REDIRECT;
import static com.taristoreapps.namanabilengkap.config.Settings.MAIN_ADS_BANNER;
import static com.taristoreapps.namanabilengkap.config.Settings.MAIN_ADS_INTER;
import static com.taristoreapps.namanabilengkap.config.Settings.SELECT_ADS;
import static com.taristoreapps.namanabilengkap.config.Settings.SELECT_BACKUP_ADS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aliendroid.alienads.AlienGDPR;
import com.aliendroid.alienads.AliendroidBanner;
import com.aliendroid.alienads.AliendroidInitialize;
import com.aliendroid.alienads.AliendroidIntertitial;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.OnFailureListener;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.taristoreapps.namanabilengkap.adapter.DownloadAdapter;
import com.taristoreapps.namanabilengkap.adapter.MainAdapter;
import com.taristoreapps.namanabilengkap.adapter.WallpaperAdapter;
import com.taristoreapps.namanabilengkap.config.Settings;
import com.taristoreapps.namanabilengkap.model.ModelMain;
import com.taristoreapps.namanabilengkap.model.Wallpaper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainAdapter.onSelectData {
    RecyclerView rvName;
    MainAdapter mainAdapter;
    List<ModelMain> modelMain = new ArrayList<>();
    //adsppp
    private RecyclerView recyclerView;
    private WallpaperAdapter adapter;
    ArrayList<Wallpaper> webLists;
    private RecyclerView lv_img;
    public static ArrayList<File> fileList = new ArrayList<File>();
    DownloadAdapter obj_adapter;
    public static int REQUEST_PERMISSIONS = 1;
    boolean boolean_permission;
    public static File dir;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //------------------------------conten-------------------------------

        rvName = findViewById(R.id.rvList);
        rvName.setHasFixedSize(true);
        rvName.setLayoutManager(new LinearLayoutManager(this));

        //setupToolbar();
        loadJSON();

        //------------------------masukan ads---------------

        AlienGDPR.loadGdpr(this, SELECT_ADS, CHILD_DIRECT_GDPR);
        switch (SELECT_ADS) {
            case "ADMOB":
                AliendroidInitialize.SelectAdsAdmob(this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "GOOGLE-ADS":
                AliendroidInitialize.SelectAdsGoogleAds(this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "APPLOVIN-D":
                AliendroidInitialize.SelectAdsApplovinDis(this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "APPLOVIN-D-NB":
                AliendroidInitialize.SelectAdsApplovinDis(this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "APPLOVIN-M":
                AliendroidInitialize.SelectAdsApplovinMax(this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "APPLOVIN-M-NB":
                AliendroidInitialize.SelectAdsApplovinMax(this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "MOPUB":
                AliendroidInitialize.SelectAdsMopub(this, SELECT_BACKUP_ADS, INITIALIZE_SDK, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "IRON":
                AliendroidInitialize.SelectAdsIron(this, SELECT_BACKUP_ADS, INITIALIZE_SDK, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "STARTAPP":
                AliendroidInitialize.SelectAdsStartApp(this, SELECT_BACKUP_ADS, INITIALIZE_SDK, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "UNITY":
                AliendroidInitialize.SelectAdsUnity(this, SELECT_BACKUP_ADS, INITIALIZE_SDK, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "FACEBOOK":
                AliendroidInitialize.SelectAdsFAN(this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                break;
        }
        RelativeLayout layAdsbanner = findViewById(R.id.layAds);
        switch (SELECT_ADS) {
            case "ADMOB":
                AliendroidBanner.SmallBannerAdmob(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER, HIGH_PAYING_KEYWORD1,
                        HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                AliendroidIntertitial.LoadIntertitialAdmob(this, SELECT_BACKUP_ADS, MAIN_ADS_INTER, BACKUP_ADS_INTER, HIGH_PAYING_KEYWORD1,
                        HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                break;
            case "APPLOVIN-M":
                AliendroidBanner.SmallBannerApplovinMax(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AliendroidIntertitial.LoadIntertitialApplovinMax(this, SELECT_BACKUP_ADS, MAIN_ADS_INTER, BACKUP_ADS_INTER);
                break;
            case "APPLOVIN-M-NB":
                AliendroidIntertitial.LoadIntertitialApplovinMax(this, SELECT_BACKUP_ADS, MAIN_ADS_INTER, BACKUP_ADS_INTER);
                break;
            case "APPLOVIN-D":
                AliendroidBanner.SmallBannerApplovinDis(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AliendroidIntertitial.LoadIntertitialApplovinDis(this, SELECT_BACKUP_ADS, MAIN_ADS_INTER, BACKUP_ADS_INTER);
                break;
            case "APPLOVIN-D-NB":
                AliendroidIntertitial.LoadIntertitialApplovinDis(this, SELECT_BACKUP_ADS, MAIN_ADS_INTER, BACKUP_ADS_INTER);
                break;
            case "MOPUB":
                AliendroidBanner.SmallBannerMopub(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AliendroidIntertitial.LoadIntertitialMopub(this, SELECT_BACKUP_ADS, MAIN_ADS_INTER, BACKUP_ADS_INTER);
                break;
            case "STARTAPP":
                AliendroidBanner.SmallBannerStartApp(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AliendroidIntertitial.LoadIntertitialStartApp(this, SELECT_BACKUP_ADS, MAIN_ADS_INTER, BACKUP_ADS_INTER);
                break;
            case "IRON":
                AliendroidBanner.SmallBannerIron(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AliendroidIntertitial.LoadIntertitialIron(this, SELECT_BACKUP_ADS, MAIN_ADS_INTER, BACKUP_ADS_INTER);
                break;
            case "FACEBOOK":
                AliendroidBanner.SmallBannerFAN(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AliendroidIntertitial.LoadIntertitialFAN(this, SELECT_BACKUP_ADS, MAIN_ADS_INTER, BACKUP_ADS_INTER);
                break;
            case "UNITY":
                AliendroidBanner.SmallBannerUnity(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AliendroidIntertitial.LoadIntertitialUnity(this, SELECT_BACKUP_ADS, MAIN_ADS_INTER, BACKUP_ADS_INTER);
                break;
            case "GOOGLE-ADS":
                AliendroidBanner.SmallBannerGoogleAds(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AliendroidIntertitial.LoadIntertitialGoogleAds(this, SELECT_BACKUP_ADS, MAIN_ADS_INTER, BACKUP_ADS_INTER);
                break;
        }

        checkUpdate();
        Review();
        if (Settings.STATUS_APP.equals("1")) {
            String str = LINK_REDIRECT;
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(str)));
            finish();
        }

    }
    private void loadUrlData() {
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "https://www.googleapis.com/drive/v3/files?&pageSize=1000&q='"+FOLDER_ID+"'+in+parents&key="+API_KEY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("files");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject jo = array.getJSONObject(i);
                        Wallpaper developers = new Wallpaper(jo.getString("id"), jo.getString("name"));
                        webLists.add(developers);
                    }
                    adapter = new WallpaperAdapter(webLists, MainActivity.this);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }
    private boolean checkConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if ((info == null || !info.isConnected() || !info.isAvailable())) {
            return false;
        } else {
            return true;
        }
    }

    public void rate(View view){
        String str = "https://play.google.com/store/apps/details?id="
                + BuildConfig.APPLICATION_ID;
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse(str)));
    }

    public void wallapper(View view){
        recyclerView.setVisibility(View.VISIBLE);
        lv_img.setVisibility(View.GONE);

    }

    public void wallapperoffline(View view){
        init();
        recyclerView.setVisibility(View.GONE);
        lv_img.setVisibility(View.VISIBLE);

    }

    public void share (View view){
        String shareLink = "https://play.google.com/store/apps/details?id="
                + BuildConfig.APPLICATION_ID;
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                getResources().getString(R.string.shareit)
                        + shareLink);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    /*
     In App Update
      */
    AppUpdateManager appUpdateManager;
    com.google.android.play.core.tasks.Task<AppUpdateInfo> appUpdateInfoTask;
    private static final int MY_REQUEST_CODE = 17326;

    ReviewInfo reviewInfo;
    ReviewManager manager;

    private void checkUpdate(){
        appUpdateManager = AppUpdateManagerFactory.create(this);
        appUpdateManager.registerListener(listener);

        appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
        appUpdateInfoTask.addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(AppUpdateInfo appUpdateInfo) {
                Log.d("appUpdateInfo :", "packageName :"+appUpdateInfo.packageName()+ ", "+ "availableVersionCode :"+ appUpdateInfo.availableVersionCode() +", "+"updateAvailability :"+ appUpdateInfo.updateAvailability() +", "+ "installStatus :" + appUpdateInfo.installStatus() );

                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                        && appUpdateInfo.isUpdateTypeAllowed(FLEXIBLE)){
                    requestUpdate(appUpdateInfo);
                    Log.d("UpdateAvailable","update is there ");
                }
                else if (appUpdateInfo.updateAvailability() == 3){
                    Log.d("Update","3");
                    notifyUser();
                }
            }
        });
    }
    private void requestUpdate(AppUpdateInfo appUpdateInfo){
        try {
            appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.FLEXIBLE, MainActivity.this,MY_REQUEST_CODE);
            resume();
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    InstallStateUpdatedListener listener = new InstallStateUpdatedListener() {
        @Override
        public void onStateUpdate(InstallState installState) {
            if (installState.installStatus() == InstallStatus.DOWNLOADED){
                Log.d("InstallDownloded","InstallStatus sucsses");
                notifyUser();
            }
        }
    };


    private void notifyUser() {

        Snackbar snackbar =
                Snackbar.make(findViewById(R.id.container),
                        "An update has just been downloaded.",
                        Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("RESTART", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appUpdateManager.completeUpdate();
            }
        });
        snackbar.setActionTextColor(
                getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }

    private void resume(){
        appUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo appUpdateInfo) {
                if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED){
                    notifyUser();
                }
            }
        });
    }

    /*
    In app review
     */

    private void Review(){
        manager = ReviewManagerFactory.create(this);
        manager.requestReviewFlow().addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
            @Override
            public void onComplete(@NonNull Task<ReviewInfo> task) {
                if(task.isSuccessful()){
                    reviewInfo = task.getResult();
                    manager.launchReviewFlow(MainActivity.this, reviewInfo).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            //Toast.makeText(MainActivity.this, "Rating Failed", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            // Toast.makeText(MainActivity.this, "Review Completed, Thank You!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(MainActivity.this, "In-App Request Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/" + getString(R.string.app_name));
        } else {
            dir = new File(Environment.getExternalStorageDirectory() + "/" + getString(R.string.app_name));
        }
        if (!dir.exists()) {
            if (!dir.mkdirs()) ;
        }
        fn_permission();

    }

    public ArrayList<File> getfile(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {

                if (listFile[i].isDirectory()) {
                    getfile(listFile[i]);

                } else {
                    boolean booleanpdf = false;
                    if (listFile[i].getName().endsWith(".jpeg") || listFile[i].getName().endsWith(".jpg")
                            || listFile[i].getName().endsWith(".png") || listFile[i].getName().endsWith(".webp")) {
                        for (int j = 0; j < fileList.size(); j++) {
                            if (fileList.get(j).getName().equals(listFile[i].getName())) {
                                booleanpdf = true;
                            } else {
                            }
                        }
                        if (booleanpdf) {
                            booleanpdf = false;
                        } else {
                            fileList.add(listFile[i]);

                        }
                    }
                }
            }
        }
        return fileList;
    }
    private void fn_permission() {
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE))) {
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);
            }
        } else {
            boolean_permission = true;
            getfile(dir);
            obj_adapter = new DownloadAdapter(fileList,MainActivity.this);
            lv_img.setAdapter(obj_adapter);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                boolean_permission = true;
                getfile(dir);
                obj_adapter = new DownloadAdapter (fileList, MainActivity.this);
                lv_img.setAdapter(obj_adapter);
            } else {
                Toast.makeText(getApplicationContext(), "Please allow the permission", Toast.LENGTH_LONG).show();
            }
        }

    }

    private void loadJSON() {
        try {
            InputStream stream = getAssets().open("kisahnabi.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            String tContents = new String(buffer, StandardCharsets.UTF_8);
            try {
                JSONArray obj = new JSONArray(tContents);
                for (int i = 0; i < obj.length(); i++) {
                    JSONObject temp = obj.getJSONObject(i);
                    ModelMain dataApi = new ModelMain();
                    dataApi.setName(temp.getString("name"));
                    dataApi.setThn_kelahiran(temp.getString("thn_kelahiran"));
                    dataApi.setUsia(temp.getString("usia"));
                    dataApi.setDescription(temp.getString("description"));
                    dataApi.setTmp(temp.getString("tmp"));
                    dataApi.setImage_url(temp.getString("image_url"));
                    modelMain.add(dataApi);
                    mainAdapter = new MainAdapter(MainActivity.this, modelMain, this);
                    rvName.setAdapter(mainAdapter);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException ignored) {

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSelected(ModelMain modelMain) {
        Intent intnt = new Intent(MainActivity.this, DetailActivity.class);
        intnt.putExtra("paramDtl", modelMain);
        startActivity(intnt);
    }
}