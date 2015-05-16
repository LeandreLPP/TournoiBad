package datas;
import java.io.IOException;


public class ListeJoueursTest {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		ListeJoueurs listejou1 = new ListeJoueurs();
		Joueur jou1 = new Joueur("123456","FRETAY","Juliette");
		Joueur jou2 = new Joueur("987654","BERNHEIM","Ninon");
		listejou1.ajouter("1", jou1);
		listejou1.ajouter("2", jou2);
		System.out.println(listejou1.existe("1") + " Si c'est true c'est bon !");
		System.out.println(listejou1.existe("3") + " Si c'est false c'est bon !");
		System.out.println(listejou1.toString());
		System.out.println(listejou1.nbrJoueurs());
		//System.out.println(listejou1.tousJoueurs());
		listejou1.sauver();
		System.out.println(listejou1.charger());
	}
}
