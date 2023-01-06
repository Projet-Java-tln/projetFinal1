package Classes;

public class Matiere {

    private String nomMatiere;
    private int IdMatiere;
    private String semestre;


    public Matiere(int IdMatiere, String nomMatiere, String semestre)
    {
        this.IdMatiere = IdMatiere;
        this.nomMatiere = nomMatiere;
        this.semestre = semestre;
    }

    public static Matiere newInstance(int IdMatiere,String nomMatiere,String semestre)
    {
        return new Matiere(IdMatiere,nomMatiere,semestre);
    }

    public int getIdMat() {
        return IdMatiere;
    }

    public String getSemestre() {
        return semestre;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    @Override
    public String toString() {
        return "nomMatiere:  "+nomMatiere+ "identMatiere:  "+IdMatiere+ " Semestre: "+ semestre;
    }
}