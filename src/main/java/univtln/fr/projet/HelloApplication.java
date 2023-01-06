package univtln.fr.projet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;

import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {



        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene2 = new Scene(root);
        stage.setTitle("Emploi du temps");
        stage.setScene(scene2);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}