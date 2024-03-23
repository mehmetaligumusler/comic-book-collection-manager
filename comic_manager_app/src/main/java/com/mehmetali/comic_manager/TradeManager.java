package com.mehmetali.comic_manager;



import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TradeManager {
    private List<Trade> comics;
    private static final String Trade_FILE_PATH = "trade.dat";
    private static final BookManager comicmanager = new BookManager();
    private static final UserManager usermanager = new UserManager();

    public TradeManager() {
        this.comics = readUsersFromFile();
    }

    //Trade Collection
    
    public String AddTrade(Trade comic) {
    	
    	if (comicmanager.isBookIDAvailable(comic.getComicID())) {
    		System.out.println("Böyle Bir Kitap Bulunamadi");
            return null;
        }
        if (!isTradeNameAvailable(comic.getTitle())) {
            System.out.println("Bu Kitap adi zaten kullaniliyor. Lutfen farkli bir kitap adi secin.");
            return null;
        }
        
        
        String Name = comicmanager.getBookTitleByID(comic.getComicID());
        comic.setTitle(Name);
        
        comics.add(comic);
        saveUsersToFile(comics);
        
        comicmanager.deleteBookByID(comic.getComicID()); // book list den silme
        
        System.out.println("Kitap başarıyla Trade listesine eklendi.");
        
        return comic.getTitle();
    }
    
    public int listAllTradeList() {
    	
    	this.comics = readUsersFromFile();
    	
        if (comics.isEmpty()) {
            System.out.println("Listelenecek kitap bulunamadı.");
            return 0;
        }

        System.out.println("----- Kitap Listesi -----");
        for (Trade comic : comics) {
            System.out.println("Kitap ID: " + comic.getComicID());
            System.out.println("Başlık: " + comic.getTitle());
            System.out.println("Sayı Numarası: " + comic.getpageNumber());
            System.out.println("Durum: " + comic.getuser());
            System.out.println("Değer: " + comic.getValue());
            System.out.println("-------------------------");
        }
        return comics.size();
    }
    
    public int listMyTradeList(String condition) {
    	
    	this.comics = readUsersFromFile();
    	
        if (comics.isEmpty()) {
            System.out.println("Listelenecek kitap bulunamadı.");
            return 0;
        }

        System.out.println("----- " + condition + " Durumundaki Kitaplar -----");
        for (Trade comic : comics) {
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
    
    public int deleteTradeByID(int TradeID) {
        boolean found = false;
        for (Trade comic : comics) {
            if (comic.getComicID() == TradeID) {
                comics.remove(comic);
                saveUsersToFile(comics);
                System.out.println("Kitap başarıyla silindi.");
                found = true;
                
                Book newUser = new Book(TradeID, comic.getTitle(),comic.getpageNumber(),comic.getuser(),comic.getValue());
                comicmanager.AddBook(newUser);
                
                return 0;
            }
        }
        if (!found) {
            System.out.println("Kitap ID'si ile eşleşen kitap bulunamadı.");
            return -1;
        }
        return -1;
    }
    
    public int BuyTradeByID(int TradeID) {
        boolean found = false;
        for (Trade comic : comics) {
            if (comic.getComicID() == TradeID) 
            {
            	Main main = new Main();
            	 
            	if(Main.LoginWallet >= comic.getValue())
            	{
            		String oldUser = comic.getuser();
                	System.out.print(oldUser);
                	
                	//kitap satilir.
                    comics.remove(comic);
                    saveUsersToFile(comics);
                    System.out.println("Kitap başarıyla silindi.");
                    found = true;
                    
                    //kitap diger kullaniciye gecer
                    Book newUser = new Book(TradeID, comic.getTitle(),comic.getpageNumber(),Main.LoginName,comic.getValue());
                    comicmanager.AddBook(newUser);
                    
                    //kitap parasi alan kullaniciden eksilir.
                    usermanager.creditSellscore(Main.LoginName, comic.getValue());
                    
                    //kitap parasi eklenir satan kullaniciye
                    usermanager.creditbuyscore(oldUser, comic.getValue());
                    
            	}
            	else {
            		System.out.println("Kredi Skoru Yetersiz! Satin Alinamadi.");
				}
            	
            	
                
                return 0;
            }
        }
        if (!found) {
            System.out.println("Kitap ID'si ile eşleşen kitap bulunamadı.");
            return -1;
        }
        
        return -1;
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
            //System.out.println("Dosya bulunamadi, yeni bir dosya olusturuluyor.");
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
            //System.out.println("Dosya okuma hatasi: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    private int saveUsersToFile(List<Trade> comics2) {
        try (FileOutputStream fileOut = new FileOutputStream(Trade_FILE_PATH);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(comics2);
            System.out.println("Kitap basariyla dosyaya kaydedildi.");
            return 0;
        } catch (IOException e) {
            System.out.println("Dosya yazma hatasi: " + e.getMessage());
            return -1;
        }
    }


}
