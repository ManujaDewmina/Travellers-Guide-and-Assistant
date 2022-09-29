package com.example.trvavelguidassistant.model;

public class SocialMediaModel {
    String userName;
    String photo;
    String location;
    String userPic;

    public SocialMediaModel() {
    }

    public SocialMediaModel(String userName, String location) {
        this.userName = userName;
        this.photo = photo;
        this.location = location;
        this.userPic = userPic;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
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
