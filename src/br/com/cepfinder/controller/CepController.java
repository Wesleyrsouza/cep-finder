package br.com.cepfinder.controller;

import br.com.cepfinder.exception.CepNotFoundException;
import br.com.cepfinder.model.Address;
import br.com.cepfinder.service.CepService;

public class CepController {

    private CepService cepService;

    public CepController() {
        this.cepService = new CepService(); // Inicializa o serviço
    }

    public Address buscarCep(String cep) {
        // Validação básica do formato do CEP
        if (cep == null || !cep.matches("\\d{8}")) {
            throw new IllegalArgumentException("Formato de CEP inválido. Deve conter 8 dígitos.");
        }

        try {
            // Chama o serviço para buscar o CEP
            return cepService.findCep(cep);

        } catch (CepNotFoundException e) {
            // Repassa a exceção para ser tratada pela Main
            throw e;

        } catch (Exception e) {
            // Trata exceções genéricas
            throw new RuntimeException("Erro inesperado ao buscar o CEP: " + e.getMessage());
        }
    }
}
