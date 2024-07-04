/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import model.*;
import View.Menu;

/**
 *
 * @author NhatNS
 */
public class CustomerApp extends Menu {

    private Customer customer;
    private Bank bankManagement;
    static String title = "Customer App";
    static String[] listOfChoices = {"Account Details", "Money Transfer", "Change Email", "Change Phone Number", "Change Password"};

    public CustomerApp(Customer customer, Bank bankManagement) {
        super(title, listOfChoices);
        this.customer = customer;
        this.bankManagement = bankManagement;
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 -> {
                displayAccountDetails();
            }
            case 2 -> {
                try{
                transferMoney();
                } catch (NumberFormatException e){
                    System.out.println("Invalid value.");
                }
            }
            case 3 -> {
                changeEmail();
            }
            case 4 -> {
                changePhoneNumber();
            }
            case 5 -> {
                changePassword();
            }
            default -> {
                System.out.println("Invalid choose");
            }
        }
    }

    private void displayAccountDetails() {
        System.out.println(String.format(
                "%-13s|%-15s|%-11s|%-12s",
                "CustomerID", "Name", "Account Number", "Account Balance"
        ));
        System.out.printf("%-13s|%-15s|%-11s|%-12f", customer.getCccd(), customer.getFullName(), customer.getSTK(),
                customer.getSoDuTaiKhoan());
    }

    private void transferMoney() {
    Customer recipient = null;
    String recipientAccountNumber = Utils.getValue("Enter recipient's account number: ");
    
    for (Customer cus : bankManagement.getCustomerList()) {
        if (cus.getSTK().equals(recipientAccountNumber)) {
            recipient = cus;
            break;
        }
    }

    if (recipient != null) {
        try {
            double amount = Double.parseDouble(Utils.getValue("Enter amount to transfer: "));
            if (amount <= 0) {
                System.out.println("Amount must be greater than zero.");
                return;
            }

            if (customer.getSoDuTaiKhoan() >= amount) {
                customer.setSoDuTaiKhoan(customer.getSoDuTaiKhoan() - amount);
                recipient.setSoDuTaiKhoan(recipient.getSoDuTaiKhoan() + amount);
                System.out.println("Transfer successful.");
            } else {
                System.out.println("Insufficient funds.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered. Please enter a numeric value.");
        }
    } else {
        System.out.println("Customer not found by the given account number.");
    }
}


    private void changeEmail() {
        String newEmail = Utils.getValue("Enter new email: ");
        customer.setMail(newEmail);
        System.out.println("Email updated successfully.");
    }

    private void changePhoneNumber() {
        String newPhoneNumber = Utils.getValue("Enter new phone number: ");
        customer.setPhone(newPhoneNumber);
        System.out.println("Phone number updated successfully.");
    }

    private void changePassword() {
        String currentPassword = Utils.getValue("Enter current password: ");
//        if (customer.getPassword().equals(customer.hashPassword(currentPassword))) {
        if (customer.getPassword().equals(currentPassword)) {
            String newPassword = Utils.getValue("Enter new password: ");
            customer.setPassword(newPassword);
            System.out.println("Password updated successfully.");
        } else {
            System.out.println("Incorrect current password.");
        }
    }
}
