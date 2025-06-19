package com.example.tp6;

import java.util.List;

public class UserResponse {
    private Info info;
    private List<User> results;

    public Info getInfo() {
        return info;
    }

    public List<User> getResults() {
        return results;
    }

    public static class Info {
        private int count;
        private int pages;
        private String next;
        private String prev;

        public int getCount() { return count; }
        public int getPages() { return pages; }
        public String getNext() { return next; }
        public String getPrev() { return prev; }
    }
}
