package com.mehmetali.comic_manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User<Comic> implements Serializable {
  private static final long serialVersionUID = 1L;


  private String username;
  private String password;
  private int wallet; 

  private List<Comic> collection;
  private List<Comic> wishlist;
  private List<Comic> tradeList;


  // Constructor
  public User(String username, String password, int wallet) {
    this.username = username;
    this.password = password;
    this.wallet = wallet;
    this.collection = new ArrayList<>();
    this.wishlist = new ArrayList<>();
    this.tradeList = new ArrayList<>();
  }
  
  public int getWallet() {
	    return wallet;
	  }

	  public void setWallet(int wallet) {
	    this.wallet = wallet;
	  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Comic> getCollection() {
    return collection;
  }

  public void setCollection(List<Comic> collection) {
    this.collection = collection;
  }

  public List<Comic> getWishlist() {
    return wishlist;
  }

  public void setWishlist(List<Comic> wishlist) {
    this.wishlist = wishlist;
  }

  public List<Comic> getTradeList() {
    return tradeList;
  }

  public void setTradeList(List<Comic> tradeList) {
    this.tradeList = tradeList;
  }

  // Add comic to collection
  public void addToCollection(Comic comic) {
    collection.add(comic);
  }

  // Remove comic from collection
  public void removeFromCollection(Comic comic) {
    collection.remove(comic);
  }

  // Add comic to wishlist
  public void addToWishlist(Comic comic) {
    wishlist.add(comic);
  }

  // Remove comic from wishlist
  public void removeFromWishlist(Comic comic) {
    wishlist.remove(comic);
  }

  // Add comic to trade list
  public void addToTradeList(Comic comic) {
    tradeList.add(comic);
  }

  // Remove comic from trade list
  public void removeFromTradeList(Comic comic) {
    tradeList.remove(comic);
  }

  // toString method for printing user details
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
