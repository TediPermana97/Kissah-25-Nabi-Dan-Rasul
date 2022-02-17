package com.taristoreapps.namanabilengkap.model;

public class Wallpaper {
    private final String id;
    private final String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Wallpaper(String id, String img) {
        this.id = id;
        this.name=img;
    }
}
