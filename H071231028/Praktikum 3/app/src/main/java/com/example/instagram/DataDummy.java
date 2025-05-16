package com.example.instagram;

import java.util.ArrayList;

public class DataDummy {
    public static ArrayList<Feed> feeds = generateDummyFeeds();
    public static ArrayList<FeedProfile> feedProfiles = generateDummyFeedProfiles();


    public static ArrayList<Story> stories = generateDummyStory();

    private static ArrayList<Feed> generateDummyFeeds() {
        ArrayList<Feed> feeds = new ArrayList<>();

        feeds.add(new Feed("coffee.girl", "coffe", "coffe for life", R.drawable.c1, R.drawable.c2, R.drawable.c3));
        feeds.add(new Feed("starry.night", "starry", "stargazing night", R.drawable.star1, R.drawable.star2, R.drawable.star5));
        feeds.add(new Feed("unhas.day", "student",  "Red Guitar", R.drawable.g1, R.drawable.g2, R.drawable.g3));
        feeds.add(new Feed("coding.life", "hacker", "IT pride", R.drawable.k2, R.drawable.k3, R.drawable.k1));
        feeds.add(new Feed("secret.admire", "admire", "I hope that in my next story, when I fall in love, I can be the winner.", R.drawable.s1, R.drawable.s2, R.drawable.s3));
        feeds.add(new Feed("bot.confess", "confess", " I just wanted to express my feelings", R.drawable.b1, R.drawable.b2, R.drawable.b3));
        feeds.add(new Feed("music.vibes", "lany", "How to be loved like lany song?", R.drawable.l1, R.drawable.l2, R.drawable.l3));
        feeds.add(new Feed("hopeless.romantic", "No romance","Love is horror movie and I’m never the lead hmm no I’m always the first one to die", R.drawable.r1, R.drawable.r2, R.drawable.r3));
        feeds.add(new Feed("drakor.rawr", "rawr", "Drakor date?", R.drawable.m1, R.drawable.m2, R.drawable.m3));
        feeds.add(new Feed("Edinburgh.town", "oldtown","almost there", R.drawable.t1, R.drawable.t2, R.drawable.t3));

        return feeds;
    }

    private static ArrayList<FeedProfile> generateDummyFeedProfiles() {
        ArrayList<FeedProfile> feedProfiles = new ArrayList<>();
        String uriBase = "android.resource://com.example.instagram/";
        feedProfiles.add(new FeedProfile(uriBase + R.drawable.star1));
        feedProfiles.add(new FeedProfile(uriBase + R.drawable.star2));
        feedProfiles.add(new FeedProfile(uriBase + R.drawable.star3));
        feedProfiles.add(new FeedProfile(uriBase + R.drawable.star4));
        feedProfiles.add(new FeedProfile(uriBase + R.drawable.star5));

        return feedProfiles;
    }

    private static ArrayList<Story> generateDummyStory() {
        ArrayList<Story> stories = new ArrayList<>();

        stories.add(new Story("Kdrama", R.drawable.m4));
        stories.add(new Story("ScAd", R.drawable.story2));
        stories.add(new Story("Burgh", R.drawable.story3));
        stories.add(new Story("cFee", R.drawable.story4));
        stories.add(new Story("Crush", R.drawable.story5));
        stories.add(new Story("Code", R.drawable.story6));

        return stories;
    }
}