package com.assignment.final_course_work.controller;

import com.assignment.final_course_work.dao.Custom.UserDAO;
import com.assignment.final_course_work.dao.Custom.impl.UserDAOImpl;
import com.assignment.final_course_work.util.LoadingTask;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SplashScreenPageController implements Initializable {

    @FXML
    private Rectangle loadingBar;

    @FXML
    private Rectangle progressBar;

    @FXML
    private AnchorPane splashScreen;

    private UserDAO userDAO = new UserDAOImpl();  // using DAO change to BO after develop


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadingTask task = new LoadingTask();
        task.progressProperty().addListener((observable, oldValue, newValue) -> {
            progressBar.setWidth(loadingBar.getWidth() * newValue.doubleValue());
            if (newValue.doubleValue() > 0.99) {
                Stage stage = (Stage) splashScreen.getScene().getWindow();
                stage.close();
                try {
                    loadPage();
                } catch (IOException | SQLException | ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        new Thread(task).start();
    }
    private void loadPage() throws IOException, SQLException, ClassNotFoundException {

        String pageString = userDAO.chooseThePage() ? "/view/LoginPage.fxml" : "/view/SignUpPage.fxml";
        Stage stage = (Stage) splashScreen.getScene().getWindow();
        AnchorPane root = FXMLLoader.load(getClass().getResource(pageString));
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
