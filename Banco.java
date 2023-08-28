import java.util.Scanner;

public class Banco {

    public static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        double saldo = 1000.0; // Saldo inicial
        double limiteChequeEspecial = 500.0; // Limite de cheque especial
        int opcao = 0;

        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("Escolha uma opção abaixo : ");
            System.out.println("""
                    1 - Saque
                    2 - Depósito
                    3 - Ver saldo
                    4 - Ver limite de cheque especial
                    5 - Sair
                    """);

            opcao = scan.nextInt();
            limparTela(); // Limpa a tela

            switch(opcao) {

                case 1:
                    System.out.print("Digite o valor do saque: ");
                    double valorSaque = scan.nextDouble();
                    if (valorSaque <= saldo + limiteChequeEspecial) {
                        if (valorSaque <= saldo) {
                            saldo -= valorSaque;
                            System.out.println("Saque realizado com sucesso. Saldo atual: " + saldo);
                        } else {
                            limiteChequeEspecial -= (valorSaque - saldo);
                            saldo = 0;
                            System.out.println("Saque realizado utilizando cheque especial. Saldo atual: " + saldo + ", Cheque especial restante: " + limiteChequeEspecial);
                        }
                    } else {
                        System.out.println("Saldo insuficiente e valor de cheque especial excedido.");
                    }
                    break;

                case 2:
                    System.out.print("Digite o valor do depósito: ");
                    double valorDeposito = scan.nextDouble();
                    saldo += valorDeposito;
                    System.out.println("Depósito realizado com sucesso. Saldo atual: " + saldo);
                    break;

                case 3:
                    System.out.println("Saldo atual: " + saldo);
                    break;

                case 4:
                    System.out.println("Limite de cheque especial: " + limiteChequeEspecial);
                    break;

                case 5:
                    System.out.println("Saindo do programa.");
                    scan.close();
                    System.exit(0);

                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        }
    }
}
