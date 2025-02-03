package com.assignment.final_course_work.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SignUpPageController {

    @FXML
    private JFXButton btnRegister;

    @FXML
    private ImageView checkConfirmPW;

    @FXML
    private ImageView checkCreatePW;

    @FXML
    private Label lblConfirmPW;

    @FXML
    private Label lblCreatePW;

    @FXML
    private Label lblPasswordStatus;

    @FXML
    private Pane signUpDetails;

    @FXML
    private AnchorPane signUpPage;

    @FXML
    private AnchorPane signUpPage1;

    @FXML
    private JFXTextField txtAdress;

    @FXML
    private JFXPasswordField txtConfirmPW;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXPasswordField txtCreatePW;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextField txtLastName;

    @FXML
    private JFXTextField txtNIC;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    void goToTheLoginPane(ActionEvent event) {

    }

    @FXML
    void hideConfirmPW(MouseEvent event) {

    }

    @FXML
    void hideCreatePassword(MouseEvent event) {

    }

    @FXML
    void showConfirmPW(MouseEvent event) {

    }

    @FXML
    void showCreatePassword(MouseEvent event) {

    }

}
