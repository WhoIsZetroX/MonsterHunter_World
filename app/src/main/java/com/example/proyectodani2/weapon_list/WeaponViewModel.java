package com.example.proyectodani2.weapon_list;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

/**
 * Created by dam2a on 23/02/18.
 */

public class WeaponViewModel extends AndroidViewModel {

    MutableLiveData<String> weaponKey;

    public WeaponViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getWeaponKey(){
        if(weaponKey == null) {
            weaponKey = new MutableLiveData<>();
        }
        return weaponKey;
    }
}