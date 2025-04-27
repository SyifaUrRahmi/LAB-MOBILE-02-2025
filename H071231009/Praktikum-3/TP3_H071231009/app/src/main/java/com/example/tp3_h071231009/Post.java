package com.example.tp3_h071231009;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Post implements Parcelable {
    private int imageResId;
    private String imageUriString;
    private String caption;
    private String date;
    private static List<Post> postList = new ArrayList<>();

    public Post(int imageResId, String caption, String date) {
        this.imageResId = imageResId;
        this.caption = caption;
        this.date = date;
        this.imageUriString = null;
    }

    public Post(String imageUriString, String caption, String date) {
        this.imageUriString = imageUriString;
        this.caption = caption;
        this.date = date;
        this.imageResId = 0;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getImageUriString() {
        return imageUriString;
    }

    public Uri getImageUri() {
        return imageUriString != null ? Uri.parse(imageUriString) : null;
    }

    public String getCaption() {
        return caption;
    }

    public String getDate() {
        return date;
    }

    protected Post(Parcel in) {
        imageResId = in.readInt();
        imageUriString = in.readString();
        caption = in.readString();
        date = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageResId);
        dest.writeString(imageUriString);
        dest.writeString(caption);
        dest.writeString(date);
    }

    public static void addPost(Post post) {
        postList.add(0, post);
    }

    public static List<Post> getAllPosts() {
        return postList;
    }



}
