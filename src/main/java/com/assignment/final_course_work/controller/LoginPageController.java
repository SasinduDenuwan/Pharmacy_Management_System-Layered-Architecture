package com.assignment.final_course_work.controller;

import com.assignment.final_course_work.bo.BOFactory;
import com.assignment.final_course_work.bo.Custom.impl.UserBOImpl;
import com.assignment.final_course_work.bo.UserBO;
import com.assignment.final_course_work.dao.Custom.UserDAO;
import com.assignment.final_course_work.dao.Custom.impl.UserDAOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import com.assignment.final_course_work.util.Regex;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

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

    private UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);
    @FXML
    void goToDashBoard(ActionEvent event) throws SQLException, ClassNotFoundException, IOException{
        boolean isUserNameValid = new Regex().isTextValid(txtUserName.getText(), "userName");
        boolean isPasswordValid = new Regex().isTextValid(txtPW.getText(), "password");

        txtUserName.setStyle(txtUserName.getStyle() + "-jfx-focus-color: #2974d6 ;");
        txtPW.setStyle(txtPW.getStyle() + "-jfx-focus-color: #2974d6 ;");

        if (!isUserNameValid) {
            txtUserName.setStyle(txtUserName.getStyle() + "-jfx-focus-color: red ;");
            txtUserName.requestFocus();
        }
        if (!isPasswordValid) {
            txtPW.setStyle(txtPW.getStyle() + "-jfx-focus-color: red ;");
            txtPW.requestFocus();
        }

        if (isUserNameValid && isPasswordValid){
            if (userBO.checkUserAndPW(txtUserName.getText(), txtPW.getText())){
                Stage stage = (Stage) loginPage.getScene().getWindow();
                AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/HomePage.fxml")));
                Scene scene = new Scene(root);
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid Username or Password").show();
                txtUserName.clear();
                txtPW.clear();
            }
        }

    }

    @FXML
    void goToForgotPasswordOption(MouseEvent event) {
        System.out.println("For Future Development");
    }

    @FXML
    void hidePassword(MouseEvent event) {
        checkPW.setImage(new Image(String.valueOf(getClass().getResource("/icons/closedEye.png"))));
        String pw = lblPW.getText();
        txtPW.setText(pw);
        lblPW.setText("");
        txtPW.setPromptText("password");
    }

    @FXML
    void showPassword(MouseEvent event) {
        checkPW.setImage(new Image(String.valueOf(getClass().getResource("/icons/openEye.png"))));
        String pw = txtPW.getText();
        lblPW.setText(pw);
        txtPW.setText("");
        txtPW.setPromptText("");
    }

}
