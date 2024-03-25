package com.mehmetali.comic_manager;

import static org.junit.Assert.*;

import org.junit.*;

public class TradeTest {

  private Trade trade;

  @Before
  public void setUp() {
    // Trade nesnesi oluştur
    trade = new Trade(1, "Title1", 10, "User1", 50);
  }

  @After
  public void tearDown() {
    // Her test sonrası Trade nesnesini sıfırla
    trade = null;
  }

  @Test
  public void testTradeConstructorAndGetters() {
    // Kurucu ve getter'ları test et
    assertEquals(1, trade.getComicID());
    assertEquals("Title1", trade.getTitle());
    assertEquals(10, trade.getpageNumber());
    assertEquals("User1", trade.getuser());
    assertEquals(50, trade.getValue());
  }

  @Test
  public void testTradeSetters() {
    // Setter'ları test et
    trade.setComicID(2);
    trade.setTitle("Title2");
    trade.setpageNumber(20);
    trade.setuser("User2");
    trade.setValue(100);
    assertEquals(2, trade.getComicID());
    assertEquals("Title2", trade.getTitle());
    assertEquals(20, trade.getpageNumber());
    assertEquals("User2", trade.getuser());
    assertEquals(100, trade.getValue());
  }
  /*
      @Test
      public void testTradeToString() {
          // toString metotunu test et
          String expectedString = "Trade{comicID=1, title='Title1', pageNumber=10, user='User1', value=50}";
          assertEquals(expectedString, trade.toString());
      }
      */
}
