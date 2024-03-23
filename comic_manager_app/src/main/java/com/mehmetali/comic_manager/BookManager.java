package com.mehmetali.comic_manager;



import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private List<Book> comics;
    private static final String BOOK_FILE_PATH = "books.dat";

    public BookManager() {
        this.comics = readUsersFromFile();
    }

    //Book Collection
    
    public String AddBook(Book comic) {
        if (!isBookNameAvailable(comic.getTitle())) {
            return null;
        }
        
        comics.add(comic);
        saveUsersToFile(comics);
        return comic.getTitle();
    }
    
    public int listBooks() {
    	
    	this.comics = readUsersFromFile();
    	
        if (comics.isEmpty()) {
            System.out.println("Listelenecek kitap bulunamadı.");
            return 0;
        }

        System.out.println("----- Kitap Listesi -----");
        for (Book comic : comics) {
            System.out.println("Kitap ID: " + comic.getComicID());
            System.out.println("Başlık: " + comic.getTitle());
            System.out.println("Sayı Numarası: " + comic.getpageNumber());
            System.out.println("Durum: " + comic.getuser());
            System.out.println("Değer: " + comic.getValue());
            System.out.println("-------------------------");
        }
        return comics.size();
    }
    
    public int listBooksByCondition(String condition) {
    	
    	this.comics = readUsersFromFile();
    	
        if (comics.isEmpty()) {
            System.out.println("Listelenecek kitap bulunamadı.");
            return 0;
        }

        System.out.println("----- " + condition + " Durumundaki Kitaplar -----");
        for (Book comic : comics) {
            if (comic.getuser().equalsIgnoreCase(condition)) {
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
        for (Book comic : comics) {
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
        for (Book comic : comics) {
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
        for (Book comic : comics) {
            if (comic.getTitle().equals(title)) {
                return false; 
            }
        }
        return true;
    }
    
    public boolean isBookIDAvailable(int ID) {
        for (Book comic : comics) {
            if (comic.getComicID() == ID) {
                return false; 
            }
        }
        return true; 
    }
    
    public String getBookTitleByID(int bookID) {
        for (Book comic : comics) {
            if (comic.getComicID() == bookID) {
                return comic.getTitle();
            }
        }
        return null; 
    }


    private List<Book> readUsersFromFile() 
    {
        File file = new File(BOOK_FILE_PATH);
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
        
        try (FileInputStream fileIn = new FileInputStream(BOOK_FILE_PATH);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            return (List<Book>) objectIn.readObject();
        } 
        catch (IOException | ClassNotFoundException e) 
        {
            //System.out.println("Dosya okuma hatasi: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    private int saveUsersToFile(List<Book> comics2) {
        try (FileOutputStream fileOut = new FileOutputStream(BOOK_FILE_PATH);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(comics2);
            return 0;
        } catch (IOException e) {
            System.out.println("Dosya yazma hatasi: " + e.getMessage());
            return -1;
        }
    }


}
