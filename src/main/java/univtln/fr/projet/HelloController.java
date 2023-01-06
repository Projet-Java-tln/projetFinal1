package univtln.fr.projet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private AnchorPane rootPane;

    @FXML
    public void lancerDID(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("DID.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    public void lancerANS(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("LoginEtud.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    @FXML
    public void lancerECO(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("test.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    @FXML
    public void lancerDROIT(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("test.fxml"));
        rootPane.getChildren().setAll(pane);
    }


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}