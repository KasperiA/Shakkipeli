
/**
 * Luokka mallintaa Tornia
 * @author Kasperi
 *
 */

class Torni extends Nappula {

	public Torni(boolean vari) {
		if (vari) {
			super.asetaSymboli('T');
		} else {
			super.asetaSymboli('t');
		}
		super.asetaVari(vari);
	}

	@Override
	public boolean liikkeenTarkistus(Shakkilauta lauta, int x1, int y1, int x2, int y2) {

		/*
		 * Laudan ulkopuolelle ei voida siirtyä.
		 * Ei voi liikkua vinoittain
		 */

		if (x2 > 7 || x2 < 0 || y2 > 7 || y2 < 0 || (x1 != x2 && y1 != y2)) {
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
		 * Torni voidaan siirtää, jos välillä ei ole muita nappuloita
		 * ja jos loppuruudun nappula on erivärinen tai ruutu on tyhjä
		 */

		if (!onkoMuitaValissa(lauta, x1, y1, x2, y2) && (nappulaVari || lauta.annaRuutu(x2, y2) == null)) {
			return true;
		}

		return false;
	}

	/**
	 * Metodi tarkistaa onko alku- ja loppuruudun välissä muita nappuloita.
	 * @param lauta Shakkilauta, jossa siirto tehdään
	 * @param x1 Alkuruudun x-koordinaatti
	 * @param y1 Alkuruudun y-koordinaatti
	 * @param x2 Loppuruudun x-koordinaatti
	 * @param y2 Loppuruudun y-koordinaatti
	 * @return true, jos muita on välissä, muulloin false
	 */
	
	public boolean onkoMuitaValissa(Shakkilauta lauta, int x1, int y1, int x2, int y2) {
		
		/*
		 * Tarkistetaan liikkuuko torni vaakasuoraan,
		 * jonka jälkeen tarkistetaan liikutaanko oikealle vai vasemmalle
		 * ja tarkistetaan onko välillä muita nappuloita.
		 */
		if (x1 == x2) {
			if (y1 < y2) {
				for (int i = y2 - 1; i > y1; i--) {
					if (lauta.annaRuutu(x2, i) != null) {
						return true;
					}
				}
			}
			if (y1 > y2) {
				for (int i = y1 - 1; i > y2; i--) {
					if (lauta.annaRuutu(x2, i) != null) {
						return true;
					}
				}
			}
		}
		
		/*
		 * Tarkistetaan liikkuuko torni vaakasuoraan,
		 * jonka jälkeen tarkistetaan liikkuuko torni ylös vai alas
		 * ja tarkastetaan onko välissä muita nappuloita
		 */
		
		if (y1 == y2) {
			if (x1 < x2) {
				for (int i = x2 - 1; i > x1; i--) {
					if (lauta.annaRuutu(i, y2) != null) {
						return true;
					}
				}
			}
			if (x1 > x2) {
				for (int i = x1 - 1; i > x2; i--) {
					if (lauta.annaRuutu(i, y2) != null) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
