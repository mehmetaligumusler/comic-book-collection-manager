/**
 * The com.mehmetali.comic_manager package contains classes related to the Comic Manager application.
 */
package com.mehmetali.comic_manager;

import java.io.Serializable;


/**
 * The Event class represents an event in the comic manager application.
 * It contains information such as event ID, title, content, and user.
 *
 * This class implements the Serializable interface to support object serialization.
 *
 * @author mehmetali
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Event implements Serializable {
  private int eventID; /**< The unique ID of the event. */
  private String title; /**< The title of the event. */
  private String content; /**< The content of the event. */
  private String user; /**< The user associated with the event. */


  /**
   * Constructs an Event object with the specified parameters.
   *
   * @param eventID   The unique identifier of the event.
   * @param title     The title of the event.
   * @param content   The content of the event.
   * @param user      The user associated with the event.
   */
  public Event(int eventID, String title, String content, String user) {
    this.eventID = eventID;
    this.title = title;
    this.content = content;
    this.user = user;
  }


  /**
   * Gets the ID of the event.
   *
   * @return the ID of the event
   */
  public int geteventID() {
    return eventID;
  }


  /**
   * Sets the ID of the event.
   *
   * @param eventID the ID of the event to set
   */
  public void seteventID(int eventID) {
    this.eventID = eventID;
  }


  /**
   * Gets the title of the event.
   *
   * @return the title of the event
   */
  public String getTitle() {
    return title;
  }


  /**
   * Sets the title of the event.
   *
   * @param title the title of the event to set
   */
  public void setTitle(String title) {
    this.title = title;
  }


  /**
   * Gets the content of the event.
   *
   * @return the content of the event
   */
  public String getContent() {
    return content;
  }


  /**
   * Sets the content of the event.
   *
   * @param content the content of the event to set
   */
  public void setContent(String content) {
    this.content = content;
  }


  /**
   * Gets the user associated with the event.
   *
   * @return the user associated with the event
   */
  public String getUser() {
    return user;
  }


  /**
   * Sets the user associated with the event.
   *
   * @param user the user associated with the event to set
   */
  public void setUser(String user) {
    this.user = user;
  }


  /**
   * Returns a string representation of the Event object.
   *
   * @return A string containing the event details.
   */
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
