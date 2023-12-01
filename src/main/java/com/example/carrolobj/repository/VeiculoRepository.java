package com.example.carrolobj.repository;

import com.example.carrolobj.models.Veiculo;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;


public class VeiculoRepository {
    private static final VeiculoRepository instance = new VeiculoRepository();
    private final List<Veiculo> arrayVeiculos;
    public VeiculoRepository(){
        this.arrayVeiculos = new ArrayList<>();
    }


    public void inserirVeiculo(Veiculo veiculoNovo){
        this.arrayVeiculos.add(veiculoNovo);
    }
    public void deletarVeiculo(Veiculo veiculo){
        this.arrayVeiculos.remove(veiculo);
    }
    public List<Veiculo> pesquisarVeiculoPorMarca(String marca){
        List<Veiculo> veiculosPorMarca = new ArrayList<>();

        for (Veiculo veiculo : this.arrayVeiculos) {
            if (veiculo.getMarca().equalsIgnoreCase(marca)) {
                veiculosPorMarca.add(veiculo);
            }
        }
        return veiculosPorMarca;
    }
    public Veiculo pesquisaVeiculoPorPlaca(String placa){
        for (Veiculo veiculo : this.arrayVeiculos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    public List<Veiculo> pesquisaTodosVeiculos(){
        return new ArrayList<>(this.arrayVeiculos);
    }

    public static VeiculoRepository getInstance() {
        return instance;
    }

}
