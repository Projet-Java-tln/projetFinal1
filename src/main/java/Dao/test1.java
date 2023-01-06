package Dao;
import Classes.Authentification;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import static java.lang.String.valueOf;


public class test1 {

    public static String DB_URL = "jdbc:postgresql://localhost:5432/Projet_edt";
    public static String USER = "postgres";
    public static String PASS = "syfsyf";

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

    public static void main(String[] args) throws Exception {

        try (AuthentificationDAO authDAO = AuthentificationDAO.create()) {
            //Authentification a6 = new Authentification("rachida@yahoo.fr", toHexString(getSHA("hajar123")),Authentification.Type.ENSEIGNANT);

            //System.out.println(" ByID" + authDAO.findById("seraph#yahoo.fr"));
            //System.out.println(" Persiste" + authDAO.persist(a6));

            //authDAO.remove("seraph#yahoo.fr");
            //Authentification a4 = new Authentification("xpringalle@gmail.com", toHexString(getSHA("pringalle123")),Authentification.Type.ADMINISTATEUR);
            //System.out.println(" Persiste" + authDAO.persist(a4));
            //Authentification a9 = new Authentification("bruno@univtln.fr", toHexString(getSHA("bruno123")),Authentification.Type.ENSEIGNANT);
            //System.out.println(" Persiste" + authDAO.persist(a9));
            //Authentification a1 = new Authentification("kadrane@univtln.fr", toHexString(getSHA("kadrane123")),Authentification.Type.ETUDIANT);
            //authDAO.remove("seraph#yahoo.fr");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }}
