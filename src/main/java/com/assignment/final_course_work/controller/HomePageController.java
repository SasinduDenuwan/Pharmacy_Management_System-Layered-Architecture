package com.assignment.final_course_work.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private JFXButton btnCashierHome;

    @FXML
    private JFXButton btnCashierInventory;

    @FXML
    private JFXButton btnCashierLogout;

    @FXML
    private JFXButton btnCashierPayment;

    @FXML
    private JFXButton btnCashierProfile;

    @FXML
    private JFXButton btnCashierSales;

    @FXML
    private JFXButton btnCashierreturn;

    @FXML
    private JFXButton btncashierCustomer;

    @FXML
    private AnchorPane cashierDashBoardPage;

    @FXML
    private Pane cshierBodyPane;

    @FXML
    private Label lblUserName;

    @FXML
    private Label lblUserposition;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblUserName.setText("Cashier");

        cshierBodyPane.getChildren().clear();
        Parent load = null;
        try {
            load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/OrderPage.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cshierBodyPane.getChildren().add(load);
    }

    @FXML
    void goToTheLoginPage(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/LoginPage.fxml")));
        Scene scene = new Scene(load);
        Stage stage = (Stage) cshierBodyPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void showCashierCustomer(ActionEvent event) throws IOException{
        cshierBodyPane.getChildren().clear();
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CustomerPage.fxml")));
        cshierBodyPane.getChildren().add(load);
    }

    @FXML
    void showCashierHome(ActionEvent event) {
        cshierBodyPane.getChildren().clear();
        Parent load = null;
        try {
            load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/OrderPage.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cshierBodyPane.getChildren().add(load);
    }

    @FXML
    void showCashierPayment(ActionEvent event) {

    }

    @FXML
    void showCashierProfile(ActionEvent event) {

    }

    @FXML
    void showCashierReturn(ActionEvent event) {

    }

    @FXML
    void showCashierSales(ActionEvent event) throws IOException{
        cshierBodyPane.getChildren().clear();
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Stocks.fxml")));
        cshierBodyPane.getChildren().add(load);
    }

    @FXML
    void showCashierinventary(ActionEvent event) throws IOException{
        cshierBodyPane.getChildren().clear();
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Inventory.fxml")));
        cshierBodyPane.getChildren().add(load);
    }
}
