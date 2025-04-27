package com.example.tp3_h071231009;

import java.util.ArrayList;
import java.util.List;

public class DataSorotan {

    public  static List<Sorotan> getSorotanList() {
        List<Sorotan> list = new ArrayList<>();

        list.add(new Sorotan(R.drawable.contohsorotan, "Cerita Anda"));
        list.add(new Sorotan(R.drawable.dennis, "denniis"));
        list.add(new Sorotan(R.drawable.ucup, "ucuup"));
        list.add(new Sorotan(R.drawable.mita, "mitaa"));
        list.add(new Sorotan(R.drawable.sopo, "sopoo"));

        return list;
    }
}
