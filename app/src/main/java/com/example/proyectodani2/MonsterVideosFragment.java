package com.example.proyectodani2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;


public class MonsterVideosFragment extends Fragment {

    VideoView videoView;
    MediaController mc;

    public MonsterVideosFragment() {
            // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragmentç
        View rootview = inflater.inflate(R.layout.fragment_monster_videos, container, false);
        videoView = rootview.findViewById(R.id.video);



        return rootview;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            System.out.println("BISIBLE");
            mc = new MediaController(getActivity());

            String path = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.n1;
            videoView.setVideoURI(Uri.parse(path));
            videoView.setMediaController(mc);
            ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

            videoView.start();

        } else {
            System.out.println("NO BISIBLE");

            if (videoView!=null){
                videoView.pause();
                videoView.setMediaController(null);
                ((AppCompatActivity)getActivity()).getSupportActionBar().show();

            }
        }
    }

    //    @Override
//    public void onStop() {
//        super.onStop();
//        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
//    }
}
