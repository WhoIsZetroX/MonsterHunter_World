package com.example.proyectodani2.weapon_info;


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
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.proyectodani2.R;
import com.example.proyectodani2.weapon_list.WeaponViewModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WeaponPicsFragment extends Fragment {
    //XML
    ImageView imageWeapon;
    LinearLayout linearLayout;
    RecyclerView recyclerView;
    WeaponPicsAdapter weaponPicsAdapter;
    //Conectar a la base de datos
    private DatabaseReference mReference = null;

    public WeaponPicsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_weapon_pics, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        WeaponViewModel weaponViewModel = ViewModelProviders.of(getActivity()).get(WeaponViewModel.class);
        weaponViewModel.getWeaponKey().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String weaponKey) {
                loadWeaponPics(weaponKey);
            }
        });
        return view;

    }

    void loadWeaponPics(final String weaponKey) {
        mReference = FirebaseDatabase.getInstance().getReference();

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<String>()
                .setQuery(mReference.child("weapons/data").child(weaponKey).child("weaponPics"), String.class)
                .setLifecycleOwner(this)
                .build();

        weaponPicsAdapter = new WeaponPicsAdapter(getActivity(), options);
        recyclerView.setAdapter(weaponPicsAdapter);

        weaponPicsAdapter.startListening();
    }

    //Metodo para mostrar los datos del monstruo seleccionado
   /* void loadMonsterPics(final String monsterKey) {

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
