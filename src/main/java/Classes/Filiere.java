package Classes;

public class Filiere {

    private int IdFiliere;
    private String NomFiliere;


    public Filiere(int IdFiliere, String NomFiliere)
    {
        this.NomFiliere = NomFiliere;
        this.IdFiliere = IdFiliere;
    }

    public static Filiere newInstance(int IdFiliere, String NomFiliere)
    {
        return new Filiere(IdFiliere, NomFiliere);
    }


    public int getIdFiliere() {
        return IdFiliere;
    }

    public String getNomFiliere() {
        return NomFiliere;
    }

    @Override
    public String toString() {
        return "NomeFiliere:  "+ NomFiliere;
    }
}
