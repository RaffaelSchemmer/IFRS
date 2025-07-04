package ifrs;

public class Conta {

    // Atributos privados (encapsulamento)
    private double saldo;
    private String titular;

    // Construtor
    public Conta(double saldoInicial, String titular) {
        this.saldo = saldoInicial;
        this.titular = titular;
    }

    // Método público para realizar saque
    public boolean sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            return true;
        }        
        return false;
    }

    // Getter para o saldo
    public double getSaldo() {
        return saldo;
    }

    // Getter para o titular
    public String getTitular() {
        return titular;
    }

    // Setter para o titular (opcional, para mostrar alteração de estado)
    public void setTitular(String novoTitular) {
        this.titular = novoTitular;
    }
}
