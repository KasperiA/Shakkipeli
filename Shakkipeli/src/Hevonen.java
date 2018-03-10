
/**
 * Luokka mallintaa hevosta
 * @author Kasperi Aavasaari
 *
 */
class Hevonen extends Nappula {

	public Hevonen(boolean vari) {
		if (vari) {
			super.asetaSymboli('H');
		} else {
			super.asetaSymboli('h');
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
		 * Täytyy tarkistaa loppuruudussa olevan nappulan väri jo metodin alussa, koska muutem on mahdollista saada NullPointerExcption
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

		/*
		 * Aluksi tarkastetaan, että loppuruudun nappula on erivärinen tai loppuruutu on tyhjä
		 * Siirtomahdollisuuksia on 8, ja voidaan liikkua L-muodossa.
		 * Hevonen voi hypätä nappuloiden yli, joten ei tarvitse tarkastaa onko siirron välissä muita nappuloita
		 */


		if (nappulaVari || lauta.annaRuutu(x2, y2) == null) {
			if ((x1 - x2 == -1 && y1 - y2 == 2) 
					|| (x1 - x2 == -2 && y1 - y2 == 1) 
					|| (x1 - x2 == -2 && y1 - y2 == -1)
					|| (x1 - x2 == -1 && y1 - y2 == -2) 
					|| (x1 - x2 == 1 && y1 - y2 == -2)
					|| (x1 - x2 == 2 && y1 - y2 == -1) 
					|| (x1 - x2 == 2 && y1 - y2 == 1)
					|| (x1 - x2 == 1 && y1 - y2 == 2)) {
				return true;
			}
		}

		return false;
	}

}
