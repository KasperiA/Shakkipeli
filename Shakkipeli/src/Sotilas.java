
class Sotilas extends Nappula {
	
	private boolean ensimmainenSiirto;
	
	public Sotilas(boolean vari) {
		
		if (this.annaVari()) {
			super.asetaSymboli('S');
		} else {
			super.asetaSymboli('s');
		}
		
		
		super.asetaVari(vari);
		this.ensimmainenSiirto = true;
	}

	@Override
	public boolean liikkeenTarkistus(Shakkilauta lauta, int x1, int y1, int x2, int y2) {
		
		/* Mustien liikkeiden erotukset y1 - y2 aina positiiviset ja valkoisten negatiiviset. 
		 * Täytyy tehdä kummallekkin omat, koska muuten ei toimisi odotetusti
		 */
		
		/* Laudan ulkopuolelle ei voida siirty� */

		if (x2 > 7 || x2 < 0 || y2 > 7 || y2 < 0) {
			return false;
		}
		
		
		
		/* Vain ensimm�isell� siirrolla voidaan liikkua kaksi ruutua eteenp�in samalla linjalla*/
				
		if ((!this.annaVari() && ensimmainenSiirto && x1 == x2 && y1 - y2 == 2 && lauta.annaRuutu(x2, y2) == null && lauta.annaRuutu(x2, y2 + 1) == null) 
				|| (this.annaVari() && ensimmainenSiirto && x1 == x2 && y1 - y2 == -2 && lauta.annaRuutu(x2, y2) == null && lauta.annaRuutu(x2, y2 - 1) == null)) {
			this.ensimmainenSiirto = false;
			return true;
		}
		
		/* Normaali yhden ruudun liike eteenp�in samalla linjalla*/
		
		if ((!this.annaVari() && x1 == x2 && lauta.annaRuutu(x2,y2) == null && y1 - y2 == 1)
				|| (this.annaVari() && x1 == x2 && lauta.annaRuutu(x2,y2) == null && y1 - y2 == -1)) {
			return true;
		}
		
		
		
		/* Sy�minen yhden ruudun verran oikealle tai vasemmalle*/
		
		if ((!this.annaVari() && x1 - x2 == 1 && y1 - y2 == 1 && lauta.annaRuutu(x2, y2).annaVari() != this.annaVari()) 
				|| (!this.annaVari() && x1 - x2 == -1 && y1 - y2 == 1 && lauta.annaRuutu(x2, y2).annaVari() != this.annaVari())
				|| (this.annaVari() && x1 - x2 == 1 && y1 - y2 == -1 && lauta.annaRuutu(x2, y2).annaVari() != this.annaVari())
				|| (this.annaVari() && x1 - x2 == -1 && y1 - y2 == -1 && lauta.annaRuutu(x2, y2).annaVari() != this.annaVari())) {
			return true;
		}
		
		return false;
	}

}
