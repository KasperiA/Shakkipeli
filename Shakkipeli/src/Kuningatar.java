
class Kuningatar extends Nappula {

	public Kuningatar(boolean vari) {
		if (vari) {
			super.asetaSymboli('Q');
		} else {
			super.asetaSymboli('q');
		}
		super.asetaVari(vari);
	}

	@Override
	public boolean liikkeenTarkistus(Shakkilauta lauta, int x1, int y1, int x2, int y2) {

		/* Laudan ulkopuolelle ei voida siirtyä */

		if (x2 > 7 || x2 < 0 || y2 > 7 || y2 < 0) {
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
		
		if (x1 == x2 || y1 == y2) {
			if (!onkoMuitaValissa(true, lauta, x1, y1, x2, y2) && (nappulaVari || lauta.annaRuutu(x2, y2) == null)) {
				return true;
			}
		} else {
			if (!onkoMuitaValissa(false, lauta, x1, y1, x2, y2) && Math.abs(x1 - x2) == Math.abs(y1 - y2)
					&& (lauta.annaRuutu(x2, y2) == null || nappulaVari)) {
				return true;
			}
		}

		return false;
	}

	// Jos liikkuu suoraan, niin true, jos vinottain niin false
	public boolean onkoMuitaValissa(boolean suunta, Shakkilauta lauta, int x1, int y1, int x2, int y2) {
		/* Neljä suuntaa, pitää tarkistaa mihin suuntaan ollaan liikkumassa */
		if (suunta) {
			if (x1 == x2) {
				if (y1 < y2) {
					for (int i = y2 - 1; i > y1; i--) {
						if (lauta.annaRuutu(x2, i) != null) {
							return true;
						}
					}
				}
				if (y1 > y2) {
					for (int i = y1 + 1; i > y2; i--) {
						if (lauta.annaRuutu(x2, i) != null) {
							return true;
						}
					}
				}
			}

			if (y1 == y2) {
				if (x1 < x2) {
					for (int i = x2 - 1; i > x1; i--) {
						if (lauta.annaRuutu(y2, i) != null) {
							return true;
						}
					}
				}
				if (x1 > x2) {
					for (int i = x1 + 1; i > x2; i--) {
						if (lauta.annaRuutu(y2, i) != null) {
							return true;
						}
					}
				}
			}
		} else {
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
				
				while (x > x2 + 1 && y > y2 + 1) {
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
		}
		return false;
	}
}
