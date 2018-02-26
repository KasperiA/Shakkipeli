
public class Kuningas extends Nappula{
	
	public Kuningas(boolean vari) {
		if (this.annaVari()) {
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
		
		
		
		return false;
	}
}
