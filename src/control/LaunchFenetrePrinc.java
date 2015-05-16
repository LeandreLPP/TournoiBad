package control;

import java.io.IOException;

import view.*;
import datas.*;

public class LaunchFenetrePrinc {
	private FenetrePrinc laFen;

	public static void main(String[] args) {
		new LaunchFenetrePrinc();
	}
	
	private LaunchFenetrePrinc(){
		this.initListeJoueurTest();
		this.laFen = new FenetrePrinc(this);
	}
	
	private void initListeJoueurTest(){
		Joueur j1 = new Joueur("123", "Jean", "Pierre");
		Joueur j2 = new Joueur("456", "Jacques", "Goldman");
		Joueur j3 = new Joueur("789", "Vulu", "Zulu");
		ListeJoueurs l1 = new ListeJoueurs();
		l1.ajouter("Test1", j1);
		l1.ajouter("Test2", j2);
		l1.ajouter("Test3", j3);
		try {
			l1.sauver();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
