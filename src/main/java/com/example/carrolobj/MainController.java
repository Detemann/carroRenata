package com.example.carrolobj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private Stage currentStage;

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    @FXML
    public void changeToAbout() {
        openNewStage("sobre.fxml", "Sobre");
    }

    @FXML
    public void onActionInserir() {
        openNewStage("CadastrarVeiculo.fxml", "Cadastro de Veículo");
    }

    @FXML
    public void onActionAlterar() {
        openNewStage("AlterarVeiculo.fxml", "Alterar Veículo");
    }

    @FXML
    public void onActionPesquisar() {
        openNewStage("PesquisarVeiculos.fxml", "Pesquisar Veículo");
    }

    @FXML
    public void onActionDeletar() {
        openNewStage("DeletarVeiculo.fxml", "Deletar Veículo");
    }

    private void openNewStage(String fxmlFileName, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();

            Stage newStage = new Stage();
            newStage.setTitle(title);
            newStage.setScene(new Scene(root, 800, 600));

            // Get the controller for the loaded FXML
            Object controller = loader.getController();

            // Check the type of the controller and handle accordingly
            if (controller instanceof MainController) {
                // Set the current stage for the new controller
                ((MainController) controller).setCurrentStage(newStage);
            }

            // Close the current stage
            if (currentStage != null) {
                currentStage.close();
            }

            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onBackToMain(ActionEvent event) {
        // Close the current stage (the stage containing the "Back" button)
        Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

}
