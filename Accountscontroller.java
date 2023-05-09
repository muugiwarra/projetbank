import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Accountscontroller {
    @FXML
    private Button canid;
    @FXML
    private Button conid;
    @FXML
    private TextField idfield;

    @FXML
    void cancel(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void cnid(ActionEvent event) throws IOException {
        String clientID = idfield.getText();
        String accountNumber = "";
        double balance = 0.0;
        double lastTransaction = 0.0;

        // search for the client with the given ID in the clients.txt file
        BufferedReader br = new BufferedReader(new FileReader("c:/clients.txt"));
        String line;
        boolean found = false;
        while ((line = br.readLine()) != null) {
            String[] fields = line.split(":");
            if (fields[0].trim().equalsIgnoreCase(clientID)) {
                found = true;
                String[] clientInfo = fields[1].split(",");
                // get the account number for the client
                accountNumber = clientInfo[4].trim();
                break;
            }
        }
        br.close();

        if (found) {
            // retrieve the account information for the client from the accounts.txt file
            BufferedReader reader = new BufferedReader(new FileReader("c:/accounts.txt"));
            String row;
            while ((row = reader.readLine()) != null) {
                String[] fields = row.split(",");
                if (fields[0].trim().equalsIgnoreCase(accountNumber)) {
                    balance = Double.parseDouble(fields[1]);
                    lastTransaction = Double.parseDouble(fields[2]);
                    break;
                }
            }
            reader.close();

            // load the client account page and set the balance and transaction information
            FXMLLoader loader = new FXMLLoader(getClass().getResource("clientaccount.fxml"));
            Parent root = loader.load();
            Clientaccountcontroller clientController = loader.getController();
            clientController.setClientID(clientID);
            clientController.setBalance(Double.toString(balance));
            clientController.setTransactions(Double.toString(lastTransaction));
            clientController.updateLabels();
            Scene scene = conid.getScene();
            scene.setRoot(root);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Client ID not found");
            alert.showAndWait();
        }
    }
}




