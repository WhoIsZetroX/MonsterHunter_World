package com.example.proyectodani2.MonsterInfo;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.proyectodani2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


/**
 * Created by dam2a on 03/04/18.
 */

public class MonsterVideosAdapter extends FirebaseRecyclerAdapter<String, VideosViewHolder> {

    Fragment context;
    private static final String API_KEY = "AIzaSyBa0iMF2ecFOuZWbTT9dvy9QhDcFh7zR";

    public MonsterVideosAdapter(Fragment context, @NonNull FirebaseRecyclerOptions<String> options) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull VideosViewHolder holder, int position, final @NonNull String model) {
        System.out.println("MODELLL " + model);

        holder.videoUrl.setText(model);

        Glide.with(context)
                .load(R.drawable.drawericon)
                .into(holder.ivContent);

        holder.ivContent.setContentDescription(model);

        holder.itemView.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        VideoViewModel videoViewModel = ViewModelProviders.of(context).get(VideoViewModel.class);
                        videoViewModel.getVideoKey().setValue(model);
                        System.out.println(model+" ---- xd");

                    }
                });

        System.out.println(model+" ---- xd");
    }

    @Override
    public VideosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new VideosViewHolder(inflater.inflate(R.layout.item_monstervideo, parent, false));
    }

}
