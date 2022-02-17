package com.taristoreapps.namanabilengkap;

import static com.taristoreapps.namanabilengkap.config.Settings.BACKUP_ADS_BANNER;
import static com.taristoreapps.namanabilengkap.config.Settings.BACKUP_ADS_INTER;
import static com.taristoreapps.namanabilengkap.config.Settings.MAIN_ADS_BANNER;
import static com.taristoreapps.namanabilengkap.config.Settings.MAIN_ADS_INTER;
import static com.taristoreapps.namanabilengkap.config.Settings.SELECT_BACKUP_ADS;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aliendroid.alienads.AliendroidBanner;
import com.aliendroid.alienads.AliendroidIntertitial;
import com.bumptech.glide.Glide;
import com.taristoreapps.namanabilengkap.adapter.DownloadAdapter;
import com.taristoreapps.namanabilengkap.config.Settings;

import java.io.IOException;

public class DetailLocalActivity extends AppCompatActivity {

    private ImageView wall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Glide
                .with(this)
                .load(DownloadAdapter.LINK_IMAGE)
                .centerCrop()
                .into(wall);
        RelativeLayout layAdsbanner = findViewById(R.id.layAds);
        switch (Settings.SELECT_ADS) {
            case "ADMOB":
                AliendroidBanner.SmallBannerAdmob(this,layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER, Settings.HIGH_PAYING_KEYWORD1,
                        Settings.HIGH_PAYING_KEYWORD2, Settings.HIGH_PAYING_KEYWORD3, Settings.HIGH_PAYING_KEYWORD4, Settings.HIGH_PAYING_KEYWORD5);
                break;
            case "APPLOVIN-M":
                AliendroidBanner.SmallBannerApplovinMax(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);break;
            case "APPLOVIN-M-NB":
                break;
            case "APPLOVIN-D":
                AliendroidBanner.SmallBannerApplovinDis(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "APPLOVIN-D-NB":
                break;
            case "MOPUB" :
                AliendroidBanner.SmallBannerMopub(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "STARTAPP":
                AliendroidBanner.SmallBannerStartApp(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "IRON":
                AliendroidBanner.SmallBannerIron(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "FACEBOOK":
                AliendroidBanner.SmallBannerFAN(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "UNITY":
                AliendroidBanner.SmallBannerUnity(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AliendroidIntertitial.LoadIntertitialUnity(this, SELECT_BACKUP_ADS, MAIN_ADS_INTER, BACKUP_ADS_INTER);
                break;
            case "GOOGLE-ADS":
                AliendroidBanner.SmallBannerGoogleAds(this,layAdsbanner,SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AliendroidIntertitial.LoadIntertitialGoogleAds(this, SELECT_BACKUP_ADS, MAIN_ADS_INTER, BACKUP_ADS_INTER);
                break;
        }
    }

    public void setWallpaper(View view) {
        Bitmap bitmap = ((BitmapDrawable) wall.getDrawable()).getBitmap();
        try {
            WallpaperManager myWallpaperManager
                    = WallpaperManager.getInstance(getApplicationContext());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                myWallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM);
            }
            Toast.makeText(DetailLocalActivity.this, "Wallpaper set Home Screen",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(DetailLocalActivity.this,
                    "Error setting wallpaper", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    public void setlockScreen(View view) {
        Bitmap bitmap = ((BitmapDrawable) wall.getDrawable()).getBitmap();
        try {
            WallpaperManager myWallpaperManager
                    = WallpaperManager.getInstance(getApplicationContext());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                myWallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK);
            }
            Toast.makeText(DetailLocalActivity.this, "Wallpaper set Lock Screen",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(DetailLocalActivity.this,
                    "Error setting wallpaper", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
