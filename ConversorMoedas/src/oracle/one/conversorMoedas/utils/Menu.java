package oracle.one.conversorMoedas.utils;

import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    String section = new String("-".repeat(50));
    String welcome = new String("\n"+ this.section + "Bem vindo ao conversor de moedas" + this.section);
    String askBaseCoin = new String("\nEscolha a moeda base que deseja coverter");
    String askTargetCoin = new String("\nEscolha a moeda para qual deseja fazer a conversão");

    private String coin (){
        String options = new String(
                "Digite a opção desejada: \n\n" +
                        "1 - Dólar (USD)  \n" +
                        "2 - Euro (EUR) \n" +
                        "3 - Iene (JPY)\n" +
                        "4 - Libra (GBP) \n" +
                        "5 - Yuan (CNY) \n" +
                        "6 - Real (BRL) \n" +
                        "7 - Desejo consultar outra moeda \n" +
                        "* Digite qualquer outra opção para encerrar a operação"
        );
        System.out.println(options);
        String choice = scanner.nextLine();
        return choice;
    }

    private String switchCase (String choice){
        String choiceCoin;
        switch (choice){
            case "1":
                choiceCoin = "USD";
                break;
            case "2":
                choiceCoin = "EUR";
                break;
            case "3":
                choiceCoin = "JPY";
                break;
            case "4":
                choiceCoin = "GBP";
                break;
            case "5":
                choiceCoin = "CNY";
                break;
            case "6":
                choiceCoin = "BRL";
                break;
            case "7":
                System.out.println("Digite a sigla da moeda (ex: BRL): ");
                choiceCoin = scanner.nextLine().toUpperCase();
                break;
            default:
                System.out.println("Encerrando..");
                System.exit(0);
                return "";

        }return choiceCoin;

    }

    public String myMenu (){
        System.out.println(this.welcome);
        System.out.println(this.askBaseCoin);
        String choiceBase = this.switchCase(this.coin());
        if(choiceBase.length() != 3){
            return "//";
        }else{
            System.out.println(this.askTargetCoin);
            String choiceTarget = this.switchCase(this.coin());
            if (choiceTarget.length() != 3){
                return "//";
            }else {
                return choiceBase + "/" + choiceTarget + "/";
            }
        }
    }
}
