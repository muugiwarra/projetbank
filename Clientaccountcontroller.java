import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class Clientaccountcontroller {
    private String balance;
    private String transactions;
    private String clientID;


    @FXML
    private Label balanceid;

    @FXML
    private Label transactionid;
    @FXML
    private Button homeidd;
    @FXML
    private Button withdrawid;
    @FXML
    private Button depositid;
    @FXML
    private Button transferid;



    public void setBalance(String balance) {
        this.balance = balance;
    }


    public void setTransactions(String transactions) {
        this.transactions = transactions;
    }

    public void updateLabels() {
        balanceid.setText(balance);
        transactionid.setText(transactions);
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientID() {
        return clientID;
    }

    // Withdraw method

    // Withdraw method
    @FXML
    void withdraw(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Withdraw");
        dialog.setHeaderText("Enter amount to withdraw");
        dialog.setContentText("Amount:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String amount = result.get();
            double currentBalance = Double.parseDouble(balanceid.getText());
            double withdrawAmount = Double.parseDouble(amount);
            if (currentBalance < withdrawAmount) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Insufficient Funds");
                alert.setHeaderText(null);
                alert.setContentText("You do not have sufficient funds to withdraw that amount.");
                alert.showAndWait();
                return;
            }
            double newBalance = currentBalance - withdrawAmount;
            balanceid.setText(String.valueOf(newBalance));
            transactionid.setText(String.valueOf(withdrawAmount));
            // Update the accounts.txt file with the new balance and last transaction
            String accountNumber = getAccountNumber(getClientID());
            try {
                BufferedReader reader = new BufferedReader(new FileReader("c:/accounts.txt"));
                List<String> lines = new ArrayList<>();
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] fields = row.split(",");
                    if (fields[0].trim().equals(accountNumber)) {
                        // update the balance and last withdraw fields
                        fields[1] = Double.toString(newBalance);
                        fields[2] = amount;
                        row = String.join(",", fields);
                    }
                    lines.add(row);
                }
                reader.close();

                BufferedWriter writer = new BufferedWriter(new FileWriter("c:/accounts.txt"));
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
                writer.flush(); // flush changes to the file
                writer.close(); // close the writer

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private String getAccountNumber(String clientID) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("c:/clients.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(":");
                if (fields[0].trim().equalsIgnoreCase(clientID)) {
                    String[] clientInfo = fields[1].split(",");
                    reader.close();
                    return clientInfo[4].trim();
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // client ID not found
    }






    // Deposit method
    @FXML
    void deposit() throws IOException {
        // get the account number for the client
        String accountNumber = getAccountNumber(getClientID());

        // prompt the user to enter the amount to be deposited
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Deposit");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter the amount to deposit:");
        Optional<String> result = dialog.showAndWait();

        // process the user's input
        double amount = 0;
        if (result.isPresent()) {
            try {
                amount = Double.parseDouble(result.get());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid amount entered.");
                alert.showAndWait();
                return;
            }
        } else {
            return;
        }

        // read the account data into memory
        BufferedReader accountsReader = new BufferedReader(new FileReader("c:/accounts.txt"));
        List<String> accountLines = new ArrayList<>();
        String accountLine;
        while ((accountLine = accountsReader.readLine()) != null) {
            accountLines.add(accountLine);
        }
        accountsReader.close();

        // find the line corresponding to the account number
        int accountIndex = -1;
        for (int i = 0; i < accountLines.size(); i++) {
            String[] fields = accountLines.get(i).split(",");
            if (fields[0].trim().equalsIgnoreCase(accountNumber.trim())) {
                accountIndex = i;
                break;
            }
        }

        // update the account balance and last transaction in memory
        if (accountIndex != -1) {
            String[] fields = accountLines.get(accountIndex).split(",");
            double balance = Double.parseDouble(fields[1].trim());
            double newBalance = balance + amount;
            fields[1] = Double.toString(newBalance).trim();
            fields[2] = Double.toString(amount).trim();
            accountLines.set(accountIndex, String.join(",", fields));

            // update the balance label
            balanceid.setText(Double.toString(newBalance));

            // write the updated account data back to the file
            BufferedWriter accountsWriter = new BufferedWriter(new FileWriter("c:/accounts.txt"));
            for (String accountLine2 : accountLines) {
                accountsWriter.write(accountLine2 + "\n");
            }
            accountsWriter.close();


        }
    }







    public void transfer(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Transfer");
        dialog.setHeaderText("Enter recipient's ID");
        dialog.setContentText("ID:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String recipientID = result.get();
            double transferAmount = 0;

            TextInputDialog amountDialog = new TextInputDialog();
            amountDialog.setTitle("Transfer");
            amountDialog.setHeaderText("Enter transfer amount");
            amountDialog.setContentText("Amount:");

            Optional<String> amountResult = amountDialog.showAndWait();
            if (amountResult.isPresent()) {
                String amountString = amountResult.get();
                if (!amountString.isEmpty()) {
                    transferAmount = Double.parseDouble(amountString);
                }
            }

            if (transferAmount <= 0) {
                // Invalid transfer amount
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Transfer");
                alert.setHeaderText("Invalid transfer amount");
                alert.setContentText("Please enter a transfer amount greater than zero.");
                alert.showAndWait();
                return;
            }

            // Search for recipient's account number
            String recipientAccountNumber = null;
            try {
                BufferedReader reader = new BufferedReader(new FileReader("c:/accounts.txt"));
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] fields = row.split(",");
                    if (fields[0].trim().equals(recipientID.trim())) {
                        recipientAccountNumber = fields[0].trim();
                        break;
                    }

                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (recipientAccountNumber == null) {
                // Recipient ID not found
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Transfer");
                alert.setHeaderText("Recipient ID not found");
                alert.setContentText("No account found with the specified ID.");
                alert.showAndWait();
            } else if (transferAmount > Double.parseDouble(balanceid.getText())) {
                // Insufficient funds
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Transfer");
                alert.setHeaderText("Insufficient funds");
                alert.setContentText("The transfer amount is greater than your current balance.");
                alert.showAndWait();
            } else {
                // Update sender's balance
                double senderCurrentBalance = Double.parseDouble(balanceid.getText());
                double senderNewBalance = senderCurrentBalance - transferAmount;
                balanceid.setText(String.valueOf(senderNewBalance));

                // Update recipient's balance
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("c:/accounts.txt"));
                    List<String> lines = new ArrayList<>();
                    String row;
                    while ((row = reader.readLine()) != null) {
                        String[] fields = row.split(",");
                        if (fields[0].trim().equals(recipientAccountNumber)) {
                            // update the balance and last deposit fields
                            double recipientCurrentBalance = Double.parseDouble(fields[1]);
                            double recipientNewBalance = recipientCurrentBalance + transferAmount;
                            fields[1] = Double.toString(recipientNewBalance);
                            fields[2] = Double.toString(transferAmount);
                            row = String.join(",", fields);
                        }
                        lines.add(row);
                    }
                    reader.close();

                    BufferedWriter writer = new BufferedWriter(new FileWriter("c:/accounts.txt"));
                    for (String line : lines) {
                        writer.write(line);
                        writer.newLine();
                    }
                    writer.flush();//flush changes to the file

                    writer.close(); // close the writer

                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Add transaction to sender's history
                transactionid.setText(String.format("Transfer to %s: %.2f", recipientID, transferAmount));

            }
        }
    }


    public void bckhome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = homeidd.getScene();
        scene.setRoot(root);
    }




}










