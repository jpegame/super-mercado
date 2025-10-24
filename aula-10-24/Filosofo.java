public class Filosofo implements Runnable {
    private final java.util.Random rng = new java.util.Random();
    private final String nome;
    private Filosofo next;

    public Filosofo(String nome) {
        this.nome = nome;
    }
    
    public void setNext(Filosofo next) {
        this.next = next;
    }

    public void run() {
        final int numJantar = 4;
        for (int i = 0; i < numJantar; i++) {
            this.comer(i + 1);
            synchronized(this.next) {
                this.next.notify();
            }
            this.esperar();
        };
    }

    public void comer(int jantarAtual) {
        final long tempoComendo = this.rng.nextLong(1000, 5000);
        final long tempoInicial = System.currentTimeMillis();
        System.out.println(nome + " está comendo pela " + jantarAtual + "a vez");
        while (System.currentTimeMillis() - tempoInicial < tempoComendo);
        System.out.println(nome + " terminou de comer pela " + 
            jantarAtual + "a vez após " + tempoComendo + "ms!");
    }


    public void esperar() {
        try {
            synchronized(this) {
                this.wait(25000);
            }
        } catch(InterruptedException e) {
            System.out.println("Erro");
        }
    }
}
