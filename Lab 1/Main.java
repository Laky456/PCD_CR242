import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // tabloul comun, generat o singura data
        int[] mas = new int[100];
        StringBuilder sb = new StringBuilder("mas[] = ");
        for (int i = 0; i < mas.length; i++) {
            mas[i] = (int) (Math.random() * 100) + 1;
            sb.append(mas[i]).append(' ');
        }

        // interfata grafica
        final JTextArea out = new JTextArea();
        SwingUtilities.invokeAndWait(() -> {
            JFrame frame = new JFrame("Laborator 1 - Thread-uri (varianta 7)");
            out.setEditable(false);
            out.setFont(new Font("Monospaced", Font.PLAIN, 13));
            out.setLineWrap(true);
            frame.add(new JScrollPane(out));
            frame.setSize(900, 550);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
        SwingUtilities.invokeLater(() -> out.append(sb + "\n\n"));

        // clasele studentilor (fiecare cu 2 fire) -> 4 fire in total
        Vlad s1 = new Vlad(mas, out);
        LucrareStudent2 s2 = new LucrareStudent2(mas, out);
        s1.start();
        s2.start();

        // main asteapta terminarea tuturor firelor
        s1.join();
        s2.join();

        // informatii despre studenti, litera cu litera, la 100 ms
        String info = "\nLucrare efectuata de: Verdes Vlad si Nume2 Prenume2, "
                + "grupa CR-242, varianta 7\n";
        for (char c : info.toCharArray()) {
            SwingUtilities.invokeLater(() -> out.append(String.valueOf(c)));
            Thread.sleep(100);
        }
    }
}