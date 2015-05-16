package datas;
import java.io.Serializable;

public class Joueur implements Serializable{
	private String nomJ;
	private String numLic;
	private String prenomJ;
	private static final long serialVersionUID = 0;
	
	public Joueur(String num, String nom, String prenom){
		this.numLic = num;
		this.nomJ = nom;
		this.prenomJ = prenom;
	}

	public String getNomJ() {
		return this.nomJ;
	}

	public String getNumLic() {
		return this.numLic;
	}

	public String getPrenomJ() {
		return this.prenomJ;
	}

	public String getKey() {
		return (this.nomJ+"_"+this.prenomJ);
	}
	
	public String toString() {
		return ("Nom : "+this.nomJ+"\n"+
				"Prenom : "+this.prenomJ+"\n"+
				"Numero de license : "+this.numLic+"\n");
	}
}
