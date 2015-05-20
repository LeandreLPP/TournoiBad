package control;

import java.awt.Color;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import utilitaires.*;

import datas.Joueur;
import datas.ListeJoueurs;
import view.*;

public class ReactionsClicBouton implements ActionListener{

	private FenetrePrinc laFen;

	public ReactionsClicBouton(FenetrePrinc laFen){
		this.laFen=laFen;
	}

	public void actionPerformed(ActionEvent e) {
		if ( this.laFen.getButListeJoueurs() == e.getSource() ) { 
			this.afficherJoueurs();
		}
		else if(this.laFen.getButFormationPaires() == e.getSource()){
			this.afficherpaires();
		}
		else if(this.laFen.getBoutonOKPaires() == e.getSource()){
			this.OKPaires();
		}
		else if(this.laFen.getButCreationPoules() == e.getSource()){
			this.afficherpoules();
		}
		else if(this.laFen.getButGestionMatchs() == e.getSource()){
			this.affichermatchs();
		}
		else if(this.laFen.getBoutonOKPoules() == e.getSource()){
			this.OKPoules();
		}
		else if(this.laFen.getItemAide() == e.getSource()){
			this.Aide();
		}
		else if(this.laFen.getItemExit() == e.getSource()){
			this.laFen.dispose();
		}
		else if(this.laFen.getItemChargerPaires() == e.getSource()){
		}
		else if(this.laFen.getItemSauverPaires() == e.getSource()){
		}
	}

	private void afficherpoules() {
		this.laFen.afficherpoules();
	}

	private void afficherpaires() {
		String[] entetes = {"License", "Nom", "Prenom"};
		MyTableModel model = new MyTableModel(this.chargerListeJoueurs(),entetes);
		this.laFen.afficherpaires(model);
	}

	private void afficherJoueurs(){//Toutes les informations recuperees, on initialise l'objet de type MyTableModel et on le passe en parametre a la vue
		String[] entetes = {"License", "Nom", "Prenom"};
		MyTableModel model = new MyTableModel(this.chargerListeJoueurs(),entetes);
		this.laFen.afficherJoueurs(model); 
	}

	private void OKPaires() {
		String[][] mainTab = new String[2][];
		String[] entetes = {"Numero","Paire"};
		MyTableModel model = new MyTableModel(mainTab,entetes);
		this.laFen.OKPaires(model);
	}

	private void OKPoules() {
		String[][] mainTab = new String[2][];
		String[] entetes = {"Numero","Poule"};
		MyTableModel model = new MyTableModel(mainTab,entetes);
		this.laFen.OKPoules(model);
	}

	private void affichermatchs(){
		String[][] mainTab= new String[12][];
		String[] entetes = {"Match", "Paire A", "Paire B","Score A","Score B","Vainqueur"};
		MyTableModel model = new MyTableModel(mainTab,entetes);
		this.laFen.affichermatchs(model); 
	}
	
	private void Aide(){
		JFrame help = new JFrame("A propos");
		help.setSize( 700, 200 );
		JLabel aPropos = new JLabel("<html>Bienvenue dans le Tournoi de Bad !<br><br> Commencer par entrer une liste de joueurs.</html>");
		JPanel aide = new JPanel();
		aPropos.setForeground(Color.PINK);
		aide.setBackground(Color.white);
		aide.add(aPropos);
		help.add(aide);
		help.setVisible(true);
	}

	private String[][] chargerListeJoueurs(){

		ListeJoueurs listeJoueurs = new ListeJoueurs();
		Joueur joueurCourant;
		String[][] mainTab;
		ArrayList<String[]> listeEntrees= new ArrayList<String[]>();
		Enumeration<Joueur> enumJoueurs;

		//On charge la liste des Joueurs enregistree sur le disque
		try {
			listeJoueurs = ListeJoueurs.charger();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//On recupere les informations dont on a besoin dans une liste de chaine de caractere
		enumJoueurs= listeJoueurs.tousJoueurs();
		int i;
		while(enumJoueurs.hasMoreElements()){
			joueurCourant = enumJoueurs.nextElement();
			String[] tabCourant = {joueurCourant.getNumLic(), joueurCourant.getNomJ(), joueurCourant.getPrenomJ()};
			listeEntrees.add(tabCourant);
		}

		//On transfere ensuite le contenu de la liste dans un tableau afin de pouvoir l'utiliser pour creer un objet de type MyTableModel
		mainTab = new String[listeEntrees.size()][];
		i = 0;
		for(String[] j : listeEntrees){
			mainTab[i] = j;
			i++;
		}
		return mainTab;
	}
}
