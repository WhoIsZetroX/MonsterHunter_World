package com.example.proyectodani2;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class MonsterPicsFragment extends Fragment {
    //XML
    ImageView imageMonster;
    LinearLayout linearLayout;
    RecyclerView recyclerView;
    //Conectar a la base de datos
    private DatabaseReference mReference = null;

    public MonsterPicsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_monster_pics, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);

        MonsterViewModel monsterViewModel = ViewModelProviders.of(getActivity()).get(MonsterViewModel.class);
        monsterViewModel.getMonsterKey().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String monsterKey) {
                loadMonsterPics(monsterKey);
                System.out.println(monsterKey + " AAAAAAAAA");
            }
        });

        //showMonsterKey(monsterKey);

        return view;

    }

    void loadMonsterPics(final String monsterKey) {
        mReference = FirebaseDatabase.getInstance().getReference();

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<MonsterPic>()
                .setQuery(mReference.child("monsters/data").child(monsterKey).child("monsterPics"), MonsterPic.class)
                .setLifecycleOwner(this)
                .build();

        MonsterPicsAdapter monsterPicsAdapter = new MonsterPicsAdapter(getActivity(), options);
        recyclerView.setAdapter(monsterPicsAdapter);

    }

    //Metodo para mostrar los datos del monstruo seleccionado
   /* void showMonsterKey(final String monsterKey) {

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("monsters").child("data").child(monsterKey).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        Monster monster = dataSnapshot.getValue(Monster.class);

                        System.out.println("AAAAAAAA");


                        for (Map.Entry<String, String> entry : monster.monsterPics.entrySet()) {

                            ImageView imageView = new ImageView(getContext());
                            //setting image position
                            imageView.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT));
                            //adding view to layout
                            linearLayout.addView(imageView);
                            //setting image resource
                            Glide.with(MonsterPicsFragment.this).load(entry.getValue()).into(imageView);
                            System.out.println(entry.getKey() + " : " + entry.getValue() + " AAAAAAAAA");

                        }
                        // Así se mostraría la imagen
                        //Glide.with(MonsterPicsFragment.this).load(value).into(viewHolder.ivContent);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("tag", "getUser:onCancelled", databaseError.toException());
                    }
                });


    }*/


}
