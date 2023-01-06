package univtln.fr.projet;

import Classes.Cours;
import Dao.AffichageDAO;
import Dao.CoursDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AdminController extends editController{
    String nom = "text";

    @FXML
    private Label text;
    @FXML
    private GridPane griddid1, griddid2, griddid3, griddid4, griddid5, griddid6, griddid7, griddid8, griddid9, griddid10;
    @FXML
    private GridPane gridmeef1, gridmeef2, gridmeef3, gridmeef4, gridmeef5, gridmeef6, gridmeef7, gridmeef8, gridmeef9, gridmeef10;
    @FXML
    private GridPane gridmiage1, gridmiage2, gridmiage3, gridmiage4, gridmiage5, gridmiage6, gridmiage7, gridmiage8, gridmiage9, gridmiage10;
    @FXML
    private GridPane gridrh1, gridrh2, gridrh3, gridrh4, gridrh5, gridrh6, gridrh7, gridrh8, gridrh9, gridrh10;
    @FXML
    private GridPane gridroit1, griddroit2, griddroit3, griddroit4, griddroit5, griddroit6, griddroit7, griddroit8, griddroit9, griddroit10;
    private GridPane grid1 = griddid1, grid2 = griddid2, grid3 = griddid3, grid4 = griddid4, grid5 = griddid5, grid6 = griddid6, grid7 = griddid7, grid8 = griddid8, grid9 = griddid9, grid10 = griddid10;
    private String Query;
    @FXML
    private TabPane tabpan;

    List<String> Colors = Arrays.asList("LIGHTSKYBLUE", "PALETURQUOISE", "PINK", "SALMON", "ORANGE", "SNOW", "MISTYROSE");
    int Colonne = 1, IdCours;
    int Ligne = 1, IDFILIERE;

    @FXML
    private Label id2;
    @FXML
    private Tab did, meef, miage, droit, rh;
    public static String DB_URL = "jdbc:postgresql://localhost:5432/Projet_edt";
    public static String USER = "postgres";
    public static String PASS = "syfsyf";
    private int matrix=0;

    private ContextMenu popupmenu2 = new ContextMenu();
    private MenuItem item1 = new MenuItem("Modifier");
    private MenuItem item2 = new MenuItem("Supprimer");
    private int clicked;
    private Label[] Labels;
    private Label toremove;

    public AdminController() throws IOException {
    }

    @FXML
    void event() {
        if (tabpan.getSelectionModel().getSelectedIndex() == 0) {
            grid1 = griddid1;
            grid2 = griddid2;
            grid3 = griddid3;
            grid4 = griddid4;
            grid5 = griddid5;
            grid6 = griddid6;
            grid7 = griddid7;
            grid8 = griddid8;
            grid9 = griddid9;
            grid10 = griddid10;
            IDFILIERE = 0;
        }
        if (tabpan.getSelectionModel().getSelectedIndex() == 4) {
            System.out.println("okbaby");
            grid1 = gridmeef1;
            grid2 = gridmeef2;
            grid3 = gridmeef3;
            grid4 = gridmeef4;
            grid5 = gridmeef5;
            grid6 = gridmeef6;
            grid7 = gridmeef7;
            grid8 = gridmeef8;
            grid9 = gridmeef9;
            grid10 = gridmeef10;
            IDFILIERE = 2;
        }
        if (tabpan.getSelectionModel().getSelectedIndex() == 3) {
            grid1 = gridrh1;
            grid2 = gridrh2;
            grid3 = gridrh3;
            grid4 = gridrh4;
            grid5 = gridrh5;
            grid6 = gridrh6;
            grid7 = gridrh7;
            grid8 = gridrh8;
            grid9 = gridrh9;
            grid10 = gridrh10;
            IDFILIERE = 4;
        }
        if (tabpan.getSelectionModel().getSelectedIndex() == 2) {
            grid1 = gridroit1;
            grid2 = griddroit2;
            grid3 = griddroit3;
            grid4 = griddroit4;
            grid5 = griddroit5;
            grid6 = griddroit6;
            grid7 = griddroit7;
            grid8 = griddroit8;
            grid9 = griddroit9;
            grid10 = griddroit10;
            IDFILIERE = 1;
        }
        if (tabpan.getSelectionModel().getSelectedIndex() == 1) {
            System.out.println("okbaby");
            grid1 = gridmiage1;
            grid2 = gridmiage2;
            grid3 = gridmiage3;
            grid4 = gridmiage4;
            grid5 = gridmiage5;
            grid6 = gridmiage6;
            grid7 = gridmiage7;
            grid8 = gridmiage8;
            grid9 = gridmiage9;
            grid10 = gridmiage10;
            IDFILIERE = 3;
        }
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
       // Image openIcon = new Image(getClass().getResourceAsStream("edit.png"));
       // ImageView openView = new ImageView(openIcon);
       // openView.setFitWidth(15);
      //  openView.setFitHeight(15);
      //  item1.setGraphic(openView);

        popupmenu2.getItems().addAll(item1, item2);
        if (tabpan.getSelectionModel().getSelectedIndex() == 0) {
            grid1 = griddid1;
            grid2 = griddid2;
            grid3 = griddid3;
            grid4 = griddid4;
            grid5 = griddid5;
            grid6 = griddid6;
            grid7 = griddid7;
            grid8 = griddid8;
            grid9 = griddid9;
            grid10 = griddid10;
        }


        if(matrix==0){
            Labels = new Label[100];
        for (int i = 0; i < 100; i++) {
            Labels[i] = new Label("-");
            int finalI = i;
            Labels[i].setOnMouseClicked(e ->
            {
                if (e.getButton() == MouseButton.SECONDARY)
                {
                    System.out.println("WAZAaaaaaaaaaaaBI"+finalI);
                    clicked = finalI;
                    popupmenu2.show(Labels[finalI], e.getSceneX(), e.getSceneY());
                }
            });}}
        CoursDAO cd = CoursDAO.create();
        //System.out.println("okbaby");
        List<Cours> CoursList = cd.findAllWhereFiliere(IDFILIERE);
        System.out.println("okbaby");

        item2.setOnAction(e -> {
            try {
                matrix++;
                cd.remove(clicked);
                //grid2.getChildren().clear();
                toremove = Labels[clicked];
                System.out.println("dkhlna bach nhydo :"+ toremove);
                grid2.getChildren().remove(toremove);
                //grid2.getChildren().clear();
                    Labels[clicked]=null;
                //grid2.setGridLinesVisible(false);
               // Labels = null;
                System.out.println("2 dkhlna bach nhydo :"+ clicked);
                //Labels = null;
                //grid1.getChildren().removeAll();
                grid3.getChildren().removeAll();
                grid4.getChildren().removeAll();
                grid5.getChildren().removeAll();
                grid6.getChildren().removeAll();
                grid7.getChildren().removeAll();
                grid8.getChildren().removeAll();
                grid9.getChildren().removeAll();
                grid10.getChildren().removeAll();
                initialize();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("cours num :" + clicked + "  clicked");
        });
  //         Labels[i].setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
     ///           @Override
   //             public void handle(ContextMenuEvent e) {
                    //popupmenu.show(Labels[finalI], e.getSceneX(), e.getSceneY());
                    //popupmenu.show(Labels[finalI], 200, 200);
   //                 System.out.println("WAZABI"+finalI);
     //               popupmenu2.show(Labels[20], e.getSceneX(), e.getSceneY());

      //              Labels[finalI].setOnMouseClicked(E ->
       //             {
       //                 if (E.getButton() == MouseButton.SECONDARY) {
       //                     System.out.println("WAZABI"+finalI);


     ///                   }

     //               });

     //           }
      //      });


        for (Cours c : CoursList) {
            String date = String.valueOf(c.getDate());
            String IdEns = String.valueOf(c.getIdEns());
            String HeureDebut = String.valueOf(c.getHeureDebut());
            String HeureFin = String.valueOf(c.getHeureFin());

            String TypeCours = String.valueOf(c.getTypeCours());
            int IdMatiere = c.getIdMatiere();
            int IdFiliere = c.getIdFiliere();
            int IdSalle = c.getIdSalle();
            int IdCours = c.getIdCours();
            String finalIdSalle = String.valueOf(IdSalle);
            String finalIdFiliere = String.valueOf(IdFiliere);
            String finalIdMatiere = String.valueOf(IdMatiere);


            System.out.println("idsalle int: "+IdSalle);
            System.out.println("idsalle string: "+finalIdSalle);


            Query = "Select \"Nom\",\"Prenom\",\"NomMatiere\",\"Filiere\",\"IdSalle\" from \"Enseignant\",\"Matiere\",\"Filiere\",\"Salle\" where \"IdEns\"=" + IdEns + " and \"IdMat\"=" + IdMatiere + " and \"IdFiliere\"=" + IdFiliere + " and \"IdSalle\"=" + IdSalle;
            // System.out.println(Query);
            try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(Query);) {
                while (resultSet.next()) {
                    //System.out.println("kidkhel");
                    //  System.out.println("ok");
                    String Matiere = resultSet.getString("NomMatiere");
                    String NomProf = resultSet.getString("Nom");
                    String PrenomProf = resultSet.getString("Prenom");
                    IdSalle = resultSet.getInt("IdSalle");
                    String Filiere = resultSet.getString("Filiere");
                    String CoursText = "" + IdCours + " " + TypeCours + " - " + Matiere + " " + NomProf + " " + PrenomProf + " - " + Filiere;

                    item1.setOnAction(e -> {
                        try {

                            //Parent fxmlLoader = FXMLLoader.load(getClass().getResource("edit.fxml"));
                            //Scene s =new Scene(fxmlLoader);
                            Stage stage = new Stage();
                            //stage.setScene(s);
                            //stage.initModality(Modality.APPLICATION_MODAL);
                            //stage.show()
                            System.out.println("MODIIIF:");
                            //Node node = (Node) e.getSource();
                            //Stage stage = (Stage) node.getScene().getWindow();
                            //  stage.close();
                            Iddd = clicked;
                            //System.out.println("idcours :" + Iddd);
                            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("edit.fxml")));

                            //Fsalle.setText(IdEns);
                            //F/filiere.setText(Filiere);
                            //Fmatiere.setText(Matiere);
                            //Fheuredebut.setText(HeureDebut);
                            //Fheurefin.setText(HeureFin);
                            //Fenseingnant.setText(NomProf + " " +PrenomProf);
                            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            //LocalDate localDate = LocalDate.parse(date);
                            //Fdate.setValue(localDate);
                            //Ftype.setText(TypeCours);
                            stage.setScene(scene);
                            stage.show();
                        } catch(Exception E) {
                            E.printStackTrace();
                        }
                    });

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
                   if (matrix==0){
                       System.out.println("matriiiiixxxxx = "+matrix);
                    Labels[IdCours].setStyle("-fx-background-color:" + Colors.get(IdMatiere));
                    Labels[IdCours].setText(CoursText);
                    Labels[IdCours].setWrapText(true);
                    Labels[IdCours].setMaxHeight(80);}

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
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }

}
















