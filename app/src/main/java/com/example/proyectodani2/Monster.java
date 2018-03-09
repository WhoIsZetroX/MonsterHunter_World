package com.example.proyectodani2;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;

/**
 * Created by dam2a on 30/01/18.
 */

@IgnoreExtraProperties
public class Monster {
    String name;
    String desc;
    String picUrl;
    HashMap<String, String> monsterPics;
    String mDetailPic;
    //int imageUrl;



    public Monster() {

    }

    public Monster(String name, String desc, String picUrl, HashMap<String, String> monsterPics, String mDetailPic) {
        this.name = name;
        this.desc = desc;
        this.picUrl = picUrl;
        this.monsterPics = monsterPics;
        this.mDetailPic = mDetailPic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {this.picUrl = picUrl;    }

    public String getmDetailPic() {

        return mDetailPic;
    }

    public void setmDetailPic(String mDetailPic) {
        this.mDetailPic = mDetailPic;
    }

    public HashMap<String, String> getMonsterPics() {
        return monsterPics;
    }

    public void setMonsterPics(HashMap<String, String> monsterPics) {
        this.monsterPics = monsterPics;
    }
}
