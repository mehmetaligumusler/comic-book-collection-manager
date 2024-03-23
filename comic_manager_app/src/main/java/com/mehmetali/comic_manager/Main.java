package com.mehmetali.comic_manager;

import java.util.Scanner;



public class Main {
	
	public static String LoginName;
	public static int LoginWallet;
	
	private static final Scanner scanner = new Scanner(System.in);
	private static final UserManager userManager = new UserManager();
	private static final BookManager comicmanager = new BookManager();
	private static final WishManager wishmanager = new WishManager();
	private static final TradeManager trademanager = new TradeManager();
	private static final EventManager eventmanager = new EventManager();
	
	public static boolean control = false;


	private static void printMenu(String title, String[] options) {
	    System.out.println("|===============================================================================|");
	    System.out.println("|" + centerText(title, 79) + "|");
	    System.out.println("|===============================================================================|");

	    // Kullanıcı adı ve parasını sağ üst köşede göster
	    
	    if(control == false)
	    {
	    	System.out.println("|" + rightPad("User: " + LoginName, 79) + "|");
		    System.out.println("|" + rightPad("Money: " + LoginWallet, 79) + "|");
	    }

	    for (String option : options) {
	        System.out.println("|" + centerText(option, 79) + "|");
	    }

	    System.out.println("|===============================================================================|");
	    System.out.print("Enter your choice: ");
	}

	// Sağa yaslı şekilde metin düzenleme metodu
	private static String rightPad(String text, int length) {
	    return String.format("%-" + length + "s", text);
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
    	
      control = true;
      String title = "MAIN MENU";
      String[] options = {
        "1. Register",
        "2. Login",
        "3. Exit"
      };
      printMenu(title, options);
      int choice = scanner.nextInt();
      scanner.nextLine(); // Boş satırı oku
      
      control = false;

      switch (choice) {
        case 1:
          registerMenu();
          break;

        case 2:
          User loggedInUser = loginMenu();
          
          setLoginName(loggedInUser.getUsername());
          setWallet(loggedInUser.getWallet());
          
          
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
    User newUser = new User(username, password, 0);
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
    
    System.out.print("kitap Sayfa sayi: ");
    String bookpage = scanner.nextLine();
    int Intbookpage= Integer.valueOf(bookId);
    
    System.out.print("kitap değeri: ");
    String bookvalue = scanner.nextLine();
    int Intbookvalue= Integer.valueOf(bookId);


    Book newUser = new Book(IntBookId, bookName,Intbookpage,LoginName,Intbookvalue);
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
    
    
    Wish newUser = new Wish(IntBookId, null,1,LoginName,"a",10);
    wishmanager.AddBook(newUser);
  }
  
  private static void WishlistBookMenu()
  {
	  System.out.println("Wish Kitap Liste");
	  //Comic newUser = new Comic(0, LoginName, 0, LoginName, LoginName, 0);
	  //comicmanager.listBooks();
	  wishmanager.listBooksByUser(LoginName);
  }
  
  private static void WishdeleteBookMenu()
  {
	  System.out.println("Wish Kitap Silme");
	  
	  System.out.print("kitap ID: ");
	  String bookId = scanner.nextLine();
	  int IntBookId = Integer.valueOf(bookId);
	  
	  wishmanager.deleteBookByID(IntBookId);
  }
  
  //TradeList
  
  //satin alan kisi parasi yetecek
  // satan kisiye para eklenecek
  
  private static void TradeAddBookMenu()
  {
  	System.out.println("Trade Kitap Kayit");
    System.out.print("Kitap ID: ");
    String bookId = scanner.nextLine();
    int IntBookId = Integer.valueOf(bookId);
    
    
    Trade newUser = new Trade(IntBookId, null,1,LoginName,"a",10);
    trademanager.AddTrade(newUser);
    //comicmanager.deleteBookByID(IntBookId); // book list den silme
  }
  
  private static void TradeMylistBookMenu()
  {
	  System.out.println("Trade Kitap Liste");
	  //Comic newUser = new Comic(0, LoginName, 0, LoginName, LoginName, 0);
	  //comicmanager.listBooks();
	  trademanager.listMyTradeList(LoginName);
  }
  
  private static void TradedeleteBookMenu()
  {
	  System.out.println("Trade Kitap Silme");
	  
	  System.out.print("kitap ID: ");
	  String bookId = scanner.nextLine();
	  int IntBookId = Integer.valueOf(bookId);
	  
	  trademanager.deleteTradeByID(IntBookId);
  }
  
  private static void TradeAllListBookMenu()
  {
	  System.out.println("Kitap Liste");
	  //Comic newUser = new Comic(0, LoginName, 0, LoginName, LoginName, 0);
	  //comicmanager.listBooks();
	  trademanager.listAllTradeList();
  }
  
  //satin alma
  
  private static void TradeBuyBookMenu()
  {
	  System.out.println("Trade Kitap Satin Alma");
	  
	  System.out.print("kitap ID: ");
	  String bookId = scanner.nextLine();
	  int IntBookId = Integer.valueOf(bookId);
	  
	  trademanager.BuyTradeByID(IntBookId);
  }
  
  public static void CreditBuyScoreMenu()
  {
	  System.out.println("Kredi puanı:");
	  
	  String score = scanner.nextLine();
	  int IntScore = Integer.valueOf(score);
	  
	  userManager.creditbuyscore(LoginName, IntScore);
  }
  
  
  //Event Menu
  
  private static void EventAddBookMenu()
  {
  	System.out.println("Event Kayit");
    System.out.print("Event ID: ");
    String bookId = scanner.nextLine();
    int IntBookId = Integer.valueOf(bookId);
    
    System.out.print("Event Adi: ");
    String title = scanner.nextLine();
    
    System.out.print("Event İçerik: ");
    String content = scanner.nextLine();


    Event newUser = new Event(IntBookId, title,content,LoginName);
    eventmanager.AddEvent(newUser);
    System.out.println("Kayit basarili!");
  }
  
  private static void EventlistBookMenu()
  {
	  System.out.println("Event Liste");
	  //Comic newUser = new Comic(0, LoginName, 0, LoginName, LoginName, 0);
	  //comicmanager.listBooks();
	  eventmanager.listEventsByCondition(LoginName);
  }
  
  private static void EventlistAllBookMenu()
  {
	  
	  
	  System.out.println("Event Liste");
	  //Comic newUser = new Comic(0, LoginName, 0, LoginName, LoginName, 0);
	  //comicmanager.listBooks();
	  eventmanager.listEvents();
  }
  
  private static void EventdeleteBookMenu()
  {
	  System.out.println("Event Silme");
	  
	  System.out.print("Event ID: ");
	  String bookId = scanner.nextLine();
	  int IntBookId = Integer.valueOf(bookId);
	  
	  eventmanager.deleteEventByID(IntBookId);
  }
  
  private static void EventupdateBookMenu()
  {
	  System.out.println("Event Güncelleme");
	  
	  System.out.print("Event ID: ");
	  String bookId = scanner.nextLine();
	  int IntBookId = Integer.valueOf(bookId);
	  
	  System.out.print("Event Adi: ");
	  String title = scanner.nextLine();
	
	  System.out.print("Event İçerik: ");
	  String content = scanner.nextLine();
	  
	  eventmanager.updateEventTitleByID(IntBookId, title,content);
	  System.out.println("Güncelleme basarili!");
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
        "4. Buy Credit Score",
        "5. Exit"
      };
      
      
      printMenu(title, options);
      int choice = scanner.nextInt();
      scanner.nextLine(); 

      
      while (choice != 5) {
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
        	  choice = 5;
        	  break;
          default:
              System.out.println("Gecersiz secim. Lutfen tekrar deneyin.");
              break;
          }
          
          break;

        case 2:
          System.out.println("Wishlist and Trade List Management");
          
          String title3 = "WISHLIST OR TRADE MENU";
          String[] options3 = {
            "1. My Wish List",
            "2. My Trade List",
            "3. Exit"
          };
          printMenu(title3, options3);
          int choice3 = scanner.nextInt();
          scanner.nextLine(); 
          
          switch (choice3) {
          case 1:
        	  String title4 = "MY WISHLIST MENU";
              String[] options4 = {
                "1. My Wish Book Add",
                "2. My Wish Book Delete",
                "3. My Wish Book List",
                "4. My Book List",
                "5. Return",
                "5. Exit"
              };
              printMenu(title4, options4);
              int choice4 = scanner.nextInt();
              scanner.nextLine(); 
              
              switch (choice4) {
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
            	  choice = 5;
            	  break;
              case 6:
            	  break;
              default:
                  System.out.println("Gecersiz secim. Lutfen tekrar deneyin.");
                  break;
              }
        	  break;
          case 2:
        	  String title5 = "MY TRADE MENU";
              String[] options5 = {
                "1. Buy Book",
                "2. Sell Book",
                "3. Return",
                "4. Exit"
              };
              printMenu(title5, options5);
              int choice5 = scanner.nextInt();
              scanner.nextLine(); 
              
              switch(choice5)
              {
              case 1:
            	  String title6 = "MY BUY MENU";
                  String[] options6 = {
                    "1. Buy New Book",
                    "2. Trade Book List",
                    "3. Return",
                    "3. Exit"
                  };
                  printMenu(title6, options6);
                  int choice6 = scanner.nextInt();
                  scanner.nextLine(); 
                  
                  switch(choice6)
                  {
                  case 1:
                	  TradeBuyBookMenu();
                	  break;
                  case 2:
                	  TradeAllListBookMenu();
                	  break;
                  case 3:
                	  choice = 5;
                	  break;
                  case 4:
                	  
                	  break;
                  default:
                      System.out.println("Gecersiz secim. Lutfen tekrar deneyin.");
                      break;
                  }
                  
                  
                  
            	  break;
              case 2:
            	  
            	  String title7 = "MY SELL MENU";
                  String[] options7 = {
                    "1. Add Sell New Book",
                    "2. Delete Sell Book",
                    "3. Trade Book List",
                    "4. My Trade Book List",
                    "5. Return",
                    "6. Exit"
                  };
                  printMenu(title7, options7);
                  int choice7 = scanner.nextInt();
                  scanner.nextLine(); 
                  
                  switch(choice7)
                  {
                  case 1:
                	  TradeAddBookMenu();
                	  break;
                  case 2:
                	  TradedeleteBookMenu();
                	  break;
                  case 3:
                	  TradeAllListBookMenu();
                	  break;
                  case 4:
                	  TradeMylistBookMenu();
                	  break;
                  case 5:
                	  choice = 5;
                	  break;
                  case 6:
                	  exit = true;
                	  break;
                  default:
                      System.out.println("Gecersiz secim. Lutfen tekrar deneyin.");
                      break;
                  }
            	  break;
              case 3:
            	  break;
              default:
                  System.out.println("Gecersiz secim. Lutfen tekrar deneyin.");
                  break;
              }
              
        	  break;
            
          case 3:
        	  choice =4;
        	  break;
          default:
              System.out.println("Gecersiz secim. Lutfen tekrar deneyin.");
              break;

          }
          

          break;

        case 3:
          System.out.println("Comic Book Events and Conventions");
          
          String title8 = "MY EVENT MENU";
          String[] options8 = {
            "1. Add My Event",
            "2. Sell My Event",
            "3. Update My Event",
            "4. My Event List",
            "5. All Event List",
            "6. Return",
            "7. Exit"
          };
          printMenu(title8, options8);
          int choice8 = scanner.nextInt();
          scanner.nextLine(); 
          
          switch(choice8)
          {
          case 1:
        	  EventAddBookMenu();
        	  break;
          case 2:
        	  EventdeleteBookMenu();
        	  break;
          case 3:
        	  EventupdateBookMenu();
        	  break;
          case 4:
        	  EventlistBookMenu();
        	  break;
          case 5:
        	  EventlistAllBookMenu();
        	  break;
          case 6:
        	  choice = 5;
        	  break;
          case 7:
        	  break;
          default:
              System.out.println("Gecersiz secim. Lutfen tekrar deneyin.");
              break;
          
          }
          break;
          
        case 4:
      	  String title9 = "MY CREDIT SCORE MENU";
            String[] options9 = {
              "1. Buy Credit Score",
              "2. Exit"
            };
            printMenu(title9, options9);
            int choice9 = scanner.nextInt();
            scanner.nextLine(); 
            
            switch(choice9)
            {
            case 1:
          	  CreditBuyScoreMenu();
          	  
          	  break;
            case 2:
          	 
          	  break;
            }
            
      	  
      	  break;

        case 5:
          
        	choice = 5;
        	exit = true;
        	
          break;

        default:
          System.out.println("Gecersiz secim. Lutfen tekrar deneyin.");
          choice = 5;
          break;
      	}
      }
    }

    scanner.close(); 
  }

public static String getLoginName() {
	return LoginName;
}

public static void setLoginName(String loginName) {
	LoginName = loginName;
}

public static int getWallet() {
	return LoginWallet;
}

public static void setWallet(int loginWallet) {
	LoginWallet = loginWallet;
}


}
