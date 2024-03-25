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
  private List<Book> comics;
  private static final String BOOK_FILE_PATH = "books.dat";

  /**
   * Constructs a BookManager object and initializes the list of comic books by reading from a file.
   */
  public BookManager() {
    this.comics = readUsersFromFile();
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
    saveUsersToFile(comics);
    return comic.getTitle();
  }

  /**
   * Lists all comic books in the collection.
   *
   * @return  The number of comic books in the collection.
   */
  public int listBooks() {
    this.comics = readUsersFromFile();

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
    this.comics = readUsersFromFile();

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
        saveUsersToFile(comics);
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
        saveUsersToFile(comics);
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
   * Reads comic books from a file and returns them as a list.
   * @return A list of comic books read from the file.
   */
  private List<Book> readUsersFromFile() {
    File file = new File(BOOK_FILE_PATH);

    if (!file.exists()) {
      try {
        if (file.createNewFile()) {
          System.out.println("books.dat file has been created.");
        } else {
          System.out.println("books.dat file already exists.");
        }
      } catch (IOException e) {
        System.out.println("File creation error: " + e.getMessage());
      }

      return new ArrayList<>();
    }

    try (FileInputStream fileIn = new FileInputStream(BOOK_FILE_PATH);
           ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
      return (List<Book>) objectIn.readObject();
    } catch (IOException | ClassNotFoundException e) {
      return new ArrayList<>();
    }
  }

  /**
   * Writes comic books to a file.
   * @param comics2 The list of comic books to be written to the file.
   * @return 0 if the comic books are successfully written to the file, -1 otherwise.
   */
  private int saveUsersToFile(List<Book> comics2) {
    try (FileOutputStream fileOut = new FileOutputStream(BOOK_FILE_PATH);
           ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
      objectOut.writeObject(comics2);
      return 0;
    } catch (IOException e) {
      System.out.println("File write error: " + e.getMessage());
      return -1;
    }
  }


}
