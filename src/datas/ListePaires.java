package datas;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Set;

public class ListePaires implements Serializable{
	private static int comptPaires;
	private static final long serialVersionUID = 0;
	private Hashtable<Integer,Paire> tabPaires;
	
	public ListePaires(){
		comptPaires = 0;
		this.tabPaires = new Hashtable<Integer,Paire>();
	}

	public void ajouter(Paire p) throws Exception {
		comptPaires++;
		if(!(this.existe(comptPaires))){
			this.tabPaires.put(comptPaires, p);
		} else {
			throw new Exception("La cle existe deja dans la table.");
		}
	}

	public void ajouter(int cle,Paire p) throws Exception {
		if(!(this.existe(cle))){
			this.tabPaires.put(cle, p);
		} else {
			throw new Exception("La cle existe deja dans la table.");
		}
	}

	public boolean existe(int cle) {
		return this.tabPaires.containsKey(cle);
	}

	public Paire consulter(int cle){
		return this.tabPaires.get(cle);
	}

	public void sauver() throws IOException{
		FileOutputStream file = new FileOutputStream("paires.out");
		ObjectOutputStream truc = new ObjectOutputStream(file);
		truc.writeObject(this);
		file.close();
	}

	public static ListePaires charger() throws IOException, ClassNotFoundException{
		FileInputStream file = new FileInputStream("paires.out");
		ObjectInputStream truc = new ObjectInputStream(file);
		ListePaires j = (ListePaires) truc.readObject();
		truc.close();
		return j;
	}

	public int getNbPaires(){
		return this.tabPaires.size();
	}

	public void toutSupprimer(){
		Set<Integer> liste = this.tabPaires.keySet();
		for(Integer e : liste){
			this.tabPaires.remove(e);
		}
	}
}
