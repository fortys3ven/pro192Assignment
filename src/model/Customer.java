package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Customer {


    private String username;
    private String password;
    private String fullName;
    private LocalDate dob;
    private String phone;
    private String mail;
    private String cccd;
    private String STK;
    private Double soDuTaiKhoan;

    public Customer( String username, String password, String fullName, String dob, String phone, String mail, String cccd, String STK, Double soDuTaiKhoan) {

        this.username = username;
        this.password = password;
        this.fullName = fullName;
        setDobByString(dob);
        this.phone = phone;
        this.mail = mail;
        this.cccd = cccd;
        this.STK = STK;
        this.soDuTaiKhoan = soDuTaiKhoan;
    }

    public final void setDobByString(String dob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate dobi = LocalDate.parse(dob);
            if (Period.between(dobi, LocalDate.now()).getYears() >= 16) {
                this.dob = dobi;
            } else {
                System.out.println("Customer must be at least 16 years old.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
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

    public void setPassword(String password) {
        if (isValidPassword(password)) {
//            this.password = hashPassword(password);
            this.password = password;
        } else {
            throw new IllegalArgumentException("Password must have 8 characters, uppercase letters, numbers and special characters");
        }
    }


    public void setPhone(String phone) {
        if (isValidPhone(phone)) {
            this.phone = phone;
        } else {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }

    public void setMail(String mail) {
        if (isValidMail(mail)) {
            this.mail = mail;
        } else {
            throw new IllegalArgumentException("Invalid mail");
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public void setSTK(String STK) {
        this.STK = STK;
    }

    public void setSoDuTaiKhoan(Double soDuTaiKhoan) {
        this.soDuTaiKhoan = soDuTaiKhoan;
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
        return cccd;
    }

    public String getSTK() {
        return STK;
    }

    public Double getSoDuTaiKhoan() {
        return soDuTaiKhoan;
    }

//    private String hashPassword(String password) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            byte[] hash = md.digest(password.getBytes());
//            StringBuilder hexString = new StringBuilder(2 * hash.length);
//            for (byte b : hash) {
//                String hex = Integer.toHexString(0xff & b);
//                if (hex.length() == 1) {
//                    hexString.append('0');
//                }
//                hexString.append(hex);
//            }
//            return hexString.toString();
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void display() {
        System.out.println("Full name: " + fullName);
        System.out.println("So tai khoan: " + STK);
        System.out.println("So du: " + soDuTaiKhoan);
    }
}
