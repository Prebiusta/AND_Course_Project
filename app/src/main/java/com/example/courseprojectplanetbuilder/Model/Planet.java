package com.example.courseprojectplanetbuilder.Model;

public class Planet {
    private String id;
    private String name;
    private String author;
    private int maxSize;
    private int currentSize;

    public Planet() {
    }

    public Planet(String name, int maxSize, String author) {
        this.name = name;
        this.maxSize = maxSize;
        this.author = author;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", maxSize=" + maxSize +
                ", currentSize=" + currentSize +
                '}';
    }
}
