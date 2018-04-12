package com.example.proyectodani2.MonsterInfo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codewaves.youtubethumbnailview.ImageLoader;
import com.codewaves.youtubethumbnailview.ThumbnailLoader;
import com.codewaves.youtubethumbnailview.ThumbnailView;
import com.codewaves.youtubethumbnailview.downloader.OembedVideoInfoDownloader;
import com.example.proyectodani2.MonsterList.MonsterViewModel;
import com.example.proyectodani2.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class MonsterVideosFragment extends Fragment {

    private static final String API_KEY = "AIzaSyBa0iMF2ecFOuZWbTT9dvy9QhDcFh7zR";

    private static String VIDEO_ID = "iNmegyyecu0";
    RecyclerView recyclerView;
    MonsterVideosAdapter monsterVideosAdapter;
    private DatabaseReference mReference = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.you_tube_api, container, false);
        ThumbnailLoader.initialize("AIzaSyBa0iMF2ecFOuZWbTT9dvy9QhDcFh7zR");
        ThumbnailLoader.initialize()
                .setVideoInfoDownloader(new OembedVideoInfoDownloader());

        recyclerView = rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(rootView.findViewById(R.id.yl).getId(), youTubePlayerFragment).addToBackStack(null).commit();
        //ThumbnailView thumbnailView = rootView.findViewById(R.id.thumbnail);
        //final ImageView lala = rootView.findViewById(R.id.lala);

        // Glide.with(this).load("https://img.youtube.com/vi/" + VIDEO_ID + "/hqdefault.jpg").into(lala);

       /* thumbnailView.loadThumbnail("https://www.youtube.com/watch?v="+VIDEO_ID, new ImageLoader() {
            @Override
            public Bitmap load(String url) throws IOException {
                System.out.println("URLLLLLL " + url);
                return  Picasso.get().load(url).get();
            }
        });*/

        youTubePlayerFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(final YouTubePlayer.Provider provider, final YouTubePlayer player, boolean wasRestored) {
                if (!wasRestored) {
                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    player.cueVideo(VIDEO_ID);
                    System.out.println("onInitiailaodnaodnodgfdgf");
                    VideoViewModel videoViewModel = ViewModelProviders.of(MonsterVideosFragment.this).get(VideoViewModel.class);
                    videoViewModel.getVideoKey().observe(MonsterVideosFragment.this, new Observer<String>() {
                        @Override
                        public void onChanged(@Nullable String videoKey) {
                            player.cueVideo(videoKey);
                            System.out.println(videoKey + " AAAAAAAAA");
                        }
                    });
                    // player.loadVideo(VIDEO_ID);
                    // player.play();
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error) {
                // YouTube error
                String errorMessage = error.toString();
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                Log.d("errorMessage:", errorMessage);
            }
        });

        MonsterViewModel monsterViewModel = ViewModelProviders.of(getActivity()).get(MonsterViewModel.class);
        monsterViewModel.getMonsterKey().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String monsterKey) {
                loadMonsterVideos(monsterKey);
                System.out.println(monsterKey + " AAAAAAAAA");
            }
        });

        //showMonsterKey(monsterKey);

        return rootView;

    }

    void loadMonsterVideos(final String monsterKey) {
        mReference = FirebaseDatabase.getInstance().getReference();

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<String>()
                .setQuery(mReference.child("monsters/data").child(monsterKey).child("monsterVideos"), String.class)
                .setLifecycleOwner(this)
                .build();

        System.out.println("MONSTERKEY = " + monsterKey);
        monsterVideosAdapter = new MonsterVideosAdapter(this, options);
        recyclerView.setAdapter(monsterVideosAdapter);

        monsterVideosAdapter.startListening();
    }

    /*
     // en el adaptador.onBindView
        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.youtube_layout, youTubePlayerFragment).commit();

        youTubePlayerFragment.initialize(API_KEY, new OnInitializedListener() {

            @Override
            public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
                if (!wasRestored) {
                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    player.cueVideo(VIDEO_ID);
                    // player.loadVideo(VIDEO_ID);
                    // player.play();
                }
            }

            @Override
            public void onInitializationFailure(Provider provider, YouTubeInitializationResult error) {
                // YouTube error
                String errorMessage = error.toString();
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                Log.d("errorMessage:", errorMessage);
            }
        });
     */
}
