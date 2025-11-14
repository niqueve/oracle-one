package oracle.one.desafio1.modelos;

public class Conta {
    private String nome;
    private int tipoDeConta;
    private String extrato;
    private double saldoEmConta;

    public String getNome() {
        return nome;
    }

    public int getTipoDeConta() {
        return tipoDeConta;
    }

    public double getSaldoEmConta() {
        return saldoEmConta;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipoDeConta(int tipoDeConta) {
        this.tipoDeConta = tipoDeConta;
    }

    public Conta(String nome, int tipo) {
        setNome(nome);
        setTipoDeConta(tipo);
    }

    void saldo() {
        System.out.println("Seu saldo atual é de: " + saldoEmConta);
    }
    void deposito(double valorDepositado) {
        if (valorDepositado <=0){
            System.out.println("Deposito não realizado. " +
                    "Valores iguais ou menores que zero não serão aceitos.");
        }else {
            double saldo = this.saldoEmConta + valorDepositado;
            this.extrato += ("/n Operação de Depósito - " +
                    "Saldo anterior %.2f - Saldo atual %.2f ").formatted(this.saldoEmConta, saldo);
            this.saldoEmConta = saldo;
        }
    }
    void saque(double valorSaque) {
        if (valorSaque > this.saldoEmConta && valorSaque < 0){
            System.out.println("Impossível realizar operação de saque. " +
                    "Verifique o saldo e valor digitado");
        }else{
            double valorRestante = this.saldoEmConta -= valorSaque;
            this.extrato += ("Operação de Saque - " +
                    "Saldo anterior %.2f - Saldo atual %.2f").formatted(this.saldoEmConta, valorRestante);
            this.saldoEmConta = valorRestante;
        }
    }
    void getExtrato () {
        System.out.print(this.extrato);
    }
}
