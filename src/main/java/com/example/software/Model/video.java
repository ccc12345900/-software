package com.example.software.Model;

public class video {
    private int id;
    private String title;
    private String href;
    private String timelong;

    public video() {
    }

    public video(String title, String href, String timelong) {
        this.title = title;
        this.href = href;
        this.timelong = timelong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }


    public String getTimelong() {
        return timelong;
    }

    public void setTimelong(String timelong) {
        this.timelong = timelong;
    }
}
