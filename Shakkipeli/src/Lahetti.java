 
/**
 * Luokka mallintaa Lähettiä
 * @author Kasperi Aavasaari
 *
 */
class Lahetti extends Nappula{
	
	public Lahetti(boolean vari) {
		if (vari) {
			super.asetaSymboli('L');
		} else {
			super.asetaSymboli('l');
		}
		super.asetaVari(vari);
	}

	@Override
	public boolean liikkeenTarkistus(Shakkilauta lauta, int x1, int y1, int x2, int y2) {
		
		/* Laudan ulkopuolelle ei voida siirtyä,
		 * Eikä muuten kuin vinottain
		 * Nappula ei liiku vinoittain, jos x- tai y-koordinaateista toinen on sama, ja toinen on eri*/

		if (x2 > 7 || x2 < 0 || y2 > 7 || y2 < 0 || (x1 != x2 && y1 == y2) || (x1 == x2 && y1 != y2)) {
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
		 * Tarkistetaan, ettei siirron välissä ole nappuloita,
		 * nappula liikkuu vinoittain eli nappulan x-koordinaattien ja y-koordinaattien erotuksen itseisarvot ovat yhtä suuret
		 * ja että loppuruutu on tyhjä tai siinä on erivärinen nappula
		 */
		
		if (!onkoMuitaValissa(lauta, x1, y1, x2, y2) && Math.abs(x1 - x2) == Math.abs(y1 - y2) && (lauta.annaRuutu(x2, y2) == null || nappulaVari)) {
			return true;
		}
		
		
		
		return false;
	}
	
	/**
	 * Tarkistaa aonko alku- ja loppuruudun välillä muita nappuloita. Lähetti ei voi hyppiä nappuloiden yli
	 * @param lauta		Lauta, jossa tarkistetaan onko muita nappuloita valissa
	 * @param x1		Alkuruuudun x-koordinaatti
	 * @param y1		Alkuruudun y-koordinaatti
	 * @param x2		Loppuruudun x-koordinaatti
	 * @param y2		Loppuruudun y-koordinaatti
	 * @return			Palauttaa true, jos muita nappuloita on alku-ja loppuruudun välissä, muuten false
	 */
	public boolean onkoMuitaValissa(Shakkilauta lauta, int x1, int y1, int x2, int y2) {
		
		/* Ideana se, että riippuen suunnasta, johon liikutaan, niin x1 - x2 ja y1 - y2 saavat joko posiitiivisen tai negatiivisen arvon.
		 * Näiden perusteella valitaan suunta, johon lädetään vinoittain tarkistamaan onko välissä muita nappuloita
		 */
		
		//alavasen
		if (x1 - x2 > 0 && y1 - y2 < 0) {
			int x = x1;
			int y = y1;
			
			while (x > x2 + 1 && y < y2 - 1) {
				x--;
				y++;
				if (lauta.annaRuutu(x, y) != null)  {
					return true;
				}
				
			}
		}
		
		//ylävasen
		if (x1 - x2 > 0 && y1 - y2 > 0) {
			int x = x1;
			int y = y1;
			
			while (x > x2 + 1&& y > y2 + 1) {
				x--;
				y--;
				if (lauta.annaRuutu(x, y) != null)  {
					return true;
				}
				
			}
		}
		
		//yläoikea
		if (x1 - x2 < 0 && y1 - y2 > 0) {
			int x = x1;
			int y = y1;
			
			while (x < x2 - 1 && y > y2 + 1) {
				x++;
				y--;
				if (lauta.annaRuutu(x, y) != null)  {
					return true;
				}
				
			}
		}
		
		//alaoikea
		if (x1 - x2 < 0 && y1 - y2 < 0) {
			int x = x1;
			int y = y1;
			
			while (x < x2 -1 && y < y2 - 1) {
				x++;
				y++;
				if (lauta.annaRuutu(x, y) != null)  {
					return true;
				}
				
			}
		}
		
		return false;
	}
}
