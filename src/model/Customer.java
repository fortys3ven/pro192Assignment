package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Customer {

    private String username;
    private String password;
    private String fullName;
    private LocalDate dob;
    private String phone;
    private String mail;
    private String id;
    private String numberAccount;
    private Double balance;
    private ArrayList<Transaction> transactions;
    
    public Customer(){};

    public Customer(String username, String password, String fullName, String dob, String phone, String mail, String cccd, String STK, String soDuTaiKhoan)throws IllegalArgumentException {

        this.username = username;
        setPassword(password);
        this.fullName = fullName;
        setDobByString(dob);
        setPhone(phone);
        setMail(mail);
        this.id = cccd;
        setNumberAccount(STK);
        setSoduTaiKhoanStr(soDuTaiKhoan);
        this.transactions = new ArrayList<>();
    }

    public final void setDobByString(String dob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate dobi = LocalDate.parse(dob, formatter);
            if (Period.between(dobi, LocalDate.now()).getYears() >= 16) {
                this.dob = dobi;
            } else {
                System.out.println("The old of customer must be greater 16.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid format. The date of birth of customer need to use the form(dd/MM/yyyy).");
        }
    }

    public boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        String upperCasePattern = ".*[A-Z].*";
        String digitPattern = ".*[0-9].*";
        String specialCharPattern = ".*[^a-zA-Z0-9].*";

        boolean hasUpperCase = password.matches(upperCasePattern);
        boolean hasDigit = password.matches(digitPattern);
        boolean hasSpecialChar = password.matches(specialCharPattern);

        return hasUpperCase && hasDigit && hasSpecialChar;
    }

    public boolean isValidMail(String email) {
        String emailPattern = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        return email != null && email.matches(emailPattern);
    }

    public boolean isValidPhone(String phone) {
        String regex = "^(\\d{9}|\\d{10})$";
        return phone != null && phone.matches(regex);
    }

    public void setPassword(String password) throws IllegalArgumentException{
        if (isValidPassword(password)) {
//            this.password = hashPassword(password);
            this.password = password;
        } else {
            throw new IllegalArgumentException("Password must have 8 characters, uppercase letters, numbers and special characters");
        }
    }

    public void setPhone(String phone) throws IllegalArgumentException {
        if (isValidPhone(phone)) {
            this.phone = phone;
        } else {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }

    public void setMail(String mail) throws IllegalArgumentException{
        if (isValidMail(mail)) {
            this.mail = mail;
        } else {
            throw new IllegalArgumentException("Invalid mail");
        }
    }

    public boolean isValidNumberAccount(String numberAccount){
        String regex = "^SE\\d{5}$";
        return numberAccount != null && numberAccount.matches(regex);
    }

    public void setNumberAccount(String numberAccount){
        if(isValidNumberAccount(numberAccount)){
            this.numberAccount = numberAccount;
        }else 
            throw new IllegalArgumentException("Number Account must follow format SE00000");
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setCccd(String cccd) {
        this.id = cccd;
    }

    public void setSTK(String STK) {
        this.numberAccount = STK;
    }

    public void setSoDuTaiKhoan(Double soDuTaiKhoan) {
        this.balance = soDuTaiKhoan;
    }
    
    public void setSoduTaiKhoanStr(String sodutaikhoan) throws IllegalArgumentException{
        try{
            double sodu = Double.parseDouble(sodutaikhoan);
            if(sodu < 0){
                throw new IllegalArgumentException("Balance must be greater or equal 0.");
            } else {
                this.balance = sodu;
            }
        } catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Balance must be greater or equal 0.");
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }
    
    public String getFirstName(){
        String[] nameParts = fullName.split(" ");
        return nameParts[nameParts.length - 1];
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public String getCccd() {
        return id;
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public Double getSoDuTaiKhoan() {
        return balance;
    }

    public ArrayList<Transaction> getTransaction(){
        return this.transactions;
    }
    
    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }
    @Override
    public String toString() {
        return String.format("|%-10s|%-15s|%-11s|%-13s|%-20s|%-11s|\n", username, fullName,numberAccount, id, mail, phone);
    }      
}
