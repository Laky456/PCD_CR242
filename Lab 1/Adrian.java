class Adrian extends Thread {
    int start, stop, pas;
    int tablou[];

    public Adrian(int start, int stop, int pas, int tablou[]) {
        this.start = start;
        this.stop = stop;
        this.pas = pas;
        this.tablou = tablou;
    }

    @Override
    public void run() {
        int sumFin = 0;
        int primImpar = -1;

        for (int positie = start; positie != stop + pas; positie += pas) {

            if (tablou[positie] % 2 == 0) {
                continue;
            }

            if (primImpar == -1) {
                primImpar = positie;
            } else {
                int suma = tablou[primImpar] + tablou[positie];
                sumFin += suma;

                System.out.println(getName() + " poz val 1: " + primImpar + " " + tablou[primImpar] + ", poz val 2: "
                        + positie + " " + tablou[positie] + ", suma: " + suma);

                primImpar = -1;
            }
        }
        System.out.println("Sum Total " + getName() + ": " + sumFin);
    }
}