package com.mehmetali.comic_manager;

import static org.junit.Assert.*;
import org.junit.*;

import java.io.File;

public class BookManagerTest {

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
  public void setUp() {
    bookManager = new BookManager();
  }


  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void addBookTest() {
    // Test adding a new book
    Book newBook = new Book(1,"Title1", 1, "User", 10);
    String result = bookManager.AddBook(newBook);
    assertEquals("Title1", result);
    deleteFile("books.dat");
  }

  @Test
  public void addDuplicateBookTest() {
    // Test adding a book with duplicate title
    Book existingBook = new Book(2,"Title2", 1, "User", 10);
    bookManager.AddBook(existingBook);
    Book newBook = new Book(3,"Title2", 2, "User2", 15);
    String result = bookManager.AddBook(newBook);
    assertNull(result);
    deleteFile("books.dat");
  }

  @Test
  public void listBooksTest() {
    // Test listing books
    int result = bookManager.listBooks();
    assertEquals(0, result); // Assuming no books exist initially
    deleteFile("books.dat");
  }

  @Test
  public void listBooksByConditionTest() {
    // Test listing books by condition
    int result = bookManager.listBooksByCondition("Condition");
    assertEquals(0, result); // Assuming no books exist initially
    deleteFile("books.dat");
  }

  @Test
  public void deleteBookByIDTest() {
    // Test deleting a book by ID
    Book newBook = new Book(4,"Title4", 1, "User", 10);
    bookManager.AddBook(newBook);
    int result = bookManager.deleteBookByID(4);
    assertEquals(0, result); // Assuming book with ID 4 exists and is deleted
    deleteFile("books.dat");
  }

  @Test
  public void updateBookTitleByIDTest() {
    // Test updating book title by ID
    Book newBook = new Book(5,"Title5", 1, "User", 10);
    bookManager.AddBook(newBook);
    int result = bookManager.updateBookTitleByID(5, "NewTitle", 2 , 10);
    assertEquals(0, result); // Assuming book with ID 5 exists and title is updated
    deleteFile("books.dat");
  }

  @Test
  public void isBookNameAvailableTest() {
    // Test checking if book name is available
    Book newBook = new Book(6,"Title6", 1, "User", 10);
    bookManager.AddBook(newBook);
    boolean result = bookManager.isBookNameAvailable("Title6");
    assertFalse(result); // Assuming book with title "Title6" exists
    deleteFile("books.dat");
  }

  @Test
  public void isBookIDAvailableTest() {
    // Test checking if book ID is available
    Book newBook = new Book(7,"Title7", 1, "User", 10);
    bookManager.AddBook(newBook);
    boolean result = bookManager.isBookIDAvailable(7);
    assertFalse(result); // Assuming book with ID 7 exists
    deleteFile("books.dat");
  }

  @Test
  public void getBookTitleByIDTest() {
    // Test getting book title by ID
    Book newBook = new Book(8,"Title8", 1, "User", 10);
    bookManager.AddBook(newBook);
    String result = bookManager.getBookTitleByID(8);
    assertEquals("Title8", result); // Assuming book with ID 8 exists
    deleteFile("books.dat");
  }
}
