
class Torni extends Nappula{
	
	public Torni(boolean vari, char symboli) {
		super.asetaSymboli(symboli);
		super.asetaVari(vari);
	}

	@Override
	public boolean liikkeenTarkistus(Pelilauta lauta, int x1, int y1, int x2, int y2) {
		
		/* Laudan ulkopuolelle ei voida siirtyä, 
		 * Eikä voi liikkua vinoittain*/

		if (x2 > 7 || x2 < 0 || y2 > 7 || y2 < 0 || (x1 != x2 && y1 != y2)) {
			return false;
		}
		
		if (!onkoMuitaValissa(lauta, x1, x2, y1, y2) && (lauta.annaRuutu(x2, y2).annaVari() != this.annaVari() || lauta.annaRuutu(x2, y2) == null)) {
			return true;
		}
		
		return false;
	}
	
	/* Tarkistaa onko nappulan alku- ja loppupaikan välillä muita nappuloita. Palauttaa false jos ei ole */
	
	public boolean onkoMuitaValissa(Pelilauta lauta, int x1, int y1, int x2, int y2) {
		if (x1 == x2) {
			if (y1 < y2) {
				for (int i = y2; i > y1; i--) {
					if (lauta.annaRuutu(x2, i) != null) {
						return true;
					}
				}
			}
			if (y1 > y2) {
				for (int i = y1; i > y2; i--) {
					if (lauta.annaRuutu(x2, i) != null) {
						return true;
				}
			}
		}
		
		if (y1 == y2) {
			if (x1 < x2) {
				for (int i = x2; i > x1; i--) {
					if (lauta.annaRuutu(y2, i) != null) {
						return true;
					}
				}
			}
			if (x1 > x2) {
				for (int i = x1; i > x2; i--) {
					if (lauta.annaRuutu(y2, i)) != null) {
						return true;
					}
				}
			}
		}
		
		return false;
		}
	}
}
