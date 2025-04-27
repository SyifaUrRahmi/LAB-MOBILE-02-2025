package com.example.tp3_h071231009;

import java.util.List;

public class MainItem {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_SOROTAN = 1;
    public static final int TYPE_FEED = 2;

    private int type;
    private Sorotan sorotan;
    private Feed feed;
    private List<Sorotan> sorotanList;

    public MainItem(int type) {
        this.type = type;
    }

    public MainItem(int type, Sorotan sorotan) {
        this.type = type;
        this.sorotan = sorotan;
    }

    public MainItem(int type, List<Sorotan> sorotanList) {
        this.type = type;
        this.sorotanList = sorotanList;
    }

    public MainItem(int type, Feed feed) {
        this.type = type;
        this.feed = feed;
    }

    public int getType() {
        return type;
    }

    public Sorotan getSorotan() {
        return sorotan;
    }

    public List<Sorotan> getSorotanList() {
        return sorotanList;
    }

    public Feed getFeed() {
        return feed;
    }
}
