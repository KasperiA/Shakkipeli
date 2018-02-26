
class Kuningatar extends Nappula{
	
	public Kuningatar(boolean vari, char symboli) {
		super.asetaSymboli(symboli);
		super.asetaVari(vari);
	}

	@Override
	public boolean liikkeenTarkistus(Shakkilauta lauta, int x1, int y1, int x2, int y2) {
		
		/* Laudan ulkopuolelle ei voida siirtyä */

		if (x2 > 7 || x2 < 0 || y2 > 7 || y2 < 0) {
			return false;
		}
		
		
		
		return false;
	}
}
