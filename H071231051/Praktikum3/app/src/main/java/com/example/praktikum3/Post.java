package com.example.praktikum3;

public class Post {
    private final String imageUri;
    private final String caption;

    public Post(String imageUri, String caption) {
        this.imageUri = imageUri;
        this.caption = caption;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getCaption() {
        return caption;
    }

    public String setImageUri(){
        return imageUri;
    }

    public String setCaption(){
        return caption;
    }
}
