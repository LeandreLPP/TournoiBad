package datas;
import java.io.Serializable;

public class Paire implements Serializable {
	private Joueur jou1;
	private Joueur jou2;
	private int numPaire;
	private static final long serialVersionUID = 0;
	
	public Paire(Joueur j1, Joueur j2){
		if ((j1 != null) && (j2 != null)){
			this.jou1 = j1;
			this.jou2 = j2;
		} else {
			throw new RuntimeException("Un des deux objets joueurs est null");
		}
	}

	public Joueur getJou1() {
		return jou1;
	}

	public Joueur getJou2() {
		return jou2;
	}

	public int getNumPaire() {
		return numPaire;
	}
	
	public void setNumPaire(int i){
		if (i >= 1){
			this.numPaire = i;
		} else {
			throw new RuntimeException("Le numero d'identification de la paire doit etre superieur a 1.");
		}
	}
	
	public String toString(){
		return ("Joueur 1 :\n"+this.jou1.toString()+"Joueur 2 :\n"+this.jou2.toString());
	}
}
