package com.mehmetali.comic_manager;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.*;

public class TradeManagerTest {

  private TradeManager trademanager;
  private BookManager bookManager;
  private User<String> user;
  

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
	user = new User<>("testUser", "password123", 300);
	trademanager = new TradeManager();
    bookManager = new BookManager();
  }

  @After
  public void tearDown() throws Exception {
  }


  @Test
  public void addDuplicateBookTest() throws IOException {
	Book newBook = new Book(8,"Title1", 100, "User", 100);
	bookManager.AddBook(newBook);
	
	
		
	Trade trade = new Trade(8,"Title1", 100, "User", 100);
    int result = trademanager.AddTrade(trade);
    assertEquals(0, result);
    deleteFile("books.dat");
    deleteFile("trade.dat");
  }
  
  @Test
  public void addDuplicateBookTest_error() throws IOException {
	Book newBook = new Book(4,"Title1", 100, "User", 100);
	bookManager.AddBook(newBook);
	
	Trade trade = new Trade(4,"Title1", 100, "User", 100);
    trademanager.AddTrade(trade);
	
    int result = trademanager.AddTrade(trade);
    assertEquals(-1, result);
    deleteFile("books.dat");
    deleteFile("trade.dat");
  }
  
  @Test
  public void ListBookTest() throws IOException {
	Book newBook = new Book(4,"Title1", 100, "User", 100);
	bookManager.AddBook(newBook);
	
	
		
	Trade trade = new Trade(4,"Title1", 100, "User", 100);
    trademanager.AddTrade(trade);
    
    int result = trademanager.listAllTradeList();
    
    assertEquals(1, result);
    deleteFile("books.dat");
    deleteFile("trade.dat");
  }
  
  @Test
  public void MyListBookTest() throws IOException {
	Book newBook = new Book(4,"Title1", 100, "User", 100);
	bookManager.AddBook(newBook);
	
	
		
	Trade trade = new Trade(4,"Title1", 100, "User", 100);
    trademanager.AddTrade(trade);
    
    int result = trademanager.listMyTradeList("User");
    
    assertEquals(2, result);
    deleteFile("books.dat");
    deleteFile("trade.dat");
  }

  
  @Test
  public void DeleteBookTest() throws IOException {
	Book newBook = new Book(4,"Title1", 100, "User", 100);
	bookManager.AddBook(newBook);
	
	
		
	Trade trade = new Trade(4,"Title1", 100, "User", 100);
    trademanager.AddTrade(trade);
    
    int result = trademanager.deleteTradeByID(4);
    
    assertEquals(0, result);
    deleteFile("books.dat");
    deleteFile("trade.dat");
  }
  
  @Test
  public void BuyDuplicateBookTest() throws IOException {
	Book newBook = new Book(4,"Title1", 100, "User", 100);
	bookManager.AddBook(newBook);
	
	
		
	Trade trade = new Trade(4,"Title1", 100, "User", 100);
    trademanager.AddTrade(trade);
    
    int result = trademanager.BuyTradeByID(4);
    
    assertEquals(0, result);
    deleteFile("books.dat");
    deleteFile("trade.dat");
  }
  
  @Test
  public void testIsTradeIDAvailable_WhenIDExists() {
      TradeManager tradeManager = new TradeManager(); // TradeManager nesnesi oluşturulur veya uygun şekilde elde edilir

      // Test durumu için bir Trade oluşturulur ve TradeManager'e eklenir
      Trade trade = new Trade(1,"Title8", 100, "User", 100);
      tradeManager.AddTrade(trade);

      // TradeManager'in isTradeIDAvailable metodunu çağırarak test ederiz
      boolean result = tradeManager.isTradeIDAvailable(1);

      // Sonuç kontrol edilir, çünkü ID mevcut olduğu için false dönmeli
      assertFalse(result);
  }

  @Test
  public void testIsTradeIDAvailable_WhenIDNotExists() {
      TradeManager tradeManager = new TradeManager(); // TradeManager nesnesi oluşturulur veya uygun şekilde elde edilir

      // Test durumu için bir Trade oluşturulur ve TradeManager'e eklenir
      Trade trade = new Trade(4,"Title7", 100, "User", 100);
      tradeManager.AddTrade(trade);

      // TradeManager'in isTradeIDAvailable metodunu çağırarak test ederiz
      boolean result = tradeManager.isTradeIDAvailable(2);

      // Sonuç kontrol edilir, çünkü ID mevcut olmadığı için true dönmeli
      assertTrue(result);
  }
 

  // Add tests for other methods such as isBookNameAvailable, readUsersFromFile, saveUsersToFile
}
