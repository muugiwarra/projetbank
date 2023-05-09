
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Homecontroller {

    @FXML
    private Button btemployees;
    @FXML
    private Button btclients;

    @FXML
    private Button btaccounts;

    @FXML
    private void getemployees(ActionEvent event) {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("employees.fxml"));
        try {
            loader.load();

        } catch (IOException ex) {
            Logger.getLogger(Employeescontroller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("failed to load");
            System.out.println(ex);
        }
        Parent parent = loader.getRoot();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void getclients(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("clients.fxml"));
        try {
            loader.load();

        } catch (IOException ex) {
            Logger.getLogger(Clientscontroller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("failed to load");
            System.out.println(ex);
        }
        Parent parent = loader.getRoot();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void getaccounts(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("accounts.fxml"));
        try {
            loader.load();

        } catch (IOException ex) {
            Logger.getLogger(Accountscontroller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("failed to load");
            System.out.println(ex);
        }
        Parent parent = loader.getRoot();
        stage.setScene(new Scene(parent));
        stage.show();
    }
}
