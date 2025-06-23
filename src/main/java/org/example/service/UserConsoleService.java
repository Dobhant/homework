package org.example.service;

import org.example.dao.UserDao;
import org.example.dao.UserDaoImpl;
import org.example.model.User;

import java.util.List;
import java.util.Scanner;

/**
 * Сервис для взаимодействия с пользователем через консоль.
 * Предоставляет CRUD-функциональность: создание, отображение,
 * обновление и удаление пользователей.
 */
public class UserConsoleService {

    /**
     * DAO-объект для доступа к данным пользователей.
     */
    private final UserDao userDao;

    /**
     * Сканер для считывания пользовательского ввода с консоли.
     */
    private final Scanner scanner;

    /**
     * Конструктор по умолчанию.
     * Инициализирует {@link UserDaoImpl} и {@link Scanner}.
     */
    public UserConsoleService() {
        this.userDao = new UserDaoImpl();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Создаёт нового пользователя, считывая данные с консоли.
     * После ввода сохраняет пользователя в базу данных.
     */
    public void createUser() {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        System.out.print("Введите email: ");
        String email = scanner.nextLine();
        System.out.print("Введите возраст: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setAge(age);

        userDao.save(user);
        System.out.println("Создан пользователь с ID: " + user.getId());
    }

    /**
     * Выводит список всех пользователей из базы данных.
     * Если пользователей нет — сообщает об этом.
     */
    public void listUsers() {
        List<User> users = userDao.findAll();
        if (users.isEmpty()) {
            System.out.println("Нет пользователей.");
        } else {
            users.forEach(System.out::println);
        }
    }

    /**
     * Обновляет данные существующего пользователя.
     * Пользователь выбирается по ID, после чего вводятся новые значения.
     * Можно оставить поле пустым, чтобы не изменять его.
     */
    public void updateUser() {
        System.out.print("Введите ID пользователя: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        User user = userDao.findById(id);

        if (user != null) {
            System.out.print("Новое имя (оставьте пустым чтобы не менять): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) user.setName(name);

            System.out.print("Новый email (оставьте пустым чтобы не менять): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) user.setEmail(email);

            System.out.print("Новый возраст (0 — не менять): ");
            int age = scanner.nextInt();
            scanner.nextLine();
            if (age > 0) user.setAge(age);

            userDao.update(user);
            System.out.println("Пользователь обновлён.");
        } else {
            System.out.println("Пользователь с таким ID не найден.");
        }
    }

    /**
     * Удаляет пользователя по указанному ID.
     * Если пользователь существует — удаляет его из базы.
     */
    public void deleteUser() {
        System.out.print("Введите ID пользователя для удаления: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        User user = userDao.findById(id);
        if (user != null) {
            userDao.delete(user);
            System.out.println("Пользователь удалён.");
        } else {
            System.out.println("Пользователь с таким ID не найден.");
        }
    }
}
