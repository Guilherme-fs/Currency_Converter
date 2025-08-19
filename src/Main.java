import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        ApiService apiService = new ApiService();

        System.out.println("=== Conversor de Moedas ===");

        boolean continuar = true;
        while (continuar) {
            System.out.print("\nDigite a moeda base (ex: USD): ");
            String moedaBase = scanner.nextLine().toUpperCase();

            System.out.print("Digite a moeda de destino (ex: BRL): ");
            String moedaDestino = scanner.nextLine().toUpperCase();

            System.out.print("Digite o valor que deseja converter: ");
            double valor = Double.parseDouble(scanner.nextLine());

            // Chama o serviço da API
            RespostaApi resposta = apiService.buscarDados(moedaBase);

            if (resposta != null && resposta.getConversion_rates().containsKey(moedaDestino)) {
                CurrencyConverter converter = new CurrencyConverter(resposta);
                double convertido = converter.converter(moedaDestino, valor);
                System.out.printf("%.2f %s = %.2f %s%n", valor, moedaBase, convertido, moedaDestino);
            } else {
                System.out.println("Erro: não foi possível obter a taxa de conversão.");
            }

            // Pergunta se o usuário deseja continuar
            System.out.print("\nDeseja fazer outra conversão? (s/n): ");
            String opcao = scanner.nextLine().trim().toLowerCase();
            if (!opcao.equals("s")) {
                continuar = false;
                System.out.println("Encerrando o programa. Até logo!");
            }
        }

        scanner.close();
    }
}