package com.example.tp3_h071231009;

import java.util.ArrayList;
import java.util.List;

public class DataFeed {
    public static List<Feed> getFeedList() {
        List<Feed> list = new ArrayList<>();

        list.add(new Feed(
                R.drawable.adit,
                "aditt",
                R.drawable.adit,
                "adiit",
                "hai😸",
                "18 April 2025"
                )
        );

        list.add(new Feed(
                        R.drawable.adel,
                        "adell",
                        R.drawable.adel,
                        "adell",
                        "hallo😸",
                        "19 April 2025"
                )
        );

        list.add(new Feed(
                        R.drawable.anas,
                        "anass",
                        R.drawable.anas,
                        "anass",
                        "hmm",
                        "20 April 2025"
                )
        );

        list.add(new Feed(
                        R.drawable.dennis,
                        "dennis",
                        R.drawable.dennis,
                        "dennis",
                        "caption dennis",
                        "21 April 2025"
                )
        );

        list.add(new Feed(
                        R.drawable.devi,
                        "devii",
                        R.drawable.devi,
                        "devii",
                        "caption devii",
                        "22 April 2025"
                )
        );

        list.add(new Feed(
                        R.drawable.hjudin,
                        "hjudin",
                        R.drawable.hjudin,
                        "hjudin",
                        "caption hjudin",
                        "23 April 2025"
                )
        );

        list.add(new Feed(
                        R.drawable.jarwo,
                        "jarwo",
                        R.drawable.jarwo,
                        "jarwo",
                        "caption jarwo",
                        "24 April 2025"
                )
        );

        list.add(new Feed(
                        R.drawable.mita,
                        "mitaa",
                        R.drawable.mita,
                        "mitaa",
                        "caption mitaa",
                        "10 Maret 2025"
                )
        );

        list.add(new Feed(
                        R.drawable.sopo,
                        "sopoo",
                        R.drawable.sopo,
                        "sopoo",
                        "caption sopoo",
                        "11 Maret 2025"
                )
        );

        list.add(new Feed(
                        R.drawable.ucup,
                        "ucuup",
                        R.drawable.ucup,
                        "ucuup",
                        "caption ucup",
                        "12 Maret 2025"
                )
        );

        return list;
    }

}
