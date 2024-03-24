package com.mehmetali.comic_manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UserManagerTest {
  private static final String TEST_USER_FILE_PATH = "test_users.dat";

  private UserManager userManager;
  private User testUser;

  @BeforeEach
  public void setUp() {
    // Test kullanıcı dosyasının oluşturulması
    try {
      File file = new File(TEST_USER_FILE_PATH);

      if (file.exists()) {
        file.delete();
      }

      file.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }

    userManager = new UserManager();
    testUser = new User("testuser", "testpassword", 100);
  }

  @Test
  public void testRegisterUser() {
    // Yeni bir kullanıcının kaydedilmesi
    String registeredUsername = userManager.registerUser(testUser);
    assertEquals(testUser.getUsername(), registeredUsername);
    // Aynı kullanıcının tekrar kaydedilmemesi
    String duplicateRegisteredUsername = userManager.registerUser(testUser);
    assertNull(duplicateRegisteredUsername);
  }

  @Test
  public void testIsUsernameAvailable() {
    // Kayıtlı bir kullanıcı adıyla kontrol edilmesi
    assertFalse(userManager.isUsernameAvailable("testuser"));
    // Kayıtlı olmayan bir kullanıcı adıyla kontrol edilmesi
    assertTrue(userManager.isUsernameAvailable("newuser"));
  }

  @Test
  public void testLogin() {
    // Doğru kullanıcı adı ve şifreyle giriş yapılması
    User loggedInUser = userManager.login("testuser", "testpassword");
    assertNotNull(loggedInUser);
    assertEquals(testUser.getUsername(), loggedInUser.getUsername());
    // Yanlış kullanıcı adı veya şifreyle giriş yapılması
    User wrongLogin = userManager.login("wronguser", "wrongpassword");
    assertNull(wrongLogin);
  }

  @Test
  public void testCreditbuyscore() {
    // Kullanıcıya kredi eklenmesi
    int initialWallet = userManager.getUserWallet(testUser.getUsername());
    int creditToAdd = 50;
    int newWallet = userManager.creditbuyscore(testUser.getUsername(), creditToAdd);
    assertEquals(initialWallet + creditToAdd, newWallet);
  }

  @Test
  public void testCreditSellscore() {
    // Kullanıcıdan kredi düşülmesi
    int initialWallet = userManager.getUserWallet(testUser.getUsername());
    int creditToDeduct = 50;
    int newWallet = userManager.creditSellscore(testUser.getUsername(), creditToDeduct);
    assertEquals(initialWallet - creditToDeduct, newWallet);
  }
}
