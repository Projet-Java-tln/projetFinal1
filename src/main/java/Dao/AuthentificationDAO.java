package Dao;
import Classes.Authentification;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;



public class AuthentificationDAO implements AutoCloseable{


    private final Connection connection;
    private final PreparedStatement findAll;
    private final PreparedStatement findById;
    private final PreparedStatement persist;
    private final PreparedStatement remove;

    public Authentification.Type type;
    public AuthentificationDAO() throws SQLException {

        connection = DriverManager.getConnection(test1.DB_URL, test1.USER, test1.PASS);
        findAll = connection.prepareStatement(" SELECT *  FROM \"Authentification\"  ");
        findById = connection.prepareStatement("SELECT * FROM \"Authentification\" WHERE \"Email\" = ?");
        persist = connection.prepareStatement("INSERT INTO \"Authentification\" VALUES (?,?,?)");
        remove = connection.prepareStatement("DELETE FROM \"Authentification\"  WHERE \"Email\" = ?");
        System.err.println("Connection reussi!");

    }

    public static AuthentificationDAO create() throws SQLException {
        return new AuthentificationDAO();
    }

    public boolean exist(String Email) throws SQLException {
        findById.setString(1,Email);
        ResultSet rs = findById.executeQuery();
        return rs.next();
    }

    public List<Authentification> findAll() throws SQLException
    {
        Authentification a1 = null;
        List<Authentification> AuthentificationList = new ArrayList<Authentification>();
        ResultSet resultSet = findAll.executeQuery();

        while (resultSet.next())
        {
            AuthentificationList.add(new Authentification(resultSet.getString("Email"),resultSet.getString("Mdp"),Authentification.Type.valueOf(resultSet.getString("Type"))));

        }
        return AuthentificationList;
    }

    public Authentification findById(String Email) throws SQLException {
        Authentification a1 = null;
        findById.setString(1,Email);
        ResultSet rs = findById.executeQuery();

        System.out.println(" RS" +rs);
        while (rs.next()) {
            a1 = new Authentification(rs.getString("Email"), rs.getString("Mdp"),
                    Authentification.Type.valueOf(rs.getString("Type")));

        }
        return a1;
    }


    public Authentification persist(Authentification a1) throws SQLException {
        return persist(a1.getEmail(), a1.getMdp(),a1.getType());
    }


    private Authentification persist( String Email,String Mdp
            , Authentification.Type type) throws SQLException {
        persist.setString(1,Email);
        persist.setString(2,Mdp);
        persist.setString(3,type.name());

        if(persist.executeUpdate() == 1){
            System.out.println(type + " persited");
        }
        return new Authentification(Email, Mdp,type);
    }

    public void remove(Authentification a1) throws SQLException {
        remove(a1.getEmail());
    }

    public void remove(String Email) throws SQLException {
        remove.setString(1, Email);
        remove.executeUpdate();
        System.out.println(Email + " removed.");
    }


    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }



    //j'ai eu des soucis avec la methode validate c pour cela j'ai dupliquer au code de la connexion db et l'appelle de la table
    private static final String SELECT_QUERY = "SELECT * FROM \"Authentification\" WHERE \"Email\" = ? and \"Mdp\" = ?";


    public boolean validate(String Email, String Mdp) throws SQLException {

        try (Connection connection1 = DriverManager
                .getConnection(test1.DB_URL, test1.USER, test1.PASS);
             PreparedStatement preparedStatement = connection1.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setString(1, Email);
            preparedStatement.setString(2, toHexString(getSHA(Mdp)));

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                type = Authentification.Type.valueOf(resultSet.getString("type"));
                return true;
            }


        } catch (SQLException | NoSuchAlgorithmException e) {
            printSQLException((SQLException) e);
        }
        return false;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
            }
        }
    }

    @Override
    public void close() throws Exception {

    }
}