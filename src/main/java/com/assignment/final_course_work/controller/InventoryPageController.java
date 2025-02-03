package com.assignment.final_course_work.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class InventoryPageController {

    @FXML
    private ComboBox<?> MedicineOnAction;

    @FXML
    private AnchorPane cashierinventoryPane;

    @FXML
    private TableColumn<?, ?> colMedicineID;

    @FXML
    private TableColumn<?, ?> colMedicineName;

    @FXML
    private TableColumn<?, ?> colMedicineQuantity;

    @FXML
    private TableColumn<?, ?> colPacksQuantity;

    @FXML
    private TableColumn<?, ?> colexpiredDate;

    @FXML
    private Text lblAvailablePacks;

    @FXML
    private Text lblMedicineCategory;

    @FXML
    private Text lblMedicineID;

    @FXML
    private Text lblMedicineIDText;

    @FXML
    private Text lblMedicineIDText1;

    @FXML
    private Text lblMedicineIDText2;

    @FXML
    private Text lblMedicineIDText3;

    @FXML
    private Text lblMedicineIDText31;

    @FXML
    private Text lblMedicineIDText311;

    @FXML
    private Text lblMedicineIDText32;

    @FXML
    private Text lblMedicineName;

    @FXML
    private Text lblMedicines;

    @FXML
    private Text lblPackPrice;

    @FXML
    private Text lblUnitPrice;

    @FXML
    private TableView<?> tblInventory;

    @FXML
    void medicineOnActionOnAction(ActionEvent event) {

    }

    @FXML
    void selectMedicine(MouseEvent event) {

    }

}
