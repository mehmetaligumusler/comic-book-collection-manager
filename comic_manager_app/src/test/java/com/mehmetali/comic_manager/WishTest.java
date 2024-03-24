package com.mehmetali.comic_manager;

import static org.junit.Assert.*;

import org.junit.*;

public class WishTest {

  private Wish wish;

  @Before
  public void setUp() {
    // Wish nesnesi oluştur
    wish = new Wish(1, "Title1", 10, "User1", 50);
  }

  @After
  public void tearDown() {
    // Her test sonrası Wish nesnesini sıfırla
    wish = null;
  }

  @Test
  public void testWishConstructorAndGetters() {
    // Kurucu ve getter'ları test et
    assertEquals(1, wish.getComicID());
    assertEquals("Title1", wish.getTitle());
    assertEquals(10, wish.getpageNumber());
    assertEquals("User1", wish.getuser());
    assertEquals(50, wish.getValue());
  }

  @Test
  public void testWishSetters() {
    // Setter'ları test et
    wish.setComicID(2);
    wish.setTitle("Title2");
    wish.setpageNumber(20);
    wish.setuser("User2");
    wish.setValue(100);
    assertEquals(2, wish.getComicID());
    assertEquals("Title2", wish.getTitle());
    assertEquals(20, wish.getpageNumber());
    assertEquals("User2", wish.getuser());
    assertEquals(100, wish.getValue());
  }
  /*
      @Test
      public void testWishToString() {
          // toString metotunu test et
          String expectedString = "Wish{comicID=1, title='Title1', pageNumber=10, user='User1', value=50}";
          assertEquals(expectedString, wish.toString());
      }
      */
}
