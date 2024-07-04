/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Scanner;
import model.*;
import view.Menu;

/**
 *
 * @author NhatNS
 */
public class MainScreen extends Menu {

    private static Bank bankManagement = new Bank();
    private Scanner sc = new Scanner(System.in);

    static String title = "Main Screen";
    static String[] listOfChoices = {"Create Account", "Login"};

    public MainScreen() {
        super(title, listOfChoices);
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 -> {
                createAccount();
            }
            case 2 -> {
                login();
            }
            default -> {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void createAccount() {
        System.out.println("Enter customer details:");
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.print("Full Name: ");
        String fullname = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Mail: ");
        String mail = sc.nextLine();
        System.out.print("CCCD: ");
        String cccd = sc.nextLine();
        System.out.print("STK: ");
        String stk = sc.nextLine();
        System.out.print("Date of Birth (dd/MM/yyyy): ");
        String dob = sc.nextLine();
        System.out.print("Balance: ");
        double balance = sc.nextDouble();
        sc.nextLine();  // Consume newline left-over

        Customer customer = new Customer(username, password, fullname, phone, mail, cccd, stk, dob, balance);
        bankManagement.addNewCustomer(customer);

        System.out.println("Account created successfully.");
    }

    private void login() {
        Menu loginMenu = new Menu("Select Mode", new String[]{"Admin", "Standard"}) {
            @Override
            public void execute(int choice) {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter admin username: ");
                        String username = sc.nextLine();
                        System.out.print("Enter admin password: ");
                        String password = sc.nextLine();

                        Admin admin = bankManagement.getAdminByUserName(username);
                        if (admin != null && admin.getPassword().equals(password)) {
                            AdminApp adminApp = new AdminApp(admin);
                            adminApp.run();
                        } else {
                            System.out.println("Invalid admin username or password.");
                        }
                    }
                    case 2 -> {
                        System.out.print("Enter customer username: ");
                        String username = sc.nextLine();
                        System.out.print("Enter customer password: ");
                        String password = sc.nextLine();

                        Customer customer = bankManagement.getCustomerByUserName(username);
                        if (customer != null && customer.getPassword().equals(password)) {
                            CustomerApp customerApp = new CustomerApp(customer);
                            customerApp.run();
                        } else {
                            System.out.println("Invalid customer username or password.");
                        }
                    }
                    default ->
                        System.out.println("Invalid choice.");
                }
            }
        };
        loginMenu.run();
    }

    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.run();
    }
}
