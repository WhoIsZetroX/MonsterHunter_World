package com.example.proyectodani2.weapon_info;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectodani2.monster_info.MonsterDetailsFragment;
import com.example.proyectodani2.monster_info.MonsterPicsFragment;
import com.example.proyectodani2.monster_info.MonsterVideosFragment;
import com.example.proyectodani2.R;

/**
 * A simple {@link Fragment} subclass.
 */


public class WeaponInfoPagerFragment extends Fragment {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public WeaponInfoPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_monster_info_pager, container, false);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        mViewPager = view.findViewById(R.id.MonsterInfoPagerContainer);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        return view;
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if(position == 0) {
            } else if(position == 1) {
                return new MonsterPicsFragment();
            } else if(position == 2) {
                return new MonsterVideosFragment();
            }
            // este es el fragment por defecto del pager?
            return new MonsterDetailsFragment();
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}

