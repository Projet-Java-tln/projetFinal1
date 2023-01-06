package Classes;

public class Salle {

    private int IdSalle;

    private Salle(int IdSalle)
    {
        this.IdSalle = IdSalle;
    }



    public static Salle newInstance(int IdSalle)
    {
        return new Salle(IdSalle);
    }


    @Override
    public String toString() {
        return "IdSalle:  "+ IdSalle;
    }

}
