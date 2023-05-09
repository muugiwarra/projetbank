

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Account {
    private int id;
    private String accountType;
    private double balance;
    private Client client;
    private static List<Account> accountList = new ArrayList<>();

    public Account(int id, String accountType, double balance, Client client) {
        this.id = id;
        this.accountType = accountType;
        this.balance = balance;
        this.client = client;
        accountList.add(this);
    }

    public Account(int accountId, String accountType, double balance) {
    }


    public int getId() {
        return id;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public static List<Account> getAccountList() {
        return accountList;
    }




    public static void removeAccount(Account account) {
        accountList.remove(account);
    }

    public static Account findAccount(int accountId) {
        for (Account account : accountList) {
            if (account.getId() == accountId) {
                return account;
            }
        }
        return null;
    }

    public static List<Account> getAccountsByClient(Client client) {
        List<Account> accounts = new ArrayList<>();
        for (Account account : accountList) {
            if (account.getClient() == client) {
                accounts.add(account);
            }
        }
        return accounts;
    }



    public String getType() {
        return this.accountType;
    }


    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. Current balance is: " + balance);
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. Current balance is: " + balance);
        } else {
            System.out.println("Invalid amount.");
        }
    }

}
