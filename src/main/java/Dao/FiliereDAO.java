package Dao;

import Classes.Etudiant;
import Classes.Filiere;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FiliereDAO implements AutoCloseable {


    private final Connection connection;
    private final PreparedStatement findAll;
    private final PreparedStatement findById;
    private final PreparedStatement persist;
    private final PreparedStatement remove;

    private FiliereDAO() throws SQLException {
        connection = DriverManager.getConnection(test1.DB_URL, test1.USER, test1.PASS);
        findAll = connection.prepareStatement(" SELECT *  FROM \"Filiere\"  ");
        findById = connection.prepareStatement("SELECT * FROM \"Filiere\" WHERE \"IdFiliere\" = ?");
        persist = connection.prepareStatement("INSERT INTO \"Filiere\" VALUES (?,?,?,?)");
        remove = connection.prepareStatement("DELETE FROM \"Filiere\"  WHERE \"IdFiliere\" = ?");
        System.err.println("Connection reussi!");
    }

    public static FiliereDAO create() throws SQLException {
        return new FiliereDAO();
    }


    public boolean exist(int IdFiliere) throws SQLException {
        findById.setInt(1,IdFiliere);
        ResultSet rs = findById.executeQuery();
        return rs.next();
    }


    public List<Filiere> findAll() throws SQLException
    {
        List<Filiere> personneList = new ArrayList<Filiere>();
        ResultSet resultSet = findAll.executeQuery();

        while (resultSet.next())
        {
            personneList.add(new Filiere(resultSet.getInt("IdFiliere"),
                    resultSet.getString("Filiere")
            ));
        }
        return personneList;
    }



    public Filiere findById(int Ident) throws SQLException {
        Filiere filiere = null;
        findById.setInt(1, Ident);
        ResultSet rs = findById.executeQuery();


        //j'extrait les données du resultset et je passe dans les variables
        while (rs.next()) {
            filiere = new Filiere(rs.getInt("IdFiliere"),
                    rs.getString("Filiere"));
        }
        return filiere;
    }




    public Filiere persist(Filiere filiere) throws SQLException {
        return persist(filiere.getIdFiliere(),
                filiere.getNomFiliere());
    }


    private Filiere persist(int IdFiliere, String Filiere) throws SQLException {
        persist.setInt(1,IdFiliere);
        persist.setString(2, Filiere);


        if(persist.executeUpdate() == 1){
            System.out.println(IdFiliere + " persited");
        }
        return new Filiere(IdFiliere, Filiere);
    }

    public void remove(Filiere filiere) throws SQLException {
        remove(filiere.getIdFiliere());
    }

    public void remove(int IdFiliere) throws SQLException {
        remove.setInt(1, IdFiliere);
        remove.executeUpdate();
        System.out.println(IdFiliere+ " removed.");
    }


    @Override
    public void close() throws Exception {
        connection.close();
    }
}
