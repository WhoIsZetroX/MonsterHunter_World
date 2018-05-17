package com.example.proyectodani2.monster_list;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.Query;

/**
 * Created by dam2a on 30/01/18.
 */

public class MonsterFavFragment extends MonsterFragment {

    @Override
    public Query setQuery() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) search.getLayoutParams();
        layoutParams.height = 1;
        search.setLayoutParams(layoutParams);
        search.setVisibility(View.INVISIBLE);
        return mReference.child("monsters/favoritos").child(FirebaseAuth.getInstance().getUid());
    }

}
