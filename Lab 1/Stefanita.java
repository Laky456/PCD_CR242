public class Stefanita extends Thread {
	int tablou[];
	int poz;
	boolean asc, pare;

	public Stefanita(int tablou[], int poz, boolean asc, boolean pare) {
		this.tablou = tablou;
		this.poz = poz;
		this.asc = asc;
		this.pare = pare;
	}

	public boolean tablouValid(int[] tablou, int pozitie) {
		return tablou != null && pozitie >= 0 && pozitie < tablou.length;
	}

	public int positieNoua(boolean asc, int[] tablou, int pozitie) {
		int pozitieUrmatoare = asc ? pozitie + 1 : pozitie - 1;
		if (tablouValid(tablou, pozitieUrmatoare)) {
			return pozitieUrmatoare;
		}
		return -1;
	}

	public boolean matchesParity(int value) {
		return pare ? value % 2 == 0 : value % 2 != 0;
	}

	private void afiseazaGrup(int[] sume, String[] pozitii) {
		int a = sume[0] + sume[1];
		int b = sume[2] + sume[3];

		System.out.println(
				getName() + " "
				+ sume[0] + " + " + sume[1] + " = " + a + "  "
				+ sume[2] + " + " + sume[3] + " = " + b + "  "
				+ a + " + " + b + " = " + (a + b)
		);
	}



	@Override
	public void run() {
		int nr1 = poz;
		int[] sumeGrup = new int[4];
		String[] pozitiiGrup = new String[4];
		int nrSumeGrup = 0;

		while (tablouValid(tablou, nr1)) {
			boolean perecheGasita = false;

			if (matchesParity(tablou[nr1])) {
				int nr2 = positieNoua(asc, tablou, nr1);

				while (nr2 != -1) {
					if (matchesParity(tablou[nr2])) {
						int sum = tablou[nr1] + tablou[nr2];


						sumeGrup[nrSumeGrup] = sum;
						pozitiiGrup[nrSumeGrup] = nr1 + "-" + nr2;
						nrSumeGrup++;

						if (nrSumeGrup == 4) {
							afiseazaGrup(sumeGrup, pozitiiGrup);
							nrSumeGrup = 0;
						}

						nr1 = positieNoua(asc, tablou, nr2);
						perecheGasita = true;
						break;
					}

					nr2 = positieNoua(asc, tablou, nr2);
				}
			}

			if (!perecheGasita) {
				nr1 = positieNoua(asc, tablou, nr1);
			}
		}
	}



}
