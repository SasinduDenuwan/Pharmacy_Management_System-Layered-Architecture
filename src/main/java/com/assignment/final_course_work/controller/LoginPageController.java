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

public class LoginPageController {

    @FXML
    private JFXButton btnLogin;

    @FXML
    private ImageView checkPW;

    @FXML
    private Label lblForgotPassword;

    @FXML
    private Label lblPW;

    @FXML
    private Pane loginContent;

    @FXML
    private AnchorPane loginPage;

    @FXML
    private Pane signUpDetails;

    @FXML
    private JFXPasswordField txtPW;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    void goToDashBoard(ActionEvent event) {

    }

    @FXML
    void goToForgotPasswordOption(MouseEvent event) {

    }

    @FXML
    void hidePassword(MouseEvent event) {

    }

    @FXML
    void showPassword(MouseEvent event) {

    }

}
