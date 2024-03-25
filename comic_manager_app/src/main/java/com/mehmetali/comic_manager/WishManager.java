package com.mehmetali.comic_manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.mehmetali.comic_manager.Book;

/**
 * Manages the wish list of comics.
 *
 * @author mehmetali
 */
public class WishManager {
  private List<Wish> comics;
  private static final String WISH_FILE_PATH = "wish.dat";
  private static final BookManager comicmanager = new BookManager();

  /**
   * Constructs a WishManager object and reads wish list from file.
   */
  public WishManager() {
    this.comics = readUsersFromFile();
  }


  /**
   * Adds a book wish to the wish list.
   *
   * @param comic the wish to add
   * @return the title of the added wish
   */
  public String AddBook(Wish comic) {
    if (comicmanager.isBookIDAvailable(comic.getComicID())) {
      System.out.println("No such book found.");
      return null;
    }

    if (!isBookNameAvailable(comic.getTitle())) {
      System.out.println("This Book name is already in use. Please choose a different book title.");
      return null;
    }

    String Name = comicmanager.getBookTitleByID(comic.getComicID());
    comic.setTitle(Name);
    comics.add(comic);
    saveUsersToFile(comics);
    System.out.println("The book has been successfully added to the wish list.");
    return comic.getTitle();
  }

  /**
   * Lists all books in the wish list.
   *
   * @return the number of books listed
   */
  public int listBooks() {
    this.comics = readUsersFromFile();

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
   * @param user the username of the user
   * @return the number of books listed
   */
  public int listBooksByUser(String User) {
    this.comics = readUsersFromFile();

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
        saveUsersToFile(comics);
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
   * Updates the title of a book wish in the wish list by its ID.
   *
   * @param bookID the ID of the book to update
   * @param newTitle the new title to set
   * @return 0 if the title is updated successfully, -1 otherwise
   */
  public int updateBookTitleByID(int bookID, String newTitle) {
    boolean found = false;

    for (Wish comic : comics) {
      if (comic.getComicID() == bookID) {
        comic.setTitle(newTitle);
        saveUsersToFile(comics);
        System.out.println("Book title updated successfully.");
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
   * Reads the wish list from the file.
   * If the file does not exist, it creates a new one and returns an empty list.
   *
   * @return the list of wishes read from the file or an empty list if the file does not exist or an error occurs
   */
  private List<Wish> readUsersFromFile() {
    File file = new File(WISH_FILE_PATH);

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

    try (FileInputStream fileIn = new FileInputStream(WISH_FILE_PATH);
           ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
      return (List<Wish>) objectIn.readObject();
    } catch (IOException | ClassNotFoundException e) {
      return new ArrayList<>();
    }
  }

  /**
   * Saves the wish list to the file.
   *
   * @param wishes the list of wishes to save
   * @return 0 if the wishes are saved successfully, -1 otherwise
   */
  private int saveUsersToFile(List<Wish> comics2) {
    try (FileOutputStream fileOut = new FileOutputStream(WISH_FILE_PATH);
           ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
      objectOut.writeObject(comics2);
      return 0;
    } catch (IOException e) {
      return -1;
    }
  }


}
