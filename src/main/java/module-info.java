module univtln.fr.projet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;



    opens univtln.fr.projet to javafx.fxml;
    exports univtln.fr.projet;
}