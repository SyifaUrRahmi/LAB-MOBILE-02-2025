package com.example.tp4_h071231009;

import java.util.ArrayList;

public class BukuData {

    public static ArrayList<Buku> buku = getListData();

    public static ArrayList<Buku> getListData() {
        ArrayList<Buku> buku = new ArrayList<>();
        buku.add(new Buku(
                "The Novel's Extra",
                "Zigap Song",
                2018,
                "Seorang penulis terbangun di dunia novelnya sebagai karakter sampingan yang tidak penting, dan harus bertahan hidup menggunakan pengetahuannya tentang cerita.",
                "Aksi",
                4.5,
                R.drawable.aksi1
        ));

        buku.add(new Buku(
                "KinnPorsche",
                "Daemi",
                2019,
                "Seorang bartender terlibat dalam dunia mafia setelah menyelamatkan pewaris keluarga mafia dan dipaksa menjadi pengawalnya.",
                "Aksi",
                3.7,
                R.drawable.aksi2
        ));

        buku.add(new Buku(
                "Even if I Fall into the Monster's Lair, I Still Have to Go to Work",
                "Baek Deok Soo",
                2022,
                "Seorang pekerja kantoran biasa tiba-tiba terlibat dalam dunia paralel penuh monster, namun tetap harus bekerja demi hidupnya.",
                "Aksi",
                4.0,
                R.drawable.aksi3
        ));

        buku.add(new Buku(
                "Whispers in the Shadows",
                "Anonim",
                2020,
                "Seseorang terjebak di rumah tua yang dihantui roh penasaran, dan setiap malam suara-suara tak dikenal terus mendekat.",
                "Horor",
                3.6,
                R.drawable.horror1
        ));

        buku.add(new Buku(
                "The Silence Below",
                "Kim Ji Hoon",
                2021,
                "Terjebak di ruang bawah tanah yang sunyi, seorang wanita mencoba bertahan hidup saat teror perlahan menghampiri.",
                "Horor",
                3.8,
                R.drawable.horror2
        ));

        buku.add(new Buku(
                "Eyes in the Mirror",
                "Natsuko Yamamoto",
                2023,
                "Seorang siswi mulai melihat bayangan dirinya bergerak sendiri di cermin—dan ternyata itu bukan hanya halusinasi.",
                "Horor",
                4.1,
                R.drawable.horror3
        ));

        buku.add(new Buku(
                "The Code of Freedom",
                "Richard M. Stallman",
                2009,
                "Sebuah kisah perjuangan RMS membangun gerakan perangkat lunak bebas dan filosofi di balik GNU/Linux.",
                "Romansa",
                4.9,
                R.drawable.rms1
        ));

        buku.add(new Buku(
                "Free as in Freedom",
                "Sam Williams",
                2010,
                "Mengupas kehidupan pribadi dan perjuangan RMS dalam menentang kepemilikan perangkat lunak tertutup.",
                "Romansa",
                3.7,
                R.drawable.rms2
        ));

        buku.add(new Buku(
                "The GNU Manifesto",
                "Richard M. Stallman",
                1985,
                "Dokumen asli yang mengubah dunia perangkat lunak, berisi pemikiran awal RMS tentang pentingnya perangkat lunak bebas.",
                "Romansa",
                4.2,
                R.drawable.rms3
        ));

        buku.add(new Buku(
                "Kacau Tapi Lucu",
                "Budi Santosa",
                2021,
                "Kisah konyol seorang guru matematika yang nyasar jadi stand-up comedian.",
                "Komedi",
                4.5,
                R.drawable.komedi1
        ));

        buku.add(new Buku(
                "Tertawa di Tengah Krisis",
                "Indah Permatasari",
                2020,
                "Cerita satir penuh humor tentang keluarga yang berjuang di masa pandemi.",
                "Komedi",
                4.3,
                R.drawable.komedi2
        ));

        buku.add(new Buku(
                "Misi Ngakak Nasional",
                "Yusuf Arief",
                2022,
                "Tiga sahabat mencoba membuat Indonesia tertawa lewat podcast absurd mereka.",
                "Komedi",
                4.6,
                R.drawable.komedi3
        ));

        buku.add(new Buku(
                "Kerajaan Langit",
                "Dian Maharani",
                2019,
                "Petualangan seorang gadis yang menemukan jalan menuju kerajaan di langit melalui cermin tua.",
                "Fantasi",
                4.7,
                R.drawable.fantasi1
        ));

        buku.add(new Buku(
                "Api dan Bayangan",
                "Rahmat Hidayat",
                2023,
                "Seorang pemuda biasa menemukan kekuatan api dalam dirinya dan harus melawan makhluk dari bayangan.",
                "Fantasi",
                4.4,
                R.drawable.fantasi2
        ));

        buku.add(new Buku(
                "Legenda Tiga Bulan",
                "Laras Sari",
                2020,
                "Sebuah dunia yang hanya disinari tiga bulan, tempat di mana mitos menjadi kenyataan.",
                "Fantasi",
                4.8,
                R.drawable.fantasi3
        ));

        return buku;
    }
}
