package com.example.proyectodani2.music_list;

import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.proyectodani2.R;

public class MusicViewHolder extends RecyclerView.ViewHolder {

    public TextView tvContent;
    public ImageView ivContent;
    public VideoView vvContent;

    public MusicViewHolder(View itemView) {
        super(itemView);
        tvContent = itemView.findViewById(R.id.text_xd);
        ivContent = itemView.findViewById(R.id.music_xd);
        vvContent = itemView.findViewById(R.id.mVideoView);
    }

}
