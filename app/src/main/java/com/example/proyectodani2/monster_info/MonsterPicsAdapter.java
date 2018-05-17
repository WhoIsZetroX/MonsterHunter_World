package com.example.proyectodani2.monster_info;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.proyectodani2.R;
import com.example.proyectodani2.photo_view.PhotoviewActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

/**
 * Created by dam2a on 06/03/18.
 */

public class MonsterPicsAdapter extends FirebaseRecyclerAdapter<String, MonsterImagesViewHolder> {

    Context context;

    public MonsterPicsAdapter(Context context, @NonNull FirebaseRecyclerOptions<String> options) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull MonsterImagesViewHolder holder, int position, @NonNull final String model) {
        Glide.with(context)
                .load(model)
                .into(holder.ivContent);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PhotoviewActivity.class);
                intent.putExtra("photourl", model);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public MonsterImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MonsterImagesViewHolder(inflater.inflate(R.layout.item_monsterpic, parent, false));
    }
}
