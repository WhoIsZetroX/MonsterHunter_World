package com.example.proyectodani2;


import android.app.FragmentTransaction;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;


public abstract class MonsterFragment extends Fragment {
    String monsterKey;
    interface MonsterClickedListener {
        void onMonsterClicked(Monster monster);
    }

    MonsterClickedListener monsterClickedListener;


    RecyclerView recyclerView;


    DatabaseReference mReference;

    public MonsterFragment() {}

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_monster, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mReference = FirebaseDatabase.getInstance().getReference();

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Monster>()
                .setIndexedQuery(setQuery(), mReference.child("monsters/data"), Monster.class)
                .setLifecycleOwner(this)
                .build();


        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Monster, MonsterViewHolder>(options) {
            @Override
            public MonsterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                return new MonsterViewHolder(inflater.inflate(R.layout.item_monster, viewGroup, false));
            }



            @Override
            protected void onBindViewHolder(final MonsterViewHolder viewHolder, final int position, final Monster monster) {
                monsterKey = getRef(position).getKey();

                viewHolder.name.setText(monster.name);

                Glide.with(MonsterFragment.this)
                        .load(monster.picUrl)
                        .into(viewHolder.image);

//                if (true) {
////                    viewHolder.like.setImageResource(R.drawable.heart_on);
////                    viewHolder.numLikes.setTextColor(getResources().getColor(R.color.red));
//                } else {
////                    viewHolder.like.setImageResource(R.drawable.heart_off);
////                    viewHolder.numLikes.setTextColor(getResources().getColor(R.color.grey));
//                }


//                viewHolder.numLikes.setText(String.valueOf(post.likeCount));
//
//                viewHolder.likeLayout.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        onLikeClicked(postKey);
//                    }
//                });

                viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {

                        mReference.child("monsters").child("favoritos").child(FirebaseAuth.getInstance().getUid()).child(monsterKey).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Boolean value = dataSnapshot.getValue(Boolean.class);

                                if(value == null) {
                                    Toast.makeText(getActivity(), "Added to favorites!", Toast.LENGTH_LONG).show();
                                    mReference.child("monsters").child("favoritos").child(FirebaseAuth.getInstance().getUid()).child(monsterKey).setValue(true);
                                } else {
                                    Toast.makeText(getActivity(), "Deleted from favorites!", Toast.LENGTH_LONG).show();
                                    mReference.child("monsters").child("favoritos").child(FirebaseAuth.getInstance().getUid()).child(monsterKey).setValue(null);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        return true;
                    }
                });
                viewHolder.itemView.setOnClickListener(
                        new View.OnClickListener(){
                            @Override
                            public void onClick(View view) {
                                monsterKey = getRef(position).getKey();
                                MonsterViewModel monsterViewModel = ViewModelProviders.of(getActivity()).get(MonsterViewModel.class);
                                monsterViewModel.getMonsterKey().setValue(monsterKey);
                                monsterClickedListener.onMonsterClicked(monster);

                            }
                        });
            }

        };


        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            monsterClickedListener = (MonsterClickedListener) context; // context==MainActivity
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnButtonClickListener");
        }
    }


    abstract Query setQuery();

}
