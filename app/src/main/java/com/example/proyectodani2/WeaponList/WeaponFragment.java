package com.example.proyectodani2.WeaponList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectodani2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeaponFragment extends Fragment {


    public WeaponFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weapon, container, false);
    }


}
