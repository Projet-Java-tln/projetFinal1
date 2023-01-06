package univtln.fr.projet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import Dao.CoursDAO;
import Dao.EnseignantDAO;
import Dao.FiliereDAO;
import Dao.MatiereDAO;
import Classes.Cours;
import Classes.Enseignant;
import Classes.Filiere;
import Classes.Matiere;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class addController {

    @FXML
    public DatePicker date;
    @FXML
    public ChoiceBox<Filiere> filiereChoiseBox;

    @FXML
    public ChoiceBox<Matiere> matiereChoiceBox;
    @FXML
    public ChoiceBox<Enseignant> enseignantChoiceBox;

    @FXML
    public TextField salle;


    @FXML
    public TextField heureDebut;
    @FXML
    public TextField heureFin;
    @FXML
    public TextField typecours;


    List<Matiere> matieres = new ArrayList<>();
    List<Filiere> filieres = new ArrayList<>();
    List<Enseignant> enseignants = new ArrayList<>();

    List<Cours.TypeCours> typeCours = new ArrayList<>();

    @FXML
    public void initialize() throws SQLException {

        heureDebut.setText(LocalTime.parse("08:00").toString());
        heureFin.setText(LocalTime.parse("19:00").toString());


        //Form Database to view
        FiliereDAO filiereDAO = FiliereDAO.create();
        filieres = filiereDAO.findAll();

        for (Filiere fi: filieres
        ) {
            filiereChoiseBox.getItems().add(fi);
        }
        filiereChoiseBox.setValue(filieres.get(0));



        // INIT ENSEIGNANT
        // From Database to view
        EnseignantDAO enseignantDAO = EnseignantDAO.create();
        enseignants = enseignantDAO.findAll();

        for (Enseignant ens: enseignants
        ) {
            enseignantChoiceBox.getItems().add(ens);
        }
        enseignantChoiceBox.setValue(enseignants.get(0));






        //From Database to view
        MatiereDAO matiereDAO = MatiereDAO.create();
        matieres = matiereDAO.findAll();

        for (Matiere ma: matieres
        ) {
            matiereChoiceBox.getItems().add(ma);
        }
        matiereChoiceBox.setValue(matieres.get(0));
    }


    public void ajouterCours(ActionEvent actionEvent) {

        LocalTime heureDebutTime = LocalTime.parse(heureDebut.getText());
        LocalTime heureFinTime = LocalTime.parse(heureFin.getText());
        Cours.TypeCours typc = Cours.TypeCours.valueOf(typecours.getText());
        int idSalle = Integer.parseInt(salle.getText());




//        int selectedIndex = matiereChoiceBox.getSelectionModel().getSelectedIndex();
//        Object selectedItem = matiereChoiceBox.getSelectionModel().getSelectedItem();
//        Matiere matiere = matieres.get(selectedIndex);

//        System.out.println(selectedItem);
//        System.out.println(matiereChoiceBox.getValue().getIdMat());

        //Matiere matiere = matiereChoiceBox.getValue();
        //Filiere filiere = filiereChoiseBox.getValue();
        //Cours.TypeCours typeCours = typeCoursChoiceBox.getValue();
        //Enseignant enseignant = enseignantChoiceBox.getValue();

        Enseignant enseignant = enseignantChoiceBox.getValue();
        Matiere matiere = matiereChoiceBox.getValue();
        //Matiere matiere  = matiereChoiceBox.getItems().get(0);
        //Filiere filiere = filiereChoiseBox.getItems().get(0);
        Filiere filiere = filiereChoiseBox.getValue();


        //int idSalle = 0;
        //int idEnseignant = 0;
        System.out.println("Matiere :"+ matiere);
        System.out.println("Enseignant :"+ enseignant.getIdEns());
        System.out.println("Filiere :"+ filiere.getIdFiliere());



        Cours cour = new Cours(20, date.getValue(), heureDebutTime, heureFinTime, enseignant.getIdEns(), filiere.getIdFiliere(), matiere.getIdMat(), idSalle,typc );

        try {
            CoursDAO coursDAO = CoursDAO.create();
            coursDAO.persist(cour);
            System.out.println(coursDAO.findAll());
            System.out.println("Le cours ete bien Inseré");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}