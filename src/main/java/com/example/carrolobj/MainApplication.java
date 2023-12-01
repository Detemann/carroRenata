package com.example.carrolobj;

import com.example.carrolobj.models.Veiculo;
import com.example.carrolobj.repository.VeiculoRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private static final VeiculoRepository veiculosRepository = VeiculoRepository.getInstance();

    public static void main(String[] args) {
        Veiculo veiculo1 = new Veiculo("PLACA", "Toyota", "Corolla", 2020, "branco", 4);
        Veiculo veiculo2 = new Veiculo("XYZ456", "Honda", "Fit", 2019, "preto", 4);
        veiculosRepository.inserirVeiculo(veiculo1);
        veiculosRepository.inserirVeiculo(veiculo2);

        launch();
    }
}