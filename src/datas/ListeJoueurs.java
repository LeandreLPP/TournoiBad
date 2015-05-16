package datas;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class ListeJoueurs implements Serializable {

	private static final long serialVersionUID = 0;
	private Hashtable<String,Joueur> tabJoueurs;

	public ListeJoueurs(){
		this.tabJoueurs = new Hashtable<String,Joueur>();
	}

	public void ajouter(String cle, Joueur j){
		if(!(this.existe(cle))){
			this.tabJoueurs.put(cle, j);
		}
	}

	public boolean existe(String cle) {
		return this.tabJoueurs.containsKey(cle);
	}

	public Joueur consulter(String cle){
		return this.tabJoueurs.get(cle);
	}
	
	public void sauver()throws IOException {
		FileOutputStream file = new FileOutputStream("joueurs.out");
		ObjectOutputStream truc = new ObjectOutputStream(file);
		truc.writeObject(this);
		truc.close();
		file.close();
	}


	public static ListeJoueurs charger() throws IOException, ClassNotFoundException{
		FileInputStream file = new FileInputStream("joueurs.out");
		ObjectInputStream truc = new ObjectInputStream(file);
		ListeJoueurs j = (ListeJoueurs) truc.readObject();
		truc.close();
		file.close();
		return j;
	}

	public Enumeration<Joueur> tousJoueurs(){
		return this.tabJoueurs.elements();
	}

	public int nbrJoueurs(){
		System.out.println("Le nombre de joueur est :");
		return this.tabJoueurs.size();
	}

	public String toString(){
		String rep = "";
		Set<String> cle = this.tabJoueurs.keySet();
		for(String e : cle){
			rep+="Joueur "+e+"\n"+this.tabJoueurs.get(e).toString();
		}
		return rep;
	}
}
