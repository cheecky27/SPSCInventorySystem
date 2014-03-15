package app.util;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class LableKoTo extends JLabel implements TableCellRenderer
{
	public LableKoTo()
	{
		super();
		setOpaque(true);
		//setBackground(Color.cyan);
	}
	@Override
	public  Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		/*table.getDefaultEditor(Object.class).addCellEditorListener(new CellEditorListener() {

		    // called when editing stops
		    public void editingStopped(ChangeEvent e) {

		        // print out the value in the TableCellEditor
		        System.out.println(((TableCellEditor)e.getSource()).getCellEditorValue().toString());
		    }

		    public void editingCanceled(ChangeEvent e) {
		        // whatever
		    }*/
		
		
		JLabel lbl = (JLabel)value;

		// check if it is second row of column
		//then render using icom
		if(row==0)
		{
			setIcon(lbl.getIcon());
		}
		
	
		return this;
	}

}

