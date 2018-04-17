package com.example.proyectodani2.weapon_list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectodani2.R;

/**
 * Created by dam2a on 30/01/18.
 */

public class WeaponViewHolder extends RecyclerView.ViewHolder {
    ImageView image;
    TextView name;

    public WeaponViewHolder(View itemView) {
        super(itemView);

        image = itemView.findViewById(R.id.weapon_image);
        name = itemView.findViewById(R.id.weapon_name);
    }
}
