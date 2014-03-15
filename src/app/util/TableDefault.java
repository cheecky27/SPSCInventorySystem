package app.util;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class TableDefault extends DefaultTableModel {
	private Class[] columnTypes;
	private JTable table;
	private Object[][] data;
	private Object[] columnNames;


	public TableDefault(Object[][] data, Object[] columnNames,
			Class[] columnTypes) {
		super(data, columnNames);
		this.columnTypes = columnTypes;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnTypes[columnIndex];
	}

}