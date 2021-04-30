package vehicule.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import vehicule.model.Modele;
import vehicule.model.Proprietaire;
import vehicule.model.Vehicule;
import vehicule.utils.Fabrique;
import vehicule.utils.Utils;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class vehiculeController implements Initializable {

        @FXML
        private TextField txtCode;

        @FXML
        private TextField txtCouleur;

        @FXML
        private TextField txtMarque;

        @FXML
        private ChoiceBox<Proprietaire> cbxProprietaire;

        @FXML
        private ChoiceBox<Modele> cbxModel;

        @FXML
        private TableColumn<Vehicule, String> colCode;

        @FXML
        private TableColumn<Vehicule, String> colCouleur;

        @FXML
        private TableColumn<Vehicule, String> colMarque;

        @FXML
        private TableColumn<Vehicule, String> colProprietaire;

        @FXML
        private TableColumn<Vehicule, String> colModel;

        @FXML
        private Button BtnEnregistrer;

        @FXML
        private Button BtnSupprimer;

        @FXML
        private Button BtnModifier;

        @FXML
        private TableView<Vehicule> tableVehivule;



    @FXML
    void add(ActionEvent event) {
        String code = txtCode.getText().trim();
        String couleur = txtCouleur.getText().trim();
        String marque = txtMarque.getText().trim();
        if(code.equals("") || couleur.equals("") || marque.equals("")){
            Utils.showMessage("Gestion VEHICULE","Veillez Remplir tout les champs !!!","!");
        }else {
            Vehicule v = new Vehicule();
            v.setCode(code);
            v.setCouleur(couleur);
            v.setMarque(marque);
            v.setModele(cbxModel.getSelectionModel().getSelectedItem());
            v.setProprietaire(cbxProprietaire.getSelectionModel().getSelectedItem());
            try {
                Fabrique.getiVehicule().add(v);
                txtCode.setText(Fabrique.getiVehicule().createMatricule());
                showTable();
                viderChamps();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    void Update(ActionEvent event) {
        String code = txtCode.getText().trim();
        String couleur = txtCouleur.getText().trim();
        String marque = txtMarque.getText().trim();
        if(code.equals("") || couleur.equals("") || marque.equals("")){
            Utils.showMessage("Gestion VEHICULE","Veillez Remplir tout les champs !!!","!");
        }else {
            Vehicule v = new Vehicule();
            v.setCode(code);
            v.setCouleur(couleur);
            v.setMarque(marque);
            v.setModele(cbxModel.getSelectionModel().getSelectedItem());
            v.setProprietaire(cbxProprietaire.getSelectionModel().getSelectedItem());
            try {
                Fabrique.getiVehicule().add(v);
                txtCode.setText(Fabrique.getiVehicule().createMatricule());
                showTable();
                viderChamps();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @FXML
    void delete(ActionEvent event) {
        if(Utils.confirmMessage("Gestion des Employés","Suppression Employé", "Etes-Vous Sur ???")){

            Vehicule vehicule = tableVehivule.getSelectionModel().getSelectedItem();

            try {
                Fabrique.getiVehicule().delete(vehicule);
                //showTable();
            }catch (Exception ex){

            }
        }
    }

    private void selectElement()
    {
        tableVehivule.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            BtnSupprimer.setDisable(false);
            BtnEnregistrer.setDisable(true);
            if(newValue!=null){
                txtCode.setText(newValue.getCode());
                txtCouleur.setText(newValue.getCouleur());
                txtMarque.setText(newValue.getMarque());
                Modele mod = null;
                for(Modele m:cbxModel.getItems()){
                    if(m.getLibelle().toLowerCase().equals(newValue.getModele().getLibelle().toLowerCase())){
                        mod = m;
                        break;
                    }
                }
                cbxModel.getSelectionModel().select(mod);
                Proprietaire pro = null;
                for (Proprietaire p : cbxProprietaire.getItems()){
                    if(p.getNom().toLowerCase().equals(newValue.getProprietaire().getNom().toLowerCase())){
                        pro = p;
                    }
                }
                cbxProprietaire.getSelectionModel().select(pro);
            }
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showTable();
        selectElement();
        try{
            txtCode.setText(Fabrique.getiVehicule().createMatricule());
            List<Modele> modeleList= Fabrique.getiModele().findAll();
            cbxModel.setItems(FXCollections.observableArrayList(modeleList));
            List<Proprietaire> proprietaireList= Fabrique.getiProprietaire().findAll();
            cbxProprietaire.setItems(FXCollections.observableArrayList(proprietaireList));

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void showTable() {
        try {
            List<Vehicule> listVehicule = Fabrique.getiVehicule().findAll();
            if (listVehicule != null) {
                colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
                colCouleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));
                colMarque.setCellValueFactory(new PropertyValueFactory<>("marque"));
                colModel.setCellValueFactory(foo -> {
                    String modele = foo.getValue().getModele().getLibelle();
                    ObservableValue<String> val = new SimpleObjectProperty<>(modele);
                    return val;
                });
                colProprietaire.setCellValueFactory(foo -> {
                    String prop = foo.getValue().getProprietaire().getNom() + " " + foo.getValue().getProprietaire().getPrenom();
                    ObservableValue<String> val = new SimpleObjectProperty<>(prop);
                    return val;
                });
                tableVehivule.getItems().clear();
                tableVehivule.setItems(FXCollections.observableArrayList(listVehicule));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viderChamps()
    {
        cbxModel.getSelectionModel().clearSelection();
        cbxProprietaire.getSelectionModel().clearSelection();
        txtMarque.setText("");
        txtCouleur.setText("");
        BtnEnregistrer.setDisable(false);
    }
}
