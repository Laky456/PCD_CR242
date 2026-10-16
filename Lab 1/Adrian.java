import javax.swing.*;

public class Adrian {
    private final int[] mas;
    private final JTextArea out;
    private final Thread th1;
    private final Thread th2;

    public Adrian(int[] mas, JTextArea out) {
        this.mas = mas;
        this.out = out;
        // Th1: parcurgere 0 -> n-1 ; Th2: parcurgere n-1 -> 0
        th1 = new Thread(new Worker(0, 1), "A-Th1");
        th2 = new Thread(new Worker(mas.length - 1, -1), "A-Th2");
    }

    public void start() {
        th1.start();
        th2.start();
    }

    public void join() throws InterruptedException {
        th1.join();
        th2.join();
    }

    private void print(String s) {
        SwingUtilities.invokeLater(() -> out.append(s + "\n"));
    }

    // Runnable comun; difera doar punctul de start si sensul (pasul)
    private class Worker implements Runnable {
        private final int start;
        private final int step;

        Worker(int start, int step) {
            this.start = start;
            this.step = step;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            long total = 0;
            int pairNo = 0;
            Integer first = null; // primul numar par din pereche

            for (int i = start; i >= 0 && i < mas.length; i += step) {
                if (mas[i] % 2 != 0) {
                    continue;
                }
                if (first == null) {
                    first = mas[i];
                } else {
                    total += (long) first * mas[i]; // suma se calculeaza intr-un singur rand
                    pairNo++;
                    print(String.format("%s | perechea %2d: %3d * %3d = %5d | suma = %6d",
                            name, pairNo, first, mas[i], (long) first * mas[i], total));
                    first = null;
                }
            }
            // un eventual numar par ramas fara pereche nu se ia in calcul
            print(name + " | REZULTAT: suma produselor = " + total
                    + " (" + pairNo + " perechi)");
        }
    }
}
