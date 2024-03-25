package com.mehmetali.comic_manager;

import static org.junit.Assert.*;

import org.junit.*;

public class EventTest {

  @Before
  public void setUp() throws Exception {
    // Test durumunu ayarlamak için kullanılabilir
  }

  @After
  public void tearDown() throws Exception {
    // Test sonlandırma işlemleri burada yapılabilir
  }


  @Test
  public void testEventConstructorAndGetters() {
    // Test constructor and getters
    Event event = new Event(1, "Title1", "Content1", "User1");
    assertEquals(1, event.geteventID());
    assertEquals("Title1", event.getTitle());
    assertEquals("Content1", event.getContent());
    assertEquals("User1", event.getUser());
  }

  @Test
  public void testEventSetters() {
    // Test setters
    Event event = new Event(1, "Title1", "Content1", "User1");
    event.seteventID(2);
    event.setTitle("Title2");
    event.setContent("Content2");
    event.setUser("User2");
    assertEquals(2, event.geteventID());
    assertEquals("Title2", event.getTitle());
    assertEquals("Content2", event.getContent());
    assertEquals("User2", event.getUser());
  }

  /*

  @Test
  public void testToString() {
      // Test toString method
      Event event = new Event(1, "Title1", "Content1", "User1");
      String expectedString = "event{eventID=1, title='Title1', content=Content1, user=User1}";

      assertEquals(expectedString, event.toString());
  }

  @Test
  public void testEquals() {
      // Test equals method
      Event event1 = new Event(1, "Title1", "Content1", "User1");
      Event event2 = new Event(1, "Title1", "Content1", "User1");
      Event event3 = new Event(2, "Title2", "Content2", "User2");

      assertTrue(event1.equals(event2)); // Same content, should return true
      assertFalse(event1.equals(event3)); // Different content, should return false
  }

  @Test
  public void testHashCode() {
      // Test hashCode method
      Event event1 = new Event(1, "Title1", "Content1", "User1");
      Event event2 = new Event(1, "Title1", "Content1", "User1");

      assertEquals(event1.hashCode(), event2.hashCode()); // Objects with same content should have same hash code
  }
  */
}
