package com.example.instagram;

import android.os.Build;

import com.example.instagram.model.FeedModel;
import com.example.instagram.model.PhotoModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FeedRepository {
    private static List<FeedModel> feedList;

    private static int postCount = 1;

    public static void setFeedList(List<FeedModel> list) {
        feedList = list;
    }

    public static void addFeed(FeedModel feed) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            feedList.addFirst(feed);
        }
    }

    public static int getPostCount(){
        return postCount++;
    }

    public static List<FeedModel> getFeedList() {
        if (feedList == null) {
            feedList = new ArrayList<>();
            List<PhotoModel> winter1 = Arrays.asList(
                    new PhotoModel("https://i.pinimg.com/736x/4a/83/04/4a8304e14b814832f9d9b90661d55bdd.jpg"),
                    new PhotoModel(R.drawable.winter2),
                    new PhotoModel(R.drawable.winter3),
                    new PhotoModel(R.drawable.winter4)
            );

            List<PhotoModel> winter2 = Arrays.asList(
                    new PhotoModel(R.drawable.winter5),
                    new PhotoModel(R.drawable.winter6),
                    new PhotoModel(R.drawable.winter7),
                    new PhotoModel(R.drawable.winter8),
                    new PhotoModel(R.drawable.winter9),
                    new PhotoModel(R.drawable.winter10),
                    new PhotoModel(R.drawable.winter11),
                    new PhotoModel(R.drawable.winter12)
            );

            List<PhotoModel> maysakz1 = Arrays.asList(
                    new PhotoModel("https://i.pinimg.com/736x/4a/83/04/4a8304e14b814832f9d9b90661d55bdd.jpg"),
                    new PhotoModel(R.drawable.maysakz2),
                    new PhotoModel(R.drawable.maysakz3)
            );

            List<PhotoModel> wielinoxd1 = List.of(
                    new PhotoModel(R.drawable.wielinoxd1)
            );

            List<PhotoModel> chajinah1 = Arrays.asList(
                    new PhotoModel(R.drawable.chajinah1),
                    new PhotoModel(R.drawable.chajinah2),
                    new PhotoModel(R.drawable.chajinah3)
            );

            List<PhotoModel> chajinah2 = Arrays.asList(
                    new PhotoModel(R.drawable.chajinah4),
                    new PhotoModel(R.drawable.chajinah5),
                    new PhotoModel(R.drawable.chajinah6),
                    new PhotoModel(R.drawable.chajinah7),
                    new PhotoModel(R.drawable.chajinah8),
                    new PhotoModel(R.drawable.chajinah9),
                    new PhotoModel(R.drawable.chajinah10),
                    new PhotoModel(R.drawable.chajinah11)
            );

            List<PhotoModel> chajinah3 = Arrays.asList(
                    new PhotoModel(R.drawable.chajinah12),
                    new PhotoModel(R.drawable.chajinah13),
                    new PhotoModel(R.drawable.chajinah14),
                    new PhotoModel(R.drawable.chajinah15),
                    new PhotoModel(R.drawable.chajinah16)
            );


            List<PhotoModel> folkative1 = Arrays.asList(
                    new PhotoModel(R.drawable.folkative1),
                    new PhotoModel(R.drawable.folkative2),
                    new PhotoModel(R.drawable.folkative3)
            );

            List<PhotoModel> ishowspeed1 = Arrays.asList(
                    new PhotoModel(R.drawable.ishowspeed1),
                    new PhotoModel(R.drawable.ishowspeed2),
                    new PhotoModel(R.drawable.ishowspeed3),
                    new PhotoModel(R.drawable.ishowspeed4)
            );

            List<PhotoModel> highlightWielinoXd = Arrays.asList(
                    new PhotoModel(R.drawable.highlightwielinoxd1),
                    new PhotoModel(R.drawable.highlightwielinoxd2),
                    new PhotoModel(R.drawable.highlightwielinoxd3),
                    new PhotoModel(R.drawable.highlightwielinoxd4),
                    new PhotoModel(R.drawable.highlightwielinoxd5),
                    new PhotoModel(R.drawable.highlightwielinoxd6),
                    new PhotoModel(R.drawable.highlightwielinoxd7)
            );

            List<PhotoModel> highlightWinter = Arrays.asList(
                    new PhotoModel("https://i.pinimg.com/736x/70/20/f3/7020f3b509ca5dd7b1a383cf7de2140f.jpg"),
                    new PhotoModel("https://i.pinimg.com/736x/70/20/f3/7020f3b509ca5dd7b1a383cf7de2140f.jpg"),
                    new PhotoModel("https://i.pinimg.com/736x/70/20/f3/7020f3b509ca5dd7b1a383cf7de2140f.jpg"),
                    new PhotoModel("https://i.pinimg.com/736x/70/20/f3/7020f3b509ca5dd7b1a383cf7de2140f.jpg"),
                    new PhotoModel("https://i.pinimg.com/736x/70/20/f3/7020f3b509ca5dd7b1a383cf7de2140f.jpg"),
                    new PhotoModel("https://i.pinimg.com/736x/70/20/f3/7020f3b509ca5dd7b1a383cf7de2140f.jpg"),
                    new PhotoModel("https://i.pinimg.com/736x/70/20/f3/7020f3b509ca5dd7b1a383cf7de2140f.jpg")
            );

            List<PhotoModel> highlightMaysakz = Arrays.asList(
                    new PhotoModel(R.drawable.highlightmaysakz1),
                    new PhotoModel(R.drawable.highlightmaysakz2),
                    new PhotoModel(R.drawable.highlightmaysakz3),
                    new PhotoModel(R.drawable.highlightmaysakz4),
                    new PhotoModel(R.drawable.highlightmaysakz5),
                    new PhotoModel(R.drawable.highlightmaysakz6),
                    new PhotoModel(R.drawable.highlightmaysakz7)
            );

            List<PhotoModel> highlightChajinah = Arrays.asList(
                    new PhotoModel(R.drawable.highlightchajinah1),
                    new PhotoModel(R.drawable.highlightchajinah2),
                    new PhotoModel(R.drawable.highlightchajinah3),
                    new PhotoModel(R.drawable.highlightchajinah4),
                    new PhotoModel(R.drawable.highlightchajinah5),
                    new PhotoModel(R.drawable.highlightchajinah6),
                    new PhotoModel(R.drawable.highlightchajinah7)
            );


            List<PhotoModel> highlightFolkative = Arrays.asList(
                    new PhotoModel(R.drawable.highlightfolkative1),
                    new PhotoModel(R.drawable.highlightfolkative2),
                    new PhotoModel(R.drawable.highlightfolkative3),
                    new PhotoModel(R.drawable.highlightfolkative4),
                    new PhotoModel(R.drawable.highlightfolkative5),
                    new PhotoModel(R.drawable.highlightfolkative6),
                    new PhotoModel(R.drawable.highlightfolkative7)
            );

            List<PhotoModel> highlightIshowspeed = Arrays.asList(
                    new PhotoModel(R.drawable.highlightishowspeed1),
                    new PhotoModel(R.drawable.highlightishowspeed2),
                    new PhotoModel(R.drawable.highlightishowspeed3),
                    new PhotoModel(R.drawable.highlightishowspeed4),
                    new PhotoModel(R.drawable.highlightishowspeed5),
                    new PhotoModel(R.drawable.highlightishowspeed6),
                    new PhotoModel(R.drawable.highlightishowspeed7)
            );

            feedList.add(new FeedModel("https://i.pinimg.com/736x/0c/79/29/0c792933fdf327dc59199661808a230e.jpg", "imwinter", winter1, "", 2039812, 8346, "WINTER\n" +
                    "aespa",
                    "Aespa\uD83D\uDE38",
                    "https://i.pinimg.com/736x/0c/79/29/0c792933fdf327dc59199661808a230e.jpg",
                    highlightWinter,
                    5,
                    12613714,
                    4
            ));
            feedList.add(new FeedModel(R.drawable.maysakz, "maysakz", maysakz1, "bye 2024", 24096, 117, "Maysa\n" +
                        "Livestream on Youtube : Maysa Kz\n" +
                        "\uD83D\uDCE9Contact : maysakz.business@gmail.com",
                        "Myself<3",
                        R.drawable.highlightmaysakz,
                        highlightMaysakz,
                        5,
                        108827,
                        367
                ));
                feedList.add(new FeedModel(R.drawable.winter, "imwinter", winter2, "\uD83C\uDF38’", 2039812, 8346, "WINTER\n" +
                        "aespa",
                        "Aespa\uD83D\uDE38",
                        R.drawable.highlightwinter,
                        highlightWinter,
                        5,
                        12613714,
                        4));
                feedList.add(new FeedModel(R.drawable.wielinoxd, "wielinoxd", wielinoxd1, "anti ketombe", 31515, 3760, "Wielino Septian Husnaa Tirta\n" +
                        "Kreator Video\n" +
                        "Orang Biasa\n" +
                        "Bussines Inq:\n" +
                        "WA: +62 813-1153-9509 (Ivana)\n" +
                        "\uD83D\uDCE9wielinoino@gmail.com",
                        "Kocheng\uD83D\uDE38",
                        R.drawable.wielinoxd_highlight,
                        highlightWielinoXd,
                        5,
                        107488,
                        982
                ));
                feedList.add(new FeedModel(R.drawable.chajinah, "chajinah_", chajinah1, "SONY 2/3(월) 8pm - 2/6(목) \uD83D\uDDA4\n" +
                        "\n" +
                        "• 소니 국내 본사 정품\n" +
                        "• 국내 정품 최저가 공구가 20만원대\n" +
                        "• 본사 AS 가능", 62714, 62, "차진아\n" +
                        "@bof.stu\n" +
                        "@justchajinah\n" +
                        "• 4/22 화 8pm 헤이홈 홈캠 open !\n" +
                        "• 4/28 월 온이브 데이핏 open !",
                        "\uD835\uDE14\uD835\uDE0C\uD835\uDE0C\uD835\uDE0C",
                        R.drawable.highlightchajinah,
                        highlightChajinah,
                        5,
                        313147,
                        179));
                feedList.add(new FeedModel(R.drawable.chajinah, "chajinah_", chajinah2, "쭈꾸리샷\n" +
                        "\n" +
                        "이번주 금요일 남친룩마켓 !\n" +
                        "\n" +
                        "지금처럼 일교차 심한 날씨부터\n" +
                        "한여름까지 쭉 입을 수 있는 신상 가득 \uD83E\uDE75", 11573, 24, "차진아\n" +
                        "@bof.stu\n" +
                        "@justchajinah\n" +
                        "• 4/22 화 8pm 헤이홈 홈캠 open !\n" +
                        "• 4/28 월 온이브 데이핏 open !",
                        "\uD835\uDE14\uD835\uDE0C\uD835\uDE0C\uD835\uDE0C",
                        R.drawable.highlightchajinah,
                        highlightChajinah,
                        5,
                        313147,
                        179));
                feedList.add(new FeedModel(R.drawable.chajinah, "chajinah_", chajinah3, "건강관리 잘 합시다", 11101, 24, "차진아\n" +
                        "@bof.stu\n" +
                        "@justchajinah\n" +
                        "• 4/22 화 8pm 헤이홈 홈캠 open !\n" +
                        "• 4/28 월 온이브 데이핏 open !",
                        "\uD835\uDE14\uD835\uDE0C\uD835\uDE0C\uD835\uDE0C",
                        R.drawable.highlightchajinah,
                        highlightChajinah,
                        5,
                        313147,
                        179));


                feedList.add(new FeedModel(R.drawable.folkative, "folkative", folkative1, "The data from Badan Pusat Statistika (BPS) shows that the Batak ethnic group has the highest percentage of university graduates in Indonesia in 2024. They are followed by the Minangkabau, Balinese, and Bugis ethnic groups. Social observer Togar Butar-Butar explained that this could happen because the Batak people highly value education. With their life philosophy emphasizing wealth, successful descendants, and social honor, they continually encourage their children to pursue higher education. What do you think? \uD83D\uDC4F\uD83C\uDFFB\uD83D\uDC4F\uD83C\uDFFB\uD83C\uDDEE\uD83C\uDDE9\uD83C\uDDEE\uD83C\uDDE9\uD83C\uDDEE\uD83C\uDDE9", 710339, 10800, "FOLKATIVE™\n" +
                        "Komunitas\n" +
                        "A one doorway to explore Indonesia’s creative culture.\n" +
                        "TEST KEPRIBADIAN LO DISINI \uD83E\uDEF5\uD83C\uDFFB",
                        "Q/A HIGHLIGHT",
                        R.drawable.highlightfolkative,
                        highlightFolkative,
                        5,
                        6470781,
                        420
                ));

                feedList.add(new FeedModel(R.drawable.ishowspeed, "ishowspeed", ishowspeed1, "Thank you South East Asia\uD83C\uDDEE\uD83C\uDDE9\uD83C\uDDF2\uD83C\uDDFE\uD83C\uDDF5\uD83C\uDDED\uD83C\uDDF0\uD83C\uDDED\uD83C\uDDF8\uD83C\uDDEC\uD83C\uDDF9\uD83C\uDDED\uD83C\uDDFB\uD83C\uDDF3\uD83C\uDDF1\uD83C\uDDE6\uD83C\uDDE7\uD83C\uDDF3\uD83C\uDDF9\uD83C\uDDF1\uD83C\uDDF2\uD83C\uDDF2", 4906880, 99200, "IShowSpeed\n" +
                        "Kreator Video\n" +
                        "@cristiano @monkeydluffy",
                        "CHINA!",
                        R.drawable.highlightishowspeed,
                        highlightIshowspeed,
                        5,
                        33909686,
                        398

                ));

        }
        return feedList;
    }
}
