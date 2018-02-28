
class Lahetti extends Nappula{
	
	public Lahetti(boolean vari) {
		if (this.annaVari()) {
			super.asetaSymboli('L');
		} else {
			super.asetaSymboli('l');
		}
		super.asetaVari(vari);
	}

	@Override
	public boolean liikkeenTarkistus(Shakkilauta lauta, int x1, int y1, int x2, int y2) {
		
		/* Laudan ulkopuolelle ei voida siirtyä,
		 * Eikä muuten kuin vinottain*/

		if (x2 > 7 || x2 < 0 || y2 > 7 || y2 < 0 || (x1 != x2 && y1 == y2) || (x1 == x2 && y1 != y2)) {
			return false;
		}
		
		/*Nappula liikkuu vinoittain, kun sen alku- ja loppukoordinaattien erotuksien itseisarvot ovat yhtä suuret |x1 - x2| = |y1 - y2| */
		
		if (!onkoMuitaValissa(lauta, x1, y1, x2, y2) && Math.abs(x1 - x2) == Math.abs(y1 - y2)) {
			return true;
		}
		
		
		
		return false;
	}
	
	/*Tarkistaa onko alku ja loppuruudun välissä muita nappuloita, jos ei ole niin palauttaa false*/
	
	public boolean onkoMuitaValissa(Shakkilauta lauta, int x1, int y1, int x2, int y2) {
		
		return false;
	}
}
