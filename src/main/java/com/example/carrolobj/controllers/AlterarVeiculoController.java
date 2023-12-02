package com.example.carrolobj.controllers;

import com.example.carrolobj.MainController;
import com.example.carrolobj.models.Veiculo;
import com.example.carrolobj.services.VeiculoService;
import com.example.carrolobj.util.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;

import static java.lang.Integer.parseInt;

public class AlterarVeiculoController implements Initializable {
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
    private RadioButton radio2Portas;
    @FXML
    private RadioButton radio4Portas;

    private VeiculoService service;

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
    }

    public void pesquisarVeiculo(){
        String placa = this.placaTextField.getText();
        Veiculo veiculo = this.service.pesquisarVeiculoPorPlaca(placa);
        if(veiculo == null){
            Alerts.showAlert("Aviso", "Nenhum veiculo cadastrado", "", Alert.AlertType.ERROR);
            return; // Sai do método
        }

        marcaTextField.setText(veiculo.getMarca());
        modeloTextField.setText(veiculo.getModelo());
        anoFabricacaoTextField.setText(""+veiculo.getAnoFabricacao());
        corTextField.setText(veiculo.getCor());

        // Cria um grupo de toggle para agrupar os RadioButtons
        ToggleGroup toggleGroup = new ToggleGroup();
        radio2Portas.setToggleGroup(toggleGroup);
        radio4Portas.setToggleGroup(toggleGroup);

        String numPortas = veiculo.getNumPortas() + "";
        if (numPortas.equals("2")) {
            radio2Portas.setSelected(true);
            radio4Portas.setSelected(false);
        } else {
            radio4Portas.setSelected(true);
            radio2Portas.setSelected(false);
        }
    }

    public void salvarVeiculo(ActionEvent event) {
        String placa = placaTextField.getText();
        Veiculo veiculo = this.service.pesquisarVeiculoPorPlaca(placa);

        String marca = marcaTextField.getText();
        String modelo = modeloTextField.getText();
        String anoFabricacao = anoFabricacaoTextField.getText();
        String cor = corTextField.getText();
        String numPortas;

        // Cria um grupo de toggle para agrupar os RadioButtons
        ToggleGroup toggleGroup = new ToggleGroup();
        radio2Portas.setToggleGroup(toggleGroup);
        radio4Portas.setToggleGroup(toggleGroup);
        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();

        if (selectedRadioButton != null) {
            numPortas = selectedRadioButton.getText();
        } else {
            Alerts.showAlert("Aviso", "Selecione o número de portas", "", Alert.AlertType.ERROR);
            return; // Sai do método se nenhum RadioButton estiver selecionado
        }

        // Verifica se algum campo está vazio
        if (marca.isEmpty() || modelo.isEmpty() || anoFabricacao.isEmpty() || cor.isEmpty()) {
            Alerts.showAlert("Aviso", "Preencha todos os campos", "", Alert.AlertType.ERROR);
            return; // Sai do método se algum campo estiver vazio
        }

        veiculo.setMarca(marca);
        veiculo.setModelo(modelo);
        veiculo.setAnoFabricacao(parseInt(anoFabricacao));
        veiculo.setCor(cor);
        veiculo.setNumPortas(parseInt(numPortas));

        Alerts.showAlert("Sucesso", "Veículo Alterado!", "", Alert.AlertType.CONFIRMATION);
        limparCampos();

    }

    public void onActionLimiparDados(){
        limparCampos();
    }

    private void limparCampos() {
        placaTextField.clear();
        marcaTextField.clear();
        modeloTextField.clear();
        anoFabricacaoTextField.clear();
        corTextField.clear();
        radio2Portas.setSelected(false);
        radio4Portas.setSelected(false);
    }
    @FXML
    public void btnVoltar(ActionEvent event) {
        MainController mainController = new MainController();
        mainController.onBackToMain(event);
    }
}
