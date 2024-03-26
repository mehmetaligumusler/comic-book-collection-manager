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
import java.util.Scanner;

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
    public void testLoginMenu_SuccessRegister() throws IOException {
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
    
    
    @Test
    public void testLoginMenu_SuccessfulLogin() throws IOException {
        // Prepare input
    	String input = "nuername1\npassword1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Execute 
        User loggedInUser = Main.loginMenu(new java.util.Scanner(System.in), System.out);

        // Check output
        assertTrue(outContent.toString().contains("UserName: "));
        assertTrue(outContent.toString().contains("Password: "));
        //assertTrue(outContent.toString().contains("Hello, uername1!"));
        
        // Check returned User object
        //assertNotNull(loggedInUser);
        //assertEquals("uername1", loggedInUser.getUsername());
        
    }

    @Test
    public void testLoginMenu_WrongCredentials() throws IOException {
        // Prepare input
        String input = "invalid_username\ninvalid_password\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Execute 
        User loggedInUser = Main.loginMenu(new java.util.Scanner(System.in), System.out);

        // Check output
        assertTrue(outContent.toString().contains("UserName: "));
        assertTrue(outContent.toString().contains("Password: "));
        assertTrue(outContent.toString().contains("Username or password is wrong!"));
        
        // Check returned User object
        assertNull(loggedInUser);
    }
    
    
    @Test
    public void testAddBookMenu() throws IOException {
        // Prepare input
        String input = "1\nBookName\n100\n50\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Execute 
        assertEquals(0, Main.AddBookMenu(new java.util.Scanner(System.in), System.out));

        // Check output
        assertTrue(outContent.toString().contains("Book ID: "));
        assertTrue(outContent.toString().contains("Book Name: "));
        assertTrue(outContent.toString().contains("Book Page Number: "));
        assertTrue(outContent.toString().contains("Book Value: "));
        
    }
    
    /*
    @Test
    public void testListBookMenu() {
       
        try {
            int result = Main.listBookMenu(new java.util.Scanner(System.in), System.out);
            
            assertEquals(0, result);
            assertTrue(outContent.toString().contains("Book List"));
           
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
    */
    
    @Test
    public void testAllListBookMenu() {
        
        try {
            int result = Main.listAllBookMenu(new java.util.Scanner(System.in), System.out);
            
            assertEquals(0, result);
            assertTrue(outContent.toString().contains("Book All List"));
           
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void testDeleteBookMenu() {
    	
    	String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
   
        try {
            int result = Main.deleteBookMenu(new java.util.Scanner(System.in), System.out);
            
           
            assertEquals(0, result);

         
            assertTrue(outContent.toString().contains("Book ID: "));
           
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void testUpdateBookMenu() throws IOException {
        // Prepare input
        String input = "1\nBookName1\n200\n40\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Execute 
        assertEquals(0, Main.updateBookMenu(new java.util.Scanner(System.in), System.out));

        // Check output
        assertTrue(outContent.toString().contains("Book ID: "));
        assertTrue(outContent.toString().contains("New Book Name: "));
        assertTrue(outContent.toString().contains("New Book Page Number: "));
        assertTrue(outContent.toString().contains("New Book Value: "));
        
    }
    
    //wish
    
    @Test
    public void testWishAddBookMenu() throws IOException {
        // Prepare input
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Execute 
        assertEquals(0, Main.WishAddBookMenu(new java.util.Scanner(System.in), System.out));

        // Check output
        assertTrue(outContent.toString().contains("Book ID: "));     
    }
    
    @Test
    public void testWishListBookMenu() {
       
        try {
            int result = Main.WishlistBookMenu(new java.util.Scanner(System.in), System.out);
            
            assertEquals(0, result);
            assertTrue(outContent.toString().contains("Wish Book List"));
           
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void testWishDeleteBookMenu() {
    	
    	String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
   
        try {
            int result = Main.WishdeleteBookMenu(new java.util.Scanner(System.in), System.out);
            
           
            assertEquals(0, result);

         
            assertTrue(outContent.toString().contains("Book ID: "));
           
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
  
    //trade
    
    @Test
    public void testTradeAddBookMenu() throws IOException {
        // Prepare input
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Execute 
        assertEquals(0, Main.TradeAddBookMenu(new java.util.Scanner(System.in), System.out));

        // Check output
        assertTrue(outContent.toString().contains("Book ID: "));
      
    
    }
    
    @Test
    public void testTradeBuyBookMenu() throws IOException {
        // Prepare input
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Execute 
        assertEquals(0, Main.TradeBuyBookMenu(new java.util.Scanner(System.in), System.out));

        // Check output
        assertTrue(outContent.toString().contains("Book ID: "));

    }
    
    @Test
    public void testTradeListBookMenu() {
       
        try {
            int result = Main.TradeMylistBookMenu(new java.util.Scanner(System.in), System.out);
            
            assertEquals(0, result);
            //assertTrue(outContent.toString().contains("Book List"));
           
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void testTradeAllListBookMenu() {
        
        try {
            int result = Main.TradeAllListBookMenu(new java.util.Scanner(System.in), System.out);
            
            assertEquals(0, result);
            assertTrue(outContent.toString().contains("Book List"));
           
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void testTradeDeleteBookMenu() {
    	
    	String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
   
        try {
            int result = Main.TradedeleteBookMenu(new java.util.Scanner(System.in), System.out);
            
           
            assertEquals(0, result);

         
            assertTrue(outContent.toString().contains("Book ID: "));
           
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void testCreditBuyScoreMenu() throws IOException {
        // Prepare input
        String input = "10\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Execute 
        assertEquals(0, Main.CreditBuyScoreMenu(new java.util.Scanner(System.in), System.out));

        // Check output
        assertTrue(outContent.toString().contains("Credit Score:"));

    }

    //event
    
    public void testEventAddMenu() throws IOException {
        // Prepare input
        String input = "1\nName\nContent\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Execute 
        assertEquals(0, Main.EventAddBookMenu(new java.util.Scanner(System.in), System.out));

        // Check output
        assertTrue(outContent.toString().contains("Event ID: "));
        assertTrue(outContent.toString().contains("Event Name: "));
        assertTrue(outContent.toString().contains("Event Content: "));
  
        
    }
    
    @Test
    public void testEventListMenu() {
       
        try {
            int result = Main.EventlistBookMenu(new java.util.Scanner(System.in), System.out);
            
            assertEquals(0, result);
            assertTrue(outContent.toString().contains("Event List"));
           
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void testEventAllListMenu() {
        
        try {
            int result = Main.EventlistAllBookMenu(new java.util.Scanner(System.in), System.out);
            
            assertEquals(0, result);
            assertTrue(outContent.toString().contains("Event All List"));
           
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void testEventDeleteMenu() {
    	
    	String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
   
        try {
            int result = Main.EventdeleteBookMenu(new java.util.Scanner(System.in), System.out);
            
           
            assertEquals(0, result);

         
            assertTrue(outContent.toString().contains("Event ID: "));
           
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void testEventUpdateMenu() throws IOException {
        // Prepare input
    	String input = "1\nName1\nContent1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Execute 
        assertEquals(0, Main.EventupdateBookMenu(new java.util.Scanner(System.in), System.out));

        // Check output
        assertTrue(outContent.toString().contains("Event ID: "));
        assertTrue(outContent.toString().contains("New Event Name: "));
        assertTrue(outContent.toString().contains("New Event Content: "));
     
    }
    
    //set-get
    
    @Test
    public void testSetGetLoginName() {
        // Test için kullanılacak değer
        String loginName = "test_user";

        // setLoginName metodunu çağırın
        Main.setLoginName(loginName);

        // getLoginName metodunu çağırarak değeri alın
        String retrievedLoginName = Main.getLoginName();

        // Beklenen değerle alınan değeri karşılaştırın
        assertEquals(loginName, retrievedLoginName);
    }

    @Test
    public void testSetGetWallet() {
        // Test için kullanılacak değer
        int wallet = 100;

        // setWallet metodunu çağırın
        Main.setWallet(wallet);

        // getWallet metodunu çağırarak değeri alın
        int retrievedWallet = Main.getWallet();

        // Beklenen değerle alınan değeri karşılaştırın
        assertEquals(wallet, retrievedWallet);
    }
    
    @Test
    public void testMainApp_3() throws IOException {
        String input = "3\n"; // Kullanıcı tarafından girilen değer (çıkış yapmak için)
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Scanner scanner = new Scanner(inputStream); // Kullanıcı girişi için bir tarama nesnesi
        PrintStream out = printStream; // Çıktıyı yönlendirmek için bir yazdırma nesnesi

        int result = Main.MainApp(scanner, out); // Ana uygulamayı çalıştır ve sonucu al
        String output = outputStream.toString().trim(); // Çıktıyı al ve baştaki ve sondaki boşlukları kaldır

        // Beklenen çıktı
        String expectedOutput = "";

        assertEquals(expectedOutput, output); // Çıktıyı kontrol et
        assertEquals(0, result); // Sonucu kontrol et
    }
    
    @Test
    public void testMainApp_1() throws IOException {
        String input = "1\naa\naa\n3\n"; // Kullanıcı tarafından girilen değer (çıkış yapmak için)
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Scanner scanner = new Scanner(inputStream); // Kullanıcı girişi için bir tarama nesnesi
        PrintStream out = printStream; // Çıktıyı yönlendirmek için bir yazdırma nesnesi

        int result = Main.MainApp(scanner, out); // Ana uygulamayı çalıştır ve sonucu al
        String output = outputStream.toString().trim(); // Çıktıyı al ve baştaki ve sondaki boşlukları kaldır

        // Beklenen çıktı
        String expectedOutput = "";

        assertEquals(expectedOutput, output); // Çıktıyı kontrol et
        assertEquals(0, result); // Sonucu kontrol et
    }
    
    @Test
    public void testMainApp_2() throws IOException {
        String input = "2\naa\naa\n5\n3\n"; // Kullanıcı tarafından girilen değer (çıkış yapmak için)
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Scanner scanner = new Scanner(inputStream); // Kullanıcı girişi için bir tarama nesnesi
        PrintStream out = printStream; // Çıktıyı yönlendirmek için bir yazdırma nesnesi

        int result = Main.MainApp(scanner, out); // Ana uygulamayı çalıştır ve sonucu al
        String output = outputStream.toString().trim(); // Çıktıyı al ve baştaki ve sondaki boşlukları kaldır

        // Beklenen çıktı
        //String expectedOutput = "";

        //assertEquals(expectedOutput, output); // Çıktıyı kontrol et
        assertEquals(0, result); // Sonucu kontrol et
    }
    
    @Test
    public void testUserMenu() throws IOException {
    	User user = new User("deneme","deneme",0); 
        String input = "5\n3\n"; // Kullanıcı tarafından girilen değer (çıkış yapmak için)
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Scanner scanner = new Scanner(inputStream); // Kullanıcı girişi için bir tarama nesnesi
        PrintStream out = printStream; // Çıktıyı yönlendirmek için bir yazdırma nesnesi

        int result = Main.userMenu(user,scanner, out); // Ana uygulamayı çalıştır ve sonucu al
        String output = outputStream.toString().trim(); // Çıktıyı al ve baştaki ve sondaki boşlukları kaldır

        // Beklenen çıktı
        //String expectedOutput = "";

        //assertEquals(expectedOutput, output); // Çıktıyı kontrol et
        assertEquals(0, result); // Sonucu kontrol et
    }
    
    @Test
    public void testUserMenu_1_add() throws IOException {
    	User user = new User("deneme1","deneme1",0); 
        String input = "1\n1\n5\nName\n50\n40\n6\n3\n"; // Kullanıcı tarafından girilen değer (çıkış yapmak için)
        
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Scanner scanner = new Scanner(inputStream); // Kullanıcı girişi için bir tarama nesnesi
        PrintStream out = printStream; // Çıktıyı yönlendirmek için bir yazdırma nesnesi

        int result = Main.userMenu(user,scanner, out); // Ana uygulamayı çalıştır ve sonucu al
        String output = outputStream.toString().trim(); // Çıktıyı al ve baştaki ve sondaki boşlukları kaldır

        // Beklenen çıktı
        //String expectedOutput = "";

        //assertEquals(expectedOutput, output); // Çıktıyı kontrol et
        assertEquals(0, result); // Sonucu kontrol et
    }
    
    @Test
    public void testUserMenu_1_delete() throws IOException {
    	User user = new User("deneme1","deneme1",0); 
        String input = "1\n2\n5\n6\n3\n"; // Kullanıcı tarafından girilen değer (çıkış yapmak için)
        
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Scanner scanner = new Scanner(inputStream); // Kullanıcı girişi için bir tarama nesnesi
        PrintStream out = printStream; // Çıktıyı yönlendirmek için bir yazdırma nesnesi

        int result = Main.userMenu(user,scanner, out); // Ana uygulamayı çalıştır ve sonucu al
        String output = outputStream.toString().trim(); // Çıktıyı al ve baştaki ve sondaki boşlukları kaldır

        // Beklenen çıktı
        //String expectedOutput = "";

        //assertEquals(expectedOutput, output); // Çıktıyı kontrol et
        assertEquals(0, result); // Sonucu kontrol et
    }
    @Test
    public void testUserMenu_1_update() throws IOException {
    	User user = new User("deneme1","deneme1",0); 
    	String input = "1\n3\n5\nName1\n60\n50\n6\n3\n"; // Kullanıcı tarafından girilen değer (çıkış yapmak için)
        
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Scanner scanner = new Scanner(inputStream); // Kullanıcı girişi için bir tarama nesnesi
        PrintStream out = printStream; // Çıktıyı yönlendirmek için bir yazdırma nesnesi

        int result = Main.userMenu(user,scanner, out); // Ana uygulamayı çalıştır ve sonucu al
        String output = outputStream.toString().trim(); // Çıktıyı al ve baştaki ve sondaki boşlukları kaldır

        // Beklenen çıktı
        //String expectedOutput = "";

        //assertEquals(expectedOutput, output); // Çıktıyı kontrol et
        assertEquals(0, result); // Sonucu kontrol et
    }
    @Test
    public void testUserMenu_1_list() throws IOException {
    	User user = new User("deneme1","deneme1",0); 
    	String input = "1\n4\n6\n3\n"; // Kullanıcı tarafından girilen değer (çıkış yapmak için)
        
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Scanner scanner = new Scanner(inputStream); // Kullanıcı girişi için bir tarama nesnesi
        PrintStream out = printStream; // Çıktıyı yönlendirmek için bir yazdırma nesnesi

        int result = Main.userMenu(user,scanner, out); // Ana uygulamayı çalıştır ve sonucu al
        String output = outputStream.toString().trim(); // Çıktıyı al ve baştaki ve sondaki boşlukları kaldır

        // Beklenen çıktı
        //String expectedOutput = "";

        //assertEquals(expectedOutput, output); // Çıktıyı kontrol et
        assertEquals(0, result); // Sonucu kontrol et
    }
    @Test
    public void testUserMenu_1_alllist() throws IOException {
    	User user = new User("deneme1","deneme1",0); 
    	String input = "1\n4\n6\n3\n"; // Kullanıcı tarafından girilen değer (çıkış yapmak için)
        
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Scanner scanner = new Scanner(inputStream); // Kullanıcı girişi için bir tarama nesnesi
        PrintStream out = printStream; // Çıktıyı yönlendirmek için bir yazdırma nesnesi

        int result = Main.userMenu(user,scanner, out); // Ana uygulamayı çalıştır ve sonucu al
        String output = outputStream.toString().trim(); // Çıktıyı al ve baştaki ve sondaki boşlukları kaldır

        // Beklenen çıktı
        //String expectedOutput = "";

        //assertEquals(expectedOutput, output); // Çıktıyı kontrol et
        assertEquals(0, result); // Sonucu kontrol et
    }
    
    
    //wish
    
    public void testWishUserMenu_1_add() throws IOException {
    	User user = new User("deneme1","deneme1",0); 
        String input = "2\n1\n1\n5\n6\n3\n"; // Kullanıcı tarafından girilen değer (çıkış yapmak için)
        
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Scanner scanner = new Scanner(inputStream); // Kullanıcı girişi için bir tarama nesnesi
        PrintStream out = printStream; // Çıktıyı yönlendirmek için bir yazdırma nesnesi

        int result = Main.userMenu(user,scanner, out); // Ana uygulamayı çalıştır ve sonucu al
        String output = outputStream.toString().trim(); // Çıktıyı al ve baştaki ve sondaki boşlukları kaldır

        // Beklenen çıktı
        //String expectedOutput = "";

        //assertEquals(expectedOutput, output); // Çıktıyı kontrol et
        assertEquals(0, result); // Sonucu kontrol et
    }
    
    @Test
    public void testWishUserMenu_1_delete() throws IOException {
    	User user = new User("deneme1","deneme1",0); 
        String input = "2\n1\n1\n2\n5\n6\n3\n"; // Kullanıcı tarafından girilen değer (çıkış yapmak için)
        
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Scanner scanner = new Scanner(inputStream); // Kullanıcı girişi için bir tarama nesnesi
        PrintStream out = printStream; // Çıktıyı yönlendirmek için bir yazdırma nesnesi

        int result = Main.userMenu(user,scanner, out); // Ana uygulamayı çalıştır ve sonucu al
        String output = outputStream.toString().trim(); // Çıktıyı al ve baştaki ve sondaki boşlukları kaldır

        // Beklenen çıktı
        //String expectedOutput = "";

        //assertEquals(expectedOutput, output); // Çıktıyı kontrol et
        assertEquals(0, result); // Sonucu kontrol et
    }
    /*
    @Test
    public void testWishUserMenu_1_update() throws IOException {
    	User user = new User("deneme1","deneme1",0); 
    	String input = "2\n1\n3\n5\nName1\n60\n50\n6\n3\n"; // Kullanıcı tarafından girilen değer (çıkış yapmak için)
        
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Scanner scanner = new Scanner(inputStream); // Kullanıcı girişi için bir tarama nesnesi
        PrintStream out = printStream; // Çıktıyı yönlendirmek için bir yazdırma nesnesi

        int result = Main.userMenu(user,scanner, out); // Ana uygulamayı çalıştır ve sonucu al
        String output = outputStream.toString().trim(); // Çıktıyı al ve baştaki ve sondaki boşlukları kaldır

        // Beklenen çıktı
        //String expectedOutput = "";

        //assertEquals(expectedOutput, output); // Çıktıyı kontrol et
        assertEquals(0, result); // Sonucu kontrol et
    }
    */
    /*
    @Test
    public void testWishUserMenu_1_list() throws IOException {
    	User user = new User("deneme1","deneme1",0); 
    	String input = "2\n1\n4\n6\n3\n"; // Kullanıcı tarafından girilen değer (çıkış yapmak için)
        
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Scanner scanner = new Scanner(inputStream); // Kullanıcı girişi için bir tarama nesnesi
        PrintStream out = printStream; // Çıktıyı yönlendirmek için bir yazdırma nesnesi

        int result = Main.userMenu(user,scanner, out); // Ana uygulamayı çalıştır ve sonucu al
        String output = outputStream.toString().trim(); // Çıktıyı al ve baştaki ve sondaki boşlukları kaldır

        // Beklenen çıktı
        //String expectedOutput = "";

        //assertEquals(expectedOutput, output); // Çıktıyı kontrol et
        assertEquals(0, result); // Sonucu kontrol et
    }
    @Test
    public void testWishUserMenu_1_alllist() throws IOException {
    	User user = new User("deneme1","deneme1",0); 
    	String input = "2\n1\n6\n3\n"; // Kullanıcı tarafından girilen değer (çıkış yapmak için)
        
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Scanner scanner = new Scanner(inputStream); // Kullanıcı girişi için bir tarama nesnesi
        PrintStream out = printStream; // Çıktıyı yönlendirmek için bir yazdırma nesnesi

        int result = Main.userMenu(user,scanner, out); // Ana uygulamayı çalıştır ve sonucu al
        String output = outputStream.toString().trim(); // Çıktıyı al ve baştaki ve sondaki boşlukları kaldır

        // Beklenen çıktı
        //String expectedOutput = "";

        //assertEquals(expectedOutput, output); // Çıktıyı kontrol et
        assertEquals(0, result); // Sonucu kontrol et
    }
    @Test
    public void testWishListMenu_1_alllist() throws IOException {
    	User user = new User("deneme1","deneme1",0); 
    	String input = "2\n1\n6\n3\n"; // Kullanıcı tarafından girilen değer (çıkış yapmak için)
        
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Scanner scanner = new Scanner(inputStream); // Kullanıcı girişi için bir tarama nesnesi
        PrintStream out = printStream; // Çıktıyı yönlendirmek için bir yazdırma nesnesi

        int result = Main.userMenu(user,scanner, out); // Ana uygulamayı çalıştır ve sonucu al
        String output = outputStream.toString().trim(); // Çıktıyı al ve baştaki ve sondaki boşlukları kaldır

        // Beklenen çıktı
        //String expectedOutput = "";

        //assertEquals(expectedOutput, output); // Çıktıyı kontrol et
        assertEquals(0, result); // Sonucu kontrol et
    }
    */
    
 //wish
    
    public void testtradeUserMenu_1_add() throws IOException {
    	User user = new User("deneme1","deneme1",0); 
        String input = "2\n2\n1\n1\n5\n3\n3\n"; // Kullanıcı tarafından girilen değer (çıkış yapmak için)
        
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Scanner scanner = new Scanner(inputStream); // Kullanıcı girişi için bir tarama nesnesi
        PrintStream out = printStream; // Çıktıyı yönlendirmek için bir yazdırma nesnesi

        int result = Main.userMenu(user,scanner, out); // Ana uygulamayı çalıştır ve sonucu al
        String output = outputStream.toString().trim(); // Çıktıyı al ve baştaki ve sondaki boşlukları kaldır

        // Beklenen çıktı
        //String expectedOutput = "";

        //assertEquals(expectedOutput, output); // Çıktıyı kontrol et
        assertEquals(0, result); // Sonucu kontrol et
    }
    
    @Test
    public void testtradeUserMenu_1_delete() throws IOException {
    	User user = new User("deneme1","deneme1",0); 
    	String input = "2\n2\n1\n1\n5\n3\n3\n"; // Kullanıcı tarafından girilen değer (çıkış yapmak için)
        
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Scanner scanner = new Scanner(inputStream); // Kullanıcı girişi için bir tarama nesnesi
        PrintStream out = printStream; // Çıktıyı yönlendirmek için bir yazdırma nesnesi

        int result = Main.userMenu(user,scanner, out); // Ana uygulamayı çalıştır ve sonucu al
        String output = outputStream.toString().trim(); // Çıktıyı al ve baştaki ve sondaki boşlukları kaldır

        // Beklenen çıktı
        //String expectedOutput = "";

        //assertEquals(expectedOutput, output); // Çıktıyı kontrol et
        assertEquals(0, result); // Sonucu kontrol et
    }
   
}