package control;

import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import datas.Joueur;
import datas.ListeJoueurs;
import view.*;

public class ReactionsClicBouton implements ActionListener{

	private FenetrePrinc laFen;

	public ReactionsClicBouton(FenetrePrinc laFen){
		this.laFen=laFen;
	}

	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		String action = source.getActionCommand();
		switch (action){
			case "ListeJoueurs" : this.afficherJoueurs();
			break;

			case "FormationPaires" : this.afficherpaires();
			break;

			case "OKPaires" : this.OKPaires();
			break;

			case "CreationPoules" : this.afficherpoules();
			break;

			case "GestionMatchs" : this.affichermatchs();
			break;

			case "OKPoules" : this.OKPoules();
			break;
		}
	}

	private void afficherpoules() {
		this.laFen.afficherpoules();
	}

	private void afficherpaires() {
		ListeJoueurs listeJoueurs = new ListeJoueurs();
		try {
			listeJoueurs = ListeJoueurs.charger();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		mainTab = new String[gitan.size()][];
		i = 0;
		for(String[] j : gitan){
			mainTab[i] = j;
			i++;
		}
		DefaultTableModel model = new DefaultTableModel(mainTab,entetes);
		this.laFen.afficherpaires(model);
	}

	private void afficherJoueurs(){
		ListeJoueurs listeJoueurs = new ListeJoueurs();
		Joueur joueurCourant;
		String[][] mainTab;
		ArrayList<String[]> listeEntrees= new ArrayList<String[]>();
		Enumeration<Joueur> enumJoueurs;

		//On charge la liste des Joueurs enregistrée sur le disque
		try {
			listeJoueurs = ListeJoueurs.charger();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//On récupère les informations dont on a besoin dans une liste de chaine de caractère
		enumJoueurs= listeJoueurs.tousJoueurs();
		int i;
		while(enumJoueurs.hasMoreElements()){
			joueurCourant = enumJoueurs.nextElement();
			String[] tabCourant = {joueurCourant.getNumLic(), joueurCourant.getNomJ(), joueurCourant.getPrenomJ()};
			listeEntrees.add(tabCourant);
		}

		//On transfere ensuite le contenu de la liste dans un tableau afin de pouvoir l'utiliser pour creer un objet de type DefaultTableModel
		mainTab = new String[listeEntrees.size()][];
		i = 0;
		for(String[] j : listeEntrees){
			mainTab[i] = j;
			i++;
		}

		//Toutes les informations recuperees, on initialise l'objet de type DefaultTableModel et on le passe en parametre a la vue
		String[] entetes = {"License", "Nom", "Prenom"};
		DefaultTableModel model = new DefaultTableModel(mainTab,entetes);
		this.laFen.afficherJoueurs(model); 
	}

	private void OKPaires() {
		String[][] mainTab = new String[2][];
		String[] entetes = {"Numero","Paire"};
		DefaultTableModel model = new DefaultTableModel(mainTab,entetes);
		this.laFen.OKPaires(model);
	}

	private void OKPoules() {
		String[][] mainTab = new String[2][];
		String[] entetes = {"Numero","Poule"};
		DefaultTableModel model = new DefaultTableModel(mainTab,entetes);
		this.laFen.OKPoules(model);
	}

	private void affichermatchs(){
		String[][] mainTab= new String[12][];
		String[] entetes = {"Match", "Paire A", "Paire B","Score A","Score B","Vainqueur"};
		DefaultTableModel model = new DefaultTableModel(mainTab,entetes);
		this.laFen.affichermatchs(model); 
	}
}
