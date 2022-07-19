package peaksoft;

import peaksoft.model.User;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserServiceImpl();
        while (true) {
            System.out.println("\n1.Create table: Users.\n" +
                    "2.Delete table: Users.\n" +
                    "3.Add data.\n" +
                    "4.Delete data by ID.\n" +
                    "5.Display all data.\n" +
                    "6.Clean table.");
            switch (scanner.nextInt()) {
                case 1 -> userService.createUsersTable();
                case 2 -> userService.dropUsersTable();
                case 3 -> userService.saveUser("Arlen", "Kubov", (byte) 21);
                case 4 -> userService.removeUserById(1);
                case 5 -> userService.getAllUsers();
                case 6 -> userService.cleanUsersTable();
            }
        }
    }

}
//empService.getAllEmployee().forEach(System.out::println);


