package com.mehmetali.comic_manager;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserManager userManager = new UserManager();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Kayit Ol");
            System.out.println("2. Giris Yap");
            System.out.println("3. Cikis");
            System.out.print("Seciminizi yapin: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Boş satırı oku

            switch (choice) {
                case 1:
                    registerMenu();
                    break;
                case 2:
                    loginMenu();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Gecersiz secim. Lutfen tekrar deneyin.");
                    break;
            }
        }
        scanner.close();
    }

    private static void registerMenu() {
        System.out.println("Kayit Ol");
        System.out.print("Kullanici Adi: ");
        String username = scanner.nextLine();
        System.out.print("Sifre: ");
        String password = scanner.nextLine();

        // Kullanıcıyı kaydet
        User newUser = new User(username, password);
        userManager.registerUser(newUser);
        System.out.println("Kayit basarili!");
    }

    private static void loginMenu() {
        System.out.println("Giris Yap");
        System.out.print("Kullanici Adi: ");
        String username = scanner.nextLine();
        System.out.print("Sifre: ");
        String password = scanner.nextLine();

        // Kullanıcıyı kontrol et ve giriş yap
        User user = userManager.login(username, password);
        if (user != null) {
            System.out.println("Hos geldiniz, " + user.getUsername() + "!");
        } else {
            System.out.println("Kullanici adi veya sifre hatali!");
        }
    }
}
