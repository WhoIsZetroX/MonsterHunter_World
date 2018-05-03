package com.example.proyectodani2.weapon_info;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.proyectodani2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

/**
 * Created by dam2a on 06/03/18.
 */

public class WeaponPicsAdapter extends FirebaseRecyclerAdapter<String, WeaponImagesViewHolder> {

    Context context;

    public WeaponPicsAdapter(Context context, @NonNull FirebaseRecyclerOptions<String> options) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull WeaponImagesViewHolder holder, int position, @NonNull String model) {
        Glide.with(context)
                .load(model)
                .into(holder.ivContent);
    }

    @Override
    public WeaponImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new WeaponImagesViewHolder(inflater.inflate(R.layout.item_weaponpic, parent, false));
    }
}
