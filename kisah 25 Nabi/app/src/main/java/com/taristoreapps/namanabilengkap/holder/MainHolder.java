package com.taristoreapps.namanabilengkap.holder;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.taristoreapps.namanabilengkap.R;

public class MainHolder extends RecyclerView.ViewHolder {

    public TextView txtName;
    public CardView cvList;

    public MainHolder(View view) {
        super(view);

        cvList = view.findViewById(R.id.cvList);
        txtName = view.findViewById(R.id.txtName);

    }
}
