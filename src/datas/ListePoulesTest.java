package datas;

public class ListePoulesTest {

	public static void main(String[] args) throws Exception {
		ListeJoueurs listejou1 = new ListeJoueurs();
		Joueur jou1 = new Joueur("123456","FRETAY","Juliette");
		Joueur jou2 = new Joueur("987654","BERNHEIM","Ninon");
		Joueur jou3 = new Joueur("621846","OUTHIER","Arthur");
		Joueur jou4 = new Joueur("498532","LEPOLLES","Leandre");
		listejou1.ajouter("1", jou1);
		listejou1.ajouter("2", jou2);
		listejou1.ajouter("3", jou3);
		listejou1.ajouter("4", jou4);
		Paire paire1 = new Paire(jou1,jou2);
		Paire paire2 = new Paire(jou3,jou4);
		paire1.setNumPaire(1);
		paire2.setNumPaire(2);
		ListePaires listepaire1 = new ListePaires();
		listepaire1.ajouter(paire1);
		listepaire1.ajouter(2,paire2);
		Poule poule1 = new Poule('A',2);
		poule1.ajouterPaire(paire1);
		poule1.ajouterPaire(paire2);
		ListePoules listepoule1 = new ListePoules();
		listepoule1.ajouter('A', poule1);
		System.out.println(listepoule1.consulter('A'));
		listepoule1.sauver();
		System.out.println(listepoule1.charger());
	}

}
