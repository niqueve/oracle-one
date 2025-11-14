package oracle.one.desafio1.modelos;

public class ContaCorrente extends Conta{
    private double taxaDeJuros = 0.3;
    private int limiteChequeEspecial = 2500;

    //------------------------------------------------------métodos get
    public double getTaxaDeJuros() {
        return taxaDeJuros;
    }
    public double getLimiteChequeEspecial(){
        return limiteChequeEspecial;
    }
    //---------------------------------------------------construtor
    public ContaCorrente(String nome, int tipo) {
        super(nome, tipo);
    }
    //------------método limite cheque especial
    public double saldoComLimite (){
        return (getSaldoEmConta() + getLimiteChequeEspecial());
    }
}
