package com.mehmetali.comic_manager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private final String testAuthenticationFile = "users.dat";
    
    public static boolean deleteFile(String filepath) {
        File file = new File(filepath);

        if (file.exists()) {
          return file.delete();
        } else {
          return false;
        }
      }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
 
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @After
    public void cleanup() {
        File file = new File(testAuthenticationFile);
        file.delete();
    } 

    
    @Test
    public void testLoginMenu_SuccessfulLogin() throws IOException {
        // Prepare input
        String input = "1\nuername1\npassword1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Execute 
        assertEquals(0, Main.registerMenu(new java.util.Scanner(System.in), System.out));

        // Check output
    
        assertTrue(outContent.toString().contains("UserName: "));
        assertTrue(outContent.toString().contains("Password: "));
        assertTrue(outContent.toString().contains("User registered successfully."));
        //deleteFile("users.dat");
    }
    

    
}