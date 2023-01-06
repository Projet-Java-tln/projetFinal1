package Classes;

public class Personnel {

    private int identPersonnel;
    private String email;
    private String nom;
    private String prenom;

    Personnel(int identPersonnel, String nom, String prenom, String email)
    {
        this.identPersonnel = identPersonnel;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
    }

    public static Personnel newInstance(int identPersonnel, String nom, String prenom, String email)
    {
        return new Personnel(identPersonnel, nom, prenom, email);
    }




    @Override
    public String toString() {
        return "nom: " + nom + "prenom: "+ prenom;
    }
}