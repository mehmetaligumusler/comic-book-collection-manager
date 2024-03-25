package com.mehmetali.comic_manager;

import static org.junit.Assert.*;

import org.junit.*;

public class BookTest {



  @Before
  public void setUp() throws Exception {
    // Test durumunu ayarlamak için kullanılabilir
  }

  @After
  public void tearDown() throws Exception {
    // Test sonlandırma işlemleri burada yapılabilir
  }

  @Test
  public void testBookConstructorAndGetters() {
    // Test constructor and getters
    Book book = new Book(1, "Title1", 10, "User1", 50);
    assertEquals(1, book.getComicID());
    assertEquals("Title1", book.getTitle());
    assertEquals(10, book.getpageNumber());
    assertEquals("User1", book.getuser());
    assertEquals(50, book.getValue());
  }

  @Test
  public void testBookSetters() {
    // Test setters
    Book book2 = new Book(3, "Title1", 10, "User1", 50);
    book2.setComicID(2);
    book2.setTitle("Title2");
    book2.setpageNumber(20);
    book2.setuser("User2");
    book2.setValue(100);
    assertEquals(2, book2.getComicID());
    assertEquals("Title2", book2.getTitle());
    assertEquals(20, book2.getpageNumber());
    assertEquals("User2", book2.getuser());
    assertEquals(100, book2.getValue());
  }
  /*
      @Test
      public void testToString() {
          // Test toString method
          Book book = new Book(4, "Title1", 10, "User1", 50);
          String expectedString = "Comic{comicID=4, title='Title1', pageNumber=10, user='User1', value=50}";

          assertEquals(expectedString, book.toString());
      }

      @Test
      public void testEquals() {
          // Test equals method
          Book book1 = new Book(5, "Title1", 10, "User1", 50);
          Book book2 = new Book(5, "Title1", 10, "User1", 50);
          Book book3 = new Book(6, "Title2", 20, "User2", 100);

          assertTrue(book1.equals(book2)); // Same content, should return true
          assertFalse(book1.equals(book3)); // Different content, should return false
      }

      @Test
      public void testHashCode() {
          // Test hashCode method
          Book book1 = new Book(7, "Title1", 10, "User1", 50);
          Book book2 = new Book(7, "Title1", 10, "User1", 50);

          assertEquals(book1.hashCode(), book2.hashCode()); // Objects with same content should have same hash code
      }
      */
}
