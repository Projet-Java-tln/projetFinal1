package Classes;

public class Authentification {

    private String Email;
    private String Mdp;
    public enum Type {ETUDIANT,ENSEIGNANT,ADMINISTATEUR};
    private Type type;

    public Authentification(String Email, String Mdp,Type type ) {
        this.Email = Email;
        this.Mdp = Mdp;
        this.type = type;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMdp() {
        return Mdp;
    }

    public void setMdp(String mdp) {
        Mdp = mdp;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Authentification{" +
                "Email='" + Email + '\'' +
                ", Mdp='" + Mdp + '\'' +
                ", type=" + type +
                '}';
    }
}

