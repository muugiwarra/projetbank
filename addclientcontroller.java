


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class addclientcontroller extends Application {
    @FXML
    private TextField nameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField emailField;



    @FXML
    private Button confirmadd;
    @FXML
    private Button canceladd;


    @FXML
    private void addClient() throws IOException {
        String name = nameField.getText();
        String address = addressField.getText();
        String phoneNumber = phoneField.getText();
        String email = emailField.getText();
        String clientsFile = "c:/clients.txt";
        String accountsFile = "c:/accounts.txt";

        // Get the new client ID
        int id = generateId();

        // Generate a new account number
        String accountNumber = generateAccountNumber();

        // Set the initial balance and last transaction to 0
        double balance = 0.0;
        double lastTransaction = 0.0;


        // Open the clients file to append the new client
        FileWriter fwClients = new FileWriter(clientsFile, true);

        // Write the client data with ID to the file
        fwClients.write(id + " : " + name + "," + address + "," + email + "," + phoneNumber + "," + accountNumber + "\n");

        // Close the clients file
        fwClients.close();

        // Open the accounts file to append the new account
        FileWriter fwAccounts = new FileWriter(accountsFile, true);

        // Write the account data to the file
        fwAccounts.write(accountNumber + " , " + balance + " , " + lastTransaction + "\n");

        // Close the accounts file
        fwAccounts.close();

        // Show confirmation message
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Client added successfully!");
        alert.showAndWait();
    }

    // Method to generate a unique 8-digit account number
    private String generateAccountNumber() throws IOException {
        String accountsFile = "c:/accounts.txt";
        String accountNumber;

        // Generate a random 8-digit account number
        do {
            accountNumber = String.format("%08d", (int) (Math.random() * 100000000));
        } while (accountNumberExists(accountNumber, accountsFile));

        return accountNumber;
    }

    // Method to check if an account number exists in the accounts file
    private boolean accountNumberExists(String accountNumber, String accountsFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(accountsFile));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(" , ");
            String currentAccountNumber = fields[0].trim();

            if (currentAccountNumber.equals(accountNumber)) {
                reader.close();
                return true;
            }
        }

        reader.close();
        return false;
    }


    private int generateId() throws IOException {
        String filename = "c:/clients.txt";
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        int maxId = 0;

        String line;
        while ((line = br.readLine()) != null) {
            String[] fields = line.split(":");
            int id = Integer.parseInt(fields[0].trim());
            if (id > maxId) {
                maxId = id;
            }
        }

        br.close();
        fr.close();
        return maxId + 1;
    }








    @FXML
    void returntoclients(ActionEvent event) throws IOException {
        // Load the client page and set it as the root of the scene
        Parent root = FXMLLoader.load(getClass().getResource("clients.fxml"));
        Scene scene = canceladd.getScene();
        scene.setRoot(root);
    }
    @FXML
    void confirmadd(ActionEvent event) throws IOException {
        // Call addClient function to add client to the text file
        addClient();

        // Load the home page and set it as the root of the scene
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = confirmadd.getScene();
        scene.setRoot(root);
    }







    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
