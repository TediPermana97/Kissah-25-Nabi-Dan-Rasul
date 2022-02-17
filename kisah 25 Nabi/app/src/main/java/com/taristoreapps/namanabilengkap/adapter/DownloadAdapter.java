package com.taristoreapps.namanabilengkap.adapter;

import static com.taristoreapps.namanabilengkap.config.Settings.BACKUP_ADS_INTER;
import static com.taristoreapps.namanabilengkap.config.Settings.HIGH_PAYING_KEYWORD1;
import static com.taristoreapps.namanabilengkap.config.Settings.HIGH_PAYING_KEYWORD2;
import static com.taristoreapps.namanabilengkap.config.Settings.HIGH_PAYING_KEYWORD3;
import static com.taristoreapps.namanabilengkap.config.Settings.HIGH_PAYING_KEYWORD4;
import static com.taristoreapps.namanabilengkap.config.Settings.HIGH_PAYING_KEYWORD5;
import static com.taristoreapps.namanabilengkap.config.Settings.INTERVAL;
import static com.taristoreapps.namanabilengkap.config.Settings.MAIN_ADS_INTER;
import static com.taristoreapps.namanabilengkap.config.Settings.SELECT_ADS;
import static com.taristoreapps.namanabilengkap.config.Settings.SELECT_BACKUP_ADS;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aliendroid.alienads.AliendroidIntertitial;
import com.bumptech.glide.Glide;
import com.taristoreapps.namanabilengkap.DetailLocalActivity;
import com.taristoreapps.namanabilengkap.R;

import java.io.File;
import java.util.ArrayList;

public class DownloadAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<File> al_pdf;
    public static String LINK_IMAGE;
    private final int VIEW_ITEM = 0;
    public DownloadAdapter(ArrayList<File> webLists, Context context) {
        this.al_pdf = webLists;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avatar_url;

        public ViewHolder(View itemView) {
            super(itemView);
            avatar_url = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_wallpaper, parent, false);
            return new ViewHolder(v);

        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_progressbar, parent, false);
            return new ProgressViewHolder(v);
        }

    }

    private static class ProgressViewHolder extends RecyclerView.ViewHolder {
        private static ProgressBar progressBar;

        private ProgressViewHolder(View v) {
            super(v);
            progressBar = v.findViewById(R.id.progressBar);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        if (holder instanceof ViewHolder) {
            Glide.with(context)
                    .load(al_pdf.get(position).getPath())
                    .centerCrop()
                    .into(((ViewHolder) holder).avatar_url);
            ((ViewHolder) holder).avatar_url.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LINK_IMAGE = al_pdf.get(position).getPath();
                    Intent intent = new Intent(context, DetailLocalActivity.class);
                    context.startActivity(intent);
                    switch (SELECT_ADS) {
                        case "ADMOB":
                            AliendroidIntertitial.ShowIntertitialAdmob((Activity) context, SELECT_BACKUP_ADS, MAIN_ADS_INTER, BACKUP_ADS_INTER, INTERVAL,
                                    HIGH_PAYING_KEYWORD1, HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                            break;
                        case "APPLOVIN-D":
                            AliendroidIntertitial.ShowIntertitialApplovinDis((Activity) context, SELECT_BACKUP_ADS, MAIN_ADS_INTER, BACKUP_ADS_INTER,  INTERVAL);
                            break;
                        case "APPLOVIN-D-NB":
                            AliendroidIntertitial.ShowIntertitialApplovinDis((Activity) context, SELECT_BACKUP_ADS, MAIN_ADS_INTER, BACKUP_ADS_INTER,  INTERVAL);
                            break;
                        case "APPLOVIN-M":
                            AliendroidIntertitial.ShowIntertitialApplovinMax((Activity) context, SELECT_BACKUP_ADS, MAIN_ADS_INTER, BACKUP_ADS_INTER,  INTERVAL);
                            break;
                        case "APPLOVIN-M-NB":
                            AliendroidIntertitial.ShowIntertitialApplovinMax((Activity) context, SELECT_BACKUP_ADS, MAIN_ADS_INTER, BACKUP_ADS_INTER,  INTERVAL);
                            break;
                        case "IRON" :
                            AliendroidIntertitial.ShowIntertitialIron((Activity) context,SELECT_BACKUP_ADS, MAIN_ADS_INTER, BACKUP_ADS_INTER, INTERVAL);
                            break;
                        case "MOPUB" :
                            AliendroidIntertitial.ShowIntertitialMopub((Activity) context,SELECT_BACKUP_ADS,MAIN_ADS_INTER, BACKUP_ADS_INTER, INTERVAL);
                            break;
                        case "STARTAPP" :
                            AliendroidIntertitial.ShowIntertitialSartApp((Activity) context, SELECT_BACKUP_ADS, MAIN_ADS_INTER, BACKUP_ADS_INTER, INTERVAL);
                            break;
                        case "FACEBOOK" :
                            AliendroidIntertitial.ShowIntertitialFAN((Activity) context, SELECT_BACKUP_ADS, MAIN_ADS_INTER, BACKUP_ADS_INTER, INTERVAL);
                            break;
                        case "GOOGLE-ADS" :
                            AliendroidIntertitial.ShowIntertitialGoogleAds((Activity) context, SELECT_BACKUP_ADS, MAIN_ADS_INTER, BACKUP_ADS_INTER, INTERVAL);
                            break;
                        case "UNITY" :
                            AliendroidIntertitial.ShowIntertitialUnity((Activity) context, SELECT_BACKUP_ADS, MAIN_ADS_INTER, BACKUP_ADS_INTER, INTERVAL);
                            break;
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return al_pdf == null ? 0 : al_pdf.size();
    }

}
