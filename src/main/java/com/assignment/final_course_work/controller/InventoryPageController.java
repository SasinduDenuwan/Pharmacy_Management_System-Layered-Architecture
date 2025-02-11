package com.assignment.final_course_work.controller;

import com.assignment.final_course_work.bo.BOFactory;
import com.assignment.final_course_work.bo.MedicineBO;
import com.assignment.final_course_work.dao.Custom.MedicineDAO;
import com.assignment.final_course_work.dao.Custom.impl.MedicineDAOImpl;
import com.assignment.final_course_work.dto.MedicineDTO;
import com.assignment.final_course_work.entity.Customer;
import com.assignment.final_course_work.entity.Medicine;
import com.assignment.final_course_work.util.Regex;
import com.assignment.final_course_work.view.tdm.CustomerTM;
import com.assignment.final_course_work.view.tdm.MedicineTM;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class InventoryPageController implements Initializable {

    @FXML
    private JFXButton btnDeleteMedicine;

    @FXML
    private JFXButton btnSaveMedicine;

    @FXML
    private JFXButton btnUpdateMedicine;

    @FXML
    private Text lblMedicineID;

    @FXML
    private TableColumn<MedicineTM, String> medicineID;

    @FXML
    private TableColumn<MedicineTM, String> medicineName;

    @FXML
    private AnchorPane medicinePage;

    @FXML
    private TableView<MedicineTM> medicineTabke;

    @FXML
    private TableColumn<MedicineTM, Double> packsPrice;

    @FXML
    private TableColumn<MedicineTM, Integer> packsQTY;

    @FXML
    private JFXTextField txtMedicineName;

    @FXML
    private TextField txtPacksPrice;

    @FXML
    private TextField txtPacksQTY;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private TextField txtUnitQTY;

    @FXML
    private TableColumn<MedicineTM, Double> untiPrice;

    @FXML
    private TableColumn<MedicineTM, Integer> untiQTY;

    private MedicineBO medicineBO = (MedicineBO) BOFactory.getInstance().getBO(BOFactory.BOType.MEDICINE);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        medicineID.setCellValueFactory(new PropertyValueFactory<>("medicineID"));
        medicineName.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        untiQTY.setCellValueFactory(new PropertyValueFactory<>("unitQty"));
        packsQTY.setCellValueFactory(new PropertyValueFactory<>("packQty"));
        untiPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        packsPrice.setCellValueFactory(new PropertyValueFactory<>("packPrice"));

        try {
            refreshPage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void refreshPage() throws SQLException, ClassNotFoundException {

        refreshTable();

        lblMedicineID.setText(medicineBO.generateNewID());
        txtMedicineName.clear();
        txtUnitQTY.clear();
        txtPacksQTY.clear();
        txtUnitPrice.clear();
        txtPacksPrice.clear();

        btnSaveMedicine.setDisable(false);
        btnDeleteMedicine.setDisable(true);
        btnUpdateMedicine.setDisable(true);
    }

    private void refreshTable() throws SQLException, ClassNotFoundException {
        ArrayList<MedicineDTO> medicines = medicineBO.getAll();
        ObservableList<MedicineTM> medicineTMS = FXCollections.observableArrayList();

        for (MedicineDTO medicine : medicines) {
            medicineTMS.add(new MedicineTM(
                    medicine.getMedicineID(),
                    medicine.getMedicineName(),
                    medicine.getUnitPrice(),
                    medicine.getPackPrice(),
                    medicine.getUnitQty(),
                    medicine.getPackQty()
            ));
        }
        medicineTabke.setItems(medicineTMS);
    }

    @FXML
    void deleteMedicineOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this medicine?", ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            if (medicineBO.delete(lblMedicineID.getText())) {
                refreshPage();
                refreshTable();
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Failed to delete medicine.");
                errorAlert.showAndWait();
            }
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        MedicineTM selectedItems = medicineTabke.getSelectionModel().getSelectedItem();
        if (selectedItems!= null) {

            btnSaveMedicine.setDisable(true);
            btnDeleteMedicine.setDisable(false);
            btnUpdateMedicine.setDisable(false);

            lblMedicineID.setText(selectedItems.getMedicineID());
            txtMedicineName.setText(selectedItems.getMedicineName());
            txtUnitQTY.setText(String.valueOf(selectedItems.getUnitQty()));
            txtPacksQTY.setText(String.valueOf(selectedItems.getPackQty()));
            txtUnitPrice.setText(String.valueOf(selectedItems.getUnitPrice()));
            txtPacksPrice.setText(String.valueOf(selectedItems.getPackPrice()));
        }
    }

    @FXML
    void saveMedicineOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to Save this medicine ? ", ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES) {

                if (medicineBO.save(new MedicineDTO(
                        lblMedicineID.getText(),
                        txtMedicineName.getText(),
                        Double.parseDouble(txtUnitPrice.getText()),
                        Double.parseDouble(txtPacksPrice.getText()),
                        Integer.parseInt(txtUnitQTY.getText()),
                        Integer.parseInt(txtPacksQTY.getText()))))
                {
                    new Alert(Alert.AlertType.INFORMATION,"Medicine Saved Successfully !!!").show();
                }
                else{
                    new Alert(Alert.AlertType.INFORMATION,"Medicine Saved Failed").show();
                }

            }
            refreshPage();
    }

    @FXML
    void updateMedicineOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to Update this Medicine ? ", ButtonType.YES,ButtonType.NO);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES) {

                if (medicineBO.update(new MedicineDTO(
                        lblMedicineID.getText(),
                        txtMedicineName.getText(),
                        Double.parseDouble(txtUnitPrice.getText()),
                        Double.parseDouble(txtPacksPrice.getText()),
                        Integer.parseInt(txtUnitQTY.getText()),
                        Integer.parseInt(txtPacksQTY.getText()))))
                {
                    new Alert(Alert.AlertType.INFORMATION,"Medicine Updated Successfully !!!").show();
                }
                else{
                    new Alert(Alert.AlertType.INFORMATION,"Medicine Updated Failed").show();
                }
            }
            refreshPage();
    }
}


//    boolean isTextBoxesValid() {
//
//        Regex regex = new Regex();
//
//        boolean isMedicineNameValid = regex.isTextValid(txtMedicineName.getText(), "name");
//        boolean isUnitQtyValid = regex.isTextValid(txtUnitQTY.getText(), "qty");
//        boolean isPackQtyValid = regex.isTextValid(txtPacksQTY.getText(), "qty");
//        boolean isUnitPriceValid = regex.isTextValid(txtUnitPrice.getText(), "price");
//        boolean isPackPriceValid = regex.isTextValid(txtPacksPrice.getText(), "price");
//
//        txtMedicineName.setStyle(txtMedicineName.getText() + "-jfx-focus-color: #2974d6 ;");
//        txtUnitQTY.setStyle(txtUnitQTY.getText() + "-jfx-focus-color: #2974d6 ;");
//        txtPacksQTY.setStyle(txtPacksQTY.getText() + "-jfx-focus-color: #2974d6 ;");
//        txtUnitPrice.setStyle(txtUnitPrice.getText() + "-jfx-focus-color: #2974d6 ;");
//        txtPacksPrice.setStyle(txtPacksPrice.getText() + "-jfx-focus-color: #2974d6 ;");
//
//        if (!isMedicineNameValid) {
//            txtMedicineName.setStyle(txtMedicineName.getStyle() + "-jfx-focus-color: red ;");
//            txtMedicineName.requestFocus();
//        }
//        if (!isUnitQtyValid) {
//            txtUnitQTY.setStyle(txtUnitQTY.getStyle() + "-jfx-focus-color: red ;");
//            txtUnitQTY.requestFocus();
//        }
//        if (!isPackQtyValid) {
//            txtPacksQTY.setStyle(txtPacksQTY.getStyle() + "-jfx-focus-color: red ;");
//            txtPacksQTY.requestFocus();
//        }
//        if (!isUnitPriceValid) {
//            txtUnitPrice.setStyle(txtUnitPrice.getStyle() + "-jfx-focus-color: red ;");
//            txtUnitPrice.requestFocus();
//        }
//        if (!isPackPriceValid) {
//            txtPacksPrice.setStyle(txtPacksPrice.getStyle() + "-jfx-focus-color: red ;");
//            txtPacksPrice.requestFocus();
//        }
//
//        return isMedicineNameValid && isUnitQtyValid && isPackQtyValid && isUnitPriceValid && isPackPriceValid;
//    }

