package com.mehmetali.comic_manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ComicManager {
    private List<Comic> comics;
    private static final String USER_FILE_PATH = "books.dat";

    public ComicManager() {
        this.comics = readUsersFromFile();
    }

    public void AddBook(Comic comic) {
        // Kullanıcı adı kontrolü
        if (!isBookNameAvailable(comic.getTitle())) {
            System.out.println("Bu Kitap adi zaten kullaniliyor. Lutfen farkli bir kitap adi secin.");
            return;
        }
        
        comics.add(comic);
        saveUsersToFile(comics);
        System.out.println("Kitap basariyla kaydedildi.");
    }
    
    public void listBooks() {
        if (comics.isEmpty()) {
            System.out.println("Listelenecek kitap bulunamadı.");
            return;
        }

        System.out.println("----- Kitap Listesi -----");
        for (Comic comic : comics) {
            System.out.println("Kitap ID: " + comic.getComicID());
            System.out.println("Başlık: " + comic.getTitle());
            System.out.println("Sayı Numarası: " + comic.getIssueNumber());
            System.out.println("Durum: " + comic.getCondition());
            System.out.println("Kapak Resmi: " + comic.getCoverArt());
            System.out.println("Değer: " + comic.getValue());
            System.out.println("-------------------------");
        }
    }
    
    public void listBooksByCondition(String condition) {
        if (comics.isEmpty()) {
            System.out.println("Listelenecek kitap bulunamadı.");
            return;
        }

        System.out.println("----- " + condition + " Durumundaki Kitaplar -----");
        for (Comic comic : comics) {
            if (comic.getCondition().equalsIgnoreCase(condition)) {
                System.out.println("Kitap ID: " + comic.getComicID());
                System.out.println("Başlık: " + comic.getTitle());
                System.out.println("Sayı Numarası: " + comic.getIssueNumber());
                System.out.println("Durum: " + comic.getCondition());
                System.out.println("Kapak Resmi: " + comic.getCoverArt());
                System.out.println("Değer: " + comic.getValue());
                System.out.println("-------------------------");
            }
        }
    }
    
    public void deleteBookByID(int bookID) {
        boolean found = false;
        for (Comic comic : comics) {
            if (comic.getComicID() == bookID) {
                comics.remove(comic);
                saveUsersToFile(comics);
                System.out.println("Kitap başarıyla silindi.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Kitap ID'si ile eşleşen kitap bulunamadı.");
        }
    }
    
    public void updateBookTitleByID(int bookID, String newTitle) {
        boolean found = false;
        for (Comic comic : comics) {
            if (comic.getComicID() == bookID) {
                comic.setTitle(newTitle);
                saveUsersToFile(comics);
                System.out.println("Kitap başlığı başarıyla güncellendi.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Kitap ID'si ile eşleşen kitap bulunamadı.");
        }
    }






    public boolean isBookNameAvailable(String title) {
        for (Comic comic : comics) {
            if (comic.getTitle().equals(title)) {
                return false; // Kitap adı daha önce kullanılmış
            }
        }
        return true; // Kitap adı kullanılabilir
    }

    private List<Comic> readUsersFromFile() {
        File file = new File(USER_FILE_PATH);
        if (!file.exists()) {
            System.out.println("Dosya bulunamadi, yeni bir dosya olusturuluyor.");
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
        
        try (FileInputStream fileIn = new FileInputStream(USER_FILE_PATH);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            return (List<Comic>) objectIn.readObject();
        } 
        catch (IOException | ClassNotFoundException e) 
        {
            System.out.println("Dosya okuma hatasi: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    private void saveUsersToFile(List<Comic> comics2) {
        try (FileOutputStream fileOut = new FileOutputStream(USER_FILE_PATH);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(comics2);
            System.out.println("Kitap basariyla dosyaya kaydedildi.");
        } catch (IOException e) {
            System.out.println("Dosya yazma hatasi: " + e.getMessage());
        }
    }


}
