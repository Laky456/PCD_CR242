import javax.swing.*;
//test
public class LucrareStudent2 {
    private final int[] mas;
    private final JTextArea out;
    private final String prefix;
    private final Thread th1, th2;

    public LucrareStudent2(int[] mas, JTextArea out) {
        this.mas = mas;
        this.out = out;
        this.prefix = "S2-";
        // Th1 = conditia 1 (de la primul element), Th2 = conditia 2 (de la ultimul)
        th1 = new Thread(new Task(true), prefix + "Th1");
        th2 = new Thread(new Task(false), prefix + "Th2");
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

    // Runnable folosit de ambele fire; diferă doar sensul de parcurgere
    private class Task implements Runnable {
        private final boolean fromStart;

        Task(boolean fromStart) {
            this.fromStart = fromStart;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            int[] g = new int[4];
            int cnt = 0;

            for (int k = 0; k < mas.length; k++) {
                int i = fromStart ? k : mas.length - 1 - k;
                if (mas[i] % 2 == 0) {
                    g[cnt++] = mas[i];
                    if (cnt == 4) {
                        long p1 = (long) g[0] * g[1];
                        long p2 = (long) g[2] * g[3];
                        print(name + ": pare: " + g[0] + " " + g[1] + " " + g[2] + " " + g[3]
                                + " | " + g[0] + "*" + g[1] + "=" + p1
                                + " | " + g[2] + "*" + g[3] + "=" + p2
                                + " | suma = " + (p1 + p2));
                        cnt = 0;
                    }
                }
            }
            // grupul incomplet (< 4 numere) nu se afiseaza
        }
    }
}