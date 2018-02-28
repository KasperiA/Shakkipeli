

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

		while (true) {

			shakkilauta.tulosta();
			
			System.out.println("Anna siirrot muodossa RUUTU1>RUUTU2 esim. A1>B2, muista isot kirjaimet!");
			System.out.println("Lyhyt tornitus tapahtuu syötteellä O-O ja pitkä syötteellä O-O-O.");
			System.out.println("Poistuminen tapahtuu syötteellä exit.");
			
			String valittu = lukija.nextLine();


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
				case "exit":
					shakkilauta.tallenna();
					System.exit(0);
				default:
					String[] siirto = valittu.split(">");
					String ruutu1 = siirto[0].charAt(0) - 'A' +  String.valueOf(siirto[0].charAt(1));
					String ruutu2 = siirto[1].charAt(0) - 'A' + String.valueOf(siirto[1].charAt(1));
					boolean totuus = teeSiirto(Integer.parseInt(ruutu1.substring(0,1)),
							Integer.parseInt(ruutu1.substring(1,2)),
							Integer.parseInt(ruutu2.substring(0,1)),
							Integer.parseInt(ruutu2.substring(1,2)), shakkilauta);
					if (totuus){
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
		boolean totuus = false;
		if (pituus) {
			if (shakkilauta.annaRuutu(0, 0) instanceof Torni && shakkilauta.annaRuutu(4, 0) instanceof Kuningas &&
					((Torni) shakkilauta.annaRuutu(0, 0)).onkoMuitaValissa(shakkilauta, 0, 0, 4, 0) == false) {
				shakkilauta.asetaRuutu(shakkilauta.annaRuutu(0, 0), 3, 0);
				shakkilauta.asetaRuutu(shakkilauta.annaRuutu(4, 0), 2, 0);
				shakkilauta.asetaRuutu(null, 0,0);
				shakkilauta.asetaRuutu(null, 4, 0);
				totuus = true;
			} else {
				System.out.println("Tornitus ei onnistunut.");
			}
		} else {
			if (shakkilauta.annaRuutu(7, 0) instanceof Torni && shakkilauta.annaRuutu(4, 0) instanceof Kuningas &&
					((Torni) shakkilauta.annaRuutu(7, 0)).onkoMuitaValissa(shakkilauta, 7, 0, 4, 0) == false) {
				shakkilauta.asetaRuutu(shakkilauta.annaRuutu(7, 0), 5, 0);
				shakkilauta.asetaRuutu(shakkilauta.annaRuutu(4, 0), 6, 0);
				shakkilauta.asetaRuutu(null, 7 ,0);
				shakkilauta.asetaRuutu(null, 4, 0);
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
		Nappula nappula = shakkilauta.annaRuutu(x1, y1);
		Nappula nappula1 = shakkilauta.annaRuutu(x2, y2);
		boolean totuus = false ;
		if (nappula.annaVari() == shakkilauta.annaPelivuoro() && nappula1.annaVari() != nappula.annaVari() && !(nappula1 instanceof Kuningas) ){
			if(nappula.liikkeenTarkistus(shakkilauta, x1, y1, x2, y2)){
				shakkilauta.asetaRuutu(shakkilauta.annaRuutu(x1, y1), x2, y2);
				shakkilauta.asetaRuutu(null, x1, y1);
				totuus = true;
			}else{
				System.out.println("Ei ole sallittu siirto.");
			}
		}else{
			System.out.println("Yrität siirtää väärän pelaajan nappulaa.");
		}
		return totuus;

	}

}


