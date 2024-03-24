package com.mehmetali.comic_manager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
  private final InputStream originalIn = System.in;
  private final PrintStream originalOut = System.out;
  private ByteArrayOutputStream outContent;
  private ByteArrayInputStream inContent;

  @BeforeEach
  public void setUpStreams() {
    outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
  }

  @AfterEach
  public void restoreStreams() {
    System.setIn(originalIn);
    System.setOut(originalOut);
  }

  @Test
  public void testRegisterMenu() {
    // Prepare input to simulate user interaction
    String simulatedInput = "1\na\na\n3\n";
    inContent = new ByteArrayInputStream(simulatedInput.getBytes());
    System.setIn(inContent);
    // Call the main method
    Main.main(new String[] {});
    // Check if "Register" menu is displayed
    assertTrue(outContent.toString().contains("Kayit Ol"));
    // Check if the program prompts for a username and password
    assertTrue(outContent.toString().contains("Kullanici Adi: "));
    assertTrue(outContent.toString().contains("Sifre: "));
  }
}
