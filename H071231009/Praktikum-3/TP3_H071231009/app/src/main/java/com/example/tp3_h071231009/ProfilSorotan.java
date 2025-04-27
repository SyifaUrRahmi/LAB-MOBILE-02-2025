package com.example.tp3_h071231009;

public class ProfilSorotan {
    private int imageResIdSorotan;
    private String namaSorotan;

    public ProfilSorotan(int imageResIdSorotan, String namaSorotan) {
        this.imageResIdSorotan = imageResIdSorotan;
        this.namaSorotan = namaSorotan;
    }

    public int getImageResIdSorotan() {
        return imageResIdSorotan;
    }

    public void setImageResIdSorotan(int imageResIdSorotan) {
        this.imageResIdSorotan = imageResIdSorotan;
    }

    public String getNamaSorotan() {
        return namaSorotan;
    }

    public void setNamaSorotan(String namaSorotan) {
        this.namaSorotan = namaSorotan;
    }
}
