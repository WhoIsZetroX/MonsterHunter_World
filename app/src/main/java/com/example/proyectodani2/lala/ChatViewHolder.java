package com.example.proyectodani2.lala;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.proyectodani2.R;

public class ChatViewHolder extends RecyclerView.ViewHolder {

    public TextView tvContent, tvContent2;
    LinearLayout linearLayout;

    public ChatViewHolder(View itemView) {
        super(itemView);
        tvContent = itemView.findViewById(R.id.text_);
        tvContent2 = itemView.findViewById(R.id.text2_);
        linearLayout = itemView.findViewById(R.id.linearLayout);
    }

}
