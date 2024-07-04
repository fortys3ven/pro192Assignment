package model;
import model.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Bank {
    private ArrayList<Customer> customerList;

    public Bank() {
        this.customerList = new ArrayList<>();
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }
    
   
    public void addNewCustomer(Customer newCustomer){
        customerList.add(newCustomer);
        System.out.println("Added successfully.");
    }
    
    public void deleteCustomer(String userName){
        boolean check = customerList.removeIf(customer -> customer.getUsername().equals(userName));
        if(check){
            System.out.println("Deleted successfully.");
        } else {
            System.out.println("The customer not found with the given user name.");
        }
    }
    
    public void displayCustomeList(ArrayList<Customer> customerList){
        customerList.sort(Comparator.comparing(Customer :: getFullName));
        System.out.println("|%-7s|%-15s|%-11s|%-11s|%-20s|%-11s|");
    }
    public ArrayList<Customer> searchCustomerByUserName(String userName){
        ArrayList<Customer> UserNameList = new ArrayList<>();
        for(Customer cus : customerList){
            if(cus.getUsername().equals(userName)){
                UserNameList.add(cus);
            }
        }
        return UserNameList;
    }
    
    public ArrayList<Customer> searchCustomerByCCCD(String userName){
        ArrayList<Customer> cccdList = new ArrayList<>();
        for(Customer cus : customerList){
            if(cus.getUsername().equals(userName)){
                cccdList.add(cus);
            }
        }
        return cccdList;
    }
    
    public ArrayList<Customer> searchCustomerByName(String userName){
        ArrayList<Customer> sameNameList = new ArrayList<>();
        for(Customer cus : customerList){
            if(cus.getUsername().contains(userName)){
                sameNameList.add(cus);
            }
        }
        return sameNameList;
    }

    public Customer getCustomerByUserName(String recipientUsername) {
        for(Customer cus : customerList){
            if(cus.getUsername().equals(recipientUsername)){
                return cus;
            }
        }
        return null;
    }
}