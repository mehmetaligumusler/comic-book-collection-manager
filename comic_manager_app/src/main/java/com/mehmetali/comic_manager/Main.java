package com.mehmetali.comic_manager;

import java.util.Scanner;



public class Main {
	
	private static String LoginName;
	
	private static final Scanner scanner = new Scanner(System.in);
	private static final UserManager userManager = new UserManager();
	private static final BookManager comicmanager = new BookManager();
	private static final WishManager wishmanager = new WishManager();


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
          
          setLoginName(loggedInUser.getUsername());
          
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
  
  //ComicBook
  
  private static void AddBookMenu()
  {
  	System.out.println("Kitap Kayit");
    System.out.print("Kitap ID: ");
    String bookId = scanner.nextLine();
    int IntBookId = Integer.valueOf(bookId);
    
    System.out.print("kitap Adi: ");
    String bookName = scanner.nextLine();


    Book newUser = new Book(IntBookId, bookName,1,LoginName,"a",10);
    comicmanager.AddBook(newUser);
    System.out.println("Kayit basarili!");
  }
  
  private static void listBookMenu()
  {
	  System.out.println("Kitap Liste");
	  //Comic newUser = new Comic(0, LoginName, 0, LoginName, LoginName, 0);
	  //comicmanager.listBooks();
	  comicmanager.listBooksByCondition(LoginName);
  }
  
  private static void listAllBookMenu()
  {
	  System.out.println("Kitap Liste");
	  //Comic newUser = new Comic(0, LoginName, 0, LoginName, LoginName, 0);
	  //comicmanager.listBooks();
	  comicmanager.listBooks();
  }
  
  private static void deleteBookMenu()
  {
	  System.out.println("Kitap Silme");
	  
	  System.out.print("kitap ID: ");
	  String bookId = scanner.nextLine();
	  int IntBookId = Integer.valueOf(bookId);
	  
	  comicmanager.deleteBookByID(IntBookId);
  }
  
  private static void updateBookMenu()
  {
	  System.out.println("Kitap Güncelleme");
	  
	  System.out.print("kitap ID: ");
	  String bookId = scanner.nextLine();
	  int IntBookId = Integer.valueOf(bookId);
	  
	  System.out.print("kitap Adi: ");
	  String bookName = scanner.nextLine();
	  
	  comicmanager.updateBookTitleByID(IntBookId, bookName);
	  System.out.println("Güncelleme basarili!");
  }

  //WishList
  
  private static void WishAddBookMenu()
  {
  	System.out.println("Wish Kitap Kayit");
    System.out.print("Kitap ID: ");
    String bookId = scanner.nextLine();
    int IntBookId = Integer.valueOf(bookId);
    
    
    Wish newUser = new Wish(IntBookId, "a",1,LoginName,"a",10);
    wishmanager.AddBook(newUser);
    //System.out.println("Kayit basarili!");
  }
  
  private static void WishlistBookMenu()
  {
	  System.out.println("Wish Kitap Liste");
	  //Comic newUser = new Comic(0, LoginName, 0, LoginName, LoginName, 0);
	  //comicmanager.listBooks();
	  wishmanager.listBooksByCondition(LoginName);
  }
  
  
  private static void WishdeleteBookMenu()
  {
	  System.out.println("Wish Kitap Silme");
	  
	  System.out.print("kitap ID: ");
	  String bookId = scanner.nextLine();
	  int IntBookId = Integer.valueOf(bookId);
	  
	  wishmanager.deleteBookByID(IntBookId);
  }
  
  
  //Normal Menu

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
      scanner.nextLine(); 

      switch (choice) {
        case 1:
          System.out.println("Cataloging Comic Book Collection");
          
          String title2 = "MY BOOK MENU";
          String[] options2 = {
            "1. My Book Add",
            "2. My Book Delete",
            "3. My Book Update",
            "4. My Book List",
            "5. All Book List",
            "6. Exit"
          };
          printMenu(title2, options2);
          int choice2 = scanner.nextInt();
          scanner.nextLine(); 
          
          switch (choice2) {
          case 1:
        	  AddBookMenu();
        	  break;
          case 2:
        	  deleteBookMenu();
        	  break;
          case 3:
        	  updateBookMenu();
        	  break;
          case 4:
        	  listBookMenu();
        	  break;
          case 5:
        	  listAllBookMenu();
        	  break;
          case 6:
        	  break;
          }
          
          break;

        case 2:
          System.out.println("Wishlist and Trade List Management");
          
          String title3 = "MY WISHLIST MENU";
          String[] options3 = {
            "1. My Wish Book Add",
            "2. My Wish Book Delete",
            "3. My Wish Book List",
            "4. My Book List",
            "5. Exit"
          };
          printMenu(title3, options3);
          int choice3 = scanner.nextInt();
          scanner.nextLine(); 
          
          switch (choice3) {
          case 1:
        	  WishAddBookMenu();
        	  break;
          case 2:
        	  WishdeleteBookMenu();
        	  break;
          case 3:
        	  WishlistBookMenu();
        	  break;
          case 4:
        	  listBookMenu();
        	  break;
          case 5:
        	  break;
          }
          
          
          
          break;

        case 3:
          System.out.println("Comic Book Events and Conventions");
          
          
          
          break;

        case 4:
          
        
        	
          break;

        default:
          System.out.println("Gecersiz secim. Lutfen tekrar deneyin.");
          break;
      }
    }

    scanner.close(); // Döngü bittiğinde scanner kapatılmalı
  }

public static String getLoginName() {
	return LoginName;
}

public static void setLoginName(String loginName) {
	LoginName = loginName;
}


}
