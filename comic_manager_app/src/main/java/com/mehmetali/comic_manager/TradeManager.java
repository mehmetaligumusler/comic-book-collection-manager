package com.mehmetali.comic_manager;



import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TradeManager {
    private List<Trade> comics;
    private static final String Trade_FILE_PATH = "trade.dat";
    private static final BookManager comicmanager = new BookManager();

    public TradeManager() {
        this.comics = readUsersFromFile();
    }

    //Trade Collection
    
    public void AddTrade(Trade comic) {
    	
    	if (comicmanager.isBookIDAvailable(comic.getComicID())) {
    		System.out.println("Böyle Bir Kitap Bulunamadi");
            return;
        }
        if (!isTradeNameAvailable(comic.getTitle())) {
            System.out.println("Bu Kitap adi zaten kullaniliyor. Lutfen farkli bir kitap adi secin.");
            return;
        }
        
        
        String Name = comicmanager.getBookTitleByID(comic.getComicID());
        comic.setTitle(Name);
        
        comics.add(comic);
        saveUsersToFile(comics);
        
        comicmanager.deleteBookByID(comic.getComicID()); // book list den silme
        
        System.out.println("Kitap başarıyla Trade listesine eklendi.");
    }
    
    public void listAllTradeList() {
    	
    	this.comics = readUsersFromFile();
    	
        if (comics.isEmpty()) {
            System.out.println("Listelenecek kitap bulunamadı.");
            return;
        }

        System.out.println("----- Kitap Listesi -----");
        for (Trade comic : comics) {
            System.out.println("Kitap ID: " + comic.getComicID());
            System.out.println("Başlık: " + comic.getTitle());
            System.out.println("Sayı Numarası: " + comic.getIssueNumber());
            System.out.println("Durum: " + comic.getuser());
            System.out.println("Kapak Resmi: " + comic.getCoverArt());
            System.out.println("Değer: " + comic.getValue());
            System.out.println("-------------------------");
        }
    }
    
    public void listMyTradeList(String condition) {
    	
    	this.comics = readUsersFromFile();
    	
        if (comics.isEmpty()) {
            System.out.println("Listelenecek kitap bulunamadı.");
            return;
        }

        System.out.println("----- " + condition + " Durumundaki Kitaplar -----");
        for (Trade comic : comics) {
            if (comic.getuser().equalsIgnoreCase(condition)) {
                System.out.println("Kitap ID: " + comic.getComicID());
                System.out.println("Başlık: " + comic.getTitle());
                System.out.println("Sayı Numarası: " + comic.getIssueNumber());
                System.out.println("Durum: " + comic.getuser());
                System.out.println("Kapak Resmi: " + comic.getCoverArt());
                System.out.println("Değer: " + comic.getValue());
                System.out.println("-------------------------");
            }
        }
    }
    
    public void deleteTradeByID(int TradeID) {
        boolean found = false;
        for (Trade comic : comics) {
            if (comic.getComicID() == TradeID) {
                comics.remove(comic);
                saveUsersToFile(comics);
                System.out.println("Kitap başarıyla silindi.");
                found = true;
                
                Book newUser = new Book(TradeID, comic.getTitle(),1,comic.getuser(),"a",10);
                comicmanager.AddBook(newUser);
                
                break;
            }
        }
        if (!found) {
            System.out.println("Kitap ID'si ile eşleşen kitap bulunamadı.");
        }
    }
    
    public void BuyTradeByID(int TradeID) {
        boolean found = false;
        for (Trade comic : comics) {
            if (comic.getComicID() == TradeID) 
            {
            	String oldUser = comic.getuser();
            	System.out.print(oldUser);
            	
                comics.remove(comic);
                saveUsersToFile(comics);
                System.out.println("Kitap başarıyla silindi.");
                found = true;
                
                Main main = new Main();
                
                Book newUser = new Book(TradeID, comic.getTitle(),1,Main.LoginName,"a",10);
                comicmanager.AddBook(newUser);
                
                break;
            }
        }
        if (!found) {
            System.out.println("Kitap ID'si ile eşleşen kitap bulunamadı.");
        }
    }
    
    
    public boolean isTradeNameAvailable(String title) {
        for (Trade comic : comics) {
            if (comic.getTitle().equals(title)) {
                return false; // Kitap adı daha önce kullanılmış
            }
        }
        return true; // Kitap adı kullanılabilir
    }
    
    public boolean isTradeIDAvailable(int ID) {
        for (Trade comic : comics) {
            if (comic.getComicID() == ID) {
                return false; 
            }
        }
        return true; 
    }
    
    public String getTradeTitleByID(int TradeID) {
        for (Trade comic : comics) {
            if (comic.getComicID() == TradeID) {
                return comic.getTitle();
            }
        }
        return null; 
    }


    private List<Trade> readUsersFromFile() 
    {
        File file = new File(Trade_FILE_PATH);
        if (!file.exists()) {
            System.out.println("Dosya bulunamadi, yeni bir dosya olusturuluyor.");
            // Dosya yoksa, dosyayı oluştur ve boş bir kitap listesi oluştur
            try {
                if (file.createNewFile()) {
                    System.out.println("Trades.dat dosyasi olusturuldu.");
                } else {
                    System.out.println("Trades.dat dosyasi zaten var.");
                }
            } catch (IOException e) {
                System.out.println("Dosya olusturma hatasi: " + e.getMessage());
            }
            return new ArrayList<>();
        }
        
        try (FileInputStream fileIn = new FileInputStream(Trade_FILE_PATH);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            return (List<Trade>) objectIn.readObject();
        } 
        catch (IOException | ClassNotFoundException e) 
        {
            System.out.println("Dosya okuma hatasi: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    private void saveUsersToFile(List<Trade> comics2) {
        try (FileOutputStream fileOut = new FileOutputStream(Trade_FILE_PATH);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(comics2);
            System.out.println("Kitap basariyla dosyaya kaydedildi.");
        } catch (IOException e) {
            System.out.println("Dosya yazma hatasi: " + e.getMessage());
        }
    }


}
