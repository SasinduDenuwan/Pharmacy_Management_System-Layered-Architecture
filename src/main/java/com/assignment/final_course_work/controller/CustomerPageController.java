package com.assignment.final_course_work.controller;

import com.assignment.final_course_work.bo.BOFactory;
import com.assignment.final_course_work.bo.CustomerBO;
import com.assignment.final_course_work.dao.Custom.CustomerDAO;
import com.assignment.final_course_work.dao.Custom.impl.CustomerDAOImpl;
import com.assignment.final_course_work.dao.SQLUtil;
import com.assignment.final_course_work.dto.CustomerDTO;
import com.assignment.final_course_work.entity.Customer;
import com.assignment.final_course_work.util.Regex;
import com.assignment.final_course_work.view.tdm.CustomerTM;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerPageController implements Initializable {

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
    private TableColumn<CustomerTM, String> customerAddEmployee;

    @FXML
    private TableColumn<CustomerTM, String> customerAddress;

    @FXML
    private TableColumn<CustomerTM, String> customerEmail;

    @FXML
    private TableColumn<CustomerTM, String> customerID;

    @FXML
    private TableColumn<CustomerTM, String> customerName;

    @FXML
    private TableColumn<CustomerTM, String> customerPhone;

    @FXML
    private TableView<CustomerTM> customerTable;

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

    private CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        customerID.setCellValueFactory(new PropertyValueFactory<>("cusID"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("cusAddress"));
        customerEmail.setCellValueFactory(new PropertyValueFactory<>("cusEmail"));
        customerPhone.setCellValueFactory(new PropertyValueFactory<>("cusPhone"));

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

        lblCustomerID.setText(customerBO.generateNewID());
        txtCustomerName.clear();
        txtXCustomerEmail.clear();
        txtCustomerAddress.clear();
        txtCustomerContact.clear();
        txtSearchCustomer.clear();

        btnSaveCustomer.setDisable(false);
        btnDeleteCustomer.setDisable(true);
        btnUpdateCustomer.setDisable(true);
    }

    private void refreshTable() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDAOAll = customerBO.getAll();
        ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();

        for (CustomerDTO customer : customerDAOAll) {
            customerTMS.add(new CustomerTM(
                    customer.getCusID(),
                    customer.getCusName(),
                    customer.getCusEmail(),
                    customer.getCusAddress(),
                    customer.getCusPhone()));
        }
        customerTable.setItems(customerTMS);
    }

    @FXML
    void clearCustomerOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        lblCustomerID.setText(customerBO.generateNewID());
        txtSearchCustomer.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerContact.clear();
        txtXCustomerEmail.clear();
        btnSaveCustomer.setDisable(false);
    }

    @FXML
    void deleteCustomerOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to delete this customer ? ", ButtonType.YES,ButtonType.NO);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES) {

                if (customerBO.delete(lblCustomerID.getText())){
                    new Alert(Alert.AlertType.INFORMATION,"Customer Deleted Successfully !!!").show();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Customer Delete Failed").show();
                }
            }
            refreshPage();
    }

    @FXML
    void onClickTable(MouseEvent event) {
        CustomerTM selectedItems = customerTable.getSelectionModel().getSelectedItem();
        if (selectedItems!= null) {

            btnSaveCustomer.setDisable(true);
            btnDeleteCustomer.setDisable(false);
            btnUpdateCustomer.setDisable(false);

            lblCustomerID.setText(selectedItems.getCusID());
            txtCustomerName.setText(selectedItems.getCusName());
            txtXCustomerEmail.setText(selectedItems.getCusEmail());
            txtCustomerAddress.setText(selectedItems.getCusAddress());
            txtCustomerContact.setText(selectedItems.getCusPhone());
        }
    }

    @FXML
    void saveCustomerOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(isTextBoxesValid()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to Save this customer ? ", ButtonType.YES,ButtonType.NO);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES) {

                if (customerBO.save(new CustomerDTO(
                        lblCustomerID.getText(),
                        txtCustomerName.getText(),
                        txtXCustomerEmail.getText(),
                        txtCustomerAddress.getText(),
                        txtCustomerContact.getText())))
                {
                    new Alert(Alert.AlertType.INFORMATION,"Customer Saved Successfully !!!").show();
                }
                else{
                    new Alert(Alert.AlertType.INFORMATION,"Customer Saved Failed").show();
                }

            }
            refreshPage();
        }
    }

    @FXML
    void searchCustomer(MouseEvent event) throws SQLException, ClassNotFoundException {

        boolean isCustomerIDValid = new Regex().isTextValid(txtSearchCustomer.getText(), "customerID");

        txtSearchCustomer.setStyle(txtSearchCustomer.getStyle() + "-jfx-focus-color: #2974d6 ;");

        if (!isCustomerIDValid) {
            txtSearchCustomer.setStyle(txtSearchCustomer.getStyle() + "-jfx-focus-color: red ;");
            txtSearchCustomer.requestFocus();
        }

        if (isCustomerIDValid){

            CustomerDTO customer = customerBO.search(txtSearchCustomer.getText());

            if (customer.getCusID() != null) {
                txtCustomerAddress.setText(customer.getCusAddress());
                txtXCustomerEmail.setText(customer.getCusEmail());
                txtCustomerName.setText(customer.getCusName());
                txtCustomerContact.setText(customer.getCusPhone());

                btnSaveCustomer.setDisable(true);
                btnDeleteCustomer.setDisable(false);
                btnUpdateCustomer.setDisable(false);

            }else{

                new Alert(Alert.AlertType.ERROR,"Customer not found !!!").show();
                txtSearchCustomer.clear();
            }
        }
    }

    @FXML
    void updateCustomeronAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        if (isTextBoxesValid()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to Update this customer ? ", ButtonType.YES,ButtonType.NO);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES) {
                if (customerBO.update(new CustomerDTO(
                        lblCustomerID.getText(),
                        txtCustomerName.getText(),
                        txtXCustomerEmail.getText(),
                        txtCustomerAddress.getText(),
                        txtCustomerContact.getText()))) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer Updated Successfully !!!").show();
                }else{
                    new Alert(Alert.AlertType.INFORMATION, "Customer Update Failed").show();
                }
            }
            refreshPage();
        }
    }
    private boolean isTextBoxesValid(){

        Regex regex = new Regex();

        boolean isCustomerNameValid = regex.isTextValid(txtCustomerName.getText(), "name");
        boolean isCustomerEmailValid = regex.isTextValid(txtXCustomerEmail.getText(), "email");
        boolean isCustomerAddressValid = regex.isTextValid(txtCustomerAddress.getText(), "address");
        boolean isCustomerContactValid = regex.isTextValid(txtCustomerContact.getText(), "phone");

        txtXCustomerEmail.setStyle(txtXCustomerEmail.getStyle() + "-jfx-focus-color: #2974d6 ;");
        txtCustomerName.setStyle(txtCustomerName.getStyle() + "-jfx-focus-color: #2974d6 ;");
        txtCustomerContact.setStyle(txtCustomerContact.getStyle() + "-jfx-focus-color: #2974d6 ;");
        txtCustomerAddress.setStyle(txtCustomerAddress.getStyle() + "-jfx-focus-color: #2974d6 ;");

        if (!isCustomerNameValid) {
            txtCustomerName.setStyle(txtCustomerName.getStyle() + "-jfx-focus-color: red ;");
            txtCustomerName.requestFocus();
        }

        if (!isCustomerAddressValid) {
            txtCustomerAddress.setStyle(txtCustomerAddress.getStyle() + "-jfx-focus-color: red ;");
            txtCustomerAddress.requestFocus();
        }

        if (!isCustomerContactValid) {
            txtCustomerContact.setStyle(txtCustomerContact.getStyle() + "-jfx-focus-color: red ;");
            txtCustomerContact.requestFocus();
        }

        if (!isCustomerEmailValid) {
            txtXCustomerEmail.setStyle(txtXCustomerEmail.getStyle() + "-jfx-focus-color: red ;");
            txtXCustomerEmail.requestFocus();
        }
        return isCustomerNameValid && isCustomerContactValid && isCustomerAddressValid && isCustomerEmailValid;
    }
}