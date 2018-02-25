import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

/**
 * Luokka mallintaa shakkipelin lautaa.
 * 
 * @author Teemu Mäkinen
 */

class Shakkilauta implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean pelivuoro; // false mustalla, true valkoisella
	private Nappula[][] lauta;

	public Shakkilauta() {
		pelivuoro = true;
		lauta = new Nappula[7][7];
	}

	/**
	 * Metodi palauttaa, kenen vuoro on käynnissä.
	 * 
	 * @return true, jos käynnissä valkoisen vuoro - false, jos käynnissä mustan
	 *         vuoro
	 */
	public boolean annaPelivuoro() {
		return pelivuoro;
	}

	/**
	 * Metodi asettaa pelivuoron päinvastaiseksi.
	 */
	public void vaihdaPelivuoro() {
		pelivuoro = !pelivuoro;
	}

	/**
	 * Metodi palauttaa shakkilaudan tietystä ruudusta löytyvän Nappulan.
	 * 
	 * @param x
	 *            Vaakarivi pelilaudalla (0-7)
	 * @param y
	 *            Pystyrivi pelilaudalla (0-7)
	 * @return Näistä koordinaateista löytyvän Nappula-olion, joka voi olla myös
	 *         null
	 */
	public Nappula annaRuutu(int x, int y) {
		return lauta[x][y];
	}

	/**
	 * Metodi asettaa shakkilaudan tiettyyn ruutuun Nappulan.
	 * 
	 * @param n
	 *            Nappula jota ollaan asettamassa (null, jos tyhjennetään)
	 * @param x
	 *            Vaakarivi pelilaudalla (0-7)
	 * @param y
	 *            Pystyrivi pelilaudalla (0-7)
	 */
	public void asetaRuutu(Nappula n, int x, int y) {
		lauta[x][y] = n;
	}

	/**
	 * Metodi asettaa nappulat pelin alkuasemiin ja antaa vuoron valkoiselle.
	 */
	public void alusta() {

		// valkoinen aloitttaa
		pelivuoro = true;

		// erikoisnappulat:
		// ensimmäinen kierros valkoisilla rivillä 0, seuraava kierros mustilla
		// rivillä 7
		boolean vari = true;
		for (int y = 0; y < 8; y = y + 7) {
			lauta[0][y] = new Torni(vari);
			lauta[1][y] = new Hevonen(vari);
			lauta[2][y] = new Lahetti(vari);
			lauta[3][y] = new Kuningatar(vari);
			lauta[4][y] = new Kuningas(vari);
			lauta[5][y] = new Lahetti(vari);
			lauta[6][y] = new Hevonen(vari);
			lauta[7][y] = new Torni(vari);
			vari = false;
		}

		// sotilaat:
		// ensimmäinen kierros mustilla rivillä 6, seuraava kierros valkoisilla
		// rivillä 1
		for (int y = 6; y > 0; y = y - 5) {
			for (int x = 0; x < 8; x++) {
				lauta[x][y] = new Sotilas(vari);
			}
			vari = true;
		}
	}

	/**
	 * Metodi kysyy pelaajalta tiedoston, johon pelilaudan tila tallennetaan ja
	 * suorittaa tallennuksen, jos mahdollista. Muutoin tulostaa
	 * virheilmoituksen.
	 */
	public void tallenna() {

		try (Scanner lukija = new Scanner(System.in);) {
			System.out.println("Minkä nimiseen tiedostoon haluat pelin tallennettavan?");
			FileOutputStream tiedosto = new FileOutputStream(lukija.nextLine() + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(tiedosto);
			out.writeObject(this);
			out.close();
			tiedosto.close();
			System.out.println("Tallennus onnistui.");
		} catch (Exception e) {
			System.out.println("Tallennus epäonnistui.");
		}
	}

	/**
	 * Metodi kysyy pelaajalta tiedoston, josta ladataan uusi pelilautaolio. Sen
	 * onnistuessa metodi palauttaa tämän tiedoston sisältämän
	 * Shakkilauta-olion. Mikäli tiedostoa ei ole tai muu virhe tapahtuu,
	 * tulostetaan virheilmoitus ja palautetaan null.
	 * 
	 * @return Latauksesta saatava Shakkilauta-olio tai null, jos lataus ei
	 *         onnistu
	 */
	public Shakkilauta lataa() {

		Shakkilauta tmp = null;

		try (Scanner lukija = new Scanner(System.in);) {
			System.out.println("Minkä nimisestä tiedostosta haluat pelin ladattavan?");
			FileInputStream tiedosto = new FileInputStream(lukija.nextLine() + ".ser");
			ObjectInputStream in = new ObjectInputStream(tiedosto);
			tmp = (Shakkilauta) in.readObject();
			in.close();
			tiedosto.close();
			System.out.println("Lataus onnistui.");
		} catch (FileNotFoundException f) {
			System.out.println("Tiedostoa ei löytynyt.");
		} catch (Exception e) {
			System.out.println("Lataus epäonnistui.");
		} finally {
			// palauttaa joko aiemmin tallennetun shakkilaudan tai null
			return tmp;
		}
	}

	/**
	 * Metodi tulostaa laudan sisällön rivi riviltä siistin ruudukon sisään,
	 * koordinaatit laudan ruuduille ja tiedon siitä, kenen pelivuoro on
	 * käynnissä.
	 */
	public void tulosta() {

		// koordinaattirivi alkuun
		System.out.println("  A B C D E F G H");

		for (int y = 0; y < 8; y++) {

			// ensin rivi pelkästään ruudukolle
			if (y == 0)
				System.out.println("╔═╤═╤═╤═╤═╤═╤═╤═╗");
			else
				System.out.println("╟─┼─┼─┼─┼─┼─┼─┼─╢");

			// sitten itse pelilaudan sisältö rivillä y pystykoordinaattien kera
			System.out.print(y + 1 + "║");
			for (int x = 0; x < 8; x++) {

				// jos nappulaa ei ole ruudussa tulostetaan vain väli
				if (lauta[x][y] != null)
					System.out.print(lauta[x][y].annaSymboli());
				else
					System.out.print(" ");

				// joka kerralla tulostetaan myös osa ruudukkoa (ja rivinvaihto
				// rivin lopussa)
				System.out.print((x != 7) ? "│" : "║\n");
			}
		}
		// lopuksi viimeinen osa ruudukkoa
		System.out.println("╚═╧═╧═╧═╧═╧═╧═╧═╝");
		System.out.println(pelivuoro ? "Valkoisella" : "Mustalla" + " on pelivuoro.");
	}
}
