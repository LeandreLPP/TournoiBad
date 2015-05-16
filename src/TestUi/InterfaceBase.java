package TestUi;

import javax.swing.*;

public class InterfaceBase extends JFrame{
	
	private static final long serialVersionUID = 0;
	private JPanel panel;

	InterfaceBase(String titre){
		super(titre);
		this.setSize(300, 300);
		this.setVisible(true);
		this.panel = new JPanel();
		this.add(panel);
	}
	
	public void interfaceTest(){
		this.panel.removeAll();
		this.panel.validate();
		this.panel = new InterfaceTest();
		this.panel.add(panel);
		this.panel.revalidate();
		this.panel.repaint();
	}
	
	public void interfaceDeux(){
		this.panel.removeAll();
		this.panel.validate();
		this.panel = new InterfacageDeux();
		this.panel.revalidate();
		this.panel.repaint();
	}

}
