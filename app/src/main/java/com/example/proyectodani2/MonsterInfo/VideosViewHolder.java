package com.example.proyectodani2.MonsterInfo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectodani2.R;

/**
 * Created by dam2a on 03/04/18.
 */

public class VideosViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivContent;
    public TextView videoUrl;

    public VideosViewHolder(View itemView) {
        super(itemView);
        videoUrl = itemView.findViewById(R.id.videoUrl);
        ivContent = itemView.findViewById(R.id.thumbnail);
    }
}
