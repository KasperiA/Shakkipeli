
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
		
		/*
		 * Täytyy tarkistaa loppuruudussa olevan nappulan väri jo metodin alussa, koska muuten on mahdollista saada NullPointerExcption
		 * Jos nappulaVari on true, niin nappulat ovat erivärisiä
		 */
		
		boolean nappulaVari = false;

		try {
			if (lauta.annaRuutu(x2, y2).annaVari() != this.annaVari()) {
				nappulaVari = true;
			}
		} catch (Exception e) {
			nappulaVari = false;
		}
		
		// Tarkistetaan uhataanko ruutua, johon ollaan siirtymässä
		
		boolean uhataankoRuutua = uhataankoRuutua(lauta, x2, y2);
		
		/* Tarkistetaan, ettei ruutua uhata, ja että ruutu on tyhjä tai siinä on erivärinen nappula 
		 * Sitten tarkistetaan, että ruutu, johon halutaan siirtyä, kuuluu yhteen kahdeksasta 
		 * ympärillä olevasta ruudusta.
		 */
		
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
	
	
	/**
	 * Metodi tarkistaa uhataanko jotakin ruutua
	 * @param lauta Shakkilauta, jossa tarkistetaan ruutu
	 * @param x Ruudun x-koordinaatti
	 * @param y Ruudun y-koordinaatti
	 * @return Jos ruutua uhataan palautetaan true, jos ei niin false
	 */
	public boolean uhataankoRuutua(Shakkilauta lauta, int x, int y) {
		/*
		 * Ideana on se, että käydään kaikki laudan ruudut läpi
		 * ja tarkastetaan ettei ruudussa ole samaa kuningas-nappulaa jota yritetään siirtää, 
		 * koska muuten metodi joutuu ikuiseen looppiin,
		 * sitten tarkistetaan että ruutu ei ole tyhjä,
		 * jonka jälkeen tarkistetaan että ruudussa on erivärinen nappula
		 * ja sitten pystyykö ruuudussa oleva nappula siirtymään ruutuun, johon ollaan siirtymässä.
		 */
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
