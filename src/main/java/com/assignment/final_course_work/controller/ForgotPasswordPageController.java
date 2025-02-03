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

public class ForgotPasswordPageController {

    @FXML
    private Pane afterUerNamePane;

    @FXML
    private JFXButton btnChangePW;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnSubmitOTP;

    @FXML
    private ImageView checkConfirmPW;

    @FXML
    private ImageView checkCreatePW;

    @FXML
    private Pane createPasswordPane;

    @FXML
    private AnchorPane forgotPasswordAnchorPane;

    @FXML
    private Label lblConfirmPW;

    @FXML
    private Label lblCreatePW;

    @FXML
    private Label lblMobileNumber;

    @FXML
    private Label lblPasswordStatus;

    @FXML
    private JFXPasswordField txtConfirmPW;

    @FXML
    private JFXTextField txtOTP;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtrCreatePW;

    @FXML
    void goToTheLogin(ActionEvent event) {

    }

    @FXML
    void hideConfirmPassword(MouseEvent event) {

    }

    @FXML
    void hideCreatePassword(MouseEvent event) {

    }

    @FXML
    void showConfirmPassword(MouseEvent event) {

    }

    @FXML
    void showCreatePassword(MouseEvent event) {

    }

    @FXML
    void showCreatePasswordPane(ActionEvent event) {

    }

    @FXML
    void showOTPPane(ActionEvent event) {

    }

}
