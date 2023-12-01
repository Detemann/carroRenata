package com.example.carrolobj.controllers;

import com.example.carrolobj.models.Veiculo;
import com.example.carrolobj.services.VeiculoService;
import com.example.carrolobj.util.Alerts;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeletarVeiculoController implements Initializable {
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
        String placa = this.placaTextField.getText().trim();
        List<Veiculo> veiculos = new ArrayList<>();

        if (!placa.isEmpty()) {
            veiculos = this.service.pesquisaTodosOsVeiculos();

            if (veiculos.isEmpty()) {
                Alerts.showAlert("Aviso", "Nenhum veiculo cadastrado", "", Alert.AlertType.ERROR);
                return;
            }

            Veiculo veiculoEncontrado = null;
            for (Veiculo veiculo : veiculos) {
                if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                    veiculoEncontrado = veiculo;
                    break;
                }
            }

            if (veiculoEncontrado == null) {
                Alerts.showAlert("Aviso", "Veiculo não existe", "", Alert.AlertType.ERROR);
                placaTextField.clear();
                return;
            }

            veiculos.clear();
            veiculos.add(veiculoEncontrado);
        } else {
            veiculos = this.service.pesquisaTodosOsVeiculos();

            if (veiculos.isEmpty()) {
                Alerts.showAlert("Aviso", "Nenhum veiculo cadastrado", "", Alert.AlertType.ERROR);
                return;
            }
        }

        System.out.println("btnPesquisar");
        placaTextField.clear();
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

    public void btnDeletar(){
        String placa = this.placaTextField.getText().trim();

        if (!placa.isEmpty()) {
            List<Veiculo> veiculos = this.service.pesquisaTodosOsVeiculos();

            if (veiculos.isEmpty()) {
                Alerts.showAlert("Aviso", "Nenhum veiculo cadastrado", "", Alert.AlertType.ERROR);
                return;
            }

            Veiculo veiculoEncontrado = veiculos.stream().filter(veiculo -> veiculo.getPlaca().equalsIgnoreCase(placa)).findFirst().orElse(null);

            if (veiculoEncontrado == null) {
                Alerts.showAlert("Aviso", "Veiculo não existe", "", Alert.AlertType.ERROR);
                placaTextField.clear();
                return;
            }


            Optional<ButtonType> optionalButtonType = Alerts.showConfirmation("Confirmação", "Tem certeza?\n" +
                    "Veiculo: "+veiculoEncontrado.getMarca()+
                    "Modelo: "+veiculoEncontrado.getModelo());

            optionalButtonType.ifPresent(buttonType -> {
                if (buttonType == ButtonType.OK) {
                    // Código a ser executado se o botão OK for pressionado
                    service.deletarVeiculo(veiculoEncontrado);
                    Alerts.showAlert("OK", "Veículo deletado", "", Alert.AlertType.CONFIRMATION);
                    placaTextField.clear();
                    obsList.clear();
                    tableView.setItems(obsList);
                }
            });
        } else {
            Alerts.showAlert("Aviso", "Informe a Placa do Veículo", "", Alert.AlertType.WARNING);
        }
    }



}
