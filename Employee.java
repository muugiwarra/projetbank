

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class Employee {
    private static String username;
    private static String password;
    private static String name;
    private static String post;
    private static int age;

    public Employee(String username, String password,int age,String post,String name) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.name = name;
        this.post = post;

    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    // Add a new client to the database



    // Delete a client from the database
    public void deleteClient(int accountNumber) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("database.txt"));
            boolean clientDeleted = false;
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.contains(String.valueOf(accountNumber))) {
                    lines.remove(i);
                    clientDeleted = true;
                    break;
                }
            }
            if (clientDeleted) {
                Files.write(Paths.get("database.txt"), lines);
                System.out.println("Client deleted successfully");
            } else {
                System.out.println("Client not found");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the client");
        }
    }

    // Modify a client's details in the database
    public void modifyClient(int accountNumber, String name, String address, String phoneNumber, String email) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("database.txt"));
            boolean clientModified = false;
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.contains(String.valueOf(accountNumber))) {
                    String newLine = name + "," + address + "," + phoneNumber + "," + email + "," + line.split(",")[4] + "," + accountNumber;
                    lines.set(i, newLine);
                    clientModified = true;
                    break;
                }
            }
            if (clientModified) {
                Files.write(Paths.get("database.txt"), lines);
                System.out.println("Client modified successfully");
            } else {
                System.out.println("Client not found");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while modifying the client");
        }
    }

    // Add a new account to a client's profile in the database
    // Add a new account to a client's profile in the database
    public void addAccount(int accountNumber, double balance) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("database.txt"));
            boolean accountAdded = false;
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.contains(String.valueOf(accountNumber))) {
                    String newLine = line + "," + balance;
                    lines.set(i, newLine);
                    accountAdded = true;
                    break;
                }
            }
            if (accountAdded) {
                Files.write(Paths.get("database.txt"), lines);
                System.out.println("Account added successfully");
            } else {
                System.out.println("Client not found");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while adding the account");
        }
    }
}