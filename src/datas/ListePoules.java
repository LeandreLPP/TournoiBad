package datas;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;


public class ListePoules implements Serializable{
	private static final long serialVersionUID=0;
	private java.util.Hashtable<java.lang.Character,Poule> tabPoules;
	
	public ListePoules(){
		this.tabPoules = new Hashtable<Character, Poule>();
	}
	
	public void ajouter(char cle, Poule p) throws java.lang.Exception{
		if(!(this.existe(cle))){
			this.tabPoules.put(cle, p);
		} else {
			throw new Exception("La cle existe deja dans la table.");
		}
	}
	
	public Poule consulter(char cle){
		Poule res = null;
		if(this.existe(cle)){
			res=this.tabPoules.get(cle);
		}
		return res;
	}
	
	public boolean existe(char cle){
		return this.tabPoules.containsKey(cle);
	}
	
	public void sauver() throws IOException{
		FileOutputStream file = new FileOutputStream("poules.out");
		ObjectOutputStream truc = new ObjectOutputStream(file);
		truc.writeObject(this);
		file.close();
	}
	
	public static ListePoules charger() throws IOException, ClassNotFoundException{
		FileInputStream file = new FileInputStream("poules.out");
		 ObjectInputStream truc = new ObjectInputStream(file);
		 ListePoules j = (ListePoules) truc.readObject();
		 truc.close();
		return j;
	}
}
