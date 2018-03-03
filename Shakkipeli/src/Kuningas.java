
public class Kuningas extends Nappula{
	
	public Kuningas(boolean vari) {
		if (vari) {
			super.asetaSymboli('K');
		} else {
			super.asetaSymboli('k');
		}
		super.asetaVari(vari);
	}

	@Override
	public boolean liikkeenTarkistus(Shakkilauta lauta, int x1, int y1, int x2, int y2) {
		
		/* Laudan ulkopuolelle ei voida siirtyä */

		if (x2 > 7 || x2 < 0 || y2 > 7 || y2 < 0) {
			return false;
		}
		
		boolean nappulaVari = false;

		try {
			if (lauta.annaRuutu(x2, y2).annaVari() != this.annaVari()) {
				nappulaVari = true;
			}
		} catch (Exception e) {
			nappulaVari = false;
		}
		
		boolean uhataankoRuutua = uhataankoRuutua(lauta, x2, y2);
		
		/* Kahdeksan ruutua ympärillä joihin voi liikkua, jos sitä ei uhata */
		
		if (!uhataankoRuutua && (lauta.annaRuutu(x2, y2) == null || nappulaVari)) {
			if (x1 - x2 == 1 && y1 - y2 == 1) {
				return true;
			} else if (x1 == x2 && y1 - y2 == 1) {
				return true;
			} else if (x1 - x2 == -1 && y1 - y2 == 1) {
				return true;
			} else if (x1 - x2 == -1 && y1 == y2) {
				return true;
			} else if (x1 - x2 == -1 && y1 - y2 == -1) {
				return true;
			} else if (x1 == x2 && y1 - y2 == -1) {
				return true;
			} else if (x1 - x2 == 1 && y1 - y2 == -1) {
				return true;
			} else if (x1 - x2 == 1 && y1 == y2) {
				return true;
			}
		}
		
		
		
		
		return false;
	}
	
	
	// Palauttaa true, jos ruutua uhataan
	public boolean uhataankoRuutua(Shakkilauta lauta, int x, int y) {
		for (int sarake = 0; sarake < 8; sarake++) {
			for(int rivi = 0; rivi < 8; rivi++) {
				if (lauta.annaRuutu(sarake, rivi) instanceof Kuningas) {
					continue;
				}
				if(lauta.annaRuutu(sarake, rivi) != null) {
					if(lauta.annaRuutu(sarake, rivi).annaVari() != this.annaVari()) {
						if (lauta.annaRuutu(sarake, rivi).liikkeenTarkistus(lauta, sarake, rivi, x, y)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
