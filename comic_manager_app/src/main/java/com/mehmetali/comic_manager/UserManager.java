package com.mehmetali.comic_manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users;
    private static final String USER_FILE_PATH = "users.dat";

    public UserManager() {
        this.users = readUsersFromFile();
    }

    public void registerUser(User user) {
        // Kullanıcı adı kontrolü
        if (!isUsernameAvailable(user.getUsername())) {
            System.out.println("Bu kullanici adi zaten kullaniliyor. Lutfen farkli bir kullanici adi secin.");
            return;
        }
        
        users.add(user);
        saveUsersToFile(users);
        System.out.println("Kullanici basariyla kaydedildi.");
    }

    public boolean isUsernameAvailable(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false; // Kullanıcı adı daha önce kullanılmış
            }
        }
        return true; // Kullanıcı adı kullanılabilir
    }

    private List<User> readUsersFromFile() {
        File file = new File(USER_FILE_PATH);
        if (!file.exists()) {
            System.out.println("Dosya bulunamadi, yeni bir dosya olusturuluyor.");
            // Dosya yoksa, dosyayı oluştur ve boş bir kullanıcı listesi oluştur
            try {
                if (file.createNewFile()) {
                    System.out.println("users.dat dosyasi olusturuldu.");
                } else {
                    System.out.println("users.dat dosyasi zaten var.");
                }
            } catch (IOException e) {
                System.out.println("Dosya olusturma hatasi: " + e.getMessage());
            }
            return new ArrayList<>();
        }
        
        try (FileInputStream fileIn = new FileInputStream(USER_FILE_PATH);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            return (List<User>) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Dosya okuma hatasi: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    private void saveUsersToFile(List<User> userList) {
        try (FileOutputStream fileOut = new FileOutputStream(USER_FILE_PATH);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(userList);
            System.out.println("Kullanicilar basariyla dosyaya kaydedildi.");
        } catch (IOException e) {
            System.out.println("Dosya yazma hatasi: " + e.getMessage());
        }
    }


    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user; // Kullanıcı adı ve şifre doğruysa kullanıcıyı döndür
            }
        }
        return null; // Kullanıcı adı veya şifre hatalıysa null döndür
    }
}
