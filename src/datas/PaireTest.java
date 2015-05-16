package datas;

public class PaireTest {
	public static void main(String[] args){
		Joueur jou1 = new Joueur("123456","FRETAY","Juliette");
		Joueur jou2 = new Joueur("987654","BERNHEIM","Ninon");
		Paire paire1 = new Paire(jou1,jou2);
		paire1.setNumPaire(1);
		if(paire1.getJou1() == jou1 && paire1.getJou2() == jou2 && paire1.getNumPaire() == 1){
			System.out.println("Les accesseurs fonctionnent !");
		}
		System.out.println(paire1.toString());
		
	}
}
