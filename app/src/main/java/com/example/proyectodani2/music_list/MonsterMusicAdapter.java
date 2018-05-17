/*
package com.example.proyectodani2.music_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.proyectodani2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MonsterMusicAdapter extends FirebaseRecyclerAdapter<Music, MusicViewHolder> {

    Context context;

    public MonsterMusicAdapter(Context context, @NonNull FirebaseRecyclerOptions<Music> options) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull MusicViewHolder holder, int position, @NonNull Music model) {

        holder.tvContent.setText(model.name);

        Glide.with(context)
                .load(model.picUrl)
                .into(holder.ivContent);
        System.out.println(model+" ---- xd");
    }

    @Override
    public MusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MusicViewHolder(inflater.inflate(R.layout.item_monstermusic, parent, false));
    }

}
*/
