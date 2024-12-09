package org.example.service;

import com.google.gson.Gson;
import org.example.repository.Endereco;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ViaCep {
    public static Endereco buscarCEP(String cep) {
        try {
            String url = "https://viacep.com.br/ws/" + cep + "/json/";

            // Configurando o HttpClient e a requisição
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            Map<String, String> dadosCEP = gson.fromJson(response.body(), Map.class);

            if (dadosCEP.containsKey("erro")) {
                System.out.println("CEP inválido ou não encontrado.");
                return null;
            }

            System.out.println("CEP: " + dadosCEP.get("cep"));
            System.out.println("Logradouro: " + dadosCEP.get("logradouro"));
            System.out.println("Bairro: " + dadosCEP.get("bairro"));
            System.out.println("Cidade: " + dadosCEP.get("localidade"));
            System.out.println("Estado: " + dadosCEP.get("uf"));

            Endereco endereco = new Endereco();
            endereco.setLogradouro(dadosCEP.get("logradouro"));
            endereco.setBairro(dadosCEP.get("bairro"));
            endereco.setLocalidade(dadosCEP.get("localidade"));
            endereco.setUf(dadosCEP.get("uf"));

            // Como a API ViaCEP não retorna diretamente "numero", "complemento" e "regiao", você pode verificar esses campos separadamente ou deixá-los nulos, dependendo do seu caso de uso.
            endereco.setNumero(""); // Ou capturar de outro lugar se disponível
            endereco.setComplemento(""); // Ou capturar de outro lugar se disponível
            endereco.setRegiao(""); // Ou capturar de outro lugar se disponível

            return endereco;

        } catch (Exception e) {
            System.err.println("Erro ao buscar o CEP: " + e.getMessage());
        }
        return null;
    }
}
