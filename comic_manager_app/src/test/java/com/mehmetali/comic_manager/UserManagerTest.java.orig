package com.mehmetali.comic_manager;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserManagerTest {

  private static final UserManager userManager = new UserManager();
  

  public static boolean deleteFile(String filepath) {
    File file = new File(filepath);

    if (file.exists()) {
      return file.delete();
    } else {
      return false;
    }
  }

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void registUserTest() {
    @SuppressWarnings("rawtypes")
	User newUser = new User("Mehmet", "gokay", 0);
    String resultString = userManager.registerUser(newUser);
    assertEquals("Mehmet",resultString);
    deleteFile("users.dat");
  }
  
  @Test
  public void registerUserWithExistingUsernameTest() {
      User existingUser = new User("Ahmet", "123", 0);
      userManager.registerUser(existingUser);

      User newUser = new User("Ahmet", "456", 0);
      String resultString = userManager.registerUser(newUser);
      assertNull(resultString);
      deleteFile("users.dat");
  }

  @Test
  public void loginTest() {
      User newUser = new User("TestUser", "testpassword", 0);
      userManager.registerUser(newUser);

      User loggedInUser = userManager.login("TestUser", "testpassword");
      assertEquals("TestUser", loggedInUser.getUsername());
      assertEquals("testpassword", loggedInUser.getPassword());
      deleteFile("users.dat");
  }

  @Test
  public void loginWithInvalidCredentialsTest() {
      User newUser = new User("TestUser", "testpassword", 0);
      userManager.registerUser(newUser);

      User loggedInUser = userManager.login("TestUser", "wrongpassword");
      assertNull(loggedInUser);
      deleteFile("users.dat");
  }

  //error
  @Test
  public void creditbuyscoreTest() {
      User newUser = new User("TestUser4", "testpassword", 100);
      userManager.registerUser(newUser);

      int newWallet = userManager.creditbuyscore("TestUser4", 50);
      assertEquals(150, newWallet);
      deleteFile("users.dat");
  }

  
  //error
  @Test
  public void creditSellscoreTest() {
      User newUser = new User("TestUser2", "testpassword", 100);
      userManager.registerUser(newUser);

      int newWallet = userManager.creditSellscore("TestUser2", 50);
      assertEquals(50, newWallet);
      deleteFile("users.dat");
  }

  //error
  @Test
  public void getUserWalletTest() {
      User newUser = new User("TestUser3", "testpassword", 200);
      userManager.registerUser(newUser);

      int walletAmount = userManager.getUserWallet("TestUser3");
      assertEquals(200, walletAmount);
      deleteFile("users.dat");
  }
 
}
