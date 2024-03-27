/**
 * The com.mehmetali.comic_manager package contains classes related to the Comic Manager application.
 */
package com.mehmetali.comic_manager;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

//import com.mehmetali.comic_manager.Book;

/**
 * Manages the wish list of comics.
 *
 * This class provides functionalities to manage the wish list of comics,
 * including reading from and writing to a file, and interacting with the BookManager.
 *
 * The class also includes an interface for writing wish data to a file.
 *
 * @author mehmetali
 * @version 1.0
 */
public class WishManager {

  private static final String WISH_FILE_PATH = "wish.dat"; /**< The file path where wish data is stored. */
  private static final BookManager comicmanager = new BookManager(); /**< The instance of BookManager for managing comic books. */
  private List<Wish> comics = readFromFile(WISH_FILE_PATH); /**< The list of wishes loaded from the wish file. */
  private int result = writeToFile(comics, WISH_FILE_PATH); /**< The result of writing wish data to the wish file. */

  /**
   * Interface for writing wish data to a file.
   */
  public interface WishWriter {
    /**
     * Saves wish data to a file.
     *
     * @param wishes The list of wishes to be saved to the file.
     * @return An integer representing the result of the operation.
     */
    int saveUsersToFile2(List<Wish> wishes);
  }




  /**
   * Constructs a WishManager object and reads wish list from file.
   */
  public WishManager() {
    this.comics = readFromFile(WISH_FILE_PATH);
  }


  /**
   * Adds a book wish to the wish list.
   *
   * @param comic the wish to add
   * @return the title of the added wish
   */
  public int AddBook(Wish comic) {
    if (!isBookNameAvailable(comic.getTitle())) {
      System.out.println("This Book name is already in use. Please choose a different book title.");
      return -1;
    }

    comicmanager.getBookTitleByID(comic.getComicID());
    //comic.setTitle(Name);
    comics.add(comic);
    writeToFile(comics, WISH_FILE_PATH);
    System.out.println("The book has been successfully added to the wish list.");
    return 0;
  }

  /**
   * Lists all books in the wish list.
   *
   * @return the number of books listed
   */
  public int listBooks() {
    this.comics = readFromFile(WISH_FILE_PATH);

    if (comics.isEmpty()) {
      System.out.println("No books found to list.");
      return 0;
    }

    System.out.println("----- Book List -----");

    for (Wish comic : comics) {
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
   * Lists books in the wish list owned by a specific user.
   *
   * @param User the username of the user
   * @return the number of books listed
   */
  public int listBooksByUser(String User) {
    this.comics = readFromFile(WISH_FILE_PATH);

    if (comics.isEmpty()) {
      System.out.println("No books found to list.");
      return 0;
    }

    System.out.println("----- " + User + " Books in Condition -----");

    for (Wish comic : comics) {
      if (comic.getuser().equalsIgnoreCase(User)) {
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
   * Deletes a book wish from the wish list by its ID.
   *
   * @param bookID the ID of the book to delete
   * @return 0 if the book is deleted successfully, -1 otherwise
   */
  public int deleteBookByID(int bookID) {
    boolean found = false;

    for (Wish comic : comics) {
      if (comic.getComicID() == bookID) {
        comics.remove(comic);
        writeToFile(comics, WISH_FILE_PATH);
        System.out.println("Book deleted successfully.");
        found = true;
        return 0;
      }
    }

    if (!found) {
      System.out.println("No book matching book ID was found.");
      return -1;
    }

    return -1;
  }







  /**
   * Checks if a book name is available in the wish list.
   *
   * @param title the title to check
   * @return true if the title is available, false otherwise
   */
  public boolean isBookNameAvailable(String title) {
    for (Wish comic : comics) {
      if (comic.getTitle().equals(title)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Reads a list of Wish objects from a file.
   *
   * @param filePath The path of the file to read from.
   * @return The list of Wish objects read from the file, or an empty list if the file doesn't exist or cannot be read.
   */
  private List<Wish> readFromFile(String filePath) {
    List<Wish> list = new ArrayList<>();
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
        list = (List<Wish>) objectIn.readObject();
      } catch (IOException | ClassNotFoundException e) {
        System.out.println("File read error: " + e.getMessage());
      }
    }

    return list;
  }

  /**
   * Writes a list of Wish objects to a file.
   *
   * @param list The list of Wish objects to be written to the file.
   * @param filePath The path of the file to write to.
   * @return Returns 0 upon successful writing to the file, or -1 if an error occurs.
   */
  private int writeToFile(List<Wish> list, String filePath) {
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
