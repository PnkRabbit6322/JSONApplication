package com.example.jsonapplication;

public class ItemModel {
    private int id;
    private String name;
    private String userName;
    private String Email;
    private String thumbnailSource;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getThumbnailSource() {
        return thumbnailSource;
    }

    public void setThumbnailSource(String thumbnailSource) {
        this.thumbnailSource = thumbnailSource;
    }

    public ItemModel(int id, String name, String userName, String email, String thumbnailSource) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.Email = email;
        this.thumbnailSource = thumbnailSource;
    }
}
