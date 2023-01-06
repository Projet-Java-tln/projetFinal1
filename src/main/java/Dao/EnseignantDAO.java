package Dao;

import Classes.Cours;
import Classes.Enseignant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnseignantDAO implements AutoCloseable {



    private final Connection connection;
    private final PreparedStatement findAll;
    private final PreparedStatement findById;
    private final PreparedStatement persist;
    private final PreparedStatement remove;





    private EnseignantDAO() throws SQLException {
        connection = DriverManager.getConnection(test1.DB_URL, test1.USER, test1.PASS);
        findAll = connection.prepareStatement(" SELECT *  FROM \"Enseignant\"  ");
        findById = connection.prepareStatement("SELECT * FROM \"Enseignant\" WHERE \"IdEns\" = ?");
        persist = connection.prepareStatement("INSERT INTO \"Enseignant\" VALUES (?,?,?,?)");
        remove = connection.prepareStatement("DELETE FROM \"Enseignant\"  WHERE \"IdEns\" = ?");
        System.err.println("Connection reussi!");
    }

    public static EnseignantDAO create() throws SQLException {
        return new EnseignantDAO();
    }


    public boolean exist(int IdEns) throws SQLException {
        findById.setInt(1,IdEns);
        ResultSet rs = findById.executeQuery();
        return rs.next();
    }


    public List<Enseignant> findAll() throws SQLException
    {
        List<Enseignant> personneList = new ArrayList<Enseignant>();
        ResultSet resultSet = findAll.executeQuery();

        while (resultSet.next())
        {
            personneList.add(new Enseignant(resultSet.getInt("IdEns"),
                    resultSet.getString("Nom"),
                    resultSet.getString("Prenom"),
                    resultSet.getString("Email")
            ));
        }
        return personneList;
    }



    public Enseignant findById(int Ident) throws SQLException {
        Enseignant enseignant = null;
        findById.setInt(1, Ident);
        ResultSet rs = findById.executeQuery();


        //j'extrait les données du resultset et je passe dans les variables
        while (rs.next()) {
           enseignant = new Enseignant(rs.getInt("IdEtud"),
                    rs.getString("Nom"),
                    rs.getString("Prenom"),
                    rs.getString("Email"));
        }
        return enseignant;
    }


    public Enseignant persist(Enseignant enseignant) throws SQLException {
        return persist(enseignant.getIdEns(),
                enseignant.getNom()
                ,enseignant.getPrenom(),
                enseignant.getEmail());
    }



    private Enseignant persist(int IdEns, String Nom, String Prenom, String Email) throws SQLException {
        persist.setInt(1,IdEns);
        persist.setString(2, Nom);
        persist.setString(3,Prenom);
        persist.setString(4,Email);

        if(persist.executeUpdate() == 1){
            System.out.println(IdEns+ " persited");
        }
        return new Enseignant(IdEns, Nom,Prenom, Email);
    }

    public void remove(Enseignant enseignant) throws SQLException {
        remove(enseignant.getIdEns());
    }

    public void remove(int IdEns) throws SQLException {
        remove.setInt(1, IdEns);
        remove.executeUpdate();
        System.out.println(IdEns + " removed.");
    }


    @Override
    public void close() throws Exception {
        connection.close();
    }
}
