package com.mehmetali.comic_manager;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.*;

public class WishManagerTest {

  private WishManager wishManager;
  private BookManager bookManager;
  

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
    wishManager = new WishManager();
    bookManager = new BookManager();
  }

  @After
  public void tearDown() throws Exception {
  }


  @Test
  public void addDuplicateBookTest() throws IOException {
	Book newBook = new Book(4,"Title1", 100, "User", 100);
	bookManager.AddBook(newBook);
	
	 
		
    Wish newWish = new Wish(4,"Title1", 100, "User", 100);
    int result = wishManager.AddBook(newWish);
    assertEquals(0, result);
    deleteFile("books.dat");
    deleteFile("wish.dat");
  }
  
  @Test
  public void addDuplicateBookTest_error() throws IOException {
	Book newBook = new Book(4,"Title1", 100, "User", 100);
	bookManager.AddBook(newBook);
	
    Wish newWish = new Wish(4,"Title1", 100, "User", 100);
    wishManager.AddBook(newWish);
    
    Wish newWish2 = new Wish(4,"Title1", 100, "User", 100);
    int result = wishManager.AddBook(newWish2);
    
    
    assertEquals(-1, result);
    deleteFile("books.dat");
    deleteFile("wish.dat");
  }

  @Test
  public void listBooksTest() {
    
	Book newBook = new Book(4,"Title1", 100, "User", 100);
	bookManager.AddBook(newBook);
		
    Wish newWish = new Wish(4,"Title1", 100, "User", 100);
    wishManager.AddBook(newWish);  
	  
    int result = wishManager.listBooks();
    assertEquals(1, result); // Assuming no books exist initially
    deleteFile("wish.dat");
    deleteFile("books.dat");
  }
  
  @Test
  public void listBooksTest_empty() {
      
    int result = wishManager.listBooks();
    assertEquals(0, result); // Assuming no books exist initially
    deleteFile("wish.dat");
    deleteFile("books.dat");
  }
  
  @Test
  public void listBooksByUserTest() {
	Book newBook = new Book(4,"Title1", 100, "User", 100);
	bookManager.AddBook(newBook);
			
	Wish newWish = new Wish(4,"Title1", 100, "User", 100);
	wishManager.AddBook(newWish);  
    int result = wishManager.listBooksByUser("User");
    assertEquals(1, result); // Assuming no books exist for user "User" initially
    deleteFile("wish.dat");
    deleteFile("books.dat");

  }

  @Test
  public void listBooksByUserTest_empty() {
    // Test listing books in wish list by user
    int result = wishManager.listBooksByUser("User");
    assertEquals(0, result); // Assuming no books exist for user "User" initially
    //deleteFile("wish.dat");
  }

  
  @Test
  public void deleteBookByIDTest() {

  
    Book newBook = new Book(5,"Title1", 100, "User", 100);
	bookManager.AddBook(newBook);
		

    Wish newWish = new Wish(5,"Title1", 100, "User", 100);
    wishManager.AddBook(newWish);

      

      int deleteResult = wishManager.deleteBookByID(5);
      assertEquals(0, deleteResult);

      deleteFile("books.dat");
      deleteFile("wish.dat");
  
  }
  
  @Test
  public void deleteBookByIDTest_error() {

    Book newBook = new Book(5,"Title1", 100, "User", 100);
	bookManager.AddBook(newBook);
		

    Wish newWish = new Wish(5,"Title1", 100, "User", 100);
    wishManager.AddBook(newWish);

      

      int deleteResult = wishManager.deleteBookByID(4);
      assertEquals(-1, deleteResult);

      deleteFile("books.dat");
      deleteFile("wish.dat");
  
  }

 

  // Add tests for other methods such as isBookNameAvailable, readUsersFromFile, saveUsersToFile
}
