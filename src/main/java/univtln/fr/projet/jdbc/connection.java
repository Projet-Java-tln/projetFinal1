package univtln.fr.projet.jdbc;
import java.sql.*;

import java.sql.Connection;
import java.sql.SQLException;
import Classes.Etudiant;
import java.util.logging.Logger;


public class connection {


    // Create a Logger
    static Logger logger
            = Logger.getLogger(
            connection.class.getName());

    /*    public static String DB_URL = "jdbc:postgresql://localhost:5433/postgres";
    public static String USER = "postgres";
    public static String PASS = "password";*/

    public static String DB_URL = "jdbc:postgresql://localhost:5432/Projet_edt";
    public static String USER = "postgres";
    public static String PASS = "syfsyf";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public String execute(String query ){
        String result = "rien";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            // Extract data from result set
            while (resultSet.next()) {
               result = String.valueOf(resultSet);
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        final String QUERY = "select * from \"Etudiant\"";
        logger.info("ok");
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(QUERY)) {
            // Extract data from result set
            while (resultSet.next()) {
                logger.info(new Etudiant(
                        resultSet.getInt("IdEtud"),
                        resultSet.getString("Nom"),
                        resultSet.getString("Prenom"),
                        resultSet.getString("Email").toString()).toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

