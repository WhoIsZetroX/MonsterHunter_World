package com.example.proyectodani2.monster_list;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.firebase.database.Query;

public class MonstersAllFragment extends MonsterFragment {

    @Override
    public Query setQuery() {

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) search.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        search.setLayoutParams(layoutParams);
        search.setVisibility(View.VISIBLE);
        return mReference.child("monsters/all");
    }
}