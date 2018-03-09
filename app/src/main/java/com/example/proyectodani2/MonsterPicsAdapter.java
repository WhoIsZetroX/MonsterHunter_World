package com.example.proyectodani2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

/**
 * Created by dam2a on 06/03/18.
 */

public class MonsterPicsAdapter extends FirebaseRecyclerAdapter<MonsterPic, ImagesViewHolder> {

    Context context;

    public MonsterPicsAdapter(Context context, @NonNull FirebaseRecyclerOptions<MonsterPic> options) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ImagesViewHolder holder, int position, @NonNull MonsterPic model) {
        Glide.with(context)
                .load(model.picUrl)
                .into(holder.ivContent);
        System.out.println(model.picUrl.toString());
    }

    @Override
    public ImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ImagesViewHolder(inflater.inflate(R.layout.item_monsterpic, parent, false));
    }
}
