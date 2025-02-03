package com.assignment.final_course_work.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class CustomerPageController {

    @FXML
    private JFXButton btnClearCustomer;

    @FXML
    private JFXButton btnDeleteCustomer;

    @FXML
    private JFXButton btnSaveCustomer;

    @FXML
    private JFXButton btnUpdateCustomer;

    @FXML
    private AnchorPane cashierCustomerPage;

    @FXML
    private TableColumn<?, ?> customerAddEmployee;

    @FXML
    private TableColumn<?, ?> customerAddress;

    @FXML
    private TableColumn<?, ?> customerEmail;

    @FXML
    private TableColumn<?, ?> customerID;

    @FXML
    private TableColumn<?, ?> customerName;

    @FXML
    private TableColumn<?, ?> customerPhone;

    @FXML
    private TableView<?> customerTable;

    @FXML
    private Text lblCustomerID;

    @FXML
    private JFXTextField txtCustomerAddress;

    @FXML
    private JFXTextField txtCustomerContact;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtSearchCustomer;

    @FXML
    private JFXTextField txtXCustomerEmail;

    @FXML
    void clearCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void deleteCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void onClickTable(MouseEvent event) {

    }

    @FXML
    void saveCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void searchCustomer(MouseEvent event) {

    }

    @FXML
    void updateCustomeronAction(ActionEvent event) {

    }

}
