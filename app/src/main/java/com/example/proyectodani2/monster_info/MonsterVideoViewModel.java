package com.example.proyectodani2.monster_info;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

/**
 * Created by dam2a on 10/04/18.
 */

public class MonsterVideoViewModel extends AndroidViewModel {

    MutableLiveData<String> videoKey;

    public MonsterVideoViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getVideoKey() {
        if (videoKey == null) {
            videoKey = new MutableLiveData<>();
        }
        return videoKey;
    }
}
