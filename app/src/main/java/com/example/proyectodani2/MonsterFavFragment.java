package com.example.proyectodani2;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.Query;

/**
 * Created by dam2a on 30/01/18.
 */

public class MonsterFavFragment extends MonsterFragment {

    @Override
    Query setQuery() {
        return mReference.child("monsters/favoritos").child(FirebaseAuth.getInstance().getUid());
    }
}
