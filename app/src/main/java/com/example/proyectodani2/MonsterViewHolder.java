package com.example.proyectodani2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dam2a on 30/01/18.
 */

public class MonsterViewHolder extends RecyclerView.ViewHolder {
    ImageView image;
    TextView name;

    public MonsterViewHolder(View itemView) {
        super(itemView);

        image = itemView.findViewById(R.id.monster_image);
        name = itemView.findViewById(R.id.monster_name);
    }
}
