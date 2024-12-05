package br.com.cepfinder.application;

import br.com.cepfinder.controller.CepController;
import br.com.cepfinder.exception.CepNotFoundException;
import br.com.cepfinder.model.Address;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Instância do controlador
        CepController cepController = new CepController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Bem-vindo ao Busca CEP =====");

        while (true) {
            try {
                System.out.print("Digite o CEP (apenas números) ou 'sair' para encerrar: ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("sair")) {
                    System.out.println("Encerrando o programa. Até logo!");
                    break;
                }

                // Chama o controlador para buscar o endereço
                Address address = cepController.buscarCep(input);

                // Exibe o resultado
                System.out.println("\n=== Endereço Encontrado ===");
                System.out.println("CEP: " + address.getCep());
                System.out.println("Logradouro: " + address.getLogradouro());
                System.out.println("Bairro: " + address.getBairro());
                System.out.println("Localidade: " + address.getLocalidade());
                System.out.println("UF: " + address.getUf());
                System.out.println();

            } catch (IllegalArgumentException e) {
                // Trata erro de formato de CEP inválido
                System.out.println("Erro: " + e.getMessage());
            } catch (CepNotFoundException e) {
                // Trata erro de CEP não encontrado
                System.out.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                // Trata erros genéricos
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }

        scanner.close();
    }
}