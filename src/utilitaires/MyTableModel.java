package utilitaires;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {

	
	public MyTableModel(String[][] mainTab2, String[] entetes2) {
		super(mainTab2,entetes2);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
}
