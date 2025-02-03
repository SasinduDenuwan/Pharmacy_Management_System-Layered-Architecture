package com.assignment.final_course_work.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class OrderPageController {

    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXButton btnItemRemove;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnReset;

    @FXML
    private ComboBox<?> cmbCustomer;

    @FXML
    private ComboBox<?> cmbMedicine;

    @FXML
    private TableColumn<?, ?> colMedicineID;

    @FXML
    private TableColumn<?, ?> colMedicineName;

    @FXML
    private TableColumn<?, ?> colMedicineQty;

    @FXML
    private TableColumn<?, ?> colMedicineUnitPrice;

    @FXML
    private TableColumn<?, ?> colPackageQty;

    @FXML
    private TableColumn<?, ?> colPackageUnitPrice;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private AnchorPane dashboardPage;

    @FXML
    private Text lblCatogary;

    @FXML
    private Text lblCustomerName;

    @FXML
    private Text lblOrderDate;

    @FXML
    private Text lblOrderID;

    @FXML
    private Text lblQOHMedicine;

    @FXML
    private Text lblQOHPackage;

    @FXML
    private Label lblShowPrice;

    @FXML
    private TableView<?> tblOrder;

    @FXML
    private Text txtPrice;

    @FXML
    private TextField txtQtyOfMedi;

    @FXML
    private TextField txtQtyOfPack;

    @FXML
    void addToCart(ActionEvent event) {

    }

    @FXML
    void itemRemove(ActionEvent event) {

    }

    @FXML
    void onClickItem(MouseEvent event) {

    }

    @FXML
    void placeOrder(ActionEvent event) {

    }

    @FXML
    void reset(ActionEvent event) {

    }

    @FXML
    void selectMedicineOnAction(ActionEvent event) {

    }

    @FXML
    void selectTheCustomerOnAction(ActionEvent event) {

    }

}
