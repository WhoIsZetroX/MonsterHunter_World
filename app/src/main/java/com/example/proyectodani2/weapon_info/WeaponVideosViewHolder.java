package com.example.proyectodani2.weapon_info;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codewaves.youtubethumbnailview.ThumbnailView;
import com.example.proyectodani2.R;

/**
 * Created by dam2a on 03/04/18.
 */

public class WeaponVideosViewHolder extends RecyclerView.ViewHolder {
    //public ImageView ivContent;
    //public TextView videoUrl;
    public ThumbnailView thumbnailView;

    public WeaponVideosViewHolder(View itemView) {
        super(itemView);
        //videoUrl = itemView.findViewById(R.id.videoUrl);
        //ivContent = itemView.findViewById(R.id.thumbnail);
        thumbnailView = itemView.findViewById(R.id.thumbnail);
    }
}
