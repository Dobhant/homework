package org.example;

import org.example.service.UserConsoleService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserConsoleService service = new UserConsoleService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Добавить пользователя");
            System.out.println("2. Показать всех пользователей");
            System.out.println("3. Обновить пользователя");
            System.out.println("4. Удалить пользователя");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> service.createUser();
                case 2 -> service.listUsers();
                case 3 -> service.updateUser();
                case 4 -> service.deleteUser();
                case 0 -> {
                    System.out.println("Выход...");
                    return;
                }
                default -> System.out.println("Неверный ввод.");
            }
        }
    }
}
