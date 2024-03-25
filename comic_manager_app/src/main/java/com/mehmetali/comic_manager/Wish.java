package com.mehmetali.comic_manager;

import java.io.Serializable;

/**
 * Represents a comic wish.
 *
 * @author mehmetali
 */
@SuppressWarnings("serial")
public class Wish implements Serializable {
  private int comicID;
  private String title;
  private int pageNumber;
  private String user;
  private int value;


  /**
   * Constructs a Wish object.
   *
   * @param comicID    the ID of the comic
   * @param title      the title of the comic
   * @param pageNumber the page number of the comic
   * @param user       the user who made the wish
   * @param value      the value of the comic
   */
  public Wish(int comicID, String title, int pageNumber, String user, int value) {
    this.comicID = comicID;
    this.title = title;
    this.pageNumber = pageNumber;
    this.user = user;
    this.value = value;
  }

  /**
   * Gets the ID of the comic.
   *
   * @return the ID of the comic
   */
  public int getComicID() {
    return comicID;
  }

  /**
   * Sets the ID of the comic.
   *
   * @param comicID the ID of the comic to set
   */
  public void setComicID(int comicID) {
    this.comicID = comicID;
  }

  /**
   * Gets the title of the comic.
   *
   * @return the title of the comic
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets the title of the comic.
   *
   * @param title the title of the comic to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Gets the page number of the comic.
   *
   * @return the page number of the comic
   */
  public int getpageNumber() {
    return pageNumber;
  }

  /**
   * Sets the page number of the comic.
   *
   * @param pageNumber the page number of the comic to set
   */
  public void setpageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  /**
   * Gets the user associated with the comic.
   *
   * @return the user associated with the comic
   */
  public String getuser() {
    return user;
  }

  /**
   * Sets the user associated with the comic.
   *
   * @param user the user associated with the comic to set
   */
  public void setuser(String user) {
    this.user = user;
  }

  /**
   * Gets the value of the comic.
   *
   * @return the value of the comic
   */
  public int getValue() {
    return value;
  }

  /**
   * Sets the value of the comic.
   *
   * @param value the value of the comic to set
   */
  public void setValue(int value) {
    this.value = value;
  }

  /**
   * Returns a string representation of the comic wish.
   *
   * @return a string representation of the comic wish
   */
  @Override
  public String toString() {
    return "Comic{" +
           "comicID=" + comicID +
           ", title='" + title + '\'' +
           ", pageNumber=" + pageNumber +
           ", user='" + user + '\'' +
           ", value=" + value +
           '}';
  }
}
