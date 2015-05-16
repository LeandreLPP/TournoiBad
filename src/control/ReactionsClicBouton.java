package control;

import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
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
		if ( this.laFen.getBoutJoueurs() == e.getSource() ) { 
			this.afficherJoueurs(); 
		}
		else if ( this.laFen.getBoutPaires() == e.getSource() ) { 
			this.afficherpaires(); 
		}
		else if ( this.laFen.getBoutOKPaires() == e.getSource() ) { 
			this.OKPaires(); 
		}
		else if ( this.laFen.getBoutPoules() == e.getSource() ) { 
			this.afficherpoules();
		}
		else if ( this.laFen.getBoutOKPoules() == e.getSource() ) { 
			this.OKPoules(); 
		}
		else if ( this.laFen.getBoutMatchs() == e.getSource() ) { 
			this.affichermatchs(); 
		}
	}

	private void afficherpoules() {
		this.laFen.afficherpoules();
	}

	private void afficherpaires() {
		this.laFen.afficherpaires();
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
