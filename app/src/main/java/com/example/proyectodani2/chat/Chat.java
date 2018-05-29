package com.example.proyectodani2.chat;

public class Chat {
    String text;
    String user;
    String order;

    public Chat(String text, String user, String order) {
        this.text = text;
        this.user = user;
        this.order = order;
    }

    public Chat() {
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
