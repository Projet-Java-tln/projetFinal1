package Dao;

import Classes.Enseignant;
import Classes.Matiere;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatiereDAO implements AutoCloseable{



    private final Connection connection;
    public final PreparedStatement findAll;
    private final PreparedStatement findById;
    private final PreparedStatement persist;
    private final PreparedStatement remove;




    private MatiereDAO() throws SQLException {
        connection = DriverManager.getConnection(test1.DB_URL, test1.USER, test1.PASS);
        findAll = connection.prepareStatement(" SELECT *  FROM \"Matiere\"  ");
        findById = connection.prepareStatement("SELECT * FROM \"Matiere\" WHERE \"IdMat\" = ?");
        persist = connection.prepareStatement("INSERT INTO \"Matiere\" VALUES (?,?,?,?)");
        remove = connection.prepareStatement("DELETE FROM \"Matiere\"  WHERE \"IdMat\" = ?");
        System.err.println("Connection reussi!");
    }

    public static MatiereDAO create() throws SQLException {
        return new MatiereDAO();
    }


    public boolean exist(int IdMat) throws SQLException {
        findById.setInt(1,IdMat);
        ResultSet rs = findById.executeQuery();
        return rs.next();
    }


    public List<Matiere> findAll() throws SQLException
    {
        List<Matiere> personneList = new ArrayList<Matiere>();
        ResultSet resultSet = findAll.executeQuery();

        while (resultSet.next())
        {
            personneList.add(new Matiere(resultSet.getInt("IdMat"),
                    resultSet.getString("NomMatiere"),
                    resultSet.getString("Semestre")
            ));
        }
        return personneList;
    }



    public Matiere findById(int Ident) throws SQLException {
        Matiere matiere = null;
        findById.setInt(1, Ident);
        ResultSet rs = findById.executeQuery();


        //j'extrait les données du resultset et je passe dans les variables
        while (rs.next()) {
            matiere = new Matiere(rs.getInt("IdMat"),
                    rs.getString("NomMatiere"),
                    rs.getString("Semestre"));
        }
        return matiere;
    }


    public Matiere persist(Matiere matiere) throws SQLException {
        return persist(matiere.getIdMat(),
                matiere.getNomMatiere()
                ,matiere.getSemestre());
    }



    private Matiere persist(int IdMat, String NomMatiere, String Semestre) throws SQLException {
        persist.setInt(1,IdMat);
        persist.setString(2, NomMatiere);
        persist.setString(3,Semestre);

        if(persist.executeUpdate() == 1){
            System.out.println(IdMat + " persited");
        }
        return new Matiere(IdMat, NomMatiere,Semestre);
    }

    public void remove(Matiere matiere) throws SQLException {
        remove(matiere.getIdMat());
    }

    public void remove(int IdMat) throws SQLException {
        remove.setInt(1, IdMat);
        remove.executeUpdate();
        System.out.println(IdMat + " removed.");
    }


    @Override
    public void close() throws Exception {
        connection.close();
    }
}
