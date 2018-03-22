package com.example.proyectodani2.MonsterInfo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.proyectodani2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

/**
 * Created by dam2a on 06/03/18.
 */

public class MonsterPicsAdapter extends FirebaseRecyclerAdapter<String, ImagesViewHolder> {

    Context context;

    public MonsterPicsAdapter(Context context, @NonNull FirebaseRecyclerOptions<String> options) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ImagesViewHolder holder, int position, @NonNull String model) {
        Glide.with(context)
                .load(model)
                .into(holder.ivContent);
        System.out.println(model+" ---- xd");
    }

    @Override
    public ImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ImagesViewHolder(inflater.inflate(R.layout.item_monsterpic, parent, false));
    }
}
