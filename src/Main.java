import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String... args) {
        final int NUMERO_SIMULACOES = 10;
        List<Integer> numeroCaixas = List.of(1, 2, 3);
        List<Double> desvioPadroes = List.of(0.25, 0.5, 1.0);

        for (Integer numero : numeroCaixas) {
            List<Double> mediaAtendimentoPorCaixa = executaSimulacoes(numero, 0.5, NUMERO_SIMULACOES);

            double media = media(mediaAtendimentoPorCaixa);
            double dp = desvioPadrao(mediaAtendimentoPorCaixa, media);
            System.out.printf("Média dos tempos de atendimento (%d simulacoes) para %d caixa(s): %.5fmin%n", NUMERO_SIMULACOES, numero, media);
            System.out.printf("Desvio-padro das medias: %.5f min%n", dp);
        };

        System.out.printf("\n");

        for (Double desvioPadrao : desvioPadroes) {
            List<Double> mediaAtendimentoPorCaixa = executaSimulacoes(1, desvioPadrao, NUMERO_SIMULACOES);

            double media = media(mediaAtendimentoPorCaixa);
            double dp = desvioPadrao(mediaAtendimentoPorCaixa, media);
            System.out.printf("Média dos tempos de atendimento (%d simulacoes) com desvio padrão = %.2f: %.5fmin%n", NUMERO_SIMULACOES, desvioPadrao, media);
            System.out.printf("Desvio-padro das medias: %.5f min%n", dp);
        };
    }

    public static List<Double> executaSimulacoes(int numeroCaixas, double desvioPadrao, int numeroSimulacoes) {
        final List<Double> mediasAtendimento = new ArrayList<>();

        SimulacaoCaixaSupermercado simulador = new SimulacaoCaixaSupermercado();

        simulador.setNumeroCaixas(numeroCaixas);
        simulador.setNumeroClientes(100);
        simulador.setMediaTempoAtendimentoPorCliente(5.00);
        simulador.setDesvioPadraoTempoAtendimentoPorCliente(desvioPadrao);

        for (int i = 0; i < numeroSimulacoes; i++) {
            double mediaAtendimento = simulador.simular();
            mediasAtendimento.add(mediaAtendimento);
        }

        return mediasAtendimento;
    }

    private static double media(List<Double> xs) {
        double s = 0.0;
        for (double x : xs)
            s += x;
        return s / xs.size();
    }

    private static double desvioPadrao(List<Double> xs, double m) {
        double s2 = 0.0;
        for (double x : xs) {
            double d = x - m;
            s2 += d * d;
        }
        return Math.sqrt(s2 / (xs.size() - 1));
    }
}
