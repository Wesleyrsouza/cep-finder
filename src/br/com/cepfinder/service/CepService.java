package br.com.cepfinder.service;

import br.com.cepfinder.exception.CepNotFoundException;
import br.com.cepfinder.model.Address;
import com.google.gson.Gson;

public class CepService {

    private HttpClientHelper httpClientHelper;

    public CepService() {
        this.httpClientHelper = new HttpClientHelper();
    }

    public Address findCep(String cep) {
        try {
            // ViaCEP API URL
            String url = "https://viacep.com.br/ws/" + cep + "/json/";

            // Making the HTTP request using HttpClientHelper
            String response = httpClientHelper.sendRequest(url);

            // Checking if the CEP is invalid
            if (response.contains("\"erro\": true")) {
                throw new CepNotFoundException("CEP not found: " + cep);
            }

            // Converting JSON to Address object
            return new Gson().fromJson(response, Address.class);

        } catch (CepNotFoundException e) {
            throw e; // Rethrowing the custom exception
        } catch (Exception e) {
            // General error handling (network error, request failure, etc.)
            throw new RuntimeException("Error when retrieving CEP: " + e.getMessage());
        }
    }
}
