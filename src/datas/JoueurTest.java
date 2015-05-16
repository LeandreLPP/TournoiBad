package datas;

public class JoueurTest {

	public static void main(String[] args) {
		Joueur jou1 = new Joueur("123456","FRETAY","Juliette");
		if(jou1.getNumLic().equals("123456") && jou1.getNomJ().equals("FRETAY") && jou1.getPrenomJ().equals("Juliette")){
			System.out.println("Les accesseurs fonctionnent ! ");
		}
		System.out.println(jou1.toString());

	}

}
