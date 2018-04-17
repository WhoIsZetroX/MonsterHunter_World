package com.example.proyectodani2.monster_list;

import com.google.firebase.database.Query;

public class MonstersAllFragment extends MonsterFragment {

    @Override
    public Query setQuery() {
        return mReference.child("monsters/all");
    }
}