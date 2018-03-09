import java.io.Serializable;

/**
 * Luokka mallintaa nappulaa
 * @author Kasperi Aavasaari
 *
 */

abstract class Nappula implements Serializable {
	
	private char symboli;
	private boolean vari;
	
	/**
	 * Metodi tarkastaa onko jokin liike sallittu
	 * @param lauta     Lauta, jossa siirto tehdään
	 * @param x1        Alkuruudun x-koordinaatti
	 * @param y1		Alkuruudun y-koordinaatti
	 * @param x2		Loppuruudun x-koordinaatti
	 * @param y2		Loppuruudun y-koordinaatti
	 * @return true, jos mahdolliden, false jos ei
	 */
	
	public abstract boolean liikkeenTarkistus(Shakkilauta lauta, int x1, int y1, int x2, int y2);
	
	/**
	 * Antaa nappulan symbolin
	 * @return char symboli
	 */
	
	public char annaSymboli() {
		return this.symboli;
	}
	
	/**
	 * Antaa nappulan varin
	 * @return true, jos valkoinen, false jos musta
	 */
	
	public boolean annaVari() {
		return this.vari;
	}
	
	/**
	 * Asettaa nappulan varin
	 * @param vari	true, jos nappula on valkoinen, false jos musta
	 */
	
	public void asetaVari(boolean vari) {
		this.vari = vari;
	}
	
	/**
	 * Asettaa nappulan symbolin
	 * @param symboli nappulan symboli
	 */
	
	public void asetaSymboli(char symboli) {
		this.symboli = symboli;
	}
}
