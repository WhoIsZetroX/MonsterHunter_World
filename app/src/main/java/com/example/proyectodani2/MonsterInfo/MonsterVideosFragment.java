package com.example.proyectodani2.MonsterInfo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectodani2.monster.MonsterViewModel;
import com.example.proyectodani2.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MonsterVideosFragment extends Fragment {

    private static final String API_KEY = "AIzaSyBa0iMF2ecFOuZWbTT9dvy9QhDcFh7zR";

    private static String VIDEO_ID = "f2MOYNTe2oU";
    RecyclerView recyclerView;
    MonsterVideosAdapter monsterVideosAdapter;
    private DatabaseReference mReference = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.you_tube_api, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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
