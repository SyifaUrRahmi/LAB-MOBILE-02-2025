package com.example.tp3_h071231009;

import java.util.List;

public class MainItem2 {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_SOROTAN = 1;
    public static final int TYPE_BAWAH = 2;

    private int type;
    private ProfilSorotan profilSorotan;
    private List<ProfilSorotan> profilSorotanList;

    private List<Post> postList;
    public MainItem2(int type) {
        this.type = type;
    }

    public MainItem2(int type, ProfilSorotan profilSorotan) {
        this.type = type;
        this.profilSorotan = profilSorotan;
    }

    public MainItem2(int type, List<Post> postList, boolean isPost) {
        this.type = type;
        this.postList = postList;
    }


    public MainItem2(int type, List<ProfilSorotan> profilSorotanList) {
        this.type = type;
        this.profilSorotanList = profilSorotanList;
    }

    public int getType() {
        return type;
    }


    public ProfilSorotan getProfilSorotan() {
        return profilSorotan;
    }

    public List<ProfilSorotan> getProfilSorotanList() {
        return profilSorotanList;
    }
}
