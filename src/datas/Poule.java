package datas;
import java.io.Serializable;


public class Poule implements Serializable{
	/**
	 * 
	 */
	private char lettre;
	private ListePaires listePaires;
	private static final int NB_MINIMUM_PAIRES=1;
	private int nbPaires;
	private static final long serialVersionUID=0;
	
	Poule(char val, int nbP) throws java.lang.IllegalArgumentException{
		this.lettre = val;
		if (nbP > NB_MINIMUM_PAIRES){
			this.nbPaires = nbP;
		} else {
			throw new RuntimeException ("Il n'y a pas assez de paires pour créer la poule.");
		}
		this.listePaires = new ListePaires();
	}
	
	public boolean ajouterPaire(Paire p){
		boolean res = false;
		try{
			this.listePaires.ajouter(p);
			res = true;
			nbPaires++;
		}
		catch(Exception e){
			System.out.println("Erreur : ["+e.getMessage()+"]");
		}
		return res;
	}
	
	public int getNbPaires(){
		return this.nbPaires;
	}
	
	public Paire consulterPaire(int cle){
		return this.listePaires.consulter(cle);
	}
	
	public char getClePoule(){
		System.out.println("La cle de la poule est :");
		return this.lettre;
	}
}
