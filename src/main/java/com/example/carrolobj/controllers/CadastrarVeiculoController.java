package com.example.carrolobj.controllers;

import com.example.carrolobj.MainController;
import com.example.carrolobj.models.Veiculo;
import com.example.carrolobj.services.VeiculoService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import com.example.carrolobj.util.Alerts;
import javafx.scene.control.TextFormatter;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import static java.lang.Integer.parseInt;

public class CadastrarVeiculoController implements Initializable {
    @FXML
    private TextField placaTextField;
    @FXML
    private TextField marcaTextField;
    @FXML
    private TextField modeloTextField;
    @FXML
    private TextField anoFabricacaoTextField;
    @FXML
    private TextField corTextField;

    @FXML
    private ComboBox<String> cmbPortas;

    private VeiculoService service;

    public CadastrarVeiculoController(){

    }


    public void salvarVeiculo() {
        String placa = this.placaTextField.getText();
        String marca = this.marcaTextField.getText();
        String modelo = this.modeloTextField.getText();
        String anoDeFabricacao = this.anoFabricacaoTextField.getText();
        String cor = this.corTextField.getText();
        String numPortas = this.cmbPortas.getValue();

        // Verifica se algum campo está vazio
        if (placa.isEmpty() || marca.isEmpty() || modelo.isEmpty() ||
                anoDeFabricacao.isEmpty() || cor.isEmpty() || numPortas == null || numPortas.isEmpty()) {
            Alerts.showAlert("Aviso", "Preencha todos os campos", "", Alert.AlertType.ERROR);
            return; // Sai do método se algum campo estiver vazio
        }

        this.service.cadastrarVeiculo(new Veiculo(placa,
                marca,
                modelo,
                Integer.parseInt(anoDeFabricacao),
                cor,
                Integer.parseInt(numPortas)));

        Alerts.showAlert("Sucesso", "Veículo Cadastrado!", "", Alert.AlertType.CONFIRMATION);
        // Limpa os campos após o salvamento bem-sucedido
        limparCampos();
    }






    private void limparCampos() {
        placaTextField.clear();
        marcaTextField.clear();
        modeloTextField.clear();
        anoFabricacaoTextField.clear();
        corTextField.clear();
        cmbPortas.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.service = new VeiculoService();

        // Configura o TextFormatter para aceitar apenas números
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            } else {
                return null;
            }
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        anoFabricacaoTextField.setTextFormatter(textFormatter);

        cmbPortas.getItems().addAll(
                "2",
                "4"
        );
    }

    @FXML
    public void btnVoltar(ActionEvent event) {
        MainController mainController = new MainController();
        mainController.onBackToMain(event);
    }
}
