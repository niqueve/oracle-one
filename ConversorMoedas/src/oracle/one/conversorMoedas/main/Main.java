package oracle.one.conversorMoedas.main;

import oracle.one.conversorMoedas.utils.Conection;
import oracle.one.conversorMoedas.utils.ExchangeRate;
import oracle.one.conversorMoedas.utils.Menu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);
    LocalDateTime dateTime = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String formattedDate = dateTime.format(myFormatObj);
    void main (String[]args){
        Conection api = new Conection();
        Menu menu = new Menu();
        String searchComponent = menu.myMenu();
        if (searchComponent.equals("//")){
            System.out.println("\n\nencerrando a aplicação..");
        } else if (searchComponent.length() != 8) {
            System.out.println("\n\nencerrando a aplicação..");
        } else {
            System.out.println("Quanto deseja converter? ");
            double value = scanner.nextDouble();
            System.out.println("Convertendo..\n");
            ExchangeRate exchangeRate = api.getExchangeRate(searchComponent);
            double answer = value * Double.parseDouble(exchangeRate.conversion_rate());
            String exchangeResume = exchangeRate.base_code() + " --> " + exchangeRate.target_code() + " | " +
                    "Data e hora da consulta: "+ formattedDate + "\n"+
                    "Valor para conversão: " + value +" (" + exchangeRate.base_code() +")\n" +
                    "Taxa de conversão atual: " + exchangeRate.conversion_rate() + "\n" +
                    "Valor total convertido: " + answer + " (" + exchangeRate.target_code() +")\n" +
                    "Data da próxima atualização (UTC): " + exchangeRate.time_next_update_utc() + "\n";
            System.out.println(exchangeResume);
        }
    }
}
