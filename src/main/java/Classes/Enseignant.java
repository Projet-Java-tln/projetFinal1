package Classes;

public class Enseignant extends Personnel {
    private int IdEns;
    private String Email;
    private String Nom;
    private String Prenom;
    public Enseignant(int IdPersonnel, String nom, String prenom , String email)
    {
        super(IdPersonnel, nom, prenom, email);
    }

    public int getIdEns() {
        return IdEns;
    }

    public String getEmail() {
        return Email;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}