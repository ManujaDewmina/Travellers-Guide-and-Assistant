package com.example.trvavelguidassistant.model;

public class SocialMediaModel {
    String userName;
    //String imageUrlSM;
    String location;
    //String userPic;

    public SocialMediaModel() {
    }

    public SocialMediaModel(String userName, String location) {
        this.userName = userName;
        //this.imageUrlSM = imageUrl;
        this.location = location;
        //this.userPic = userPic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
