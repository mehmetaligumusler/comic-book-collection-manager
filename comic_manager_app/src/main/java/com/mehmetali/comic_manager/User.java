/**
 * The com.mehmetali.comic_manager package contains classes related to the Comic Manager application.
 */
package com.mehmetali.comic_manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a user in the comic management system.
 *
 * This class represents a user in the comic management system.
 * It contains information such as username, password, wallet balance,
 * collection of comics, wishlist, and trade list.
 *
 * The class implements the Serializable interface to support object serialization.
 *
 * @author mehmetali
 * @version 1.0
 * @param <Comic> the type of comic in the user's collection, wishlist, and trade list
 */
public class User<Comic> implements Serializable {
  private static final long serialVersionUID = 1L; /**< The serial version UID for object serialization. */

  private String username; /**< The username of the user. */
  private String password; /**< The password of the user. */
  private int wallet; /**< The wallet balance of the user. */

  private List<Comic> collection; /**< The collection of comics owned by the user. */
  private List<Comic> wishlist; /**< The wishlist of comics desired by the user. */
  private List<Comic> tradeList; /**< The list of comics available for trade by the user. */


  /**
   * Constructs a user with the specified username, password, and wallet balance.
   *
   * @param username the username of the user
   * @param password the password of the user
   * @param wallet   the wallet balance of the user
   */
  public User(String username, String password, int wallet) {
    this.username = username;
    this.password = password;
    this.wallet = wallet;
    this.collection = new ArrayList<>();
    this.wishlist = new ArrayList<>();
    this.tradeList = new ArrayList<>();
  }


  /**
   * Gets the wallet balance of the user.
   *
   * @return the wallet balance of the user
   */
  public int getWallet() {
    return wallet;
  }


  /**
   * Sets the wallet balance of the user.
   *
   * @param wallet the wallet balance of the user to set
   */
  public void setWallet(int wallet) {
    this.wallet = wallet;
  }


  /**
   * Gets the username of the user.
   *
   * @return the username of the user
   */
  public String getUsername() {
    return username;
  }


  /**
   * Sets the username of the user.
   *
   * @param username the username of the user to set
   */
  public void setUsername(String username) {
    this.username = username;
  }


  /**
   * Gets the password of the user.
   *
   * @return the password of the user
   */
  public String getPassword() {
    return password;
  }


  /**
   * Sets the password of the user.
   *
   * @param password the password of the user to set
   */
  public void setPassword(String password) {
    this.password = password;
  }


  /**
   * Gets the collection of comics owned by the user.
   *
   * @return the collection of comics owned by the user
   */
  public List<Comic> getCollection() {
    return collection;
  }


  /**
   * Sets the collection of comics owned by the user.
   *
   * @param collection the collection of comics owned by the user to set
   */
  public void setCollection(List<Comic> collection) {
    this.collection = collection;
  }


  /**
   * Gets the wishlist of comics of the user.
   *
   * @return the wishlist of comics of the user
   */
  public List<Comic> getWishlist() {
    return wishlist;
  }


  /**
   * Sets the wishlist of comics of the user.
   *
   * @param wishlist the wishlist of comics of the user to set
   */
  public void setWishlist(List<Comic> wishlist) {
    this.wishlist = wishlist;
  }


  /**
   * Gets the trade list of comics of the user.
   *
   * @return the trade list of comics of the user
   */
  public List<Comic> getTradeList() {
    return tradeList;
  }


  /**
   * Sets the trade list of comics of the user.
   *
   * @param tradeList the trade list of comics of the user to set
   */
  public void setTradeList(List<Comic> tradeList) {
    this.tradeList = tradeList;
  }


  /**
   * Adds a comic to the user's collection.
   *
   * @param comic the comic to add to the collection
   */
  public void addToCollection(Comic comic) {
    collection.add(comic);
  }


  /**
   * Removes a comic from the user's collection.
   *
   * @param comic the comic to remove from the collection
   */
  public void removeFromCollection(Comic comic) {
    collection.remove(comic);
  }


  /**
   * Adds a comic to the user's wishlist.
   *
   * @param comic the comic to add to the wishlist
   */
  public void addToWishlist(Comic comic) {
    wishlist.add(comic);
  }


  /**
   * Removes a comic from the user's wishlist.
   *
   * @param comic the comic to remove from the wishlist
   */
  public void removeFromWishlist(Comic comic) {
    wishlist.remove(comic);
  }


  /**
   * Adds a comic to the user's trade list.
   *
   * @param comic the comic to add to the trade list
   */
  public void addToTradeList(Comic comic) {
    tradeList.add(comic);
  }


  /**
   * Removes a comic from the user's trade list.
   *
   * @param comic the comic to remove from the trade list
   */
  public void removeFromTradeList(Comic comic) {
    tradeList.remove(comic);
  }


  /**
   * Returns a string representation of the user.
   *
   * @return a string representation of the user
   */
  @Override
  public String toString() {
    return "User{" +
           "username='" + username + '\'' +
           ", password='" + password + '\'' +
           ", collection=" + collection +
           ", wishlist=" + wishlist +
           ", tradeList=" + tradeList +
           '}';
  }
}
