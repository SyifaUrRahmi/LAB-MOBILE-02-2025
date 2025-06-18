package com.example.tp2_h071231048;

import android.net.Uri;

public class Profile {
    private static String username = "Aisar";
    private static Uri profileImageUri;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Profile.username = username;
    }

    public static Uri getProfileImageUri() {
        return profileImageUri;
    }

    public static void setProfileImageUri(Uri uri) {
        profileImageUri = uri;
    }
}
