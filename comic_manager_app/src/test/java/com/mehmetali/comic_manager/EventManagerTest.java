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
	  
	  Event newEvent = new Event(1, "Title1", "Content1", "User1");
	    eventManager.AddEvent(newEvent);
	    
    int result = eventManager.listEvents();
    assertEquals(1, result); // Assuming no events exist initially
    deleteFile("event.dat");
  }

  @Test
  public void listEventsByConditionTest() {
	  
	  Event newEvent = new Event(1, "Title1", "Content1", "User1");
	    eventManager.AddEvent(newEvent);
    // Test listing events by user
    int result = eventManager.listEventsByCondition("User1");
    assertEquals(2, result); // Assuming no events exist for user "User" initially
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
  
  @Test
  public void testIsEventIDAvailable_WhenIDExists() {
      EventManager eventManager = new EventManager(); // EventManager nesnesi oluşturulur veya uygun şekilde elde edilir

      // Test durumu için bir Event oluşturulur ve EventManager'e eklenir
      Event event = new Event(1, "Title4", "Content5", "User5");
      eventManager.AddEvent(event);

      // EventManager'in isEventIDAvailable metodunu çağırarak test ederiz
      boolean result = eventManager.isEventIDAvailable(1);

      // Sonuç kontrol edilir, çünkü ID mevcut olduğu için false dönmeli
      assertFalse(result);
  }

  @Test
  public void testIsEventIDAvailable_WhenIDNotExists() {
      EventManager eventManager = new EventManager(); // EventManager nesnesi oluşturulur veya uygun şekilde elde edilir

      // Test durumu için bir Event oluşturulur ve EventManager'e eklenir
      Event event = new Event(2, "Title4", "Content5", "User5");
      eventManager.AddEvent(event);

      // EventManager'in isEventIDAvailable metodunu çağırarak test ederiz
      boolean result = eventManager.isEventIDAvailable(3);

      // Sonuç kontrol edilir, çünkü ID mevcut olmadığı için true dönmeli
      assertTrue(result);
  }




  @Test
  public void testGetEventTitleByID_WhenIDNotExists() {
      EventManager eventManager = new EventManager(); // EventManager nesnesi oluşturulur veya uygun şekilde elde edilir

      // Test durumu için bir Event oluşturulur ve EventManager'e eklenir
      Event event = new Event(5, "Title4", "Content5", "User5");
      eventManager.AddEvent(event);

      // EventManager'in getEventTitleByID metodunu çağırarak test ederiz
      String result = eventManager.getEventTitleByID(2);

      // Sonuç kontrol edilir, çünkü ID mevcut olmadığı için null dönmeli
      assertNull(result);
  }

  // Add tests for other methods such as isEventNameAvailable, readUsersFromFile, saveUsersToFile
}
