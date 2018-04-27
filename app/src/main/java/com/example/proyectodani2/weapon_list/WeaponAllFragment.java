package com.example.proyectodani2.weapon_list;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.proyectodani2.R;
import com.example.proyectodani2.start_menu.MainActivity;
import com.example.proyectodani2.weapon.Weapon;
import com.example.proyectodani2.weapon.WeaponViewModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeaponAllFragment extends Fragment {

    public WeaponClickedListener weaponClickedListener;
    public RecyclerView recyclerView;
    public ProgressBar pbar;
    public DatabaseReference mReference;
    public WeaponAllFragment() {}

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weapon_all, container, false);

        TabLayout tabLayout = ((MainActivity) getActivity()).getTabLayout();
        tabLayout.removeAllTabs();
        tabLayout.addTab(tabLayout.newTab().setText("All weapons"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        recyclerView = view.findViewById(R.id.recycler);
        pbar = view.findViewById(R.id.progress_bar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.setAnimation(new RotateAnimation(0, 180));

        mReference = FirebaseDatabase.getInstance().getReference();


        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Weapon>()
                .setIndexedQuery(setQuery(), mReference.child("weapons/data"), Weapon.class)
                .setLifecycleOwner(this)
                .build();


        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Weapon, WeaponViewHolder>(options) {
            @Override
            public WeaponViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                return new WeaponViewHolder(inflater.inflate(R.layout.item_weapon, viewGroup, false));
            }



            @Override
            protected void onBindViewHolder(final WeaponViewHolder viewHolder, final int position, final Weapon weapon) {
                final String weaponKey = getRef(position).getKey();

                viewHolder.name.setText(weapon.name);

                Glide.with(com.example.proyectodani2.weapon_list.WeaponAllFragment.this)
                        .load(weapon.picUrl)
                        .into(viewHolder.image);

                viewHolder.itemView.setOnClickListener(
                        new View.OnClickListener(){
                            @Override
                            public void onClick(View view) {
                                WeaponViewModel weaponViewModel = ViewModelProviders.of(getActivity()).get(WeaponViewModel.class);
                                weaponViewModel.getWeaponKey().setValue(weaponKey);
                                weaponClickedListener.onWeaponClicked(weapon);

                            }
                        });
            }

            @Override
            public void onDataChanged() {
                super.onDataChanged();
                pbar.setVisibility(View.INVISIBLE);
            }
        };

        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            weaponClickedListener = (WeaponClickedListener) context; // context==MainActivity
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnButtonClickListener");
        }
    }


    public Query setQuery(){
        return mReference.child("weapons/all").orderByValue();
    }

    public interface WeaponClickedListener {
        void onWeaponClicked(Weapon weapon);
    }
}
