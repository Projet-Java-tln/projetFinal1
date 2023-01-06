package univtln.fr.projet;

import Classes.Cours;
import Classes.Enseignant;
import Classes.Filiere;
import Classes.Matiere;
import Dao.CoursDAO;
import Dao.EnseignantDAO;
import Dao.FiliereDAO;
import Dao.MatiereDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class editController {
    @FXML
    public ChoiceBox<Matiere> matiere2;
    @FXML
    public ChoiceBox<Filiere> filiere2;
    @FXML
    public ChoiceBox<Enseignant> ens2;


    @FXML
    public TextField salle;
   // @FXML
    //public TextField filiere;
    //@FXML
    //public TextField matiere;
  //  @FXML
  //  public TextField enseingnant;
    @FXML
    public TextField heuredebut;
    @FXML
    public TextField heurefin ;
    @FXML
    public DatePicker date;
    @FXML
    public TextField type;

    public static TextField Fsalle;
    public static TextField Fheuredebut;
    public static TextField Fheurefin;
    public static TextField Fenseingnant;
    public static ChoiceBox<Matiere> Fmatiere;
    public static TextField Ffiliere;
    public static TextField Ftype;
    public static DatePicker Fdate;
    public static int  Iddd;


    @FXML
    public void initialize() throws SQLException {
    Fsalle=salle;
    Fdate = date;
    Fmatiere = matiere2;
    Fheuredebut = heuredebut;
    Fheurefin = heurefin;
    Ftype = type;

    int id = Iddd;
    System.out.println("idddddd====="+id);
    CoursDAO cd = CoursDAO.create();
    Cours cours = cd.findById(id);

        String type = String.valueOf(cours.getTypeCours());
        int IdMatiere = cours.getIdMatiere();
        int IdFiliere = cours.getIdFiliere();
        int IdEns = cours.getIdEns();
        String Date = String.valueOf(cours.getDate());
        int salle = cours.getIdSalle();
        String heuredebut = String.valueOf(cours.getHeureDebut());
        String heurefin = String.valueOf(cours.getHeureFin());
        //System.out.println("id filiere====="+IdMatiere);
        String Filiere = cd.GetFiliereById(IdFiliere);
        String Matiere = cd.GetMatiereById(IdMatiere);
        String Ens = cd.GetEnsById(IdEns);
        Fheurefin.setText(heurefin);
        Fheuredebut.setText(heuredebut);
        Ftype.setText(type);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(Date);
        Fsalle.setText(String.valueOf(salle));
        Fdate.setValue(localDate);

        List<Matiere> matieres = new ArrayList<>();

        //From Database to view
        MatiereDAO matiereDAO = MatiereDAO.create();
        matieres = matiereDAO.findAll();

        for (Classes.Matiere ma: matieres
        ) {
            matiere2.getItems().add(ma);
        }
        matiere2.setValue(matieres.get(IdMatiere));



        List<Filiere> filieres = new ArrayList<>();

        //From Database to view
        FiliereDAO f = FiliereDAO.create();
        filieres = f.findAll();

        for (Classes.Filiere ma: filieres
        ) {
            filiere2.getItems().add(ma);
        }
        filiere2.setValue(filieres.get(IdFiliere));


        List<Enseignant> ens = new ArrayList<>();

        //From Database to view
        EnseignantDAO E = EnseignantDAO.create();
        ens = E.findAll();

        for (Classes.Enseignant ma: ens
        ) {
            ens2.getItems().add(ma);
        }
        ens2.setValue(ens.get(IdEns));


    }




    @FXML
    public void save_action(ActionEvent event) throws SQLException, ParseException {



    int id = Iddd ;
        String type = Ftype.getText();
        String Date = String.valueOf(Fdate.getValue());
        int salle = Integer.parseInt(Fsalle.getText());
        String heuredebut = Fheuredebut.getText();
        String heurefin = Fheurefin.getText();
        CoursDAO cd2 = CoursDAO.create();
        int idmat2 = matiere2.getValue().getIdMat();;
        int idens2 = ens2.getValue().getIdEns();;
        int idfiliere2 = filiere2.getValue().getIdFiliere();;
        cd2.update(id,idfiliere2,idmat2,idens2,Date,heuredebut,heurefin,type,salle);
        System.out.println(idmat2);

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();




    }
}
