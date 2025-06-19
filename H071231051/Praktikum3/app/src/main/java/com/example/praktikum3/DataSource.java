package com.example.praktikum3;

import com.example.praktikum3.Post;
import com.example.praktikum3.R;
import com.example.praktikum3.Highlight;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataSource {
    public static ArrayList<Post> getDummyPosts(String packageName) {
        ArrayList<Post> posts = new ArrayList<>();

        posts.add(new Post("android.resource://" + packageName + "/" + R.drawable.tzuyu2, "night"));
        posts.add(new Post("android.resource://" + packageName + "/" + R.drawable.pict1, "today ootd color is black"));
        posts.add(new Post("android.resource://" + packageName + "/" + R.drawable.pict2, "sunrise"));
        posts.add(new Post("android.resource://" + packageName + "/" + R.drawable.pict3, "fresh"));
        posts.add(new Post("android.resource://" + packageName + "/" + R.drawable.pict5, "shiny"));

        return posts;
    }
    public static ArrayList<FeedPost> getDummyFeedPosts(String packageName) {
        ArrayList<FeedPost> feedPosts = new ArrayList<>();
        Map<String, UserProfile> profiles = getUserProfiles(packageName);

        feedPosts.add(new FeedPost(
                "android.resource://" + packageName + "/" + R.drawable.nayeon,
                "bunny",
                "nayeon",
                profiles.get("nayeon").getProfileImageUrl(),
                1000,
                "baru saja"
        ));

        feedPosts.add(new FeedPost(
                "android.resource://" + packageName + "/" + R.drawable.jeongyeon,
                "helloo",
                "jeongyeon",
                profiles.get("jeongyeon").getProfileImageUrl(),
                627,
                "1 jam yang lalu"
        ));

        feedPosts.add(new FeedPost(
                "android.resource://" + packageName + "/" + R.drawable.momo,
                "anyeong",
                "momo",
                profiles.get("momo").getProfileImageUrl(),
                259,
                "1 jam yang lalu"
        ));

        feedPosts.add(new FeedPost(
                "android.resource://" + packageName + "/" + R.drawable.jihyo,
                "coffee",
                "jihyo",
                profiles.get("jihyo").getProfileImageUrl(),
                528,
                "2 jam yang lalu"
        ));

        feedPosts.add(new FeedPost(
                "android.resource://" + packageName + "/" + R.drawable.twice,
                "twice",
                "Twice",
                profiles.get("Twice").getProfileImageUrl(),
                9917,
                "8 jam yang lalu"
        ));

        feedPosts.add(new FeedPost(
                "android.resource://" + packageName + "/" + R.drawable.chaeyeong,
                "haii",
                "chaeyeong",
                profiles.get("chaeyeong").getProfileImageUrl(),
                635,
                "1 hari yang lalu"
        ));

        feedPosts.add(new FeedPost(
                "android.resource://" + packageName + "/" + R.drawable.mina,
                "morning",
                "mina",
                profiles.get("mina").getProfileImageUrl(),
                900,
                "2 hari yang lalu"
        ));

        feedPosts.add(new FeedPost(
                "android.resource://" + packageName + "/" + R.drawable.dahyun,
                "with my book",
                "dahyunie",
                profiles.get("dahyunie").getProfileImageUrl(),
                635,
                "2 hari yang lalu"
        ));

        feedPosts.add(new FeedPost(
                "android.resource://" + packageName + "/" + R.drawable.sana,
                "hi",
                "sana",
                profiles.get("sana").getProfileImageUrl(),
                635,
                "2 hari yang lalu"
        ));

        feedPosts.add(new FeedPost(
                "android.resource://" + packageName + "/" + R.drawable.pict2,
                "sunrise",
                "Twice",
                profiles.get("Twice").getProfileImageUrl(),
                9917,
                "3 hari yang lalu"
        ));

        return feedPosts;
    }


    public static Map<String, UserProfile> getUserProfiles(String packageName) {
        Map<String, UserProfile> profiles = new HashMap<>();

        profiles.put("nayeon", new UserProfile(
                "nayeon",
                "nayeonie",
                "i'm a bunny?!",
                "android.resource://" + packageName + "/" + R.drawable.nayeon,
                1,
                4453,
                9
        ));

        profiles.put("jihyo", new UserProfile(
                "jihyo",
                "jihyonie",
                "the leader of twice",
                "android.resource://" + packageName + "/" + R.drawable.jihyo,
                1,
                8765,
                9
        ));

        profiles.put("jeongyeon", new UserProfile(
                "jeongyeon",
                "yeon",
                "singing",
                "android.resource://" + packageName + "/" + R.drawable.jeongyeon,
                1,
                5432,
                9
        ));

        profiles.put("momo", new UserProfile(
                "momo",
                "momo",
                "dance machine",
                "android.resource://" + packageName + "/" + R.drawable.momo,
                1,
                6543,
                9
        ));

        profiles.put("mina", new UserProfile(
                "mina",
                "minaa",
                "ballerina",
                "android.resource://" + packageName + "/" + R.drawable.mina,
                1,
                5432,
                9
        ));

        profiles.put("chaeyeong", new UserProfile(
                "chaeyeong",
                "chaeyeong",
                "ballerina",
                "android.resource://" + packageName + "/" + R.drawable.chaeyeong,
                1,
                5432,
                9
        ));

        profiles.put("Twice", new UserProfile(
                "Twice",
                "once",
                "once-twice",
                "android.resource://" + packageName + "/" + R.drawable.pptwice,
                5,
                9876,
                9
        ));

        profiles.put("sana", new UserProfile(
                "sana",
                "sanaya",
                "sunshine",
                "android.resource://" + packageName + "/" + R.drawable.sana,
                1,
                8876,
                9
        ));

        profiles.put("dahyunie", new UserProfile(
                "dahyunie",
                "dahyunie",
                "sunshine",
                "android.resource://" + packageName + "/" + R.drawable.dahyun,
                1,
                8476,
                9
        ));



        return profiles;
    }

    public static ArrayList<Highlight> getDummyHighlights() {
        ArrayList<Highlight> highlights = new ArrayList<>();
        highlights.add(new Highlight(R.drawable.twice, "twice"));
        highlights.add(new Highlight(R.drawable.tzuyu2, "night"));
        highlights.add(new Highlight(R.drawable.pict1, "concer"));
        highlights.add(new Highlight(R.drawable.pict3, "sky"));
        highlights.add(new Highlight(R.drawable.pict2, "sun"));
        highlights.add(new Highlight(R.drawable.pict4, "party"));
        highlights.add(new Highlight(R.drawable.tzuyu, "allaboutzyu"));
        return highlights;
    }

    public static ArrayList<Highlight> getUserProfileHighlights(String username) {
        ArrayList<Highlight> highlights = new ArrayList<>();
        switch (username) {
            case "jihyo":
                highlights.add(new Highlight(R.drawable.jihyo, "i'am"));
                break;
            case "sana":
                highlights.add(new Highlight(R.drawable.sana, "me"));
                break;
            case "chaeyeong":
                highlights.add(new Highlight(R.drawable.chaeyeong, "me"));
                break;
            case "nayeon":
                highlights.add(new Highlight(R.drawable.nayeon, "me"));
                break;
            default:
                highlights.add(new Highlight(R.drawable.twice, "us"));
                break;
        }
        return highlights;
    }

}

