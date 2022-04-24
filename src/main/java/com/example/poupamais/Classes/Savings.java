package com.example.poupamais.Classes;

public class Savings {

    private String month;
    private String mark;
    private String balance;
    private String fees;
    private String reminder;

    public Savings(String mark, String balance, String fees, String reminder, String month) {
        this.mark = mark;
        this.balance = balance;
        this.fees = fees;
        this.reminder = reminder;
        this.month = month;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }
}

