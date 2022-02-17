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

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.aliendroid.alienads.AliendroidIntertitial;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.taristoreapps.namanabilengkap.DetailActivity;
import com.taristoreapps.namanabilengkap.MainActivity;
import com.taristoreapps.namanabilengkap.R;
import com.taristoreapps.namanabilengkap.model.Wallpaper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class WallpaperAdapter extends RecyclerView.Adapter   {


    private final int VIEW_ITEM = 0;
    public static ArrayList<Wallpaper> webLists;
    public static ArrayList<Wallpaper> mFilteredList;
    public Context context;
    public static  String ulrgbr;
    public static String nameimg;
    public WallpaperAdapter(ArrayList<Wallpaper> webLists, Context context) {
        this.webLists = webLists;
        this.mFilteredList = webLists;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
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
            final Wallpaper webList = mFilteredList.get(position);
            Glide
                    .with(context)
                    .load("https://drive.google.com/thumbnail?id="+webList.getId())
                    .centerCrop()
                    .into(((ViewHolder) holder).avatar_url);

            ((ViewHolder)holder).avatar_url.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ulrgbr = "http://drive.google.com/uc?export=view&id="+webList.getId();
                    nameimg = webList.getName();

                    final ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setMessage("Download Image...");
                    progressDialog.show();

                    Glide.with(context).asFile()
                            .load("https://drive.google.com/uc?export=download&id="+webList.getId())
                            .apply(new RequestOptions()
                                    .format(DecodeFormat.PREFER_ARGB_8888)
                                    .override(Target.SIZE_ORIGINAL))
                            .into(new Target<File>() {
                                @Override
                                public void onStart() {

                                }

                                @Override
                                public void onStop() {

                                }

                                @Override
                                public void onDestroy() {

                                }

                                @Override
                                public void onLoadStarted(@Nullable Drawable placeholder) {

                                }

                                @Override
                                public void onLoadFailed(@Nullable Drawable errorDrawable) {

                                }

                                @Override
                                public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                                    storeImage(resource);
                                }

                                @Override
                                public void onLoadCleared(@Nullable Drawable placeholder) {

                                }

                                @Override
                                public void getSize(@NonNull SizeReadyCallback cb) {

                                }

                                @Override
                                public void removeCallback(@NonNull SizeReadyCallback cb) {

                                }

                                @Nullable
                                @Override
                                public Request getRequest() {
                                    return null;
                                }

                                private void storeImage(File image) {
                                    File pictureFile = getOutputMediaFile();
                                    if (pictureFile == null) {
                                        return;
                                    }
                                    try {
                                        FileOutputStream output = new FileOutputStream(pictureFile);
                                        FileInputStream input = new FileInputStream(image);

                                        FileChannel inputChannel = input.getChannel();
                                        FileChannel outputChannel = output.getChannel();

                                        inputChannel.transferTo(0, inputChannel.size(), outputChannel);
                                        output.close();
                                        input.close();
                                        if (Build.VERSION.SDK_INT > 15) {
                                            try {
                                                WallpaperManager.getInstance(context).clear();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            nameimg = webList.getName();
                                            progressDialog.dismiss();
                                            if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                                    != PackageManager.PERMISSION_GRANTED) {
                                                ActivityCompat.requestPermissions((Activity)context,
                                                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                        123);
                                            } else {
                                                Intent intent = new Intent(context, DetailActivity.class);
                                                intent.putExtra("position", position);
                                                context.startActivity(intent);
                                            }
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
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                                private File getOutputMediaFile() {
                                    if (!MainActivity.dir.exists()) {
                                        if (!MainActivity.dir.mkdirs())
                                            return null;
                                    }

                                    File mediaFile;
                                    mediaFile = new File(MainActivity.dir.getPath() + "/" + webList.getName());
                                    return mediaFile;
                                }                                    @Override
                                public void setRequest(@Nullable Request request) {

                                }
                            });
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mFilteredList == null ? 0 : mFilteredList.size();
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilteredList = webLists;
                } else {
                    ArrayList<Wallpaper> filteredList = new ArrayList<>();
                    for (Wallpaper androidVersion : mFilteredList) {
                        if (androidVersion.getName().toLowerCase().contains(charString)) {
                            filteredList.add(androidVersion);
                        }
                    }
                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Wallpaper>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }
}
