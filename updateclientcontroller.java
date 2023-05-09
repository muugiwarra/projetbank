

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

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class updateclientcontroller extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }

    @FXML
    private TextField updateid;
    @FXML
    private Button confirmid;
    @FXML
    private Button cancel;

    public void confirm(ActionEvent event) throws IOException {
        String clientID = updateid.getText(); // Get the client ID from the UI

        // Search for the client with the given ID in the clients.txt file
        BufferedReader br = new BufferedReader(new FileReader("c:/clients.txt"));
        String line;
        boolean found = false;
        while ((line = br.readLine()) != null) {
            String[] fields = line.split(":");
            if (fields[0].trim().equalsIgnoreCase(clientID)) {
                found = true;
                String[] clientInfo = fields[1].split(",");
                // Load the client edit page and set the client information in the textfields
                FXMLLoader loader = new FXMLLoader(getClass().getResource("updateclient1.fxml"));
                Parent root = loader.load();
                updateclient1controller clientController = loader.getController();
                loader.setController(clientController);
                clientController.setClientID(clientID);
                clientController.setNameid(clientInfo[0].trim());
                clientController.setAddressid(clientInfo[1].trim());
                clientController.setPhoneNumber(clientInfo[2].trim());
                clientController.setEmail(clientInfo[3].trim());
                Scene scene = confirmid.getScene();
                scene.setRoot(root);
                break;
            }
        }
        br.close();

        if (!found) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Client ID not found");
            alert.showAndWait();
        }
    }


    public void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = cancel.getScene();
        scene.setRoot(root);
    }
    }






