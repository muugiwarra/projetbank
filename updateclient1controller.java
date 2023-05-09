

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
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class updateclient1controller extends Application {

    public void setNameid(String firstName) {
        this.nameid = nameid;
        nameid.setText(firstName);
    }


    @FXML
    private Button leave;
    @FXML
    private Button update;
    @FXML
    private Text id;

    @FXML
    private TextField nameid;

    @FXML
    private TextField addressid;

    @FXML
    private TextField phoneNumberid;

    @FXML
    private TextField emailid;

    private String clientID;



    private void loadClientInfo() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("c:/clients.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(":");
                if (fields[0].trim().equalsIgnoreCase(clientID)) {
                    String[] clientInfo = fields[1].split(",");
                    nameid.setText(clientInfo[0].trim());
                    addressid.setText(clientInfo[1].trim());
                    phoneNumberid.setText(clientInfo[2].trim());
                    emailid.setText(clientInfo[3].trim());
                    break;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void savechanges(ActionEvent event) {
        String newName = nameid.getText().trim();
        String newAddress = addressid.getText().trim();
        String newPhoneNumber = phoneNumberid.getText().trim();
        String newEmail = emailid.getText().trim();

        String oldClientInfo = "";
        String newClientInfo = "";
        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("c:/clients.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(":");
                if (fields[0].trim().equalsIgnoreCase(clientID)) {
                    found = true;
                    oldClientInfo = fields[1];
                    String[] clientInfo = fields[1].split(",");
                    if (!newName.equals("")) {
                        clientInfo[0] = newName;
                    }
                    if (!newAddress.equals("")) {
                        clientInfo[1] = newAddress;
                    }
                    if (!newPhoneNumber.equals("")) {
                        clientInfo[2] = newPhoneNumber;
                    }
                    if (!newEmail.equals("")) {
                        clientInfo[3] = newEmail;
                    }
                    newClientInfo = String.join(",", clientInfo);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("An error occurred while reading the file");
            alert.showAndWait();
            return;
        }

        if (found) {
            try {
                List<String> lines = Files.readAllLines(Paths.get("c:/clients.txt"));
                Iterator<String> iter = lines.iterator();
                while (iter.hasNext()) {
                    String currLine = iter.next();
                    if (currLine.contains(oldClientInfo)) {
                        String newLine = clientID + ":" + newClientInfo;
                        iter.remove();
                        lines.add(newLine);
                        Files.write(Paths.get("c:/clients.txt"), lines);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setContentText("Client information updated successfully");
                        alert.showAndWait();
                        return;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("An error occurred while writing to the file");
                alert.showAndWait();
                return;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Client ID not found");
            alert.showAndWait();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }



        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumberid = phoneNumberid;
            phoneNumberid.setText(phoneNumber);
        }


    public void setEmail(String email) {
        this.emailid = emailid;
        emailid.setText(email);
    }

    public void home(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = leave.getScene();
        scene.setRoot(root);
    }


    public void setAddressid(String address) {
        this.addressid = addressid;
        addressid.setText(address);
    }
    public void setClientID(String clientID) {
        this.clientID = clientID;
        loadClientInfo();
    }
}
