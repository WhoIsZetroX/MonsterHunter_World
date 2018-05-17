package com.example.proyectodani2.weapon_info;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.proyectodani2.R;
import com.example.proyectodani2.photo_view.PhotoviewActivity;
import com.example.proyectodani2.weapon.Weapon;
import com.example.proyectodani2.weapon_list.WeaponViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class WeaponDetailFragment extends Fragment {

    //XML
    TextView weaponName, weaponDesc;
    ImageView weaponImage, weaponFlow;
    //Conectar a la base de datos
    private DatabaseReference mDatabase = null;

    public WeaponDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weapon_detail, container, false);

        weaponName = view.findViewById(R.id.weaponName);
        weaponDesc = view.findViewById(R.id.weaponDesc);
        weaponImage = view.findViewById(R.id.weaponImage);
        weaponFlow = view.findViewById(R.id.weaponFlow);


        //String monsterKey="-L5PJ79zN1mmghpBW2KV";
        WeaponViewModel weaponViewModel = ViewModelProviders.of(getActivity()).get(WeaponViewModel.class);
        weaponViewModel.getWeaponKey().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String weaponKey) {
                showWeaponKey(weaponKey);
            }
        });

        //showMonsterKey(monsterKey);

        return view;

    }

    //Metodo para mostrar los datos del monstruo seleccionado
    void showWeaponKey(String weaponKey) {

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("weapons").child("data").child(weaponKey).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        final Weapon weapon = dataSnapshot.getValue(Weapon.class);

                        weaponName.setText(weapon.name);
                        weaponDesc.setText(weapon.desc);
                        Glide.with(WeaponDetailFragment.this).load(weapon.mDetailPic).into(weaponImage);
                        Glide.with(WeaponDetailFragment.this).load(weapon.flow).into(weaponFlow);

                        weaponImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getContext(), PhotoviewActivity.class);
                                intent.putExtra("photourl", weapon.getmDetailPic());
                                getContext().startActivity(intent);
                            }
                        });
                        weaponFlow.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getContext(), PhotoviewActivity.class);
                                intent.putExtra("photourl", weapon.getFlow());
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
