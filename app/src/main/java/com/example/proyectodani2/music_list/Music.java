package com.example.proyectodani2.music_list;

public class Music {

    String name;
    String picUrl;
    String songUrl;

    public Music(String name, String picUrl, String songUrl) {
        this.name = name;
        this.picUrl = picUrl;
        this.songUrl = songUrl;
    }

    public Music() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

}
