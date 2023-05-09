

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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class deleteclientcontroller extends Application {
    @FXML
    private Button cndl;
    @FXML
    private Button ret;
    @FXML
    private TextField clientid;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }

    public void confirm(ActionEvent event) throws IOException {
        String clientID = clientid.getText(); // Get the client ID from the UI
        boolean deleted = deleteClient(clientID);
        if (!deleted) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Client ID not found");
            alert.showAndWait();
        }

        if (deleted) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("success");
            alert.setContentText("Client was successfully deleted");
            alert.showAndWait();
        }
        // Load the client page and set it as the root of the scene
        Parent root = FXMLLoader.load(getClass().getResource("clients.fxml"));
        Scene scene = cndl.getScene();
        scene.setRoot(root);
    }

    public boolean deleteClient(String clientID) throws IOException {
        // Read all the lines from clients.txt and store them in an ArrayList
        List<String> lines = Files.readAllLines(Paths.get("c:/clients.txt"));

        // Loop through the lines and find the line that contains the specified client ID
        Iterator<String> iter = lines.iterator();
        while (iter.hasNext()) {
            String line = iter.next();
            String[] fields = line.split(":");
            if (fields[0].trim().equalsIgnoreCase(clientID)) {
                // Remove the line from the ArrayList
                iter.remove();
                // Write the updated ArrayList back to clients.txt
                Files.write(Paths.get("c:/clients.txt"), lines);
                return true;
            }
        }

        // Client not found, return false
        return false;
    }



    public void Return(ActionEvent event) throws IOException {

        // Load the client page and set it as the root of the scene
        Parent root = FXMLLoader.load(getClass().getResource("clients.fxml"));
        Scene scene = ret.getScene();
        scene.setRoot(root);
    }
}
