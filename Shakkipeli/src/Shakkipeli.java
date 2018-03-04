

import java.util.Scanner;

/**
@author Jaakko Kittilä
 */



public class Shakkipeli {

	public static void main(String[] args) {
		
		Scanner lukija = new Scanner(System.in);
		
		Shakkilauta shakkilauta = new Shakkilauta(lukija);
		shakkilauta = shakkilauta.lataa();
		if (shakkilauta == null) {
			shakkilauta = new Shakkilauta(lukija);
			System.out.println("Alustetaan uusi lauta");
			shakkilauta.alusta();
		}
		shakkilauta.asetaLukija(lukija);
		// tulee tehdä sillä scanner ei säily latauksessa ja tallennuksessa
		
		while (true) {

			shakkilauta.tulosta();
			
			System.out.println("Anna siirrot muodossa RUUTU1>RUUTU2 esim. A1>B2");
			System.out.println("Lyhyt tornitus tapahtuu syötteellä O-O ja pitkä syötteellä O-O-O.");
			System.out.println("Poistuminen tapahtuu syötteellä exit.");
			
			String valittu = lukija.nextLine().toUpperCase();

			switch (valittu){
				case "O-O":
					boolean tornitus1 = teeTornitus(false, shakkilauta);
					if (tornitus1){
						shakkilauta.vaihdaPelivuoro();
					}
					break;
				case "O-O-O":
					boolean tornitus2 = teeTornitus(true, shakkilauta);
					if (tornitus2){
						shakkilauta.vaihdaPelivuoro();
					}
					break;
				case "EXIT":
					shakkilauta.tallenna();
					System.exit(0);
				default:
					boolean siirtoOnnistuu = false;
					try {
						String siirto = valittu.replaceAll("[^A-H1-8]", "");
						int x1 = siirto.charAt(0) - 'A';
						int x2 = siirto.charAt(2) - 'A';
						int y1 = Integer.parseInt(siirto.charAt(1)+"")-1;
						int y2 = Integer.parseInt(siirto.charAt(3)+"")-1;
						siirtoOnnistuu = teeSiirto(x1, y1, x2, y2, shakkilauta);
					} catch (Exception e) {
						System.out.println("Epäkelpo syöte, yritä uudelleen!");
					}
					if(siirtoOnnistuu) {
						shakkilauta.korota();
						shakkilauta.vaihdaPelivuoro();
					}
			}
		}
	}

	/**
	 * @author Jaakko Kittilä
	 * @return String
	 * Kysytään käyttäjältä, mitä pelissä tehdään seuraavaksi
	 */


	/**
	 * Metodi erikoissiirtoa, tornitusta varten
	 * @param pituus
	 * @return boolean, joka kertoo onnistuiko tornitus vai ei
	 */
	public static boolean teeTornitus(boolean pituus, Shakkilauta shakkilauta) {
		
		int paaty = 7;
		if(shakkilauta.annaPelivuoro()) 
			paaty = 0;
		
		boolean totuus = false;
		if (pituus) {
			if (shakkilauta.annaRuutu(0, paaty) instanceof Torni && shakkilauta.annaRuutu(4, paaty) instanceof Kuningas &&
					((Torni) shakkilauta.annaRuutu(0, paaty)).onkoMuitaValissa(shakkilauta, 0, paaty, 4, paaty) == false) {
				shakkilauta.asetaRuutu(shakkilauta.annaRuutu(0, paaty), 3, paaty);
				shakkilauta.asetaRuutu(shakkilauta.annaRuutu(4, paaty), 2, paaty);
				shakkilauta.asetaRuutu(null, 0, paaty);
				shakkilauta.asetaRuutu(null, 4, paaty);
				totuus = true;
			} else {
				System.out.println("Tornitus ei onnistunut.");
			}
		} else {
			if (shakkilauta.annaRuutu(7, paaty) instanceof Torni && shakkilauta.annaRuutu(4, paaty) instanceof Kuningas &&
					((Torni) shakkilauta.annaRuutu(7, paaty)).onkoMuitaValissa(shakkilauta, 7, paaty, 4, paaty) == false) {
				shakkilauta.asetaRuutu(shakkilauta.annaRuutu(7, paaty), 5, paaty);
				shakkilauta.asetaRuutu(shakkilauta.annaRuutu(4, paaty), 6, paaty);
				shakkilauta.asetaRuutu(null, 7 ,paaty);
				shakkilauta.asetaRuutu(null, 4, paaty);
				totuus = true;
			} else {
				System.out.println("Tornitus ei onnistunut.");
			}
		}
		return  totuus;
	}

	/**
	 *
	 * @param x1 Alkuruudun koordinaatit
	 * @param y1
	 * @param x2 Loppuruudun koordinaatit
	 * @param y2
	 * @param shakkilauta lauta, jossa siiretään nappuloita
	 * @return palauttaa totuusarvon riippuen tehtiinki siirto vai ei
	 */
	public static boolean teeSiirto(int x1, int y1, int x2, int y2, Shakkilauta shakkilauta){
		
		if (x1 > 7 || x1 < 0 || y1 > 7 || y1 < 0 || x2 > 7 || x2 < 0 || y2 > 7 || y2 < 0) {
			System.out.println("Ainakin toinen ruuduista laudan ulkopuolella!");
			return false;
		}
		
		Nappula nappula = shakkilauta.annaRuutu(x1, y1);
		Nappula nappula1 = shakkilauta.annaRuutu(x2, y2);
		boolean totuus = false;
		String viesti = "";
		
		if (nappula != null) {
			if (nappula.annaVari() == shakkilauta.annaPelivuoro()){
				if (nappula1 == null || nappula1.annaVari() != nappula.annaVari()) {
					if (nappula.liikkeenTarkistus(shakkilauta, x1, y1, x2, y2)){
						shakkilauta.asetaRuutu(shakkilauta.annaRuutu(x1, y1), x2, y2);
						shakkilauta.asetaRuutu(null, x1, y1);
						totuus = true;
						viesti = "Siirto onnistui!";
					} else {
						viesti = "Ei ole sallittu siirto!";
					}
				} else {
					viesti = "Kohderuutuun ei voi siirtää!"; 
				}
			} else {
				viesti = "Yrität siirtää väärän pelaajan nappulaa!";
			}
		} else {
			viesti = "Ruudussa ei ole nappulaa";
		}
		System.out.println(viesti);
		return totuus;

	}

}


