
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
		 * Eikä muuten kuin vinottain*/

		if (x2 > 7 || x2 < 0 || y2 > 7 || y2 < 0 || (x1 != x2 && y1 == y2) || (x1 == x2 && y1 != y2)) {
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
		
		/*Nappula liikkuu vinoittain, kun sen alku- ja loppukoordinaattien erotuksien itseisarvot ovat yhtä suuret |x1 - x2| = |y1 - y2| */
		
		if (!onkoMuitaValissa(lauta, x1, y1, x2, y2) && Math.abs(x1 - x2) == Math.abs(y1 - y2) && (lauta.annaRuutu(x2, y2) == null || nappulaVari)) {
			return true;
		}
		
		
		
		return false;
	}
	
	/*Tarkistaa onko alku ja loppuruudun välissä muita nappuloita, jos ei ole niin palauttaa false*/
	
	public boolean onkoMuitaValissa(Shakkilauta lauta, int x1, int y1, int x2, int y2) {
		
		/* Neljä suuntaa, pitää tarkistaa mihin suuntaan ollaan liikkumassa */
		
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
