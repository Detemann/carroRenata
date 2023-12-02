package com.example.carrolobj;

import com.example.carrolobj.models.Veiculo;
import com.example.carrolobj.repository.VeiculoRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

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
        //veiculo para testes com placa facil de lembrar
        Veiculo veiculo1 = new Veiculo("PLACA", "Toyota", "Corolla", 2020, "branco", 4);
        veiculosRepository.inserirVeiculo(veiculo1);


        String[] marcas = {"Chevrolet", "Fiat", "Volkswagen", "Ford", "Renault"};
        String[][] modelosPorMarca = {
                {"Onix", "Prisma", "Cruze"},
                {"Uno", "Palio", "Strada"},
                {"Gol", "Voyage", "Saveiro"},
                {"Ka", "Fiesta", "EcoSport"},
                {"Sandero", "Logan", "Duster"}
        };

        String[] cores = {"branco", "preto", "prata", "azul", "vermelho", "verde"};

        //cria 10 veiculos para exemplo
        for (int i = 1; i <= 10; i++) {
            String placa = generateMercoSulPlate();
            String marca = marcas[new Random().nextInt(marcas.length)];
            String[] modelosDaMarca = modelosPorMarca[getIndexByMarca(marcas, marca)];
            String modelo = modelosDaMarca[new Random().nextInt(modelosDaMarca.length)];
            int ano = 2022 - i;
            String cor = cores[new Random().nextInt(cores.length)];
            int numPortas = i % 2 + 3;

            Veiculo veiculo = new Veiculo(placa, marca, modelo, ano, cor, numPortas);
            veiculosRepository.inserirVeiculo(veiculo);
        }

        launch();
    }

    private static int getIndexByMarca(String[] marcas, String marca) {
        for (int i = 0; i < marcas.length; i++) {
            if (marcas[i].equals(marca)) {
                return i;
            }
        }
        return -1;
    }
    private static String generateMercoSulPlate() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        Random random = new Random();
        StringBuilder plateBuilder = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            plateBuilder.append(letters.charAt(random.nextInt(letters.length())));
        }

        plateBuilder.append(numbers.charAt(random.nextInt(numbers.length())));

        for (int i = 0; i < 2; i++) {
            plateBuilder.append(letters.charAt(random.nextInt(letters.length())));
        }

        for (int i = 0; i < 2; i++) {
            plateBuilder.append(numbers.charAt(random.nextInt(numbers.length())));
        }

        return plateBuilder.toString();
    }



}