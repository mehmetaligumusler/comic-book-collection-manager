package com.mehmetali.comic_manager;


import java.io.Serializable;

@SuppressWarnings("serial")
public class Event implements Serializable {
    private int eventID;
    private String title;
    private String content;
    private String user;


    // Constructor
    public Event(int eventID, String title, String content, String user) {
        this.eventID = eventID;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    // Getters and setters
    public int geteventID() {
        return eventID;
    }

    public void seteventID(int eventID) {
        this.eventID = eventID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public String getUser() {
        return user;
    }

    public void setUser(String User) {
        this.user = User;
    }

  
    // toString method for printing event details
    @Override
    public String toString() {
        return "event{" +
                "eventID=" + eventID +
                ", title='" + title + '\'' +
                ", content=" + content +
                ", user=" + user +
                '}';
    }
}
