package com.mehmetali.comic_manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages user operations such as registration, login, and credit manipulation.
 *
 * @author mehmetali
 */
public class UserManager {
  private List<User> users;
  private static final String USER_FILE_PATH = "users.dat";

  /**
   * Constructs a UserManager and reads users from the file.
   */
  public UserManager() {
    this.users = readUsersFromFile();
  }

  /**
   * Registers a new user.
   *
   * @param user the user to register
   * @return the username of the registered user, or null if registration fails
   */
  public String registerUser(User user) {
    if (!isUsernameAvailable(user.getUsername())) {
      System.out.println("This username is already in use. Please choose a different username.");
      return null;
    }

    users.add(user);
    saveUsersToFile(users);
    System.out.println("User registered successfully.");
    return user.getUsername();
  }

  /**
   * Checks if a username is available.
   *
   * @param username the username to check
   * @return true if the username is available, false otherwise
   */
  public boolean isUsernameAvailable(String username) {
    for (User user : users) {
      if (user.getUsername().equals(username)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Reads users from the file.
   *
   * @return the list of users read from the file, or an empty list if the file doesn't exist
   */
  private List<User> readUsersFromFile() {
    File file = new File(USER_FILE_PATH);

    if (!file.exists()) {
      try {
        if (file.createNewFile()) {
          System.out.println("users.dat file created.");
        } else {
          System.out.println("users.dat file already exists.");
        }
      } catch (IOException e) {
        System.out.println("File creation error: " + e.getMessage());
      }

      return new ArrayList<>();
    }

    try (FileInputStream fileIn = new FileInputStream(USER_FILE_PATH);
           ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
      return (List<User>) objectIn.readObject();
    } catch (IOException | ClassNotFoundException e) {
      return new ArrayList<>();
    }
  }

  /**
   * Saves users to the file.
   *
   * @param userList the list of users to save
   * @return 0 if saving is successful, -1 otherwise
   */
  private int saveUsersToFile(List<User> userList) {
    try (FileOutputStream fileOut = new FileOutputStream(USER_FILE_PATH);
           ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
      objectOut.writeObject(userList);
      return 0;
    } catch (IOException e) {
      System.out.println("File creation error: " + e.getMessage());
      return -1;
    }
  }

  /**
   * Logs in a user with the given username and password.
   *
   * @param username the username of the user
   * @param password the password of the user
   * @return the user object if login is successful, null otherwise
   */
  public User login(String username, String password) {
    for (User user : users) {
      if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
        return user;
      }
    }

    return null;
  }

  /**
   * Increases the wallet balance of a user.
   *
   * @param username the username of the user
   * @param credit   the amount of credit to add to the wallet
   * @return the new wallet balance of the user, or -1 if the user is not found
   */
  public int creditbuyscore(String username, int credit) {
    User foundUser = null;

    for (User user : users) {
      if (user.getUsername().equals(username)) {
        foundUser = user;
        break;
      }
    }

    if (foundUser == null) {
      System.out.println("User not found.");
      return -1;
    }

    int currentWallet = foundUser.getWallet();
    int newWallet = currentWallet + credit;
    foundUser.setWallet(newWallet);
    saveUsersToFile(users);
    return newWallet;
  }

  /**
   * Decreases the wallet balance of a user.
   *
   * @param username the username of the user
   * @param credit   the amount of credit to deduct from the wallet
   * @return the new wallet balance of the user, or -1 if the user is not found
   */
  public int creditSellscore(String username, int credit) {
    User foundUser = null;

    for (User user : users) {
      if (user.getUsername().equals(username)) {
        foundUser = user;
        break;
      }
    }

    if (foundUser == null) {
      System.out.println("User not found.");
      return -1;
    }

    int currentWallet = foundUser.getWallet();
    int newWallet = currentWallet - credit;
    foundUser.setWallet(newWallet);
    saveUsersToFile(users);
    return newWallet;
  }
  /**
      * Gets the wallet balance of a user.
      *
      * @param username the username of the user
      * @return the wallet balance of the user, or -1 if the user is not found
      */
  public int getUserWallet(String username) {
    for (User user : users) {
      if (user.getUsername().equals(username)) {
        return user.getWallet();
      }
    }

    return -1;
  }

}
