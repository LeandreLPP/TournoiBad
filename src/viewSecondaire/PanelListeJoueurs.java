package viewSecondaire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.*;
import datas.*;

public class PanelListeJoueurs extends JPanel{
	private static final long serialVersionUID = 0;
	JTable tableJoueurs;

	PanelListeJoueurs(){
		super();
		this.chargerJoueurs();
		this.add(tableJoueurs);
	}

	private void chargerJoueurs(){
		ListeJoueurs listeJoueurs;
		try {
			listeJoueurs = ListeJoueurs.charger();
			Enumeration<Joueur> enumJoueurs;
			ArrayList<String[]> gitan = new ArrayList<String[]>();
			Joueur joueurCourant;
			String[][] mainTab;
			String[] entetes = {"License", "Nom", "Prenom"};
			int i;
			enumJoueurs= listeJoueurs.tousJoueurs();
			while(enumJoueurs.hasMoreElements()){
				joueurCourant = enumJoueurs.nextElement();
				String[] tabCourant = {joueurCourant.getNumLic(), joueurCourant.getNomJ(), joueurCourant.getPrenomJ()};
				gitan.add(tabCourant);
			}
			mainTab = new String[gitan.size()][3];
			i = 0;
			for(String[] j : gitan){
				mainTab[i] = j;
				i++;
			}
			this.tableJoueurs = new JTable(mainTab, entetes);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

}
