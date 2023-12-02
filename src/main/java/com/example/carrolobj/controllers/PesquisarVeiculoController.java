package com.example.carrolobj.controllers;

import com.example.carrolobj.MainController;
import com.example.carrolobj.models.Veiculo;
import com.example.carrolobj.services.VeiculoService;
import com.example.carrolobj.util.Alerts;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PesquisarVeiculoController implements Initializable {
    @FXML
    public TextField marcaTextField;
    @FXML
    public TextField modeloTextField;
    @FXML
    private TableView<Veiculo> tableView;
    @FXML
    private TableColumn<Veiculo, String> placaColumn;
    @FXML
    private TableColumn<Veiculo, String> marcaColumn;
    @FXML
    private TableColumn<Veiculo, String> modeloColumn;
    @FXML
    private TableColumn<Veiculo, String> anoFabColumn;
    @FXML
    private TableColumn<Veiculo, String> corColumn;
    @FXML
    private TableColumn<Veiculo, String> portasColumn;
    @FXML
    private TextField placaTextField;
    private ObservableList<Veiculo> obsList;

    private VeiculoService service;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.service = new VeiculoService();
    }
    @FXML
    public void btnPesquisar() {
        List<Veiculo> veiculos = new ArrayList<>();
        String termoPlaca = placaTextField.getText().trim();
        String termoMarca = marcaTextField.getText().trim();
        String termoModelo = modeloTextField.getText().trim();

        if (termoPlaca.isEmpty() && termoMarca.isEmpty() && termoModelo.isEmpty()) {
            // Ambos os campos estão vazios, pesquise todos os veículos
            veiculos = service.pesquisaTodosOsVeiculos();
            if (veiculos.isEmpty()) {
                Alerts.showAlert("Aviso", "Nenhum veículo cadastrado", "", Alert.AlertType.WARNING);
                return;
            }
        } else if (!termoPlaca.isEmpty() && termoMarca.isEmpty() && termoModelo.isEmpty()) {
            // Apenas a placa está preenchida, pesquise por placa
            Veiculo veiculoEncontrado = service.pesquisarVeiculoPorPlaca(termoPlaca);
            if (veiculoEncontrado != null) {
                veiculos.add(veiculoEncontrado);
            } else {
                Alerts.showAlert("Aviso", "Veículo com a placa " + termoPlaca + " não encontrado", "", Alert.AlertType.WARNING);
                placaTextField.clear();
                marcaTextField.clear();
                modeloTextField.clear();
                return;
            }
        } else if (termoPlaca.isEmpty() && !termoMarca.isEmpty() && termoModelo.isEmpty()) {
            // Apenas a marca está preenchida, pesquise por marca
            veiculos = service.pesquisarPorMarca(termoMarca);
            if (veiculos.isEmpty()) {
                Alerts.showAlert("Aviso", "Nenhum veículo encontrado para a marca: " + termoMarca, "", Alert.AlertType.WARNING);
                placaTextField.clear();
                marcaTextField.clear();
                modeloTextField.clear();
                return;
            }
        }else if(termoPlaca.isEmpty() && termoMarca.isEmpty() && !termoModelo.isEmpty()){
            // Apenas o modelo está preenchida, pesquise por marca
            veiculos = service.pesquisarPorModelo(termoModelo);
            if (veiculos.isEmpty()) {
                Alerts.showAlert("Aviso", "Nenhum veículo encontrado para a modelo: " + termoModelo, "", Alert.AlertType.WARNING);
                placaTextField.clear();
                marcaTextField.clear();
                modeloTextField.clear();
                return;
            }
        }else {
            // Ambos os campos estão preenchidos, exiba um aviso
            Alerts.showAlert("Aviso", "Preencha apenas um campo de pesquisa", "", Alert.AlertType.WARNING);
            placaTextField.clear();
            marcaTextField.clear();
            modeloTextField.clear();
            return; // Não execute a pesquisa
        }

        placaTextField.clear();
        marcaTextField.clear();
        modeloTextField.clear();
        inicializarTabela(veiculos);
    }



    public void inicializarTabela(List<Veiculo> veiculos){
        placaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlaca()));
        marcaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarca()));
        modeloColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModelo()));
        anoFabColumn.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getAnoFabricacao()));
        corColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCor()));
        portasColumn.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getNumPortas()));

        obsList = FXCollections.observableArrayList(veiculos);
        tableView.setItems(obsList);
    }

    public void btnLimpar() {
        obsList.clear();
        tableView.setItems(obsList);
    }

    @FXML
    public void btnVoltar(ActionEvent event) {
        MainController mainController = new MainController();
        mainController.onBackToMain(event);
    }
}
