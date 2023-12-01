package com.example.carrolobj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void changeToAbout() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("sobre.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Sobre");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onActionInserir(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CadastrarVeiculo.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Cadastro de Veículo");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onActionAlterar(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AlterarVeiculo.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Alterar Veículo");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onActionPesquisar(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("PesquisarVeiculos.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Pesquisar Veículo");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onActionDeletar(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DeletarVeiculo.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Deletar Veículo");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}