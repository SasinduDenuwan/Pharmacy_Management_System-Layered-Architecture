package com.assignment.final_course_work.controller;

import com.assignment.final_course_work.bo.BOFactory;
import com.assignment.final_course_work.bo.MedicineBO;
import com.assignment.final_course_work.dao.Custom.MedicineDAO;
import com.assignment.final_course_work.dao.Custom.impl.MedicineDAOImpl;
import com.assignment.final_course_work.dto.MedicineDTO;
import com.assignment.final_course_work.entity.Medicine;
import com.assignment.final_course_work.view.tdm.MedicineTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StocksPageController implements Initializable {

    @FXML
    private ComboBox<MedicineTM> MedicineOnAction;

    @FXML
    private AnchorPane cashierinventoryPane;

    @FXML
    private TableColumn<MedicineTM, String> colMedicineID;

    @FXML
    private TableColumn<MedicineTM, String> colMedicineName;

    @FXML
    private TableColumn<MedicineTM, Integer> colMedicineQuantity;

    @FXML
    private TableColumn<MedicineTM, Integer> colPacksQuantity;

    @FXML
    private Text lblAvailablePacks;

    @FXML
    private Text lblMedicineID;

    @FXML
    private Text lblMedicineIDText;

    @FXML
    private Text lblMedicineIDText1;

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
    private TableColumn<MedicineTM, Double> packPrice;

    @FXML
    private TableView<MedicineTM> tblInventory;

    @FXML
    private TableColumn<MedicineTM, Double> untiPrice;

    private MedicineBO medicineBO = (MedicineBO) BOFactory.getInstance().getBO(BOFactory.BOType.MEDICINE);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colMedicineID.setCellValueFactory(new PropertyValueFactory<>("medicineID"));
        colMedicineName.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        colMedicineQuantity.setCellValueFactory(new PropertyValueFactory<>("unitQty"));
        colPacksQuantity.setCellValueFactory(new PropertyValueFactory<>("packQty"));
        untiPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        packPrice.setCellValueFactory(new PropertyValueFactory<>("packPrice"));

        lblMedicineID.setText("");
        lblMedicineName.setText("");
        lblUnitPrice.setText("");
        lblPackPrice.setText("");
        lblAvailablePacks.setText("");
        lblMedicines.setText("");

        try {
            loadMedicineTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadMedicineTable() throws SQLException, ClassNotFoundException {
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
        tblInventory.setItems(medicineTMS);
    }

    @FXML
    void OnClickAction(MouseEvent event) {

        MedicineTM selectedMedicine = tblInventory.getSelectionModel().getSelectedItem();

        if (selectedMedicine!= null) {
            lblMedicineID.setText(selectedMedicine.getMedicineID());
            lblMedicineName.setText(selectedMedicine.getMedicineName());
            lblUnitPrice.setText(String.valueOf(selectedMedicine.getUnitPrice()));
            lblPackPrice.setText(String.valueOf(selectedMedicine.getPackPrice()));
            lblAvailablePacks.setText(String.valueOf(selectedMedicine.getPackQty()));
            lblMedicines.setText(String.valueOf(selectedMedicine.getUnitQty()));
        }
    }
}
