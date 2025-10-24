
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String args[]) {
        
        List<Thread> threads = new ArrayList<>();
        Filosofo ultimoFilosofo = null;
        Filosofo primeiroFilosofo = null;

        for (int i = 0; i < 5; i++) {
            Filosofo f = new Filosofo("F" + String.valueOf(i + 1));

            if (primeiroFilosofo == null) primeiroFilosofo = f;
            if (ultimoFilosofo != null) ultimoFilosofo.setNext(f);

            Thread t = new Thread(f);
            threads.add(t);
            
            ultimoFilosofo = f;
        }

        ultimoFilosofo.setNext(primeiroFilosofo);

        for (Thread t : threads) t.start();

        for (Thread t : threads)
            
            try {
                
                t.join();
            
            } catch(InterruptedException e) {

            }
    }
}