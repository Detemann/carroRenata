package com.example.carrolobj.services;

import com.example.carrolobj.models.Veiculo;
import com.example.carrolobj.repository.VeiculoRepository;

import java.util.List;

public class VeiculoService {
    private final VeiculoRepository repository = VeiculoRepository.getInstance();

    public void cadastrarVeiculo(Veiculo veiculo) {
        this.repository.inserirVeiculo(veiculo);
    }

    public List<Veiculo> pesquisaTodosOsVeiculos() {
        return this.repository.pesquisaTodosVeiculos();
    }

    public Veiculo pesquisarVeiculoPorPlaca(String placa) {
        return this.repository.pesquisaVeiculoPorPlaca(placa);
    }

    public void deletarVeiculo(Veiculo veiculoEncontrado) {
        this.repository.deletarVeiculo(veiculoEncontrado);
    }

    public List<Veiculo> pesquisarPorMarca(String termoPesquisa) {
        return this.repository.pesquisarVeiculoPorMarca(termoPesquisa);
    }

    public List<Veiculo> pesquisarPorModelo(String termoModelo) {
        return this.repository.pesquisarVeiculoPorModelo(termoModelo);
    }
}
