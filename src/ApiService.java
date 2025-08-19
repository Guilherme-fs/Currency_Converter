import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

public class ApiService {
    private static final String CHAVE_API = "822c9b724aed1607920ff927"; // sua chave aqui
    private static final String URL_BASE = "https://v6.exchangerate-api.com/v6/";

    public RespostaApi buscarDados(String codigoMoedaBase) throws IOException, InterruptedException {
        String url = URL_BASE + CHAVE_API + "/latest/" + codigoMoedaBase;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            Gson gson = new Gson();
            return gson.fromJson(response.body(), RespostaApi.class);
        } else {
            System.out.println("Erro na requisição: " + response.statusCode());
            return null;
        }
    }
}
