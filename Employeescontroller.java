import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.Scanner;

public class Employeescontroller {
    @FXML
    private Button checklist;
    @FXML
    private TextArea textarea;
    @FXML
    private Button gobckbtn;

    public void check(ActionEvent event) throws IOException {
        // Open the employees.txt file for reading
        BufferedReader reader = new BufferedReader(new FileReader("C:/employees.txt"));

        // Read the file line by line
        String line;
        while ((line = reader.readLine()) != null) {
            // Split the line into fields using the colon as a delimiter
            String[] fields = line.split(":");
            if (fields.length > 1) {
                // Append the second field (after the colon) to the text area
                String info = fields[1].trim();
                textarea.appendText(info + "\n");
            }
        }

        // Close the file
        reader.close();
    }

    @FXML
    void gobck(ActionEvent event) throws IOException {
        // Load the client page and set it as the root of the scene
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = gobckbtn.getScene();
        scene.setRoot(root);
    }
}

