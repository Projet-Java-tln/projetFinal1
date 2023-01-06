package univtln.fr.projet;
import Classes.Authentification;
import Dao.AuthentificationDAO;


import Dao.CoursDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class AuthentificationController {

    public static int idens = 0;
    @FXML
    private TextField emailIdField;

    Authentification.Type type;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;


    @FXML
    public void login(ActionEvent event) throws SQLException, IOException {


        if (emailIdField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            System.out.println("enter valid email or password");
            return ;
        }

        String emailId = emailIdField.getText();
        String password = passwordField.getText();

        AuthentificationDAO a11 = new AuthentificationDAO();

        boolean ok = a11.validate(emailId, password);

        if (!ok) {
            System.out.println("enter valid email or password");
        } else {
            if (a11.type == type.ENSEIGNANT) {

                CoursDAO c = CoursDAO.create();
                idens = c.GetIdfromEnseignantByEmail(emailId);
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("affichageedtens.fxml")));
                //Scene scene = new Scene(FXMLLoader.load(getClass().getResource("affichagedt.fxml")));
                stage.setScene(scene);
                stage.show();
            }


            if (a11.type == type.ETUDIANT) {
                CoursDAO c = CoursDAO.create();
                idens = c.GetIdfromEnseignantByEmail(emailId);
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("affichagedt.fxml")));
                stage.setScene(scene);
                stage.show();
            }
            else if (a11.type == type.ADMINISTATEUR) {

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("admin2.fxml")));
                stage.setScene(scene);
                stage.show();
            }
        }
    }

}