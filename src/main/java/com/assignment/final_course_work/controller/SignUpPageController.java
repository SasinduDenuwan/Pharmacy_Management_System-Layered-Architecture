package com.assignment.final_course_work.controller;

import com.assignment.final_course_work.bo.BOFactory;
import com.assignment.final_course_work.bo.UserBO;
import com.assignment.final_course_work.dao.Custom.UserDAO;
import com.assignment.final_course_work.dao.Custom.impl.UserDAOImpl;
import com.assignment.final_course_work.dto.UserDTO;
import com.assignment.final_course_work.entity.User;
import com.assignment.final_course_work.util.Regex;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

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

    private UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    @FXML
    void goToTheLoginPane(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        // variables for regex validation
        String checkUserName = "userName", checkPassword = "password", checkNIC = "NIC", checkName = "name", checkAddress = "address", checkPhone = "phone", checkEmail = "email";


        boolean isUserNameValid = new Regex().isTextValid(txtUserName.getText(), checkUserName);
        boolean isFirstNameValid = new Regex().isTextValid(txtFirstName.getText(), checkName);
        boolean isLastNameValid = new Regex().isTextValid(txtLastName.getText(), checkName);
        boolean isNICValid = new Regex().isTextValid(txtNIC.getText(), checkNIC);
        boolean isEmailValid = new Regex().isTextValid(txtEmail.getText(), checkEmail);
        boolean isAddressValid = new Regex().isTextValid(txtAdress.getText(), checkAddress);
        boolean isPhoneValid = new Regex().isTextValid(txtContact.getText(), checkPhone);
        boolean isCreatePasswordValid = new Regex().isTextValid(txtCreatePW.getText(), checkPassword);
        boolean isConfirmPasswordValid = new Regex().isTextValid(txtConfirmPW.getText(), checkPassword);

        txtUserName.setStyle(txtUserName.getStyle() + "-jfx-focus-color: #2974d6 ;");
        txtFirstName.setStyle(txtFirstName.getStyle() + "-jfx-focus-color: #2974d6 ;");
        txtLastName.setStyle(txtLastName.getStyle() + "-jfx-focus-color: #2974d6 ;");
        txtNIC.setStyle(txtNIC.getStyle() + "-jfx-focus-color: #2974d6 ;");
        txtEmail.setStyle(txtEmail.getStyle() + "-jfx-focus-color: #2974d6 ;");
        txtAdress.setStyle(txtAdress.getStyle() + "-jfx-focus-color: #2974d6 ;");
        txtContact.setStyle(txtContact.getStyle() + "-jfx-focus-color: #2974d6 ;");
        txtCreatePW.setStyle(txtCreatePW.getStyle() + "-jfx-focus-color: #2974d6 ;");
        txtConfirmPW.setStyle(txtConfirmPW.getStyle() + "-jfx-focus-color: #2974d6 ;");

        if (!isUserNameValid) {
            txtUserName.setStyle(txtUserName.getStyle() + "-jfx-focus-color: red ;");
            txtUserName.requestFocus();
        }
        if (!isFirstNameValid) {
            txtFirstName.setStyle(txtFirstName.getStyle() + "-jfx-focus-color: red ;");
            txtFirstName.requestFocus();
        }
        if (!isLastNameValid) {
            txtLastName.setStyle(txtLastName.getStyle() + "-jfx-focus-color: red ;");
            txtLastName.requestFocus();
        }
        if (!isNICValid) {
            txtNIC.setStyle(txtNIC.getStyle() + "-jfx-focus-color: red ;");
            txtNIC.requestFocus();
        }
        if (!isEmailValid) {
            txtEmail.setStyle(txtEmail.getStyle() + "-jfx-focus-color: red ;");
            txtEmail.requestFocus();
        }
        if (!isAddressValid) {
            txtAdress.setStyle(txtAdress.getStyle() + "-jfx-focus-color: red ;");
            txtAdress.requestFocus();
        }
        if (!isPhoneValid) {
            txtContact.setStyle(txtContact.getStyle() + "-jfx-focus-color: red ;");
            txtContact.requestFocus();
        }
        if (!isCreatePasswordValid) {
            txtCreatePW.setStyle(txtCreatePW.getStyle() + "-jfx-focus-color: red ;");
            txtCreatePW.requestFocus();
        }
        if (!isConfirmPasswordValid) {
            txtConfirmPW.setStyle(txtConfirmPW.getStyle() + "-jfx-focus-color: red ;");
            txtConfirmPW.requestFocus();
        }

        if (isUserNameValid && isFirstNameValid && isLastNameValid && isNICValid && isEmailValid && isAddressValid && isPhoneValid && isCreatePasswordValid && isConfirmPasswordValid) {
            if (txtConfirmPW.getText().equals(txtCreatePW.getText())) {
                lblPasswordStatus.setVisible(true);
                lblPasswordStatus.setText("Password Matches");
                lblPasswordStatus.setTextFill(Color.rgb(14,248,14));

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure Save this Details ?", ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.YES) {
                    new Alert(Alert.AlertType.INFORMATION,"Account Created Successfully !!!").show();
                    saveAndLoginPage();
                }
            }
            else{
                lblPasswordStatus.setVisible(true);
                lblPasswordStatus.setText("Password didn't match");
                lblPasswordStatus.setTextFill(Color.RED);
            }
        }
    }

    private void saveAndLoginPage() throws SQLException, ClassNotFoundException, IOException {

        if (userBO.save(new UserDTO(userBO.generateNewID(), txtUserName.getText(), txtConfirmPW.getText(), txtEmail.getText()))) {
            new Alert(Alert.AlertType.INFORMATION, "Account Created Successfully !!!").show();
            signUpPage.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/loginPage.fxml"));
            signUpPage.getChildren().add(load);
        }
    }

    @FXML
    void hideConfirmPW(MouseEvent event) {
        checkConfirmPW.setImage(new Image(String.valueOf(getClass().getResource("/icons/closedEye.png"))));
        String pw = lblConfirmPW.getText();
        txtConfirmPW.setText(pw);
        txtConfirmPW.setPromptText("confirm password");
        lblConfirmPW.setText("");
    }

    @FXML
    void hideCreatePassword(MouseEvent event) {
        checkCreatePW.setImage(new Image(String.valueOf(getClass().getResource("/icons/closedEye.png"))));
        String pw = lblCreatePW.getText();
        txtCreatePW.setText(pw);
        txtCreatePW.setPromptText("create password");
        lblCreatePW.setText("");
    }

    @FXML
    void showConfirmPW(MouseEvent event) {
        checkConfirmPW.setImage(new Image(String.valueOf(getClass().getResource("/icons/openEye.png"))));
        String pw = txtConfirmPW.getText();
        lblConfirmPW.setText(pw);
        txtConfirmPW.setPromptText("");
        txtConfirmPW.setText("");
    }

    @FXML
    void showCreatePassword(MouseEvent event) {
        checkCreatePW.setImage(new Image(String.valueOf(getClass().getResource("/icons/openEye.png"))));
        String pw = txtCreatePW.getText();
        lblCreatePW.setText(pw);
        txtCreatePW.setPromptText("");
        txtCreatePW.setText("");
    }
}
