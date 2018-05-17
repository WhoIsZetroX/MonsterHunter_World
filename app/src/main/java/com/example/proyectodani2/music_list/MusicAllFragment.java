package com.example.proyectodani2.music_list;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.proyectodani2.R;
import com.example.proyectodani2.start_menu.MainActivity;
import com.example.proyectodani2.weapon_list.WeaponAllFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicAllFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference mReference;
    ImageView imageMonster;
    LinearLayout linearLayout;
    TextView monsterName, monsterDesc;
    ImageView monsterImage;
    MediaPlayer mp;// = new MediaPlayer();

    //Conectar a la base de datos
    private DatabaseReference mDatabase = null;

    public MusicAllFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music_all, container, false);

        TabLayout tabLayout = ((MainActivity) getActivity()).getTabLayout();
        tabLayout.removeAllTabs();
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) tabLayout.getLayoutParams();
        layoutParams.height = 1;
        tabLayout.setLayoutParams(layoutParams);

        mReference = FirebaseDatabase.getInstance().getReference();

        recyclerView = view.findViewById(R.id.recyclerview_music);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadMonsterPics();


        return view;

    }

        void loadMonsterPics() {

            FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Music>()
                    .setQuery(mReference.child("music/data"), Music.class)
                    .setLifecycleOwner(this)
                    .build();

            FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Music, MusicViewHolder>(options) {
                @Override
                public MusicViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                    LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                    return new MusicViewHolder(inflater.inflate(R.layout.item_monstermusic, viewGroup, false));
                }



                @Override
                protected void onBindViewHolder(final MusicViewHolder viewHolder, final int position, final Music music) {
                    System.out.println("LALALA  " +music.name + " - " + music.picUrl + " - MUSICA - "+ music.songUrl );
                    final String musicName = music.name;
                    String cap = musicName.substring(0, 1).toUpperCase() + musicName.substring(1);
                    viewHolder.tvContent.setText(cap);

                    Glide.with(MusicAllFragment.this)
                            .load(music.picUrl)
                            .into(viewHolder.ivContent);

                    viewHolder.itemView.setOnClickListener(
                            new View.OnClickListener(){
                                @Override
                                public void onClick(View view) {

                                    try {
                                        if(mp!=null)
                                        {
                                            mp.release();
                                            mp=null;
                                        }
                                        mp = new MediaPlayer();
                                        mp.setDataSource(music.songUrl);
/*

                                        if (mp.isPlaying()) mp.stop();

                                        mp.start();
*/

                                        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                            @Override
                                            public void onPrepared(MediaPlayer mediaPlayer) {
                                                if (mediaPlayer.isPlaying()){
                                                    mediaPlayer.stop();
                                                }
                                                mediaPlayer.start();

                                            }
                                        });
                                        mp.prepare();
                                    }catch (Exception e){ e.printStackTrace(); }
                                    System.out.println( " LALALA esto es imposibleS");
                                   // MediaController mc = new MediaController(getContext());
                                    /*mc.setAnchorView(viewHolder.vvContent);
                                    viewHolder.vvContent.setMediaController(mc);
                                    viewHolder.vvContent.setVideoPath(music.songUrl);
                                    viewHolder.vvContent.start();*/

                                }
                            });
                }

            };

            recyclerView.setAdapter(adapter);



            /*mReference = FirebaseDatabase.getInstance().getReference();

            FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<String>()
                    .setQuery(mReference.child("music"), String.class)
                    .setLifecycleOwner(this)
                    .build();

            monsterMusicAdapter = new MonsterMusicAdapter(getActivity(), options);
            recyclerView.setAdapter(monsterMusicAdapter);

            monsterMusicAdapter.startListening();*/
        }


    }
