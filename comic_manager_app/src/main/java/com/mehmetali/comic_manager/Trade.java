package com.mehmetali.comic_manager;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Trade implements Serializable {
    private int comicID;
    private String title;
    private int issueNumber;
    private String user;
    private String coverArt;
    private double value;

    // Constructor
    public Trade(int comicID, String title, int issueNumber, String user, String coverArt, double value) {
        this.comicID = comicID;
        this.title = title;
        this.issueNumber = issueNumber;
        this.user = user;
        this.coverArt = coverArt;
        this.value = value;
    }

    // Getters and setters
    public int getComicID() {
        return comicID;
    }

    public void setComicID(int comicID) {
        this.comicID = comicID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getuser() {
        return user;
    }

    public void setuser(String user) {
        this.user = user;
    }

    public String getCoverArt() {
        return coverArt;
    }

    public void setCoverArt(String coverArt) {
        this.coverArt = coverArt;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    // toString method for printing comic details
    @Override
    public String toString() {
        return "Comic{" +
                "comicID=" + comicID +
                ", title='" + title + '\'' +
                ", issueNumber=" + issueNumber +
                ", user='" + user + '\'' +
                ", coverArt='" + coverArt + '\'' +
                ", value=" + value +
                '}';
    }
}
