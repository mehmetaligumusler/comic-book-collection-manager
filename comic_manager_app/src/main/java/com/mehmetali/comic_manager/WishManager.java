package com.mehmetali.comic_manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.mehmetali.comic_manager.Book;

public class WishManager {
    private List<Wish> comics;
    private static final String WISH_FILE_PATH = "wish.dat";
    private static final BookManager comicmanager = new BookManager();

    public WishManager() {
        this.comics = readUsersFromFile();
    }

    //Book Collection
    
    public String AddBook(Wish comic) {
        // Kitabın var olup olmadığını kontrol et
    	
    	if (comicmanager.isBookIDAvailable(comic.getComicID())) {
    		System.out.println("Böyle Bir Kitap Bulunamadi");
            return null;
        }
        // Kullanıcı adı kontrolü
        if (!isBookNameAvailable(comic.getTitle())) {
            System.out.println("Bu Kitap adı zaten kullanılıyor. Lütfen farklı bir kitap adı seçin.");
            return null;
        }
        
        String Name = comicmanager.getBookTitleByID(comic.getComicID());
        
        comic.setTitle(Name);
        
        comics.add(comic);
        saveUsersToFile(comics);
        System.out.println("Kitap başarıyla wish listesine eklendi.");
        
        return comic.getTitle();
    }

    
    public int listBooks() {
    	
    	this.comics = readUsersFromFile();
    	
        if (comics.isEmpty()) {
            System.out.println("Listelenecek kitap bulunamadı.");
            return 0;
        }

        System.out.println("----- Kitap Listesi -----");
        for (Wish comic : comics) {
            System.out.println("Kitap ID: " + comic.getComicID());
            System.out.println("Başlık: " + comic.getTitle());
            System.out.println("Sayı Numarası: " + comic.getpageNumber());
            System.out.println("Durum: " + comic.getuser());
            System.out.println("Değer: " + comic.getValue());
            System.out.println("-------------------------");
        }
        
        return comics.size();
    }
    
    public int listBooksByUser(String User) {
    	
    	this.comics = readUsersFromFile();
    	
        if (comics.isEmpty()) {
            System.out.println("Listelenecek kitap bulunamadı.");
            return 0;
        }

        System.out.println("----- " + User + " Durumundaki Kitaplar -----");
        for (Wish comic : comics) {
            if (comic.getuser().equalsIgnoreCase(User)) {
                System.out.println("Kitap ID: " + comic.getComicID());
                System.out.println("Başlık: " + comic.getTitle());
                System.out.println("Sayı Numarası: " + comic.getpageNumber());
                System.out.println("Durum: " + comic.getuser());
                System.out.println("Değer: " + comic.getValue());
                System.out.println("-------------------------");
            }
        }
        return comics.size();
    }
    
    public int deleteBookByID(int bookID) {
        boolean found = false;
        for (Wish comic : comics) {
            if (comic.getComicID() == bookID) {
                comics.remove(comic);
                saveUsersToFile(comics);
                System.out.println("Kitap başarıyla silindi.");
                found = true;
                return 0;
            }
        }
        if (!found) {
            System.out.println("Kitap ID'si ile eşleşen kitap bulunamadı.");
            return -1;
        }
        return -1;
    }
    
    public int updateBookTitleByID(int bookID, String newTitle) {
        boolean found = false;
        for (Wish comic : comics) {
            if (comic.getComicID() == bookID) {
                comic.setTitle(newTitle);
                saveUsersToFile(comics);
                System.out.println("Kitap başlığı başarıyla güncellendi.");
                found = true;
                return 0;
            }
        }
        if (!found) {
            System.out.println("Kitap ID'si ile eşleşen kitap bulunamadı.");
            return -1;
        }
        return -1;
    }

    


    public boolean isBookNameAvailable(String title) {
        for (Wish comic : comics) {
            if (comic.getTitle().equals(title)) {
                return false; // Kitap adı daha önce kullanılmış
            }
        }
        return true; // Kitap adı kullanılabilir
    }
    

    private List<Wish> readUsersFromFile() 
    {
        File file = new File(WISH_FILE_PATH);
        if (!file.exists()) {
            //System.out.println("Dosya bulunamadi, yeni bir dosya olusturuluyor.");
            // Dosya yoksa, dosyayı oluştur ve boş bir kitap listesi oluştur
            try {
                if (file.createNewFile()) {
                    System.out.println("books.dat dosyasi olusturuldu.");
                } else {
                    System.out.println("books.dat dosyasi zaten var.");
                }
            } catch (IOException e) {
                System.out.println("Dosya olusturma hatasi: " + e.getMessage());
            }
            return new ArrayList<>();
        }
        
        try (FileInputStream fileIn = new FileInputStream(WISH_FILE_PATH);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            return (List<Wish>) objectIn.readObject();
        } 
        catch (IOException | ClassNotFoundException e) 
        {
            return new ArrayList<>();
        }
    }


    private int saveUsersToFile(List<Wish> comics2) {
        try (FileOutputStream fileOut = new FileOutputStream(WISH_FILE_PATH);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(comics2);
            return 0;
        } catch (IOException e) {
            return -1;
        }
    }


}
