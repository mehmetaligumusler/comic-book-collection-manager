/**
 * com.mehmetali.comic_manager is a group of bar utils for operating on foo things.
 */
package com.mehmetali.comic_manager;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The BookManager class manages the collection of comic books.
 * It provides methods for adding, listing, deleting, and updating comic books.
 *
 * @author mehmetali
 */
public class BookManager {
  //private List<Book> comics;
  private static final String BOOK_FILE_PATH = "books.dat"; /**< The file path where the book data is stored. */
  private static final BookManager comicmanager = new BookManager(); /**< Instance of the BookManager class. */

  List<Book> comics = readUsersFromFile(BOOK_FILE_PATH);
  int result = saveUsersToFile(comics, BOOK_FILE_PATH);

  /**
   * Constructs a BookManager object and initializes the list of comic books by reading from a file.
   */
  public BookManager() {
    this.comics = readUsersFromFile(BOOK_FILE_PATH);
  }


  /**
   * Adds a new comic book to the collection.
   *
   * @param comic   The comic book to be added.
   * @return      The title of the added comic book, or null if the comic book already exists.
   */
  public String AddBook(Book comic) {
    if (!isBookNameAvailable(comic.getTitle())) {
      return null;
    }

    comics.add(comic);
    saveUsersToFile(comics,BOOK_FILE_PATH);
    readUsersFromFile(BOOK_FILE_PATH);
    return comic.getTitle();
  }

  /**
   * Lists all comic books in the collection.
   *
   * @return  The number of comic books in the collection.
   */
  public int listBooks() {
    this.comics = readUsersFromFile(BOOK_FILE_PATH);

    if (comics.isEmpty()) {
      System.out.println("No books found to list.");
      return 0;
    }

    System.out.println("----- Book List -----");

    for (Book comic : comics) {
      System.out.println("Book ID: " + comic.getComicID());
      System.out.println("Title: " + comic.getTitle());
      System.out.println("Page Number: " + comic.getpageNumber());
      System.out.println("Situation: " + comic.getuser());
      System.out.println("Value: " + comic.getValue());
      System.out.println("-------------------------");
    }

    return comics.size();
  }

  /**
   * Lists comic books in the collection based on a specific condition.
   *
   * @param condition   The condition to filter comic books.
   * @return        The number of comic books satisfying the condition.
   */
  public int listBooksByCondition(String condition) {
    this.comics = readUsersFromFile(BOOK_FILE_PATH);
    
   
    if (comics.isEmpty()) {
      System.out.println("No books found to list..");
      return 0;
    }

    System.out.println("----- " + condition + " Books in Condition -----");

    for (Book comic : comics) {
      if (comic.getuser().equalsIgnoreCase(condition)) {
        System.out.println("Book ID: " + comic.getComicID());
        System.out.println("Title: " + comic.getTitle());
        System.out.println("Page Number: " + comic.getpageNumber());
        System.out.println("Situation: " + comic.getuser());
        System.out.println("Value: " + comic.getValue());
        System.out.println("-------------------------");
      }
    }

    return comics.size();
  }

  /**
   * Deletes a comic book from the collection based on its ID.
   *
   * @param bookID  The ID of the comic book to be deleted.
   * @return      0 if the comic book is successfully deleted, -1 otherwise.
   */
  public int deleteBookByID(int bookID) {
    boolean found = false;

    for (Book comic : comics) {
      if (comic.getComicID() == bookID) {
        comics.remove(comic);
        saveUsersToFile(comics,BOOK_FILE_PATH);
        System.out.println("Book deleted successfully.");
        found = true;
        return 0;
      }
    }

    if (!found) {
      System.out.println("No book matching the book ID was found.");
      return -1;
    }

    return -1;
  }

  /**
   * Updates the title of a comic book in the collection based on its ID.
   *
   * @param bookID    The ID of the comic book to be updated.
   * @param newTitle    The new title for the comic book.
   * @param PageNumber    The new PageNumbers for the comic book.
   * @param value    The new value for the comic book.
   * @return        0 if the comic book title is successfully updated, -1 otherwise.
   */
  public int updateBookTitleByID(int bookID, String newTitle,int PageNumber,int value) 
  {
    boolean found = false;

    for (Book comic : comics) {
      if (comic.getComicID() == bookID) {
        comic.setTitle(newTitle);
        comic.setpageNumber(PageNumber);
        comic.setValue(value);
        saveUsersToFile(comics,BOOK_FILE_PATH);
        System.out.println("The book title has been successfully updated.");
        found = true;
        return 0;
      }
    }

    if (!found) {
      System.out.println("No book matching the book ID was found.");
      return -1;
    }

    return -1;
  }

  /**
   * Checks if a given comic book title is available in the collection.
   * @param title   The title of the comic book.
   * @return      true if the title is available, false otherwise.
   */
  public boolean isBookNameAvailable(String title) {
    for (Book comic : comics) {
      if (comic.getTitle().equals(title)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Checks if a given comic book ID is available in the collection.
   * @param ID The ID of the comic book.
   * @return true if the ID is available, false otherwise.
   */
  public boolean isBookIDAvailable(int ID) {
    for (Book comic : comics) {
      if (comic.getComicID() == ID) {
        return false;
      }
    }

    return true;
  }

  /**
   * Retrieves the title of a comic book based on its ID.
   * @param bookID  The ID of the comic book.
   * @return      The title of the comic book, or null if not found.
   */
  public String getBookTitleByID(int bookID) {
    for (Book comic : comics) {
      if (comic.getComicID() == bookID) {
        return comic.getTitle();
      }
    }

    return null;
  }
  
  
  /**
   * Retrieves the page number of the book with the specified ID.
   * 
   * @param bookID The ID of the book.
   * @return The page number of the book with the specified ID, or 0 if the book is not found.
   */
  public int getBookPageNumberByID(int bookID) {
      for (Book comic : comics) {
          if (comic.getComicID() == bookID) {
              return comic.getpageNumber();
          }
      }
      return 0;
  }

  /**
   * Retrieves the value of the book with the specified ID.
   * 
   * @param bookID The ID of the book.
   * @return The value of the book with the specified ID, or 0 if the book is not found.
   */
  public int getBookValueByID(int bookID) {
      for (Book comic : comics) {
          if (comic.getComicID() == bookID) {
              return comic.getValue();
          }
      }
      return 0;
  }

  
  
  
  /**
   * Reads a list of Book objects from a file.
   * 
   * @param filePath The path of the file to read from.
   * @return The list of Book objects read from the file, or an empty list if the file doesn't exist or cannot be read.
   */
  private List<Book> readUsersFromFile(String filePath) {
      List<Book> list = new ArrayList<>();
      File file = new File(filePath);

      if (!file.exists()) {
          try {
              if (file.createNewFile()) {
                  System.out.println(filePath + " file has been created.");
              } else {
                  System.out.println(filePath + " file already exists.");
              }
          } catch (IOException e) {
              System.out.println("File creation error: " + e.getMessage());
          }
      } else {
          try (FileInputStream fileIn = new FileInputStream(filePath);
               ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
              list = (List<Book>) objectIn.readObject();
          } catch (IOException | ClassNotFoundException e) {
              System.out.println("File read error: " + e.getMessage());
          }
      }

      return list;
  }

  /**
   * Writes a list of Book objects to a file.
   * 
   * @param list The list of Book objects to be written to the file.
   * @param filePath The path of the file to write to.
   * @return Returns 0 upon successful writing to the file, or -1 if an error occurs.
   */
  private int saveUsersToFile(List<Book> list, String filePath) {
      try (FileOutputStream fileOut = new FileOutputStream(filePath);
           ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
          objectOut.writeObject(list);
          return 0;
      } catch (IOException e) {
          System.out.println("File write error: " + e.getMessage());
          return -1;
      }
  }



}
