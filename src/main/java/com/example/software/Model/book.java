package com.example.software.Model;

public class book {
    private int id;
    private String title;
    private String content;
    private String path;

    public book() {
    }

    public book(int id, String title, String path) {
        this.id = id;
        this.title = title;
        this.path = path;
    }

    public book(String title, String path) {
        this.title = title;
        this.path = path;
    }

    public book(String title, String content, String path) {
        this.title = title;
        this.content = content;
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
