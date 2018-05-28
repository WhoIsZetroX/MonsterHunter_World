package com.example.proyectodani2.monster_info;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.proyectodani2.R;
import com.example.proyectodani2.monster.Monster;
import com.example.proyectodani2.monster_list.MonsterViewModel;
import com.example.proyectodani2.photo_view.PhotoviewActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MonsterDetailsFragment extends Fragment {

    //XML
    TextView monsterName, monsterDesc;
    ImageView monsterImage, monsterWeakness;
    //Conectar a la base de datos
    private DatabaseReference mDatabase = null;

    public MonsterDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_monster_detail, container, false);

        monsterName = view.findViewById(R.id.monsterName);
        monsterDesc = view.findViewById(R.id.monsterDesc);
        monsterImage = view.findViewById(R.id.monsterImage);
        monsterWeakness = view.findViewById(R.id.monsterWeakness);

        //String monsterKey="-L5PJ79zN1mmghpBW2KV";
        MonsterViewModel monsterViewModel = ViewModelProviders.of(getActivity()).get(MonsterViewModel.class);
        monsterViewModel.getMonsterKey().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String monsterKey) {
                showMonsterKey(monsterKey);
            }
        });
        //showMonsterKey(monsterKey);

        return view;

    }

    //Metodo para mostrar los datos del monstruo seleccionado
    void showMonsterKey(String monsterKey){

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("monsters").child("data").child(monsterKey).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        final Monster monster = dataSnapshot.getValue(Monster.class);

                        monsterName.setText(monster.name);
                        monsterDesc.setText(monster.desc);
                        Glide.with(MonsterDetailsFragment.this).load(monster.mDetailPic).into(monsterImage);
                        Glide.with(MonsterDetailsFragment.this).load(monster.weakness).into(monsterWeakness);

                        monsterImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getContext(), PhotoviewActivity.class);
                                intent.putExtra("photourl", monster.getmDetailPic());
                                getContext().startActivity(intent);
                            }
                        });

                        monsterWeakness.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getContext(), PhotoviewActivity.class);
                                intent.putExtra("photourl", monster.getWeakness());
                                getContext().startActivity(intent);
                            }
                        });

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("tag", "getUser:onCancelled", databaseError.toException());
                    }
                });
    }

}
