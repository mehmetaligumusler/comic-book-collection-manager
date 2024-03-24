package com.mehmetali.comic_manager;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserManagerTest {

  private static final UserManager userManager = new UserManager();
  private static final Main main = new Main();

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
    User newUser = new User("Mehmet", "gokay", 0);
    String resultString = userManager.registerUser(newUser);
    assertEquals("Mehmet",resultString);
    deleteFile("users.dat");
  }
  /*
  @Test
  public void isusernameavailableTest() {

  }
  */
}
