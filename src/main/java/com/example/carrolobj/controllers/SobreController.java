package com.example.carrolobj.controllers;

import com.example.carrolobj.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SobreController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void btnVoltar(ActionEvent event) {
        MainController mainController = new MainController();
        mainController.onBackToMain(event);
    }
}
