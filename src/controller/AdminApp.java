package controller;

import model.*;
import view.Menu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminApp extends Menu {

    private Bank Bank;


    ArrayList<Customer> customerList = Bank.getCustomerList();
    public AdminApp() {
        super("Admin Menu", new String[]{
                "View All Customers",
                "Add Customer",
                "Remove Customer",
                "Search Customer",
                "View Transaction History"
        });
        Bank = new Bank();
    }

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        AdminApp app = new AdminApp();
    }

    public boolean login() {
        System.out.println("usename: ");
        String username = sc.nextLine();
        System.out.println("pass: ");
        String pass = sc.nextLine();
        if (username == "admin" && pass == "admin") {
            return true;
        }
        return false;
    }

    @Override
    public void execute(int ch) {
        switch (ch) {
            case 1:
                displayAllCustomer();
                break;
            case 2:
                addCustomer();
                break;
            case 3:
                removeCustomer();
                break;
            case 4:
                searchCustomer();
                break;
            case 5:
//                viewTransaction();
                break;
            default:
                System.out.println("invalid choices");
                break;
        }
        if (login()) {
            run();
        } else {
            System.out.println("wrong username or password!");
        }
    }

    private void displayAllCustomer() {
        Bank.displayCustomeList(customerList);
    }

    private void addCustomer() {
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
        System.out.println("Blance: ");
        Double soDuTaiKhoan = sc.nextDouble();
        Customer customer = new Customer(username, password, fullname, phone, mail, cccd, stk, dob, soDuTaiKhoan);
        Bank.addNewCustomer(customer);
    }

    private void removeCustomer() {
        System.out.print("Enter customer username to remove: ");
        String username = sc.nextLine();
        Bank.deleteCustomer(username);
    }

    private void searchCustomer() {
        System.out.print("Enter to search: 1)By Username, 2)Byname, 3)By CCCD: ");
        int i = sc.nextInt();
        System.out.println("Enter name to search: ");
        String name = sc.nextLine();
        switch (i) {
            case 1:
                System.out.println(Bank.searchCustomerByUserName(name));
                break;
            case 2:
                System.out.println(Bank.searchCustomerByName(name));
                break;
            case 3:
                System.out.println(Bank.searchCustomerByCCCD(name));
                break;
        }
    }

}
