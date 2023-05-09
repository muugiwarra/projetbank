


import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.application.Application;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Logger;

public class logincontroller implements Initializable {
    @FXML
    private TextField tfusername;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private Button buttonlogin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void DoLogin(ActionEvent event) throws IOException {
        String username = tfusername.getText();
        String password = tfpassword.getText();

        boolean credentialsMatch = false;

        // Check entered username and password against valid credentials in employees.txt file
        File file = new File("C:/employees.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] values = line.split(":")[0].split(",");
            if (values.length == 2 && values[0].equals(username) && values[1].equals(password)) {
                credentialsMatch = true;
                break;
            }
        }
        scanner.close();

        if (credentialsMatch) {
            // Redirect to home page if credentials match
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            // Display error message if credentials do not match
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("Invalid username or password");
            alert.setContentText("Please try again.");
            alert.showAndWait();
        }
    }
}


