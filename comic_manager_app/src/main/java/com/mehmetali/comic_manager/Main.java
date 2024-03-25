package com.mehmetali.comic_manager;

import java.util.Scanner;
import java.io.IOException;
import java.io.PrintStream;



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
 
	    int wallet = userManager.getUserWallet(LoginName);
	    
	    if(control == false)
	    {
	    	System.out.println("|" + rightPad("User: " + LoginName, 79) + "|");
		    System.out.println("|" + rightPad("Money: " + wallet, 79) + "|");
	    }

	    for (String option : options) {
	        System.out.println("|" + centerText(option, 79) + "|");
	    }

	    System.out.println("|===============================================================================|");
	    System.out.print("Enter your choice: ");
	}

	private static String rightPad(String text, int length) {
	    return String.format("%-" + length + "s", text);
	}


  private static String centerText(String text, int length) {
    int spaces = length - text.length();
    int before = spaces / 2;
    int after = spaces - before;
    return " ".repeat(before) + text + " ".repeat(after);
  }
  
  
    public static void clearConsole() {
        try {
            // Windows
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            // Unix/Linux/Mac
            else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            // Handle exception
            ex.printStackTrace();
        }
    }

  
  
  public static void main(String[] args) throws IOException {
      Scanner scanner = new Scanner(System.in);
      MainApp(scanner, System.out);
  }

  public static int MainApp(Scanner scanner, PrintStream out) throws IOException {
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
	      scanner.nextLine();
	      
	      control = false;

	      switch (choice) {
	        case 1:
	          registerMenu(scanner, System.out);
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
	          System.out.println("Invalid election. Please try again.");
	          break;
	      }
	    }

	    scanner.close();
	    return 0;
  }

  public static int registerMenu(Scanner scanner, PrintStream out) throws IOException {
 
    System.out.print("UserName: ");
    String username = scanner.nextLine();
    System.out.print("Password: ");
    String password = scanner.nextLine();
    User newUser = new User(username, password, 0);
    userManager.registerUser(newUser);
    return 0;
   
  }
  
  //ComicBook
  
  private static int AddBookMenu()
  {
  	
    System.out.print("Book ID: ");
    int bookId = scanner.nextInt();
    scanner.nextLine();
    
    System.out.print("Book Name: ");
    String bookName = scanner.nextLine();
    
    System.out.print("Book Page Number: ");
    int bookpage = scanner.nextInt();
    
    System.out.print("Book Value: ");
    int bookvalue = scanner.nextInt();


    Book newUser = new Book(bookId, bookName,bookpage,LoginName,bookvalue);
    comicmanager.AddBook(newUser);
    return 0;
  }
  
  private static int listBookMenu()
  {
	  System.out.println("Book List");
	  comicmanager.listBooksByCondition(LoginName);
	  return 0;
  }
  
  private static int listAllBookMenu()
  {
	  System.out.println("Book All List");
	  comicmanager.listBooks();
	  return 0;
  }
  
  private static int deleteBookMenu()
  { 
	  System.out.print("Book ID: ");
	  int bookId = scanner.nextInt();
	  
	  comicmanager.deleteBookByID(bookId);
	  return 0;
  }
  
  private static int updateBookMenu()
  {
	  System.out.print("Book ID: ");
	  int bookId = scanner.nextInt();
	  scanner.nextLine();
	  
	  System.out.print("New Book Name: ");
	  String bookName = scanner.nextLine();
	  
	  System.out.print("New Book Value: ");
	  int bookvalue = scanner.nextInt();
	  scanner.nextLine();

	  System.out.print("New Book PageNumber: ");
	  int bookpage = scanner.nextInt();
	  scanner.nextLine();
	  
	  comicmanager.updateBookTitleByID(bookId, bookName,bookpage,bookvalue);
	  return 0;
  }

  //WishList
  
  private static int WishAddBookMenu()
  {
    System.out.print("Book ID: ");
    int bookId = scanner.nextInt();
    scanner.nextLine();
    
    
    Wish newUser = new Wish(bookId, null,1,LoginName,10);
    wishmanager.AddBook(newUser);
    return 0;
  }
  
  private static int WishlistBookMenu()
  {
	  System.out.println("Wish Book List");
	  wishmanager.listBooksByUser(LoginName);
	  return 0;
  }
  
  private static int WishdeleteBookMenu()
  {
	  System.out.print("Book ID: ");
	  int bookId = scanner.nextInt();
	  
	  wishmanager.deleteBookByID(bookId);
	  return 0;
  }
  
  //TradeList
  
  
  private static int TradeAddBookMenu()
  {
    System.out.print("Book ID: ");
    int bookId = scanner.nextInt();
    
    Trade newUser = new Trade(bookId, null,1,LoginName,10);
    trademanager.AddTrade(newUser);
    return 0;
  }
  
  private static int TradeMylistBookMenu()
  {
	  trademanager.listMyTradeList(LoginName);
	  return 0;
  }
  
  private static int TradedeleteBookMenu()
  {
	  System.out.print("Book ID: ");
	  int bookId = scanner.nextInt();
	  
	  trademanager.deleteTradeByID(bookId);
	  return 0;
  }
  
  private static int TradeAllListBookMenu()
  {
	  System.out.println("Book List");
	  trademanager.listAllTradeList();
	  return 0;
  }
    
  private static int TradeBuyBookMenu()
  {	  
	  System.out.print("Book ID: ");
	  int bookId = scanner.nextInt();
	  
	  trademanager.BuyTradeByID(bookId);
	  return 0;
  }
  
  public static int CreditBuyScoreMenu()
  {
	  System.out.println("Credit Score:");
	  
	  int score = scanner.nextInt();
	  
	  userManager.creditbuyscore(LoginName, score);
	  return 0;
  }
  
 
  private static int EventAddBookMenu()
  {
    System.out.print("Event ID: ");
    int bookId = scanner.nextInt();
    scanner.nextLine();
    
    System.out.print("Event Name: ");
    String title = scanner.nextLine();
    
    System.out.print("Event Content: ");
    String content = scanner.nextLine();

    Event newUser = new Event(bookId, title,content,LoginName);
    eventmanager.AddEvent(newUser);
    return 0;
  }
  
  private static int EventlistBookMenu()
  {
	  System.out.println("Event List");
	  eventmanager.listEventsByCondition(LoginName);
	  return 0;
  }
  
  private static int EventlistAllBookMenu()
  {
	  System.out.println("Event All List");
	  eventmanager.listEvents();
	  return 0;
  }
  
  private static int EventdeleteBookMenu()
  {
	  System.out.print("Event ID: ");
	  int bookId = scanner.nextInt();
	  
	  eventmanager.deleteEventByID(bookId);
	  return 0;
  }
  
  private static int EventupdateBookMenu()
  {
	  System.out.print("Event ID: ");
	  int bookId = scanner.nextInt();
	  scanner.nextLine();
	  
	  System.out.print("New Event Name: ");
	  String title = scanner.nextLine();
	
	  System.out.print("New Event Content: ");
	  String content = scanner.nextLine();
	  
	  eventmanager.updateEventTitleByID(bookId, title,content);
	  return 0;
  }
  
  //test

  private static User loginMenu() {
    System.out.print("UserName: ");
    String username = scanner.nextLine();
    System.out.print("Password: ");
    String password = scanner.nextLine();
 
    User user = userManager.login(username, password);

    if (user != null) {
      System.out.println("Hello, " + user.getUsername() + "!");
    } else {
      System.out.println("Username or password is wrong!");
    }

    return user;
  }

  private static int userMenu(User user) {
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
        	  clearConsole();
        	  listBookMenu();
        	  break;
          case 5:
        	  listAllBookMenu();
        	  break;
          case 6:
        	  choice = 5;
        	  break;
          default:
              System.out.println("Invalid election. Please try again.");
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
                  System.out.println("Invalid election. Please try again.");
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
                      System.out.println("Invalid election. Please try again.");
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
                      System.out.println("Invalid election. Please try again.");
                      break;
                  }
            	  break;
              case 3:
            	  choice = 5;
            	  break;
              default:
                  System.out.println("Invalid election. Please try again.");
                  break;
              }
              
        	  break;
            
          case 3:
        	  choice = 5;
        	  break;
          default:
              System.out.println("Invalid election. Please try again.");
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
              System.out.println("Invalid election. Please try again.");
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
          	 choice = 5;
          	  break;
            }
            
      	  
      	  break;

        case 5:
          
        	System.exit(0); 

        	//exit = true;
        	
          break;

        default:
          System.out.println("Invalid election. Please try again.");
          choice = 5;
          break;
      	}
      }
    }

    scanner.close(); 
    return 0;
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
