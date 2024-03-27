/**
 * The com.mehmetali.comic_manager package contains classes related to the Comic Manager application.
 */
package com.mehmetali.comic_manager;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The TradeManager class manages the trading of comic books.
 * It handles adding, listing, deleting, and buying/selling comic books.
 *
 * This class manages the list of trades and provides methods for managing trade operations.
 *
 * @author mehmetali
 * @version 1.0
 */
public class TradeManager {
  private List<Trade> comics; /**< The list of trades managed by the TradeManager. */
  private static final String Trade_FILE_PATH = "trade.dat"; /**< The file path where the trade data is stored. */
  private static final BookManager comicmanager = new BookManager(); /**< The instance of BookManager for managing comic books. */
  private static final UserManager usermanager = new UserManager(); /**< The instance of UserManager for managing users. */



  /**
   * Constructs a TradeManager object.
   * Initializes the list of trades by reading from file.
   */
  public TradeManager() {
    this.comics = readUsersFromFile();
  }

  /**
   * Adds a comic book to the trade list.
   * If the book ID is not available or the book title is already used, it returns null.
   *
   * @param comic The comic book to add to the trade list.
   * @return The title of the comic book if added successfully, null otherwise.
   */
  public int AddTrade(Trade comic) {
    

    if (!isTradeNameAvailable(comic.getTitle())) {
      System.out.println("This Book name is already in use. Please choose a different book name.");
      return -1;
    }

    /*
    comic.setTitle(comicmanager.getBookTitleByID(comic.getComicID()));
    comic.setpageNumber(comicmanager.getBookPageNumberByID(comic.getComicID()));
    comic.setValue(comicmanager.getBookValueByID(comic.getComicID()));
    */
    
    comics.add(comic);
    saveUsersToFile(comics);
    //comicmanager.deleteBookByID(comic.getComicID());
    System.out.println("The book has been successfully added to the Trade list.");
    return 0;
  }

  /**
   * Lists all comic books available for trade.
   *
   * @return The number of comic books listed.
   */
  public int listAllTradeList() {
    this.comics = readUsersFromFile();

    if (comics.isEmpty()) {
      System.out.println("No books found to list.");
      return 0;
    }

    System.out.println("----- Book List -----");

    for (Trade comic : comics) {
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
   * Lists the comic books available for trade by a specific condition (e.g., user).
   *
   * @param condition The condition to filter the comic books (e.g., user).
   * @return The number of comic books listed.
   */
  public int listMyTradeList(String condition) {
    this.comics = readUsersFromFile();

    if (comics.isEmpty()) {
      System.out.println("No books found to list.");
      return 0;
    }

    System.out.println("----- " + condition + " Books in Condition -----");

    for (Trade comic : comics) {
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
   * Deletes a comic book from the trade list by its ID.
   * Moves the book back to the user's book list.
   *
   * @param TradeID The ID of the comic book to delete from the trade list.
   * @return 0 if successfully deleted, -1 otherwise.
   */
  public int deleteTradeByID(int TradeID) {
    boolean found = false;

    for (Trade comic : comics) {
      if (comic.getComicID() == TradeID) {
        comics.remove(comic);
        saveUsersToFile(comics);
        System.out.println("Book deleted successfully.");
        found = true;
        Book newUser = new Book(TradeID, comic.getTitle(),comic.getpageNumber(),comic.getuser(),comic.getValue());
        comicmanager.AddBook(newUser);
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
   * Buys a comic book from the trade list by its ID.
   * Transfers the book ownership and credits between users.
   *
   * @param TradeID The ID of the comic book to buy from the trade list.
   * @return 0 if successfully bought, -1 otherwise.
   */
  public int BuyTradeByID(int TradeID) {
    boolean found = false;

    for (Trade comic : comics) {
      if (comic.getComicID() == TradeID) {
        
      String oldUser = comic.getuser();
      System.out.print(oldUser);
      comics.remove(comic);
      saveUsersToFile(comics);
      System.out.println("Book deleted successfully.");
      found = true;
      Book newUser = new Book(TradeID, comic.getTitle(),comic.getpageNumber(),Main.LoginName,comic.getValue());
      comicmanager.AddBook(newUser);
      usermanager.creditSellscore(Main.LoginName, comic.getValue());
      usermanager.creditbuyscore(oldUser, comic.getValue());
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
   * Checks if a trade name is available (not already used).
   *
   * @param title The title to check availability for.
   * @return True if the title is available, false otherwise.
   */
  public boolean isTradeNameAvailable(String title) {
    for (Trade comic : comics) {
      if (comic.getTitle().equals(title)) {
        return false;
      }
    }

    return true;
  }
  
  /**
   * Returns the value of the book with the specified ID.
   * 
   * @param bookID The ID of the book.
   * @return The value of the book with the specified ID, or 0 if the book is not found.
   */
  public int getBookValueByID(int bookID) {
      for (Trade comic : comics) {
          if (comic.getComicID() == bookID) {
              return comic.getValue();
          }
      }

      return 0;
  }


  /**
   * Checks if a trade ID is available (not already used).
   *
   * @param ID The ID to check availability for.
   * @return True if the ID is available, false otherwise.
   */
  public boolean isTradeIDAvailable(int ID) {
    for (Trade comic : comics) {
      if (comic.getComicID() == ID) {
        return false;
      }
    }

    return true;
  }

  /**
   * Retrieves the title of a comic book by its ID.
   *
   * @param TradeID The ID of the comic book.
   * @return The title of the comic book if found, null otherwise.
   */
  public String getTradeTitleByID(int TradeID) {
    for (Trade comic : comics) {
      if (comic.getComicID() == TradeID) {
        return comic.getTitle();
      }
    }

    return null;
  }

  /**
   * Reads the list of trades from the file specified by the Trade_FILE_PATH.
   * If the file doesn't exist, it creates a new one and returns an empty list.
   *
   * @return The list of trades read from the file or an empty list if the file doesn't exist.
   */
  private List<Trade> readUsersFromFile() {
    File file = new File(Trade_FILE_PATH);

    if (!file.exists()) {
      try {
        if (file.createNewFile()) {
          System.out.println("Trades.dat file created.");
        } else {
          System.out.println("Trades.dat file already exists.");
        }
      } catch (IOException e) {
        System.out.println("File creation error: " + e.getMessage());
      }

      return new ArrayList<>();
    }

    try (FileInputStream fileIn = new FileInputStream(Trade_FILE_PATH);
           ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
      return (List<Trade>) objectIn.readObject();
    } catch (IOException | ClassNotFoundException e) {
      return new ArrayList<>();
    }
  }

  /**
   * Saves the list of trades to the file specified by the Trade_FILE_PATH.
   *
   * @param comics2 The list of trades to be saved to the file.
   * @return 0 if the trades are successfully saved, -1 otherwise.
   */
  private int saveUsersToFile(List<Trade> comics2) {
    try (FileOutputStream fileOut = new FileOutputStream(Trade_FILE_PATH);
           ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
      objectOut.writeObject(comics2);
      System.out.println("Book successfully saved to file.");
      return 0;
    } catch (IOException e) {
      System.out.println("File write error: " + e.getMessage());
      return -1;
    }
  }


}
