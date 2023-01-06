package Classes;


public class Etudiant extends Personnel {


    public Etudiant(int IdPersonnel, String nom, String prenom, String email)
    {
        super(IdPersonnel, nom, prenom, email);
    }


    @Override
    public String toString() {
        return super.toString();
    }
}