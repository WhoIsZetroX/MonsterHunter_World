package com.example.proyectodani2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by dam2a on 01/03/18.
 */

public class ImagesViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivContent;


    public ImagesViewHolder(View itemView) {
        super(itemView);
        ivContent = itemView.findViewById(R.id.imageMonsterPics);
    }
}