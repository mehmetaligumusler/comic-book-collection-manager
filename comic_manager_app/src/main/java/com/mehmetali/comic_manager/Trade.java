package com.mehmetali.comic_manager;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Trade implements Serializable {
    private int comicID;
    private String title;
    private int issueNumber;
    private String condition;
    private String coverArt;
    private double value;

    // Constructor
    public Trade(int comicID, String title, int issueNumber, String condition, String coverArt, double value) {
        this.comicID = comicID;
        this.title = title;
        this.issueNumber = issueNumber;
        this.condition = condition;
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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
                ", condition='" + condition + '\'' +
                ", coverArt='" + coverArt + '\'' +
                ", value=" + value +
                '}';
    }
}
