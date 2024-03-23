package com.mehmetali.comic_manager;



import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private List<Event> comics;
    private static final String Event_FILE_PATH = "event.dat";

    public EventManager() {
        this.comics = readUsersFromFile();
    }

    //Event Collection
    
    public void AddEvent(Event comic) {
        // Kullanıcı adı kontrolü
        if (!isEventNameAvailable(comic.getTitle())) {
            System.out.println("Bu Event adi zaten kullaniliyor. Lutfen farkli bir Event adi secin.");
            return;
        }
        
        comics.add(comic);
        saveUsersToFile(comics);
        System.out.println("Event basariyla kaydedildi.");
    }
    
    public void listEvents() {
    	
    	this.comics = readUsersFromFile();
    	
        if (comics.isEmpty()) {
            System.out.println("Listelenecek Event bulunamadı.");
            return;
        }

        System.out.println("----- Event Listesi -----");
        for (Event comic : comics) {
            System.out.println("Event ID: " + comic.geteventID());
            System.out.println("Başlık: " + comic.getTitle());
            System.out.println("İçerik: " + comic.getContent());
            System.out.println("-------------------------");
        }
    }
    
    public void listEventsByCondition(String condition) {
    	
    	this.comics = readUsersFromFile();
    	
        if (comics.isEmpty()) {
            System.out.println("Listelenecek Event bulunamadı.");
            return;
        }

        System.out.println("----- " + condition + " Durumundaki Eventlar -----");
        for (Event comic : comics) {
            if (comic.getUser().equalsIgnoreCase(condition)) {
                System.out.println("Event ID: " + comic.geteventID());
                System.out.println("Başlık: " + comic.getTitle());
                System.out.println("İçerik " + comic.getContent());
                System.out.println("User: " + comic.getUser());
                System.out.println("-------------------------");
            }
        }
    }
    
    public void deleteEventByID(int EventID) {
        boolean found = false;
        for (Event comic : comics) {
            if (comic.geteventID() == EventID) {
                comics.remove(comic);
                saveUsersToFile(comics);
                System.out.println("Event başarıyla silindi.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Event ID'si ile eşleşen Event bulunamadı.");
        }
    }
    
    public void updateEventTitleByID(int EventID, String newTitle, String newContent) {
        boolean found = false;
        for (Event comic : comics) {
            if (comic.geteventID() == EventID) {
            	comic.setContent(newContent);
                comic.setTitle(newTitle);
                saveUsersToFile(comics);
                System.out.println("Event başlığı başarıyla güncellendi.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Event ID'si ile eşleşen Event bulunamadı.");
        }
    }

    
    public boolean isEventNameAvailable(String title) {
        for (Event comic : comics) {
            if (comic.getTitle().equals(title)) {
                return false; // Event adı daha önce kullanılmış
            }
        }
        return true; // Event adı kullanılabilir
    }
    
    public boolean isEventIDAvailable(int ID) {
        for (Event comic : comics) {
            if (comic.geteventID() == ID) {
                return false; 
            }
        }
        return true; 
    }
    
    public String getEventTitleByID(int EventID) {
        for (Event comic : comics) {
            if (comic.geteventID() == EventID) {
                return comic.getTitle();
            }
        }
        return null; 
    }


    private List<Event> readUsersFromFile() 
    {
        File file = new File(Event_FILE_PATH);
        if (!file.exists()) {
            System.out.println("Dosya bulunamadi, yeni bir dosya olusturuluyor.");
            // Dosya yoksa, dosyayı oluştur ve boş bir Event listesi oluştur
            try {
                if (file.createNewFile()) {
                    System.out.println("Events.dat dosyasi olusturuldu.");
                } else {
                    System.out.println("Events.dat dosyasi zaten var.");
                }
            } catch (IOException e) {
                System.out.println("Dosya olusturma hatasi: " + e.getMessage());
            }
            return new ArrayList<>();
        }
        
        try (FileInputStream fileIn = new FileInputStream(Event_FILE_PATH);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            return (List<Event>) objectIn.readObject();
        } 
        catch (IOException | ClassNotFoundException e) 
        {
            System.out.println("Dosya okuma hatasi: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    private void saveUsersToFile(List<Event> comics2) {
        try (FileOutputStream fileOut = new FileOutputStream(Event_FILE_PATH);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(comics2);
            System.out.println("Event basariyla dosyaya kaydedildi.");
        } catch (IOException e) {
            System.out.println("Dosya yazma hatasi: " + e.getMessage());
        }
    }


}
