package com.example.proyectodani2.weapon_info;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.proyectodani2.R;

/**
 * Created by dam2a on 01/03/18.
 */

public class WeaponImagesViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivContent;

    public WeaponImagesViewHolder(View itemView) {
        super(itemView);
        ivContent = itemView.findViewById(R.id.imageWeaponPics);
    }
}