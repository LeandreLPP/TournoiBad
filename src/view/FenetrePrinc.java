package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import control.*;
import datas.*;

public class FenetrePrinc extends JFrame{

	//================================================LES ATTRIBUTS==================================================================================

	//L'attribut de serie
	private static final long serialVersionUID = 0;
	//-------------Les donnees---------------------------------------
	private static ListeJoueurs listeJoueurs;
	//private static ListePaires listePaires;

	//-------------La vue--------------------------------------------

	//Le menu:
	private JMenuBar mainMenu;
	private JMenu menuFichier;
	private JMenu menuAide;
	private JMenuItem itemChargerPaires;
	private JMenuItem itemSauverPaires;
	private JMenuItem itemExit;
	private JMenuItem itemAide;

	//Les quatres boutons:
	private JButton butListeJoueurs;
	private JButton butFormationPaires;
	private JButton butCreationPoules;
	private JButton butGestionMatchs;

	//Les panels de placements:

	private JPanel panelNord;
	private JPanel panelTransparentNORD;
	private JPanel panelTransparentSUD;
	private JPanel panelTransparentEST;
	private JPanel panelTransparentOUEST;

	//Image accueil
	private JLabel labelCentre;
	private ImageIcon imgTournoi;

	//Fenetre active
	private JComponent compCentral;
	
	
	
	//Pas tries encore
	JButton boutonOKPaires =new JButton("OK");
	JButton boutonOKPoules = new JButton("OK");

	//================================================LES METHODES===================================================================================
	/**
	 * Constructeur de la fenetre principale de l'application TournoiBad
	 * Appelle successivement les methodes de mise en place du decors d'accueil
	 * et la methode d'initialisation des reactions
	 */
	public FenetrePrinc(){
		super("Logiciel de gestion de tournois badminton");
		this.miseEnPlaceDecor();
		this.attacherReaction();
		this.setSize( 800, 500 ); // dimensionnement de la fenêtre 
		this.setVisible( true ); // rendre la fenêtre visible 
		this.setDefaultCloseOperation ( EXIT_ON_CLOSE ); // clic sur la croix

	}

	/**
	 * Mise en place du decors d'accueil
	 */
	private void miseEnPlaceDecor(){
		//Preparation de la gestion de placement de la fenetre principale
		this.getContentPane().setLayout(new BorderLayout());

		//Le Menu:
		itemChargerPaires = new JMenuItem("Charger liste paires"); 
		itemSauverPaires = new JMenuItem("Sauver liste paires"); 
		itemExit = new JMenuItem("Exit"); 
		itemAide = new JMenuItem("A propos"); 
		this.mainMenu = new JMenuBar();
		this.menuFichier = new JMenu("Fichier");
		this.menuAide = new JMenu("Aide");
		this.menuFichier.add(itemChargerPaires); 
		this.menuFichier.add(itemSauverPaires); 
		this.menuFichier.add(itemExit); 
		this.menuAide. add(itemAide); 
		setJMenuBar(mainMenu);
		this.mainMenu.add(menuFichier);
		this.mainMenu.add(menuAide);

		//Les 4 boutons (PanelNord):
		this.butListeJoueurs = new JButton("Liste des Joueurs");
		this.butFormationPaires = new JButton("Formation des paires");
		this.butCreationPoules = new JButton("Creation des poules");
		this.butGestionMatchs = new JButton("Gestion des matchs");
		this.panelNord = new JPanel();
		this.panelNord.setLayout(new GridLayout(1,4));
		this.panelNord.add(butListeJoueurs);
		this.panelNord.add(butFormationPaires);
		this.panelNord.add(butCreationPoules);
		this.panelNord.add(butGestionMatchs);

		//--------------------------Label Centre------------------------------
		this.imgTournoi = new ImageIcon("datas/badminton.jpg");
		this.labelCentre = new JLabel ( imgTournoi );
		this.compCentral = new JPanel();
		this.compCentral.setVisible(false);
		this.panelTransparentEST = new JPanel();
		this.panelTransparentOUEST = new JPanel();
		this.panelTransparentNORD = new JPanel();
		this.panelTransparentSUD = new JPanel();
		this.panelTransparentEST.setOpaque(false);
		this.panelTransparentEST.setPreferredSize(new Dimension(100,500));
		this.panelTransparentSUD.setOpaque(false);
		this.panelTransparentSUD.setPreferredSize(new Dimension(800,100));
		this.panelTransparentOUEST.setOpaque(false);
		this.panelTransparentOUEST.setPreferredSize(new Dimension(100,500));
		this.panelTransparentNORD.setOpaque(false);
		this.panelTransparentNORD.setPreferredSize(new Dimension(800,70));
		this.labelCentre.setLayout(new BorderLayout());
		this.labelCentre.add(compCentral,BorderLayout.CENTER);
		this.labelCentre.add(panelTransparentNORD,BorderLayout.NORTH);
		this.labelCentre.add(panelTransparentEST,BorderLayout.EAST);
		this.labelCentre.add(panelTransparentOUEST,BorderLayout.WEST);
		this.labelCentre.add(panelTransparentSUD,BorderLayout.SOUTH);

		//Placement du label centre et du panel nord:
		this.getContentPane().add(labelCentre, BorderLayout.CENTER);
		this.getContentPane().add(panelNord, BorderLayout.NORTH);
	}
	
	/**
	 * Affecte les réactions aux actions de l'utilisateur
	 */
	private void attacherReaction(){
		this.addComponentListener ( new WinReaction());
		ReactionsClicBouton reactBout = new ReactionsClicBouton(this); 
		this.butListeJoueurs.addActionListener(reactBout); 
		this.butFormationPaires.addActionListener(reactBout);
		this.boutonOKPaires.addActionListener(reactBout);
		this.butCreationPoules.addActionListener(reactBout);
		this.butGestionMatchs.addActionListener(reactBout);
		this.boutonOKPoules.addActionListener(reactBout);

	}

	/**
	 * Action realisee quand on clique sur le "bouton Liste des joueurs"
	 */
	public void afficherJoueurs(DefaultTableModel model){ 
		this.labelCentre.remove(this.compCentral);
		//On creer un panel pour cette "page"
		JTable tableJoueurs = new JTable(model);
		JPanel panJou = new JPanel();

		//Gestion de placement de la fenetre Liste des joueurs:
		panJou.setLayout(new BorderLayout());
		panJou.add(tableJoueurs, BorderLayout.CENTER);
		panJou.add(tableJoueurs.getTableHeader(), BorderLayout.NORTH);
		panJou.setOpaque(false);

		//Ajout de la fenetre Liste des joueurs en tant que fenetre active
		this.compCentral = panJou;
		this.labelCentre.add(compCentral,BorderLayout.CENTER);
		this.pack();
	}

	public JButton getBoutJoueurs(){
		return this.butListeJoueurs;
	}
	
	public void afficherpaires(){
		this.labelCentre.remove(this.compCentral);
		//On creer un panel pour cette "page"
		JPanel panPaire = new JPanel();
		panPaire.setLayout(new GridLayout(1,3));

		//On charge un tableau de joueurs
		JTable tableJoueurs;
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
		tableJoueurs = new JTable(model);

		JPanel les3boutons = new JPanel();
		JButton bouton1 = new JButton(">");
		JButton bouton2 = new JButton("<");
		les3boutons.setLayout(new GridLayout(3,1));
		les3boutons.add(bouton1);
		les3boutons.add(bouton2);
		les3boutons.add(boutonOKPaires);
		
		
		//========================================TABLEAU PAIRES=====================================================================================
		String[][] mainTab2 = new String[1][];
		String[] entetes2 = {"Paires formees"};
		DefaultTableModel model2 = new DefaultTableModel(mainTab2,entetes2);
		JTable tablePaires = new JTable(model2);
		
		
		
		
		JPanel paneltableau = new JPanel();
		paneltableau.setLayout(new BorderLayout());
		paneltableau.add(tableJoueurs, BorderLayout.CENTER);
		paneltableau.add(tableJoueurs.getTableHeader(), BorderLayout.NORTH);
		
		JPanel paneltableau2 = new JPanel();
		paneltableau2.setLayout(new BorderLayout());
		paneltableau2.add(tablePaires, BorderLayout.CENTER);
		paneltableau2.add(tablePaires.getTableHeader(), BorderLayout.NORTH);
		
		//Gestion de placement de la fenetre Paires:
		panPaire.add(paneltableau);
		panPaire.add(les3boutons);
		panPaire.add(paneltableau2);
		panPaire.setOpaque(false);

		//Ajout de la fenetre Paires en tant que fenetre active
		this.compCentral = panPaire;
		this.labelCentre.remove ( this.compCentral ); 
		this.labelCentre.add(compCentral,BorderLayout.CENTER);
		this.pack();
	}

	public JButton getBoutPaires(){
		return this.butFormationPaires;
	}
	
	public void OKPaires(DefaultTableModel model){ 
		this.labelCentre.remove(this.compCentral);
		//
		JPanel panJou = new JPanel();

		//
		JTable tableJoueurs = new JTable(model);

		//
		panJou.setLayout(new BorderLayout());
		panJou.add(tableJoueurs, BorderLayout.CENTER);
		panJou.add(tableJoueurs.getTableHeader(), BorderLayout.NORTH);
		panJou.setOpaque(false);

		//
		this.compCentral = panJou;
		this.labelCentre.add(compCentral,BorderLayout.CENTER);
		this.pack();
	}
	
	public JButton getBoutOKPaires(){
		return this.boutonOKPaires;
	}
	
	public void afficherpoules(){
		this.labelCentre.remove(this.compCentral);
		//On creer un panel pour cette "page"
		JPanel panPaire = new JPanel();
		panPaire.setLayout(new GridLayout(1,3));

		//========================================TABLEAU PAIRES=====================================================================================
		String[][] mainTab2 = new String[12][];
		String[] entetes2 = {"Paires formees"};
		DefaultTableModel model2 = new DefaultTableModel(mainTab2,entetes2);
		JTable tablePaires = new JTable(model2);

		//========================================BOUTONS PRINCIPAUX=====================================================================================
		JPanel les3boutons = new JPanel();
		JButton bouton1 = new JButton(">");
		JButton bouton2 = new JButton("<");
		les3boutons.setLayout(new GridLayout(3,1));
		les3boutons.add(bouton1);
		les3boutons.add(bouton2);
		les3boutons.add(boutonOKPoules);
		
		
		//========================================TABLEAU POULES=====================================================================================
		String[][] mainTab3 = new String[12][];
		String[] entetes3 = {"Poules formees"};
		DefaultTableModel model3 = new DefaultTableModel(mainTab3,entetes3);
		JTable tablePoules = new JTable(model3);
		
		
		JPanel paneltableau = new JPanel();
		paneltableau.setLayout(new BorderLayout());
		paneltableau.add(tablePaires, BorderLayout.CENTER);
		paneltableau.add(tablePaires.getTableHeader(), BorderLayout.NORTH);
		
		JPanel paneltableau2 = new JPanel();
		paneltableau2.setLayout(new BorderLayout());
		paneltableau2.add(tablePoules, BorderLayout.CENTER);
		paneltableau2.add(tablePoules.getTableHeader(), BorderLayout.NORTH);
		
		//Gestion de placement de la fenetre Paires:
		panPaire.add(paneltableau);
		panPaire.add(les3boutons);
		panPaire.add(paneltableau2);
		panPaire.setOpaque(false);

		//Ajout de la fenetre Paires en tant que fenetre active
		this.compCentral = panPaire;
		this.labelCentre.remove ( this.compCentral ); 
		this.labelCentre.add(compCentral,BorderLayout.CENTER);
		this.pack();
	}

	public JButton getBoutPoules(){
		return this.butCreationPoules;
	}
	
	public void OKPoules(DefaultTableModel model){ 
		this.labelCentre.remove(this.compCentral);
		//
		JPanel panJou = new JPanel();

		//
		JTable tableJoueurs = new JTable(model);

		//:
		panJou.setLayout(new BorderLayout());
		panJou.add(tableJoueurs, BorderLayout.CENTER);
		panJou.add(tableJoueurs.getTableHeader(), BorderLayout.NORTH);
		panJou.setOpaque(false);

		//
		this.compCentral = panJou;
		this.labelCentre.add(compCentral,BorderLayout.CENTER);
		this.pack();

	}
	public JButton getBoutOKPoules(){
		return this.boutonOKPoules;
	}
	
	public void affichermatchs(DefaultTableModel model){
		this.labelCentre.remove(this.compCentral);
		//On creer un panel pour cette "page"
		JPanel panJou = new JPanel();

		//On charge un tableau de joueurs
		JTable tableMatchs = new JTable(model);

		//Gestion de placement de la fenetre Liste des joueurs:
		panJou.setLayout(new BorderLayout());
		panJou.add(tableMatchs, BorderLayout.CENTER);
		panJou.add(tableMatchs.getTableHeader(), BorderLayout.NORTH);
		panJou.setOpaque(false);

		//Ajout de la fenetre Liste des joueurs en tant que fenetre active
		this.compCentral = panJou;
		this.labelCentre.add(compCentral,BorderLayout.CENTER);
		this.pack();

	}
	
	public JButton getBoutMatchs(){
		return this.butGestionMatchs;
	}
	
	/*private void initBdd(){

	}*/

	private class WinReaction extends ComponentAdapter { 

		public void componentResized ( ComponentEvent e ) { 
			// labelCentre = attribut déjà déclaré (voir mise en place décor) 
			// imgTournoi = attribut déjà déclaré (voir mise en place décor) 
			// getScaledImage(…) : méthode à écrire (voir ci-après) 
			int w = labelCentre.getSize().width; 
			int h = labelCentre.getSize().height; 
			ImageIcon tmp = new ImageIcon(getScaledImage( imgTournoi.getImage(), w, h )); 
			labelCentre.setIcon(tmp); 
		}

		private Image getScaledImage ( Image srcImg, int w, int h ){ 
			BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB); 
			Graphics2D g2 = resizedImg.createGraphics(); 
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
					RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
			g2.drawImage(srcImg, 0, 0, w, h, null); 
			g2.dispose(); 
			return resizedImg; 
		} 
	}
}