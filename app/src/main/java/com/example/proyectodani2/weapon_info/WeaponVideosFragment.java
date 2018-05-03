package com.example.proyectodani2.weapon_info;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import android.widget.Toast;

import com.codewaves.youtubethumbnailview.ThumbnailLoader;
import com.codewaves.youtubethumbnailview.downloader.OembedVideoInfoDownloader;
import com.example.proyectodani2.R;
import com.example.proyectodani2.weapon_list.WeaponViewModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WeaponVideosFragment extends Fragment {

    private static final String API_KEY = "AIzaSyBa0iMF2ecFOuZWbTT9dvy9QhDcFh7zR";

    private static String VIDEO_ID = "iNmegyyecu0";
    RecyclerView recyclerView;
    com.example.proyectodani2.weapon_info.WeaponVideosAdapter weaponVideosAdapter;
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
                    WeaponVideoViewModel weaponVideoViewModel = ViewModelProviders.of(WeaponVideosFragment.this).get(WeaponVideoViewModel.class);
                    weaponVideoViewModel.getVideoKey().observe(WeaponVideosFragment.this, new Observer<String>() {
                        @Override
                        public void onChanged(@Nullable String videoKey) {
                            player.cueVideo(videoKey);
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

        WeaponViewModel weaponViewModel = ViewModelProviders.of(getActivity()).get(WeaponViewModel.class);
        weaponViewModel.getWeaponKey().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String weaponKey) {
                loadWeaponVideos(weaponKey);
            }
        });

        //showMonsterKey(monsterKey);

        return rootView;

    }

    void loadWeaponVideos(final String weaponKey) {
        mReference = FirebaseDatabase.getInstance().getReference();

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<String>()
                .setQuery(mReference.child("weapons/data").child(weaponKey).child("weaponVideos"), String.class)
                .setLifecycleOwner(this)
                .build();

        weaponVideosAdapter = new WeaponVideosAdapter(this, options);
        recyclerView.setAdapter(weaponVideosAdapter);

        weaponVideosAdapter.startListening();
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
