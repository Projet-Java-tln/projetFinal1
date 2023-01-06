package Classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Cours {




    public enum TypeCours {TP,TD,CM,CT};
    private int IdCours;
    private LocalDate Date;
    private LocalTime HeureDebut;
    private LocalTime HeureFin;
    private TypeCours TypeCours;
    private int IdFiliere;
    private int IdSalle;
    private int IdEns;
    private int IdMatiere;

    public Cours(int IdCours, LocalDate Date, LocalTime HeureDebut,
                 LocalTime HeureFin,int IdEns, int IdFiliere , int IdMatiere ,int IdSalle,TypeCours typeCours)
    {
        this.IdCours = IdCours;
        this.IdFiliere = IdFiliere;
        this.IdSalle = IdSalle;
        this.IdEns = IdEns;
        this.IdMatiere = IdMatiere;
        this.Date = Date;
        this.HeureDebut = HeureDebut;
        this.HeureFin = HeureFin;
        this.TypeCours = typeCours;
    }

    public static Cours newInstance(int IdCours,LocalDate Date,LocalTime HeureDebut, LocalTime HeureFin ,int IdEns, int IdFiliere
            ,int IdMatiere, int IdSalle
            ,TypeCours typeCours)
    {
        return new Cours(IdCours,Date,HeureDebut,HeureFin,IdEns,IdFiliere,IdMatiere,IdSalle,typeCours);
    }



    public int getIdEns() {
        return IdEns;
    }

    public void setIdEns(int idEns) {
        IdEns = idEns;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public TypeCours getTypeCours() {
        return TypeCours;
    }

    public void setTypeCours(TypeCours typeCours) {
        this.TypeCours = typeCours;
    }

    public int getIdCours() {
        return IdCours;
    }

    public void setIdCours(int idCours) {
        IdCours = idCours;
    }

    public LocalTime getHeureDebut() {
        return HeureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        HeureDebut = heureDebut;
    }

    public LocalTime getHeureFin() {
        return HeureFin;
    }

    public void setHeureFin(LocalTime heureFin) {
        HeureFin = heureFin;
    }

    public int getIdFiliere() {
        return IdFiliere;
    }

    public void setIdFiliere(int idFiliere) {
        IdFiliere = idFiliere;
    }

    public int getIdSalle() {
        return IdSalle;
    }

    public void setIdSalle(int idSalle) {
        IdSalle = idSalle;
    }

    public int getIdMatiere() {
        return IdMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        IdMatiere = idMatiere;
    }
    @Override
    public String toString() {
        return "Cours{" +
                "identCours='" + IdCours + '\'' +
                ", typeCours=" + TypeCours +
                ", filiere=" + IdFiliere +
                ", salle=" + IdSalle +
                ", professeur=" + IdEns +
                ", matiere=" + IdMatiere +
                '}';
    }
}