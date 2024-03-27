package com.mehmetali.comic_manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//import com.mehmetali.comic_manager.Book;

/**
 * Manages the wish list of comics.
 *
 * @author mehmetali
 */
public class WishManager {

  
  private static final String WISH_FILE_PATH = "wish.dat";
  private static final BookManager comicmanager = new BookManager();
  List<Wish> comics = readFromFile(WISH_FILE_PATH);
  int result = writeToFile(comics, WISH_FILE_PATH);

  

  public interface WishWriter {
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
   * @param user the username of the user
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