package com.example.proyectodani2.weapon_info;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codewaves.youtubethumbnailview.ImageLoader;
import com.example.proyectodani2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.io.IOException;


/**
 * Created by dam2a on 03/04/18.
 */

public class WeaponVideosAdapter extends FirebaseRecyclerAdapter<String, WeaponVideosViewHolder> {

    private static final String API_KEY = "AIzaSyBa0iMF2ecFOuZWbTT9dvy9QhDcFh7zR";
    Fragment context;

    public WeaponVideosAdapter(Fragment context, @NonNull FirebaseRecyclerOptions<String> options) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull WeaponVideosViewHolder holder, int position, final @NonNull String model) {

        //holder.videoUrl.setText(model);

        /*Glide.with(context)
                .load(R.drawable.drawericon)
                .into(holder.thumbnailView);*/
        holder.thumbnailView.loadThumbnail("https://www.youtube.com/watch?v=" + model, new ImageLoader() {
            @Override
            public Bitmap load(String url) throws IOException {
                return Picasso.get().load(url).get();
            }
        });

        //holder.thumbnailView.setContentDescription(model);

        holder.thumbnailView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        WeaponVideoViewModel weaponVideoViewModel = ViewModelProviders.of(context).get(WeaponVideoViewModel.class);
                        weaponVideoViewModel.getVideoKey().setValue(model);
                    }
                });
    }

    @Override
    public WeaponVideosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new WeaponVideosViewHolder(inflater.inflate(R.layout.item_weaponvideo, parent, false));
    }
}