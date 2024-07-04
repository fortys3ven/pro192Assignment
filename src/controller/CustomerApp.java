package controller;

import model.Customer;
import model.Bank;
import view.Menu;

import java.util.Scanner;

public class CustomerApp extends Menu {

    private Customer customer;
    private Bank bank;
    private Scanner sc;

    public CustomerApp() {
        super("Customer Menu", new String[]{
                "Check Balance",
                "Transfer Money",
                "Change Email",
                "Change Phone Number",
                "Change Password"
        });
        sc = new Scanner(System.in);
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1:
                checkBalance();
                break;
            case 2:
                transferMoney();
                break;
            case 3:
                changeEmail();
                break;
            case 4:
                changePhoneNumber();
                break;
            case 5:
                changePassword();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private void checkBalance() {
        System.out.println("Your current balance is: " + customer.getSoDuTaiKhoan());
    }

    private void transferMoney() {
        System.out.print("Enter recipient's username: ");
        String recipientUsername = sc.nextLine();
        System.out.print("Enter amount to transfer: ");
        double amount = sc.nextDouble();
        sc.nextLine(); // Consume newline character

        Customer recipient = bank.getCustomerByUserName(recipientUsername);
        if (recipient != null) {
            if (customer.getSoDuTaiKhoan() >= amount) {
                customer.setSoDuTaiKhoan(customer.getSoDuTaiKhoan() - amount);
                recipient.setSoDuTaiKhoan(recipient.getSoDuTaiKhoan() + amount);
                System.out.println("Transfer successful.");
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Recipient not found.");
        }
    }

    private void changeEmail() {
        System.out.print("Enter new email: ");
        String newEmail = sc.nextLine();
        customer.setMail(newEmail);
        System.out.println("Email updated successfully.");
    }

    private void changePhoneNumber() {
        System.out.print("Enter new phone number: ");
        String newPhoneNumber = sc.nextLine();
        customer.setPhone(newPhoneNumber);
        System.out.println("Phone number updated successfully.");
    }

    private void changePassword() {
        System.out.print("Enter current password: ");
        String currentPassword = sc.nextLine();
//        if (customer.getPassword().equals(customer.hashPassword(currentPassword))) {
        if (customer.getPassword().equals(currentPassword)) {
            System.out.print("Enter new password: ");
            String newPassword = sc.nextLine();
            customer.setPassword(newPassword);
            System.out.println("Password updated successfully.");
        } else {
            System.out.println("Incorrect current password.");
        }
    }

    public static void main(String[] args) {
        Bank bank = new Bank();

        CustomerApp app = new CustomerApp();
        app.run();
    }
}
