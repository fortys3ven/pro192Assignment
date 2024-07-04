/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import View.Menu;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;

/**
 *
 * @author NhatNS
 */
public class AdminApp extends Menu {

    Scanner sc = new Scanner(System.in);
    private Bank bankManagement;

    ArrayList<Customer> customerList;

    static String title = "Admin App";
    static String[] listOfChoices = {"View All Customers", "Add Customer", "Remove Customer", "Search Customer", "View Transaction History"};

    public AdminApp(Bank bankManagement) {
        super(title, listOfChoices);
        this.bankManagement = bankManagement;
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 ->
                displayAllCustomer();
            case 2 -> {
                try {
                    addCustomer();
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            case 3 ->
                removeCustomer();
            case 4 ->
                searchCustomer();
            case 5 -> {
                // viewTransaction();

            }
            default ->
                System.out.println("invalid choices");
        }
    }

    private void displayAllCustomer() {
        bankManagement.displayCustomeList(customerList);
    }

    private void addCustomer() throws IllegalArgumentException {
        String userName = Utils.getValue("Enter user-name: ");
        while (true) {
            boolean check = false;
            for (Customer cus : bankManagement.getCustomerList()) {
                if (userName.equals(cus.getUsername())) {
                    check = true;
                    break;
                }
            }
            if (check) {
                System.out.println("The user-name of customer must be unique.");
                userName = Utils.getValue("Enter user-name: ");
            } else {
                break;
            }
        }
        String passWord = Utils.getValue("Enter pass-word (password include 8 digit, at least 1 Upper-case, number and special character): ");
        String name = Utils.getValue("Enter full-name: ");
        String dobs = Utils.getValue("Enter date of birth(dd/MM/yyyy): ");
        String phone = Utils.getValue("Enter phone(the phone must be 9 or 10 digit): ");
        String mail = Utils.getValue("Enter mail: ");
        String id = Utils.getValue("Enter id: ");
        while (true) {
            boolean check = false;
            for (Customer cus : bankManagement.getCustomerList()) {
                if (id.equals(cus.getCccd())) {
                    check = true;
                    break;
                }
            }
            if (check) {
                System.out.println("The idof customer must be unique.");
                id = Utils.getValue("Enter id: ");
            } else {
                break;
            }
        }
        String stk = Utils.getValue("Enter Stk: ");
        while (true) {
            boolean check = false;
            for (Customer cus : bankManagement.getCustomerList()) {
                if (stk.equals(cus.getSTK())) {
                    check = true;
                    break;
                }
            }
            if (check) {
                System.out.println("The stk of customer must be unique.");
                stk = Utils.getValue("Enter stk: ");
            } else {
                break;
            }
        }

        bankManagement.addNewCustomer(new Customer(userName, passWord, name, dobs, phone, mail, id, stk, stk));
    }

    private void removeCustomer() {
        String userName = Utils.getValue("Enter customer username to remove: ");
        bankManagement.deleteCustomer(userName);
    }

    private void searchCustomer() {
        String[] searchMenuchoice = {"Search by user-name", "Search by name", "Search by ID", "Back to main menu"};
        Menu searchMenu = new Menu("Search Menu", searchMenuchoice) {
            @Override
            public void execute(int choice) {
                switch (choice) {
                    case 1:
                        String userName = Utils.getValue("Enter user-name: ");
                        bankManagement.displayCustomeList(bankManagement.searchCustomerByUserName(userName));
                        break;
                    case 2:
                        String name = Utils.getValue("Enter name: ");
                        bankManagement.displayCustomeList(bankManagement.searchCustomerByName(name));
                        break;
                    case 3:
                        String id = Utils.getValue("Enter id: ");
                        bankManagement.displayCustomeList(bankManagement.searchCustomerByCCCD(id));
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter option correctly.");
                        break;
                }
            }
        };
        searchMenu.run();
    }

}
