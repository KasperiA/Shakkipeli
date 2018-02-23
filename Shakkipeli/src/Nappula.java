
abstract class Nappula {
	
	private char symboli;
	private boolean vari;
	
	public abstract boolean liikkeenTarkistus(Pelilauta lauta, int x1, int y1, int x2, int y2);
	
	
	public char annaSymboli() {
		return this.symboli;
	}
	
	public boolean annaVari() {
		return this.vari;
	}
	
	public void asetaVari(boolean vari) {
		this.vari = vari;
	}
	
	public void asetaSymboli(char symboli) {
		this.symboli = symboli;
	}
}
