/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
import java.time.LocalDate;
public class Transaction {
    private LocalDate date;
    private String type;
    private double amount;
    private String details;

    public Transaction(LocalDate date, String type, double amount, String details) {
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.details = details;
    }

    public LocalDate getDate() {
        return date;
    }


    public String getType() {
        return type;
    }


    public double getAmount() {
        return amount;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "Transaction{" + "date=" + date + ", type=" + type + ", amount=" + amount + ", details=" + details + '}';
    }
    
}
