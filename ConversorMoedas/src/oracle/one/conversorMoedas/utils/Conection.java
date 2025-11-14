package oracle.one.conversorMoedas.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conection {
    Dotenv dotenv = Dotenv.load();
    private String apiKey = dotenv.get("API_KEY");
    private String url = "https://v6.exchangerate-api.com/v6/"
            + this.apiKey +
            "/pair/";

    private String conectToApi (String url){
       try {
           HttpClient client = HttpClient.newHttpClient();

           HttpRequest request = HttpRequest.newBuilder()
                   .uri(URI.create(url))
                   .build();

           HttpResponse<String> response = client
                   .send(request, HttpResponse.BodyHandlers.ofString());

           return response.body();

       } catch (IOException | InterruptedException e) {
           System.out.println("Erro de conexão: " + e.getMessage());
       } catch (Exception e){
           System.out.println("Ocorreu um problema: " + e.getMessage());
       }
       return "";
    }

    public ExchangeRate getExchangeRate (String searchComponent){
        url = this.url + searchComponent;
        String responseJson = this.conectToApi(url);

        if (responseJson.equals("")){
            System.out.println("Conversão indisponível no momento. Tente mais tarde");
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.fromJson(responseJson, ExchangeRate.class);

    }



}
