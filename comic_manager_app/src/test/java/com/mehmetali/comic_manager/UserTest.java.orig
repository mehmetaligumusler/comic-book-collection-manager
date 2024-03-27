package com.mehmetali.comic_manager;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

    private User<String> user;

    @Before
    public void setUp() {
        user = new User<>("testUser", "password123", 100);
    }

    @Test
    public void testGetters() {
        assertEquals("testUser", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals(100, user.getWallet());
    }

    @Test
    public void testSetters() {
        user.setUsername("newUsername");
        user.setPassword("newPassword123");
        user.setWallet(200);

        assertEquals("newUsername", user.getUsername());
        assertEquals("newPassword123", user.getPassword());
        assertEquals(200, user.getWallet());
    }

    @Test
    public void testAddToCollectionAndRemoveFromCollection() {
        assertEquals(0, user.getCollection().size());

        user.addToCollection("comic1");
        user.addToCollection("comic2");
        user.addToCollection("comic3");

        assertEquals(3, user.getCollection().size());

        user.removeFromCollection("comic2");

        assertEquals(2, user.getCollection().size());
        assertFalse(user.getCollection().contains("comic2"));
    }

    @Test
    public void testAddToWishlistAndRemoveFromWishlist() {
        assertEquals(0, user.getWishlist().size());

        user.addToWishlist("comic1");
        user.addToWishlist("comic2");
        user.addToWishlist("comic3");

        assertEquals(3, user.getWishlist().size());

        user.removeFromWishlist("comic2");

        assertEquals(2, user.getWishlist().size());
        assertFalse(user.getWishlist().contains("comic2"));
    }

    @Test
    public void testAddToTradeListAndRemoveFromTradeList() {
        assertEquals(0, user.getTradeList().size());

        user.addToTradeList("comic1");
        user.addToTradeList("comic2");
        user.addToTradeList("comic3");

        assertEquals(3, user.getTradeList().size());

        user.removeFromTradeList("comic2");

        assertEquals(2, user.getTradeList().size());
        assertFalse(user.getTradeList().contains("comic2"));
    }
    
    @Test
    public void testSetWishlist() {
        List<String> wishlist = new ArrayList<>();
        wishlist.add("comic1");
        wishlist.add("comic2");
        wishlist.add("comic3");

        user.setWishlist(wishlist);

        assertEquals(wishlist, user.getWishlist());
    }
    
    @Test
    public void testSetCollection() {
        List<String> collection = new ArrayList<>();
        collection.add("comic1");
        collection.add("comic2");
        collection.add("comic3");

        user.setCollection(collection);

        assertEquals(collection, user.getCollection());
    }
    
    @Test
    public void testSetTradeList() {
        List<String> tradeList = new ArrayList<>();
        tradeList.add("comic1");
        tradeList.add("comic2");
        tradeList.add("comic3");

        user.setTradeList(tradeList);

        assertEquals(tradeList, user.getTradeList());
    }
    
    @Test
    public void testToString() {
        List<String> collection = new ArrayList<>();
        collection.add("comic1");
        collection.add("comic2");
        collection.add("comic3");
        user.setCollection(collection);

        List<String> wishlist = new ArrayList<>();
        wishlist.add("comic4");
        wishlist.add("comic5");
        user.setWishlist(wishlist);

        List<String> tradeList = new ArrayList<>();
        tradeList.add("comic6");
        tradeList.add("comic7");
        user.setTradeList(tradeList);

        String expected = "User{username='testUser', password='password123', collection=[" +
                            "comic1, comic2, comic3], wishlist=[comic4, comic5], tradeList=[comic6, comic7]}";

        assertEquals(expected, user.toString());
    }
}

