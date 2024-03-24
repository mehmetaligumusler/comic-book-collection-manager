package com.mehmetali.comic_manager;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.*;

public class EventManagerTest {

  private EventManager eventManager;

  public static boolean deleteFile(String filepath) {
    File file = new File(filepath);

    if (file.exists()) {
      return file.delete();
    } else {
      return false;
    }
  }



  @Before
  public void setUp() throws Exception {
    eventManager = new EventManager();
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void addEventTest() {
    // Test adding a new event
    Event newEvent = new Event(1, "Title1", "Content1", "User1");
    String result = eventManager.AddEvent(newEvent);
    assertEquals("Title1", result);
    deleteFile("event.dat");
  }

  @Test
  public void addDuplicateEventTest() {
    // Test adding an event with duplicate title
    Event existingEvent = new Event(2, "Title2", "Content2", "User2");
    eventManager.AddEvent(existingEvent);
    Event newEvent = new Event(3, "Title2", "Content3", "User3");
    String result = eventManager.AddEvent(newEvent);
    assertNull(result);
    deleteFile("event.dat");
  }

  @Test
  public void listEventsTest() {
    // Test listing events
    int result = eventManager.listEvents();
    assertEquals(0, result); // Assuming no events exist initially
    deleteFile("event.dat");
  }

  @Test
  public void listEventsByConditionTest() {
    // Test listing events by user
    int result = eventManager.listEventsByCondition("User");
    assertEquals(0, result); // Assuming no events exist for user "User" initially
    deleteFile("event.dat");
  }

  @Test
  public void deleteEventByIDTest() {
    // Test deleting an event by ID
    Event eventToDelete = new Event(4, "Title3", "Content4", "User4");
    eventManager.AddEvent(eventToDelete);
    int deleteResult = eventManager.deleteEventByID(4);
    assertEquals(0, deleteResult);
    deleteFile("event.dat");
  }

  @Test
  public void updateEventTitleByIDTest() {
    // Test updating title of an event by ID
    Event eventToUpdate = new Event(5, "Title4", "Content5", "User5");
    eventManager.AddEvent(eventToUpdate);
    int updateResult = eventManager.updateEventTitleByID(5, "New Title", "New Content");
    assertEquals(0, updateResult);
    deleteFile("event.dat");
  }

  // Add tests for other methods such as isEventNameAvailable, readUsersFromFile, saveUsersToFile
}
