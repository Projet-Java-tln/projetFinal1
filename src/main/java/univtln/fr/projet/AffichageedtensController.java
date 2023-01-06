package univtln.fr.projet;

import Classes.Cours;
import Dao.AffichageDAO;
import Dao.CoursDAO;
import Dao.test1;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class AffichageedtensController extends AuthentificationController {


    String  nom = "text";
    @FXML
    private Label text;
    @FXML
    private GridPane gridens1,gridens2,gridens3,gridens4,gridens5,gridens6,gridens7,gridens8,gridens9,gridens10;
    @FXML
    private GridPane griddid1,griddid2,griddid3,griddid4,griddid5,griddid6,griddid7,griddid8,griddid9,griddid10;
    @FXML
    private GridPane gridmeef1,gridmeef2,gridmeef3,gridmeef4,gridmeef5,gridmeef6,gridmeef7,gridmeef8,gridmeef9,gridmeef10;
    @FXML
    private GridPane gridmiage1,gridmiage2,gridmiage3,gridmiage4,gridmiage5,gridmiage6,gridmiage7,gridmiage8,gridmiage9,gridmiage10;
    @FXML
    private GridPane gridrh1,gridrh2,gridrh3,gridrh4,gridrh5,gridrh6,gridrh7,gridrh8,gridrh9,gridrh10;
    @FXML
    private GridPane gridroit1,griddroit2,griddroit3,griddroit4,griddroit5,griddroit6,griddroit7,griddroit8,griddroit9,griddroit10;
    private GridPane grid1 =griddid1 ,grid2 =griddid2,grid3 =griddid3,grid4 = griddid4,grid5 =griddid5 ,grid6 = griddid6,grid7 = griddid7,grid8= griddid8,grid9 = griddid9,grid10 = griddid10;
    private String Query;
    @FXML
    private TabPane tabpan;

    List<String> Colors = Arrays.asList("LIGHTSKYBLUE", "PALETURQUOISE", "PINK", "SALMON","ORANGE" ,"SNOW" ,"MISTYROSE" );
    int Colonne = 1,IdCours;
    int Ligne = 1,IDFILIERE;

    @FXML
    private Label id2;
    @FXML
    private Tab did , meef, miage, droit, rh;

    @FXML
    void event(Event ev) {
        if(tabpan.getSelectionModel().getSelectedIndex()==0){ grid1 = griddid1 ;grid2 = griddid2; grid3 = griddid3;grid4 = griddid4;grid5 = griddid5; grid6 = griddid6;grid7 = griddid7;grid8 = griddid8;grid9 = griddid9;grid10 = griddid10;}
        if(tabpan.getSelectionModel().getSelectedIndex()==1){ grid1 = griddid1 ;grid2 = griddid2; grid3 = griddid3;grid4 = griddid4;grid5 = griddid5; grid6 = griddid6;grid7 = griddid7;grid8 = griddid8;grid9 = griddid9;grid10 = griddid10;IDFILIERE=0;}
        if(tabpan.getSelectionModel().getSelectedIndex()==5){ System.out.println("okbaby");grid1 = gridmeef1 ;grid2 = gridmeef2; grid3 = gridmeef3;grid4 = gridmeef4;grid5 = gridmeef5; grid6 = gridmeef6;grid7 = gridmeef7;grid8 = gridmeef8;grid9 = gridmeef9;grid10 = gridmeef10;IDFILIERE=2;}
        if(tabpan.getSelectionModel().getSelectedIndex()==4){ grid1 = gridrh1 ;grid2 = gridrh2; grid3 = gridrh3;grid4 = gridrh4;grid5 = gridrh5; grid6 = gridrh6;grid7 = gridrh7;grid8 = gridrh8;grid9 = gridrh9;grid10 = gridrh10;IDFILIERE=4;}
        if(tabpan.getSelectionModel().getSelectedIndex()==3){ grid1 = gridroit1;grid2 = griddroit2; grid3 = griddroit3;grid4 = griddroit4;grid5 = griddroit5; grid6 = griddroit6;grid7 = griddroit7;grid8 = griddroit8;grid9 = griddroit9;grid10 = griddroit10;IDFILIERE=1;}
        if(tabpan.getSelectionModel().getSelectedIndex()==2){System.out.println("okbaby"); grid1 = gridmiage1;grid2 = gridmiage2; grid3 = gridmiage3;grid4 = gridmiage4;grid5 = gridmiage5; grid6 = gridmiage6;grid7 = gridmiage7;grid8 = gridmiage8;grid9 = gridmiage9;grid10 = gridmiage10;IDFILIERE=3;}
        //if(miage.isSelected()){System.out.println("okbaby"); grid1 = gridmiage1;grid2 = gridmiage2; grid3 = gridmiage3;grid4 = gridmiage4;grid5 = gridmiage5; grid6 = gridmiage6;grid7 = gridmiage7;grid8 = gridmiage8;grid9 = gridmiage9;grid10 = gridmiage10;IDFILIERE=3;}
    }


    @FXML
    public void logout(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("login.fxml")));
        //Scene scene = new Scene(FXMLLoader.load(getClass().getResource("affichagedt.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() throws SQLException {
        //if(tabpan.getSelectionModel().getSelectedIndex()==0){;}


        //System.out.println("okbaby");
        CoursDAO cd = CoursDAO.create();
        List<Cours> CoursList2 =  cd.findByIdEns(idens);

        for (Cours c : CoursList2) {
           // if(tabpan.getSelectionModel().getSelectedIndex()==0){ grid1 = gridens1 ;grid2 = gridens2; grid3 = gridens3;grid4 = gridens4;grid5 = gridens5; grid6 = gridens6;grid7 = gridens7;grid8 = gridens8;grid9 = gridens9;grid10 = gridens10;}

            String date = String.valueOf(c.getDate());
            String IdEns = String.valueOf(c.getIdEns());
            String HeureDebut = String.valueOf(c.getHeureDebut());
            String TypeCours = String.valueOf(c.getTypeCours());
            int IdMatiere = c.getIdMatiere();
            int IdFiliere = c.getIdFiliere();
            int IdSalle = c.getIdSalle();

            Label[ ] Labels = new Label[100];
            for( int i=0 ; i<100 ; i++) {
                Labels[i] = new Label("-");
            }
            Query = "Select \"Nom\",\"Prenom\",\"NomMatiere\",\"Filiere\",\"IdSalle\" from \"Enseignant\",\"Matiere\",\"Filiere\",\"Salle\" where \"IdEns\"="+IdEns+" and \"IdMat\"="+IdMatiere+" and \"IdFiliere\"="+IdFiliere+" and \"IdSalle\"="+IdSalle;
            // System.out.println(Query);
            try (Connection connection = DriverManager.getConnection(test1.DB_URL, test1.USER, test1.PASS);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(Query);){
                while (resultSet.next()) {


                    //  System.out.println("ok");
                    String Matiere = resultSet.getString("NomMatiere");
                    String NomProf = resultSet.getString("Nom");
                    String PrenomProf = resultSet.getString("Prenom");
                    IdSalle = resultSet.getInt("IdSalle");
                    String Filiere = resultSet.getString("Filiere");
                    String CoursText = " " + TypeCours + " - " + Matiere + " " + NomProf + " " + PrenomProf + " - " + Filiere;


                    if (date.equals("2023-01-09") || date.equals("2023-01-16") || date.equals("2023-01-23") || date.equals("2023-01-30") || date.equals("2023-02-06") || date.equals("2023-02-13") || date.equals("2023-02-20") || date.equals("2023-02-27") || date.equals("2023-03-06") || date.equals("2023-03-13")) {
                        Colonne = 1;
                    } else if (date.equals("2023-01-10") || date.equals("2023-01-17") || date.equals("2023-01-24") || date.equals("2023-01-31") || date.equals("2023-02-07") || date.equals("2023-02-14") || date.equals("2023-02-21") || date.equals("2023-02-28") || date.equals("2023-03-07") || date.equals("2023-03-14")) {
                        Colonne = 2;
                    } else if (date.equals("2023-01-11") || date.equals("2023-01-18") || date.equals("2023-01-25") || date.equals("2023-02-01") || date.equals("2023-02-08") || date.equals("2023-02-15") || date.equals("2023-02-22") || date.equals("2023-03-01") || date.equals("2023-03-08") || date.equals("2023-03-15")) {
                        Colonne = 3;
                    } else if (date.equals("2023-01-12") || date.equals("2023-01-19") || date.equals("2023-01-26") || date.equals("2023-02-02") || date.equals("2023-02-09") || date.equals("2023-02-16") || date.equals("2023-02-23") || date.equals("2023-03-02") || date.equals("2023-03-09") || date.equals("2023-03-16")) {
                        Colonne = 4;
                    } else if (date.equals("2023-01-13") || date.equals("2023-01-20") || date.equals("2023-01-27") || date.equals("2023-02-03") || date.equals("2023-02-10") || date.equals("2023-02-17") || date.equals("2023-02-24") || date.equals("2023-03-03") || date.equals("2023-03-10") || date.equals("2023-03-17")) {
                        Colonne = 5;
                    }
                    if (HeureDebut.equals("08:00")) {
                        //   System.out.println("okkokokokok");
                        Ligne = 1;
                    } else if (HeureDebut.equals("10:00")) {
                        Ligne = 2;
                    } else if (HeureDebut.equals("12:00")) {
                        Ligne = 3;
                    } else if (HeureDebut.equals("14:00")) {
                        Ligne = 4;
                    } else if (HeureDebut.equals("16:00")) {
                        Ligne = 5;
                    } else if (HeureDebut.equals("18:00")) {
                        Ligne = 6;
                    }

                    Labels[IdCours].setStyle("-fx-background-color:" + Colors.get(IdMatiere));
                    Labels[IdCours].setText(CoursText);
                    Labels[IdCours].setWrapText(true);
                    Labels[IdCours].setMaxHeight(80);

                    if ((date.equals("2023-01-10") || date.equals("2023-01-09") || date.equals("2023-01-11") || date.equals("2023-01-12") || date.equals("2023-01-13"))) {
                        gridens1.setConstraints(Labels[IdCours], Colonne, Ligne);
                        gridens1.getChildren().addAll(Labels[IdCours]);
                    }
                    if ((date.equals("2023-01-17") || date.equals("2023-01-18") || date.equals("2023-01-19") || date.equals("2023-01-20") || date.equals("2023-01-16"))) {
                        gridens2.setConstraints(Labels[IdCours], Colonne, Ligne);
                        gridens2.getChildren().addAll(Labels[IdCours]);
                    }
                    if ((date.equals("2023-01-23") || date.equals("2023-01-24") || date.equals("2023-01-25") || date.equals("2023-01-26") || date.equals("2023-01-27"))) {
                        gridens3.setConstraints(Labels[IdCours], Colonne, Ligne);
                        gridens3.getChildren().addAll(Labels[IdCours]);
                    }
                    if ((date.equals("2023-01-30") || date.equals("2023-01-31") || date.equals("2023-02-01") || date.equals("2023-02-02") || date.equals("2023-02-03"))) {
                        gridens4.setConstraints(Labels[IdCours], Colonne, Ligne);
                        gridens4.getChildren().addAll(Labels[IdCours]);
                    }
                    if ((date.equals("2023-02-06") || date.equals("2023-02-07") || date.equals("2023-02-08") || date.equals("2023-02-09") || date.equals("2023-02-10"))) {
                        gridens5.setConstraints(Labels[IdCours], Colonne, Ligne);
                        gridens5.getChildren().addAll(Labels[IdCours]);
                    }
                    if ((date.equals("2023-02-13") || date.equals("2023-02-14") || date.equals("2023-02-15") || date.equals("2023-02-16") || date.equals("2023-02-17"))) {
                        gridens6.setConstraints(Labels[IdCours], Colonne, Ligne);
                        gridens6.getChildren().addAll(Labels[IdCours]);
                    }
                    if ((date.equals("2023-02-20") || date.equals("2023-02-21") || date.equals("2023-02-22") || date.equals("2023-02-23") || date.equals("2023-02-24"))) {
                        gridens7.setConstraints(Labels[IdCours], Colonne, Ligne);
                        gridens7.getChildren().addAll(Labels[IdCours]);
                    }
                    if ((date.equals("2023-02-27") || date.equals("2023-02-28") || date.equals("2023-03-01") || date.equals("2023-03-02") || date.equals("2023-03-03"))) {
                        gridens8.setConstraints(Labels[IdCours], Colonne, Ligne);
                        gridens8.getChildren().addAll(Labels[IdCours]);
                    }
                    if ((date.equals("2023-03-06") || date.equals("2023-03-07") || date.equals("2023-03-08") || date.equals("2023-03-09") || date.equals("2023-03-10"))) {
                        gridens9.setConstraints(Labels[IdCours], Colonne, Ligne);
                        gridens9.getChildren().addAll(Labels[IdCours]);
                    }
                    if ((date.equals("2023-03-13") || date.equals("2023-03-14") || date.equals("2023-03-15") || date.equals("2023-03-16") || date.equals("2023-03-17"))) {
                        gridens10.setConstraints(Labels[IdCours], Colonne, Ligne);
                        gridens10.getChildren().addAll(Labels[IdCours]);
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }



        }
        if(tabpan.getSelectionModel().getSelectedIndex()==0){ grid1 = griddid1 ;grid2 = griddid2; grid3 = griddid3;grid4 = griddid4;grid5 = griddid5; grid6 = griddid6;grid7 = griddid7;grid8 = griddid8;grid9 = griddid9;grid10 = griddid10;}
        List<Cours> CoursList = cd.findAllWhereFiliere(IDFILIERE);
        for (Cours c : CoursList) {

            String date = String.valueOf(c.getDate());
            String IdEns = String.valueOf(c.getIdEns());
            String HeureDebut = String.valueOf(c.getHeureDebut());
            String TypeCours = String.valueOf(c.getTypeCours());
            int IdMatiere = c.getIdMatiere();
            int IdFiliere = c.getIdFiliere();
            int IdSalle = c.getIdSalle();

            Label[ ] Labels = new Label[100];
            for( int i=0 ; i<100 ; i++) {
                Labels[i] = new Label("-");
            }
            Query = "Select \"Nom\",\"Prenom\",\"NomMatiere\",\"Filiere\",\"IdSalle\" from \"Enseignant\",\"Matiere\",\"Filiere\",\"Salle\" where \"IdEns\"="+IdEns+" and \"IdMat\"="+IdMatiere+" and \"IdFiliere\"="+IdFiliere+" and \"IdSalle\"="+IdSalle;
            // System.out.println(Query);
            try (Connection connection = DriverManager.getConnection(test1.DB_URL, test1.USER, test1.PASS);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(Query);){
                while (resultSet.next()) {


                    //  System.out.println("ok");
                    String Matiere = resultSet.getString("NomMatiere");
                    String NomProf = resultSet.getString("Nom");
                    String PrenomProf = resultSet.getString("Prenom");
                    IdSalle = resultSet.getInt("IdSalle");
                    String Filiere = resultSet.getString("Filiere");
                    String CoursText = " " + TypeCours + " - " + Matiere + " " + NomProf + " " + PrenomProf + " - " + Filiere;


                    if (date.equals("2023-01-09") || date.equals("2023-01-16") || date.equals("2023-01-23") || date.equals("2023-01-30") || date.equals("2023-02-06") || date.equals("2023-02-13") || date.equals("2023-02-20") || date.equals("2023-02-27") || date.equals("2023-03-06") || date.equals("2023-03-13")) {
                        Colonne = 1;
                    } else if (date.equals("2023-01-10") || date.equals("2023-01-17") || date.equals("2023-01-24") || date.equals("2023-01-31") || date.equals("2023-02-07") || date.equals("2023-02-14") || date.equals("2023-02-21") || date.equals("2023-02-28") || date.equals("2023-03-07") || date.equals("2023-03-14")) {
                        Colonne = 2;
                    } else if (date.equals("2023-01-11") || date.equals("2023-01-18") || date.equals("2023-01-25") || date.equals("2023-02-01") || date.equals("2023-02-08") || date.equals("2023-02-15") || date.equals("2023-02-22") || date.equals("2023-03-01") || date.equals("2023-03-08") || date.equals("2023-03-15")) {
                        Colonne = 3;
                    } else if (date.equals("2023-01-12") || date.equals("2023-01-19") || date.equals("2023-01-26") || date.equals("2023-02-02") || date.equals("2023-02-09") || date.equals("2023-02-16") || date.equals("2023-02-23") || date.equals("2023-03-02") || date.equals("2023-03-09") || date.equals("2023-03-16")) {
                        Colonne = 4;
                    } else if (date.equals("2023-01-13") || date.equals("2023-01-20") || date.equals("2023-01-27") || date.equals("2023-02-03") || date.equals("2023-02-10") || date.equals("2023-02-17") || date.equals("2023-02-24") || date.equals("2023-03-03") || date.equals("2023-03-10") || date.equals("2023-03-17")) {
                        Colonne = 5;
                    }
                    if (HeureDebut.equals("08:00")) {
                        //   System.out.println("okkokokokok");
                        Ligne = 1;
                    } else if (HeureDebut.equals("10:00")) {
                        Ligne = 2;
                    } else if (HeureDebut.equals("12:00")) {
                        Ligne = 3;
                    } else if (HeureDebut.equals("14:00")) {
                        Ligne = 4;
                    } else if (HeureDebut.equals("16:00")) {
                        Ligne = 5;
                    } else if (HeureDebut.equals("18:00")) {
                        Ligne = 6;
                    }

                    Labels[IdCours].setStyle("-fx-background-color:" + Colors.get(IdMatiere));
                    Labels[IdCours].setText(CoursText);
                    Labels[IdCours].setWrapText(true);
                    Labels[IdCours].setMaxHeight(80);

                    if ((date.equals("2023-01-10") || date.equals("2023-01-09") || date.equals("2023-01-11") || date.equals("2023-01-12") || date.equals("2023-01-13"))) {
                        grid1.setConstraints(Labels[IdCours], Colonne, Ligne);
                        grid1.getChildren().addAll(Labels[IdCours]);
                    }
                    if ((date.equals("2023-01-17") || date.equals("2023-01-18") || date.equals("2023-01-19") || date.equals("2023-01-20") || date.equals("2023-01-16"))) {
                        grid2.setConstraints(Labels[IdCours], Colonne, Ligne);
                        grid2.getChildren().addAll(Labels[IdCours]);
                    }
                    if ((date.equals("2023-01-23") || date.equals("2023-01-24") || date.equals("2023-01-25") || date.equals("2023-01-26") || date.equals("2023-01-27"))) {
                        grid3.setConstraints(Labels[IdCours], Colonne, Ligne);
                        grid3.getChildren().addAll(Labels[IdCours]);
                    }
                    if ((date.equals("2023-01-30") || date.equals("2023-01-31") || date.equals("2023-02-01") || date.equals("2023-02-02") || date.equals("2023-02-03"))) {
                        grid4.setConstraints(Labels[IdCours], Colonne, Ligne);
                        grid4.getChildren().addAll(Labels[IdCours]);
                    }
                    if ((date.equals("2023-02-06") || date.equals("2023-02-07") || date.equals("2023-02-08") || date.equals("2023-02-09") || date.equals("2023-02-10"))) {
                        grid5.setConstraints(Labels[IdCours], Colonne, Ligne);
                        grid5.getChildren().addAll(Labels[IdCours]);
                    }
                    if ((date.equals("2023-02-13") || date.equals("2023-02-14") || date.equals("2023-02-15") || date.equals("2023-02-16") || date.equals("2023-02-17"))) {
                        grid6.setConstraints(Labels[IdCours], Colonne, Ligne);
                        grid6.getChildren().addAll(Labels[IdCours]);
                    }
                    if ((date.equals("2023-02-20") || date.equals("2023-02-21") || date.equals("2023-02-22") || date.equals("2023-02-23") || date.equals("2023-02-24"))) {
                        grid7.setConstraints(Labels[IdCours], Colonne, Ligne);
                        grid7.getChildren().addAll(Labels[IdCours]);
                    }
                    if ((date.equals("2023-02-27") || date.equals("2023-02-28") || date.equals("2023-03-01") || date.equals("2023-03-02") || date.equals("2023-03-03"))) {
                        grid8.setConstraints(Labels[IdCours], Colonne, Ligne);
                        grid8.getChildren().addAll(Labels[IdCours]);
                    }
                    if ((date.equals("2023-03-06") || date.equals("2023-03-07") || date.equals("2023-03-08") || date.equals("2023-03-09") || date.equals("2023-03-10"))) {
                        grid9.setConstraints(Labels[IdCours], Colonne, Ligne);
                        grid9.getChildren().addAll(Labels[IdCours]);
                    }
                    if ((date.equals("2023-03-13") || date.equals("2023-03-14") || date.equals("2023-03-15") || date.equals("2023-03-16") || date.equals("2023-03-17"))) {
                        grid10.setConstraints(Labels[IdCours], Colonne, Ligne);
                        grid10.getChildren().addAll(Labels[IdCours]);
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }



        }
    }


}
















