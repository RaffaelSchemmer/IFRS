package ifrs;

public class ATM {

    public static void main(String[] args) {

        // Criando uma conta com R$ 1.000 de saldo
        Conta conta = new Conta(1000.0, "Raffael Bottoli Schemmer");

        // Exibindo saldo atual
        System.out.println("Saldo atual: R$ " + conta.getSaldo());
        
        // Tentando sacar R$ 1000
        if (conta.sacar(100)) {
            System.out.println("Saque realizado com sucesso!");
        } else {
            System.out.println("Saldo insuficiente!");
        }

        // Exibindo saldo atual
        System.out.println("Saldo atual: R$ " + conta.getSaldo());
    }
}
