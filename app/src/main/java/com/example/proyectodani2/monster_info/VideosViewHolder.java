package com.example.proyectodani2.monster_info;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codewaves.youtubethumbnailview.ThumbnailView;
import com.example.proyectodani2.R;

/**
 * Created by dam2a on 03/04/18.
 */

public class VideosViewHolder extends RecyclerView.ViewHolder {
    //public ImageView ivContent;
    //public TextView videoUrl;
    public ThumbnailView thumbnailView;

    public VideosViewHolder(View itemView) {
        super(itemView);
        //videoUrl = itemView.findViewById(R.id.videoUrl);
        //ivContent = itemView.findViewById(R.id.thumbnail);
        thumbnailView = itemView.findViewById(R.id.thumbnail);
    }
}
