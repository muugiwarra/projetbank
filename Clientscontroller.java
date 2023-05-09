


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Clientscontroller {
    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField accountNumberField;
    @FXML
    private Button homeid;

    @FXML
    private void addClient(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addclient.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    // Other methods for handling user actions

    @FXML
    private void deleteClient(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("deleteclient.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void modifyClient(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("updateclient.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void backhome(ActionEvent event) throws IOException {
        // Load the client page and set it as the root of the scene
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = homeid.getScene();
        scene.setRoot(root);
    }
}

