package com.example.proyectodani2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

/**
 * Created by dam2a on 23/02/18.
 */

public class MonsterViewModel extends AndroidViewModel {

    MutableLiveData<String> monsterKey;


    public MonsterViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getMonsterKey(){
        if(monsterKey == null) {
            monsterKey = new MutableLiveData<>();
        }
        return monsterKey;
    }

}
