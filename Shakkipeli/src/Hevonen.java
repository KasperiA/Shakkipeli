
class Hevonen extends Nappula{
	
	
	public Hevonen(boolean vari) {
		if (this.annaVari()) {
			super.asetaSymboli('H');
		} else {
			super.asetaSymboli('h');
		}
		super.asetaVari(vari);
	}

	@Override
	public boolean liikkeenTarkistus(Shakkilauta lauta, int x1, int y1, int x2, int y2) {
		
		/* Laudan ulkopuolelle ei voida siirty� */

		if (x2 > 7 || x2 < 0 || y2 > 7 || y2 < 0) {
			return false;
		}
		
		/*Yhdest� ruudusta on mahdollista siirty� 8 eri ruutuun
		 * My�t�p�iv��n tehty liikkeet */
		
		if ((x1 - x2 == -1 && y1 - y2 == 2 && (lauta.annaRuutu(x2,y2).annaVari() != this.annaVari() || lauta.annaRuutu(x2,y2) == null))
				||(x1 - x2 == -2 && y1 - y2 == 1 && (lauta.annaRuutu(x2,y2).annaVari() != this.annaVari() || lauta.annaRuutu(x2,y2) == null))
				||(x1 - x2 == -2 && y1 - y2 == -1 && (lauta.annaRuutu(x2,y2).annaVari() != this.annaVari() || lauta.annaRuutu(x2,y2) == null))
				||(x1 - x2 == -1 && y1 - y2 == -2 && (lauta.annaRuutu(x2,y2).annaVari() != this.annaVari() || lauta.annaRuutu(x2,y2) == null))
				||(x1 - x2 == 1 && y1 - y2 == -2 && (lauta.annaRuutu(x2,y2).annaVari() != this.annaVari() || lauta.annaRuutu(x2,y2) == null))
				||(x1 - x2 == 2 && y1 - y2 == -1 && (lauta.annaRuutu(x2,y2).annaVari() != this.annaVari() || lauta.annaRuutu(x2,y2) == null))
				||(x1 - x2 == 2 && y1 - y2 == 1 && (lauta.annaRuutu(x2,y2).annaVari() != this.annaVari() || lauta.annaRuutu(x2,y2) == null))
				||(x1 - x2 == 1 && y1 - y2 == 2 && (lauta.annaRuutu(x2,y2).annaVari() != this.annaVari() || lauta.annaRuutu(x2,y2) == null))) {
					return true;
				}
		
		
		return false;
	}
	
	
}
