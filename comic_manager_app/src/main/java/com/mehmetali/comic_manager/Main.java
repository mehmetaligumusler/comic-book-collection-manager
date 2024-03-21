package com.mehmetali.comic_manager;

import java.util.Scanner;

public class Main {
  private static final Scanner scanner = new Scanner(System.in);
  private static final UserManager userManager = new UserManager();


  private static void printMenu(String title, String[] options) {
    System.out.println("|===============================================================================|");
    System.out.println("|" + centerText(title, 79) + "|");
    System.out.println("|===============================================================================|");

    for (String option : options) {
      System.out.println("|" + centerText(option, 79) + "|");
    }

    System.out.println("|===============================================================================|");
    System.out.print("Enter your choice: ");
  }

  private static String centerText(String text, int length) {
    int spaces = length - text.length();
    int before = spaces / 2;
    int after = spaces - before;
    return " ".repeat(before) + text + " ".repeat(after);
  }

  public static void main(String[] args) {
    boolean exit = false;

    while (!exit) {
      String title = "MAIN MENU";
      String[] options = {
        "1. Register",
        "2. Login",
        "3. Exit"
      };
      printMenu(title, options);
      int choice = scanner.nextInt();
      scanner.nextLine(); // Boş satırı oku

      switch (choice) {
        case 1:
          registerMenu();
          break;

        case 2:
          User loggedInUser = loginMenu();

          if (loggedInUser != null) {
            userMenu(loggedInUser);
          }

          break;

        case 3:
          exit = true;
          break;

        default:
          System.out.println("Gecersiz secim. Lutfen tekrar deneyin.");
          break;
      }
    }

    scanner.close();
  }

  private static void registerMenu() {
    System.out.println("Kayit Ol");
    System.out.print("Kullanici Adi: ");
    String username = scanner.nextLine();
    System.out.print("Sifre: ");
    String password = scanner.nextLine();
    // Kullanıcıyı kaydet
    User newUser = new User(username, password);
    userManager.registerUser(newUser);
    System.out.println("Kayit basarili!");
  }

  private static User loginMenu() {
    System.out.println("Giris Yap");
    System.out.print("Kullanici Adi: ");
    String username = scanner.nextLine();
    System.out.print("Sifre: ");
    String password = scanner.nextLine();
    // Kullanıcıyı kontrol et ve giriş yap
    User user = userManager.login(username, password);

    if (user != null) {
      System.out.println("Hos geldiniz, " + user.getUsername() + "!");
    } else {
      System.out.println("Kullanici adi veya sifre hatali!");
    }

    return user;
  }

  private static void userMenu(User user) {
    boolean exit = false;

    while (!exit) {
      String title = "USER MENU";
      String[] options = {
        "1. Cataloging Comic Book Collection",
        "2. Wishlist and Trade List Management",
        "3. Comic Book Events and Conventions",
        "4. Exit"
      };
      printMenu(title, options);
      int choice = scanner.nextInt();
      scanner.nextLine(); // Boş satırı oku

      switch (choice) {
        case 1:
          System.out.println("Cataloging Comic Book Collection");
          // Implement cataloging menu here
          break;

        case 2:
          System.out.println("Wishlist and Trade List Management");
          // Implement wishlist and trade menu here
          break;

        case 3:
          System.out.println("Comic Book Events and Conventions");
          // Implement events and conventions menu here
          break;

        case 4:
          exit = true;
          break;

        default:
          System.out.println("Gecersiz secim. Lutfen tekrar deneyin.");
          break;
      }
    }

    scanner.close(); // Döngü bittiğinde scanner kapatılmalı
  }


}
