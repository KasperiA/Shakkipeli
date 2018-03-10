
/**
 * Luokka mallintaa sotilas-nappulaa
 * 
 * @author Kasperi Aavasaari
 *
 */

class Sotilas extends Nappula {

	private boolean ensimmainenSiirto;

	public Sotilas(boolean vari) {

		if (vari) {
			super.asetaSymboli('S');
		} else {
			super.asetaSymboli('s');
		}

		super.asetaVari(vari);
		this.ensimmainenSiirto = true;
	}

	@Override
	public boolean liikkeenTarkistus(Shakkilauta lauta, int x1, int y1, int x2, int y2) {

		/*
		 * Mustien liikkeiden erotukset y1 - y2 aina positiiviset ja valkoisten
		 * negatiiviset. Täytyy tehdä kummallekkin omat, koska sotilaat eivät
		 * voi liikkua taaksepäin
		 */

		/* Laudan ulkopuolelle ei voida siirtyä */

		if (x2 > 7 || x2 < 0 || y2 > 7 || y2 < 0) {
			return false;
		}
		
		/*
		 * Täytyy tarkistaa loppuruudun nappulan väri jo metodin alussa, koska muuten on mahdollista saada NullPointerException
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
		 * Vain ensimmäisellä siirrolla voidaan liikkua kaksi ruutua eteenpäin samalla linjalla
		 * Tarkistetaan, onko nappula musta vai valkoinen, onko kyseessä ensimmäinen siirto, liikutaanko samalla linjalla,
		 * liike saa olla 2 ruudun pituinen, loppuruudun täytyy olla tyhjä ja välissä oleva ruutu täytyy myös olla tyhjä
		 */

		if ((!this.annaVari() && ensimmainenSiirto && x1 == x2 && y1 - y2 == 2 && lauta.annaRuutu(x2, y2) == null && lauta.annaRuutu(x2, y2 + 1) == null)
				|| (this.annaVari() && ensimmainenSiirto && x1 == x2 && y1 - y2 == -2 && lauta.annaRuutu(x2, y2) == null && lauta.annaRuutu(x2, y2 - 1) == null)) {
			this.ensimmainenSiirto = false;
			return true;
		}

		/* 
		 * Normaali yhden ruudun liike eteenpäin samalla linjalla 
		 * Tarkistetaan, onko kyseessä musta vai valkoinen nappula, liikutaanko samalla linjalla, loppuruutu täytyy olla tyhjä ja liikkua saa vain yhden ruudun verran
		 */

		
		if ((!this.annaVari() && x1 == x2 && lauta.annaRuutu(x2, y2) == null && y1 - y2 == 1)
				|| (this.annaVari() && x1 == x2 && lauta.annaRuutu(x2, y2) == null && y1 - y2 == -1)) {
			this.ensimmainenSiirto = false;
			return true;
		}

		/* 
		 * Syöminen yhden ruudun verran oikealle tai vasemmalle
		 * Tarkistetaan, onko kyseessä musta vai valkoinen nappula, liikkua voi vain yhden ruudun oikealle tai vasemmalle,
		 * eteenpäin voi liikkua vain yhden ruudun, loppuruudun nappula on erivärinen
		 */

		if ((!this.annaVari() && x1 - x2 == 1 && y1 - y2 == 1 && nappulaVari)
				|| (!this.annaVari() && x1 - x2 == -1 && y1 - y2 == 1 && nappulaVari)
				|| (this.annaVari() && x1 - x2 == 1 && y1 - y2 == -1 && nappulaVari)
				|| (this.annaVari() && x1 - x2 == -1 && y1 - y2 == -1 && nappulaVari)) {
			this.ensimmainenSiirto = false;
			return true;
		}

		return false;
	}

}
