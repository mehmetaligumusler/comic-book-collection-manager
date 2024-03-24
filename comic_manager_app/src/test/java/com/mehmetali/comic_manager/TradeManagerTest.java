package com.mehmetali.comic_manager;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.*;

public class TradeManagerTest {

    private TradeManager tradeManager;
    
    public static boolean deleteFile(String filepath) {
        File file = new File(filepath);

        if (file.exists()) {
          return file.delete();
        } else {
          return false;
        }
    }

    @Before
    public void setUp() {
        tradeManager = new TradeManager();
    }

    @After
    public void tearDown() {
        tradeManager = null;
    }

    @Test
    public void addTradeTest() {
        // Test adding a new trade
        Trade newTrade = new Trade(1, "Title1", 1, "User", 10);
        String result = tradeManager.AddTrade(newTrade);
        assertEquals("Title1", result);
        deleteFile("trade.dat");
    }

    @Test
    public void addDuplicateTradeTest() {
        // Test adding a trade with duplicate title
        Trade existingTrade = new Trade(2, "Title2", 1, "User", 10);
        tradeManager.AddTrade(existingTrade);

        Trade newTrade = new Trade(3, "Title2", 2, "User2", 15);
        String result = tradeManager.AddTrade(newTrade);
        assertNull(result);
        deleteFile("trade.dat");
    }

    @Test
    public void listAllTradeListTest() {
        // Test listing all trades
        int result = tradeManager.listAllTradeList();
        assertEquals(0, result); // Assuming no trades exist initially
        deleteFile("trade.dat");
    }

    @Test
    public void listMyTradeListTest() {
        // Test listing trades by user
        int result = tradeManager.listMyTradeList("User");
        assertEquals(0, result); // Assuming no trades exist for user "User" initially
        deleteFile("trade.dat");
    }

    @Test
    public void deleteTradeByIDTest() {
        // Test deleting a trade by ID
        Trade tradeToDelete = new Trade(4, "Title3", 1, "User3", 10);
        tradeManager.AddTrade(tradeToDelete);

        int deleteResult = tradeManager.deleteTradeByID(4);
        assertEquals(0, deleteResult);
        deleteFile("trade.dat");
    }

    @Test
    public void buyTradeByIDTest() {
        // Test buying a trade by ID
        Trade tradeToBuy = new Trade(5, "Title4", 1, "User4", 10);
        tradeManager.AddTrade(tradeToBuy);

        int buyResult = tradeManager.BuyTradeByID(5);
        assertEquals(0, buyResult);
        deleteFile("trade.dat");
    }

    // Add tests for other methods such as isTradeNameAvailable, readUsersFromFile, saveUsersToFile
}
