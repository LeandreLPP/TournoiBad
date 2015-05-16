package viewSecondaire;

import java.awt.*;
import javax.swing.*;
import datas.*;

public class FenetrePrinc extends JFrame{
	
	private static ListeJoueurs listeJoueurs;
	private static ListePaires listePaires;
	private PanelListeJoueurs panelListeJoueurs;
	private JPanel panelFormationPaires;
	private JPanel panelCreationPoules;
	private JPanel panelGestionMatchs;
	private JMenuBar mainMenu;
	private JMenu menuFichier;
	private JMenu menuAide;
	private JTabbedPane onglets;
	
	public FenetrePrinc (){
		super("Logiciel de gestion de tournois badminton");
		//-------------
		this.onglets = new JTabbedPane(SwingConstants.TOP);
		this.panelListeJoueurs = new PanelListeJoueurs();
		this.panelFormationPaires = new JPanel();
		this.panelCreationPoules = new JPanel();
		this.panelGestionMatchs = new JPanel();
		
		this.mainMenu = new JMenuBar();
		this.menuFichier = new JMenu("Fichier");
		this.menuAide = new JMenu("Aide");
		//-------------
		this.miseEnPlaceDecor();
		this.setSize( 800, 500 ); // dimensionnement de la fenêtre 
		this.setVisible( true ); // rendre la fenêtre visible 
		this.setDefaultCloseOperation ( EXIT_ON_CLOSE ); // clic sur la croix

	}
	
	private void miseEnPlaceDecor(){this.menuFichier.add("Nouveau");
		this.menuFichier.add("Charger");
		this.menuFichier.add("Sauvegarder");
		this.menuFichier.add("Sauvegarder sous...");
		this.menuFichier.add("Quitter");
		
		this.menuAide.add("Bonne chance");
		this.menuAide.add("Au revoir");
		
		this.mainMenu.add(menuFichier);
		this.mainMenu.add(menuAide);
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(mainMenu, BorderLayout.NORTH);
		
		this.onglets.addTab("Liste des joueurs", this.panelListeJoueurs);
		this.onglets.addTab("Formation des paires", this.panelFormationPaires);
		this.onglets.addTab("Creation des poules", this.panelCreationPoules);
		this.onglets.addTab("Gestion des matchs", this.panelGestionMatchs);
		this.onglets.setOpaque(true);
		this.getContentPane().add(onglets);

	}
	
	private void attacherReaction(){
		
	}
	
	private void initBdd(){
		
	}
}
