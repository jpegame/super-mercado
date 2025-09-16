public class SimulacaoCaixaSupermercado {

    private int numeroCaixas;
    private int numeroClientes;
    private double mediaTempoAtendimentoPorCliente;
    private double desvioPadraoTempoAtendimentoPorCliente;

    private static final double TEMPO_MINIMO_ATENDIMENTO = 0.1;

    private final java.util.Random rng = new java.util.Random(42);

    public void setNumeroCaixas(int n) {
        this.numeroCaixas = n;
    }

    public void setNumeroClientes(int n) {
        this.numeroClientes = n;
    }

    public void setMediaTempoAtendimentoPorCliente(double mu) {
        this.mediaTempoAtendimentoPorCliente = mu;
    }

    public void setDesvioPadraoTempoAtendimentoPorCliente(double sigma) {
        this.desvioPadraoTempoAtendimentoPorCliente = sigma;
    }

    private double tempoAtendimentoNormalTruncado() {
        double z = rng.nextGaussian();
        double s = mediaTempoAtendimentoPorCliente +
                desvioPadraoTempoAtendimentoPorCliente * z;
        return (s < TEMPO_MINIMO_ATENDIMENTO) ? TEMPO_MINIMO_ATENDIMENTO : s;
    }

    public double simular() {
        double soma = 0.0;
        int clientesPorCaixa = numeroClientes / numeroCaixas;
        for (int i = 0; i < numeroCaixas; i++) {
            soma += simularCaixa(clientesPorCaixa);
        }

        return soma / numeroClientes;
    }

    public double simularCaixa(int clientesPorCaixa) {
        double soma = 0.0;
        for (int i = 0; i < clientesPorCaixa; i++) {
            soma += tempoAtendimentoNormalTruncado();
        }

        return soma;
    }
}