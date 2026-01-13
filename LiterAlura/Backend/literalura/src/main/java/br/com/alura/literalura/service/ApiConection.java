package br.com.alura.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiConection {

    public String getData(String url) {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        }catch (IOException|InterruptedException e){
            throw new RuntimeException(e);
        }
        String json = response.body();

        // conferir resposta
        if (json == null || json.isEmpty()) {
            throw new RuntimeException("O corpo da resposta da API está vazio.");
        }

        return json;
    }

}
