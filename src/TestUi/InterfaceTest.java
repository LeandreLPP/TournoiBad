package TestUi;

import javax.swing.*;

public class InterfaceTest extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 0;
	JButton jb1;
	JButton jb2;

	InterfaceTest(){
		jb1 = new JButton("Bouton 1");
		jb2 = new JButton("Bouton 2");
		this.add(jb1);
		this.add(jb2);
	}
}
