package com.example.tp3_h071231009;

import java.util.ArrayList;
import java.util.List;

public class ProfilSorotanData {

    public  static List<ProfilSorotan> getProfilSorotanList() {
        List<ProfilSorotan> profilSorotanList = new ArrayList<>();

        profilSorotanList.add(new ProfilSorotan(R.drawable.sorotan1, "Sorotan1"));
        profilSorotanList.add(new ProfilSorotan(R.drawable.sorotan2, "Sorotan2"));
        profilSorotanList.add(new ProfilSorotan(R.drawable.sorotan3, "Sorotan3"));
        profilSorotanList.add(new ProfilSorotan(R.drawable.sorotan4, "Sorotan4"));
        profilSorotanList.add(new ProfilSorotan(R.drawable.sorotan5, "Sorotan5"));

        return profilSorotanList;
    }
}
