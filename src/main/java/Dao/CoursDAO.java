package Dao;
import Classes.Cours;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


import static java.lang.String.valueOf;

public class CoursDAO implements AutoCloseable{




    private final Connection connection;
    private final PreparedStatement findAll;
    private final PreparedStatement findAllWhereFiliere;
    private final PreparedStatement findById;
    private final PreparedStatement persist;
    private final PreparedStatement update;
    private final PreparedStatement findByIdEns;
    private final PreparedStatement GetIdfromMatiere;
    private final PreparedStatement GetIdfromFiliere;
    private final PreparedStatement GetIdfromEnseignant;
    private final PreparedStatement GetIdfromEnseignantByEmail;
    private final PreparedStatement GetFiliereById;
    private final PreparedStatement remove;
    private final PreparedStatement GetEnsById;



    private final PreparedStatement GetMatiereById;


    public CoursDAO() throws SQLException {
        connection = DriverManager.getConnection(test1.DB_URL, test1.USER, test1.PASS);
        findAll = connection.prepareStatement(" SELECT *  FROM \"Cours\"  ");
        findAllWhereFiliere = connection.prepareStatement(" SELECT *  FROM \"Cours\" WHERE \"IdFiliere\"= ? ");
        findById = connection.prepareStatement("SELECT * FROM \"Cours\" WHERE \"IdCours\" = ?");
        findByIdEns = connection.prepareStatement("SELECT * FROM \"Cours\" WHERE \"IdEns\" = ?");
        persist = connection.prepareStatement("INSERT INTO \"Cours\" VALUES (?,?,?,?,?,?,?,?,?)");
        remove = connection.prepareStatement("DELETE FROM \"Cours\"  WHERE \"IdCours\" = ?");
        update =  connection.prepareStatement("UPDATE public.\"Cours\" SET \"IdCours\"=?, \"Date\"=?, \"HeureDebut\"=?, \"HeureFin\"=?, \"IdEns\"=?, \"IdFiliere\"=?, \"IdMatiere\"=?, \"IdSalle\"=?, \"TypeCours\"=? WHERE \"IdCours\"=?");
        GetIdfromMatiere = connection.prepareStatement(" SELECT \"IdMat\"  FROM \"Matiere\" WHERE \"NomMatiere\"= ? ");
        GetIdfromFiliere = connection.prepareStatement(" SELECT \"IdFiliere\"  FROM \"Filiere\" WHERE \"Filiere\"= ? ");
        GetIdfromEnseignant = connection.prepareStatement(" SELECT \"IdEns\"  FROM \"Enseignant\" WHERE \"Nom\"= ? ");
        GetFiliereById = connection.prepareStatement(" SELECT \"Filiere\"  FROM \"Filiere\" WHERE \"IdFiliere\"= ? ");
        GetMatiereById = connection.prepareStatement(" SELECT \"NomMatiere\"  FROM \"Matiere\" WHERE \"IdMat\"= ? ");
        GetEnsById = connection.prepareStatement(" SELECT \"Nom\"  FROM \"Enseignant\" WHERE \"IdEns\"= ? ");
        GetIdfromEnseignantByEmail = connection.prepareStatement(" SELECT \"IdEns\"  FROM \"Enseignant\" WHERE \"Email\"= ? ");



        System.err.println("Connection reussi!");
    }

    public static CoursDAO create() throws SQLException {
        return new CoursDAO();
    }

    public boolean exist(int IdCours) throws SQLException {
        findById.setInt(1,IdCours);
        ResultSet rs = findById.executeQuery();
        return rs.next();
    }

    public List<Cours> findAll() throws SQLException
    {
        Cours cours = null;
        List<Cours> personneList = new ArrayList<Cours>();
        ResultSet resultSet = findAll.executeQuery();

        while (resultSet.next())
        {
            personneList.add(new Cours(resultSet.getInt("IdCours"),resultSet.getDate("Date").toLocalDate(),resultSet.getTime("HeureDebut").toLocalTime(),
                    resultSet.getTime("HeureFin").toLocalTime(),resultSet.getInt("IdEns"),resultSet.getInt("IdFiliere"),
                    resultSet.getInt("IdMatiere"),resultSet.getInt("IdSalle"), Cours.TypeCours.valueOf(resultSet.getString("typeCours"))


            ));
        }
        return personneList;
    }
    public List<Cours> findAllWhereFiliere(int IdFiliere) throws SQLException
    {
        Cours cours = null;
        List<Cours> personneList = new ArrayList<Cours>();
        findAllWhereFiliere.setInt(1, IdFiliere);
        ResultSet resultSet = findAllWhereFiliere.executeQuery();
        while (resultSet.next())
        {
            personneList.add(new Cours(resultSet.getInt("IdCours"),resultSet.getDate("Date").toLocalDate(),resultSet.getTime("HeureDebut").toLocalTime(),
                    resultSet.getTime("HeureFin").toLocalTime(),resultSet.getInt("IdEns"),resultSet.getInt("IdFiliere"),
                    resultSet.getInt("IdMatiere"),resultSet.getInt("IdSalle"), Cours.TypeCours.valueOf(resultSet.getString("typeCours"))


            ));
        }
        return personneList;
    }
    public Cours findById(int Id) throws SQLException {
        Cours cours = null;
        // int iden = 0, idens = 0, idfiliere =0,idsalle =0,idematiere=0;
        findById.setInt(1, Id);
        ResultSet rs = findById.executeQuery();

        System.out.println(" RS" +rs);
        //j'extrait les données du resultset
        while (rs.next()) {
            cours = new Cours(rs.getInt("IdCours"), rs.getDate("Date").toLocalDate(),
                    rs.getTime("HeureDebut").toLocalTime(),  rs.getTime("HeureFin").toLocalTime(),
                    rs.getInt("IdEns"), rs.getInt("IdFiliere"),rs.getInt("IdMatiere"),rs.getInt("IdSalle"),
                    Cours.TypeCours.valueOf(rs.getString("typeCours")));

        }
        return cours;
    }
    public List<Cours> findByIdEns(int Id) throws SQLException {

        List<Cours> personneList = new ArrayList<Cours>();

        // int iden = 0, idens = 0, idfiliere =0,idsalle =0,idematiere=0;
        findByIdEns.setInt(1, Id);
        ResultSet resultSet = findByIdEns.executeQuery();

        System.out.println(" RS" +resultSet);
        //j'extrait les données du resultset
        while (resultSet.next()) {
            personneList.add(new Cours(resultSet.getInt("IdCours"),resultSet.getDate("Date").toLocalDate(),resultSet.getTime("HeureDebut").toLocalTime(),
                    resultSet.getTime("HeureFin").toLocalTime(),resultSet.getInt("IdEns"),resultSet.getInt("IdFiliere"),
                    resultSet.getInt("IdMatiere"),resultSet.getInt("IdSalle"), Cours.TypeCours.valueOf(resultSet.getString("typeCours"))


            ));

        }
        return personneList;
    }


    public Cours persist(Cours cours) throws SQLException {
        return persist(cours.getIdCours(), cours.getDate(),
                cours.getHeureDebut(),cours.getHeureFin(),
                cours.getIdEns(),
                cours.getIdFiliere(),cours.getIdSalle()
                ,cours.getIdMatiere(),cours.getTypeCours());
    }

    private Cours persist(int IdCours, LocalDate  Date, LocalTime HeureDebut,
                          LocalTime HeureFin , int IdEns, int IdFiliere
            , int IdMatiere, int IdSalle
            , Cours.TypeCours typeCours) throws SQLException {

        persist.setString(9, typeCours.name());

        if(persist.executeUpdate() == 1){
            System.out.println(IdCours + " persited");
        }
        return new Cours(IdCours, Date, HeureDebut, HeureFin ,  IdEns, IdFiliere
                ,  IdMatiere, IdSalle
                , typeCours);
    }

    public void remove(Cours cours) throws SQLException {
        remove(cours.getIdCours());
    }

    public void remove(int IdCours) throws SQLException {
        remove.setInt(1, IdCours);
        remove.executeUpdate();
        System.out.println(IdCours + " removed.");
    }




    public void update(int Id, int Filiere, int Matiere, int Ens, String date, String Heuredebut, String Heurefin, String Type, int salle) throws SQLException, ParseException {
        update.setInt(1, Id);
        Date Date2 = Date.valueOf(date);
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        java.sql.Time heuredebut = new java.sql.Time(formatter.parse(Heuredebut).getTime());
        java.sql.Time heurefin = new java.sql.Time(formatter.parse(Heurefin).getTime());

        // \"IdEns\"=?, \"IdFiliere\"=?, \"IdMatiere\"=?, \"IdSalle\"=?, \"TypeCours\"=? WHERE \"IdCours\"=?"
        update.setDate(2, Date2);
        update.setTime(3, heuredebut);
        update.setTime(4, heurefin);
        update.setInt(5, Ens);
        update.setInt(6, Filiere);
        update.setInt(7, Matiere);
        update.setInt(8, salle);
        update.setString(9, Type);
        update.setInt(10, Id);
        update.executeUpdate();
        System.out.println(Id + " updated.");
    }


    public int GetIdfromEnseingnant(String Nom) throws SQLException {
        GetIdfromEnseignant.setString(1, Nom);
        int id = 0;
        ResultSet rs = GetIdfromEnseignant.executeQuery();
        while (rs.next()){
            id = rs.getInt("IdEns");
        }
        return id;
    }
    public int GetIdfromEnseignantByEmail(String Email) throws SQLException {
        GetIdfromEnseignantByEmail.setString(1, Email);
        int id = 0;
        ResultSet rs = GetIdfromEnseignantByEmail.executeQuery();
        while (rs.next()){
            id = rs.getInt("IdEns");
        }
        return id;
    }
    public int GetIdfromFiliere(String Filiere) throws SQLException {
        GetIdfromFiliere.setString(1, Filiere);
        int id = 0;
        ResultSet rs = GetIdfromFiliere.executeQuery();
        while (rs.next()){
            id = rs.getInt("IdFiliere");
        }
        return id;
    }
    public int GetIdfromMatiere(String Matiere) throws SQLException {
        GetIdfromMatiere.setString(1, Matiere);
        int id = 0;
        ResultSet rs = GetIdfromMatiere.executeQuery();
        while (rs.next()){
            id = rs.getInt("IdMat");
        }
        return id;
    }

    public String GetEnsById(int idens) throws SQLException {
        GetEnsById.setInt(1, idens);
        String ENS = "";
        ResultSet rs = GetEnsById.executeQuery();
        while (rs.next()){
            ENS = rs.getString("Nom");
        }
        return ENS;
    }
    public String GetFiliereById(int idfil) throws SQLException {
        GetFiliereById.setInt(1, idfil);
        String fil = "";
        // fil = String.valueOf(GetFiliereById.executeUpdate());\
        ResultSet rs = GetFiliereById.executeQuery();
        System.out.println(" RS" +rs);
        while (rs.next()){
            fil = String.valueOf(rs.getString("Filiere"));
        }
        return fil;
    }

    public String GetMatiereById(int idmat) throws SQLException {
        GetMatiereById.setInt(1, idmat);
        String fil = "";
        ResultSet rs = GetMatiereById.executeQuery();
        while (rs.next()){
            fil = rs.getString("NomMatiere");
        }
        return fil;
    }



    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
