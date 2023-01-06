package Dao;
import Classes.Cours;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;



public class AffichageDAO {
    private final Connection connection;
    private final PreparedStatement SFW;

    public AffichageDAO() throws SQLException {
        connection = DriverManager.getConnection(test1.DB_URL, test1.USER, test1.PASS);
        SFW = connection.prepareStatement(" Select \"Nom\",\"Prenom\",\"NomMatiere\",\"Filiere\",\"IdSalle\" from \"Enseignant\",\"Matiere\",\"Filiere\",\"Salle\" where \"IdEns\"= ? and \"IdMat\"= ? and \"IdFiliere\"= ? and \"IdSalle\"=?");
        System.err.println("Connection reussi!");
    }

    public static AffichageDAO create() throws SQLException {
        return new AffichageDAO();
    }

    public boolean SFW(int IdEns, int IdMatiere , int IdFiliere , int IdSalle) throws SQLException {
        SFW.setInt(1,IdEns);
        SFW.setInt(3,IdFiliere);
        SFW.setInt(2,IdMatiere);
        SFW.setInt(4,IdSalle);
        ResultSet rs = SFW.executeQuery();
        return rs.next();
    }

}
