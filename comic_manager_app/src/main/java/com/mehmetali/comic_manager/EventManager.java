/**
 * The com.mehmetali.comic_manager package contains classes related to the Comic Manager application.
 */
package com.mehmetali.comic_manager;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The EventManager class manages events in the comic manager application.
 * It provides functionalities to add, list, delete, and update events.
 *
 * This class manages the list of events and provides methods for managing events.
 *
 * @author mehmetali
 * @version 1.0
 */
public class EventManager {
  private List<Event> comics; /**< The list of events managed by the EventManager. */
  private static final String Event_FILE_PATH = "event.dat"; /**< The file path where the event data is stored. */



  /**
   * Constructs an EventManager object.
   * Initializes the list of events by reading from a file.
   */
  public EventManager() {
    this.comics = readUsersFromFile();
  }


  /**
   * Adds a new event to the collection.
   * @param comic The event to be added.
   * @return The title of the added event if successful, null otherwise.
   */
  public String AddEvent(Event comic) {
    if (!isEventNameAvailable(comic.getTitle())) {
      System.out.println("This Event name is already in use. Please choose a different Event name.");
      return null;
    }

    comics.add(comic);
    saveUsersToFile(comics);
    System.out.println("Event saved successfully.");
    return comic.getTitle();
  }

  /**
   * Lists all events.
   *
   * @return The number of events listed.
   */
  public int listEvents() {
    this.comics = readUsersFromFile();

    if (comics.isEmpty()) {
      System.out.println("No Events found to list.");
      return 0;
    }

    System.out.println("----- Event List -----");

    for (Event comic : comics) {
      System.out.println("Event ID: " + comic.geteventID());
      System.out.println("Title: " + comic.getTitle());
      System.out.println("Content: " + comic.getContent());
      System.out.println("-------------------------");
    }

    return comics.size();
  }

  /**
   * Lists events based on a condition.
   *
   * @param condition The condition to filter events.
   * @return The number of events listed.
   */
  public int listEventsByCondition(String condition) {
    this.comics = readUsersFromFile();

    if (comics.isEmpty()) {
      System.out.println("No Events found to list.");
      return 0;
    }

    System.out.println("----- " + condition + " Events in Status -----");

    for (Event comic : comics) {
      if (comic.getUser().equalsIgnoreCase(condition)) {
        System.out.println("Event ID: " + comic.geteventID());
        System.out.println("Title: " + comic.getTitle());
        System.out.println("Content " + comic.getContent());
        System.out.println("User: " + comic.getUser());
        System.out.println("-------------------------");
      }
    }

    return comics.size();
  }

  /**
   * Deletes an event by its ID.
   *
   * @param EventID The ID of the event to be deleted.
   * @return 0 if the event is successfully deleted, -1 otherwise.
   */
  public int deleteEventByID(int EventID) {
    boolean found = false;

    for (Event comic : comics) {
      if (comic.geteventID() == EventID) {
        comics.remove(comic);
        saveUsersToFile(comics);
        System.out.println("Event deleted successfully.");
        found = true;
        return 0;
      }
    }

    if (!found) {
      System.out.println("No Event matching Event ID was found..");
      return -1;
    }

    return -1;
  }

  /**
   * Updates the title and content of an event by its ID.
   *
   * @param EventID   The ID of the event to be updated.
   * @param newTitle    The new title for the event.
   * @param newContent  The new content for the event.
   * @return 0 if the event title is successfully updated, -1 otherwise.
   */
  public int updateEventTitleByID(int EventID, String newTitle, String newContent) {
    boolean found = false;

    for (Event comic : comics) {
      if (comic.geteventID() == EventID) {
        comic.setContent(newContent);
        comic.setTitle(newTitle);
        saveUsersToFile(comics);
        System.out.println("Event title updated successfully.");
        found = true;
        return 0;
      }
    }

    if (!found) {
      System.out.println("No Event matching Event ID was found.");
      return -1;
    }

    return -1;
  }


  /**
   * Checks if an event name is available.
   *
   * @param title The title of the event to be checked.
   * @return true if the event name is available, false otherwise.
   */
  public boolean isEventNameAvailable(String title) {
    for (Event comic : comics) {
      if (comic.getTitle().equals(title)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Checks if an event ID is available.
   *
   * @param ID The ID of the event to be checked.
   * @return true if the event ID is available, false otherwise.
   */
  public boolean isEventIDAvailable(int ID) {
    for (Event comic : comics) {
      if (comic.geteventID() == ID) {
        return false;
      }
    }

    return true;
  }

  /**
   * Retrieves the title of an event by its ID.
   *
   * @param EventID The ID of the event.
   * @return The title of the event if found, null otherwise.
   */
  public String getEventTitleByID(int EventID) {
    for (Event comic : comics) {
      if (comic.geteventID() == EventID) {
        return comic.getTitle();
      }
    }

    return null;
  }

  /**
   * Reads events from a file.
   *
   * @return The list of events read from the file.
   */
  private List<Event> readUsersFromFile() {
    File file = new File(Event_FILE_PATH);

    if (!file.exists()) {
      try {
        if (file.createNewFile()) {
          System.out.println("Events.dat file has been created.");
        } else {
          System.out.println("Events.dat file already exists.");
        }
      } catch (IOException e) {
        System.out.println("File creation error: " + e.getMessage());
      }

      return new ArrayList<>();
    }

    try (FileInputStream fileIn = new FileInputStream(Event_FILE_PATH);
           ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
      return (List<Event>) objectIn.readObject();
    } catch (IOException | ClassNotFoundException e) {
      return new ArrayList<>();
    }
  }

  /**
   * Writes the list of events to a file.
   *
   * @param comics2 The list of events to be written to the file.
   * @return 0 if the events are successfully written to the file, -1 otherwise.
   */
  private int saveUsersToFile(List<Event> comics2) {
    try (FileOutputStream fileOut = new FileOutputStream(Event_FILE_PATH);
           ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
      objectOut.writeObject(comics2);
      return 0;
    } catch (IOException e) {
      System.out.println("File write error: " + e.getMessage());
      return -1;
    }
  }


}
