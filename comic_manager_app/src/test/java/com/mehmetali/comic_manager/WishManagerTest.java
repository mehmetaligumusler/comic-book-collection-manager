package com.mehmetali.comic_manager;

import static org.junit.Assert.*;

import java.io.File;

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
  }

  @After
  public void tearDown() throws Exception {
  }

  /*
  @Test
  public void addBookTest() {

    Book newBook = new Book(1,"Title1", 1, "User", 10);
      bookManager.AddBook(newBook);

      // Test adding a new book to wish list
      Wish newWish = new Wish(1, "Title1", 1, "User", 10);
      String result = wishManager.AddBook(newWish);
      assertEquals("Title1", result);

      deleteFile("books.dat");
      deleteFile("wish.dat");
  }
  */

  @Test
  public void addDuplicateBookTest() {
    // Test adding a book to wish list with duplicate title
    Wish existingWish = new Wish(2, "Title2", 1, "User", 10);
    wishManager.AddBook(existingWish);
    Wish newWish = new Wish(3, "Title2", 2, "User2", 15);
    String result = wishManager.AddBook(newWish);
    assertNull(result);
    deleteFile("wish.dat");
  }

  @Test
  public void listBooksTest() {
    // Test listing books in wish list
    int result = wishManager.listBooks();
    assertEquals(0, result); // Assuming no books exist initially
    deleteFile("wish.dat");
  }

  @Test
  public void listBooksByUserTest() {
    // Test listing books in wish list by user
    int result = wishManager.listBooksByUser("User");
    assertEquals(0, result); // Assuming no books exist for user "User" initially
    deleteFile("wish.dat");
  }

  /*
  @Test
  public void deleteBookByIDTest() {

    Book newBook = new Book(4,"Title1", 1, "User3", 10);
      bookManager.AddBook(newBook);

      // Test adding a new book to wish list
      Wish newWish = new Wish(4, "Title1", 1, "User3", 10);
      String result = wishManager.AddBook(newWish);

      // Test deleting a book from wish list by ID
      Wish wishToDelete = new Wish(4, "Title1", 1, "User3", 10);
      wishManager.AddBook(wishToDelete);

      int deleteResult = wishManager.deleteBookByID(4);
      assertEquals(0, deleteResult);

      deleteFile("books.dat");
      deleteFile("wish.dat");
  }
  */

  /*
  @Test
  public void updateBookTitleByIDTest() {

    Book newBook = new Book(5,"Title1", 1, "User3", 10);
      bookManager.AddBook(newBook);

      // Test adding a new book to wish list
      Wish newWish = new Wish(5, "Title1", 1, "User3", 10);
      String result = wishManager.AddBook(newWish);
      // Test updating title of a book in wish list by ID
      Wish wishToUpdate = new Wish(5, "Title4", 1, "User4", 10);
      wishManager.AddBook(wishToUpdate);

      int updateResult = wishManager.updateBookTitleByID(5, "New Title");
      assertEquals(0, updateResult);

      deleteFile("books.dat");
      deleteFile("wish.dat");
  }
  */

  // Add tests for other methods such as isBookNameAvailable, readUsersFromFile, saveUsersToFile
}
