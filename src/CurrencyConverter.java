public class CurrencyConverter {
    private RespostaApi dados;

    public CurrencyConverter(RespostaApi dados) {
        this.dados = dados;
    }

    public double converter(String moedaDestino, double valor) {
        Double taxa = dados.getConversion_rates().get(moedaDestino);
        if (taxa == null) {
            throw new IllegalArgumentException("Moeda destino não encontrada: " + moedaDestino);
        }
        return valor * taxa;
    }
}

