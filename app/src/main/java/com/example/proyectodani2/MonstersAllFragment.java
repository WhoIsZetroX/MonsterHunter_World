package com.example.proyectodani2;

import com.google.firebase.database.Query;

public class MonstersAllFragment extends MonsterFragment {

    @Override
    Query setQuery() {
        return mReference.child("monsters/all");
    }
}