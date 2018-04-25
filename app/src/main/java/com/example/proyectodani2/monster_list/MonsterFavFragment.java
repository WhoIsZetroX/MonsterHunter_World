package com.example.proyectodani2.monster_list;

import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.Query;

/**
 * Created by dam2a on 30/01/18.
 */

public class MonsterFavFragment extends MonsterFragment {

    @Override
    public Query setQuery() {
        search.setVisibility(View.INVISIBLE);
        return mReference.child("monsters/favoritos").child(FirebaseAuth.getInstance().getUid());
    }
}
