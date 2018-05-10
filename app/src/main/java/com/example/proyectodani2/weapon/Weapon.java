package com.example.proyectodani2.weapon;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by dam2a on 06/04/18.
 */
@IgnoreExtraProperties
public class Weapon {
    public String name;
    public String desc;
    public String picUrl;
    public String mDetailPic;
    public String flow;
    //int imageUrl;

    public Weapon() {

    }

    public Weapon(String name, String desc, String picUrl, String mDetailPic, String flow) {
        this.name = name;
        this.desc = desc;
        this.picUrl = picUrl;
        this.mDetailPic = mDetailPic;
        this.flow = flow;
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

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public void setmDetailPic(String mDetailPic) {
        this.mDetailPic = mDetailPic;
    }
}
