package app.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import app.dao.allitems.ReadAllItemsDao;
import app.dao.allitems.UpdateInventoryItemsDao;
import app.dao.allitems.impl.ReadAllItemsDaoImpl;
import app.dao.allitems.impl.UpdateInventoryItemsDaoImpl;
import app.dao.logdata.ReadItemLogDao;
import app.dao.logdata.WriteItemLogDao;
import app.dao.logdata.impl.ReadItemLogDaoImpl;
import app.dao.logdata.impl.WriteItemLogDaoImpl;
import app.dao.newentry.UpdateNewItemDao;
import app.dao.newentry.impl.UpdateNewItemDaoImpl;
import app.model.Item;
import app.service.cmblocationunit.impl.CmbLocationUnitServiceImpl;
import app.util.CurrentDate;
import app.util.Time;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Window.Type;
import java.util.List;

import javax.swing.JComboBox;

public class UpdateItems extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtDescription;
	private JTextField txtQuantity;
	private JTextArea txtRemarks;
	private ItemManagement itemManagement;
	private JTable tblLog;
	JComboBox cmbLocation;
	String ungId;


	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("rawtypes")
	public UpdateItems(final Item i, final ItemManagement itemManagement) {
		ungId = i.getItemID();
		this.itemManagement = itemManagement;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {


				txtDescription.setText(i.getItemDes());
				txtQuantity.setText(Integer.toString(i.getQuantity()));
				txtRemarks.setText(i.getRemarks());
				cmbLocation.setSelectedItem(i.getLocation());

			}
		});
		setBounds(350, 175, 535, 529);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 153, 102));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		CmbLocationUnitServiceImpl myCmbLocationUnitServiceImpl = new CmbLocationUnitServiceImpl();
		cmbLocation = new JComboBox(myCmbLocationUnitServiceImpl.addDataToCmbLocation());
		cmbLocation.setBounds(153, 310, 192, 25);
		contentPanel.add(cmbLocation);


		JLabel label_1 = new JLabel("Item Description: ");
		label_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
		label_1.setForeground(new Color(255, 255, 255));
		label_1.setBounds(10, 232, 134, 25);
		contentPanel.add(label_1);

		txtDescription = new JTextField();
		txtDescription.setColumns(10);
		txtDescription.setBounds(153, 232, 192, 25);
		contentPanel.add(txtDescription);

		JLabel label_2 = new JLabel("Quantity:");
		label_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
		label_2.setForeground(new Color(255, 255, 255));
		label_2.setBounds(10, 277, 85, 20);
		contentPanel.add(label_2);

		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(153, 277, 61, 20);
		contentPanel.add(txtQuantity);

		JLabel label_4 = new JLabel("Remarks:");
		label_4.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
		label_4.setForeground(new Color(255, 255, 255));
		label_4.setBounds(10, 373, 101, 14);
		contentPanel.add(label_4);

		txtRemarks = new JTextArea();
		txtRemarks.setBackground(new Color(245, 245, 245));
		txtRemarks.setBounds(153, 364, 192, 60);
		contentPanel.add(txtRemarks);

		JLabel lblLog = new JLabel("Log");
		lblLog.setForeground(new Color(255, 255, 255));
		lblLog.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 27));
		lblLog.setBounds(24, -2, 46, 49);
		contentPanel.add(lblLog);

		JScrollPane scrpLog = new JScrollPane();
		scrpLog.setBounds(21, 71, 472, 130);
		contentPanel.add(scrpLog);

		tblLog = new JTable();
		tblLog.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Item ID", "Item Description", "Quantity", "Remarks", "Date"
				}
				) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrpLog.setViewportView(tblLog);

		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
		lblLocation.setForeground(new Color(255, 255, 255));
		lblLocation.setBounds(10, 317, 91, 14);
		contentPanel.add(lblLocation);

		final CurrentDate myCurrentDate = new CurrentDate();
		JLabel lblDate = new JLabel(myCurrentDate.showDateToday());
		lblDate.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
		lblDate.setForeground(new Color(255, 255, 255));
		lblDate.setBounds(416, 15, 61, 25);
		contentPanel.add(lblDate);


		JLabel lblTime = new JLabel();
		lblTime.setBounds(375, 20, 91, 16);
		contentPanel.add(lblTime);
		new Time(lblTime);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(0, 153, 102));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Item editKo = new Item();
						WriteItemLogDao myWriteItemLogDao = new WriteItemLogDaoImpl();
						UpdateNewItemDao updateNewItem = new UpdateNewItemDaoImpl();
						UpdateInventoryItemsDao myUpdateInventoryItemsDao = new UpdateInventoryItemsDaoImpl();

						editKo.setLocation((String) cmbLocation.getSelectedItem());
						editKo.setItemID(i.getItemID());
						editKo.setItemDes(txtDescription.getText());
						editKo.setQuantity(Integer.parseInt(txtQuantity.getText()));
						editKo.setRemarks(txtRemarks.getText());
						editKo.setInstock(editKo.getQuantity());
						editKo.setDateDelivered(myCurrentDate.intoTheDBDate());

						if(txtDescription.getText().equals("") || txtQuantity.getText().equals("") || txtRemarks.getText().equals("")){
							JOptionPane.showMessageDialog(null,"Please fill-up the fields");
						}else{
							myWriteItemLogDao.writeIntoLog(editKo);
							updateNewItem.updateIntoDB(editKo);
							myUpdateInventoryItemsDao.updateInventory(editKo);
							showUpdateListTable();
							dispose();
							itemManagement.showInventoryListTable();
						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		showUpdateListTable();
	}

	public void showUpdateListTable(){

		DefaultTableModel dtmVI = (DefaultTableModel) tblLog.getModel();
		dtmVI.getDataVector().removeAllElements();

		ReadItemLogDao myReadItemLogDao = new ReadItemLogDaoImpl();
		List<Item> logList = myReadItemLogDao.showListOfLogs(ungId);
		for (Item myLog : logList) {
			Object[] rowData = new Object[5];
			rowData[0] = myLog.getItemID();
			rowData[1] =myLog.getItemDes();
			rowData[2] = myLog.getQuantity();
			rowData[3] =myLog.getRemarks();
			rowData[4] =myLog.getDateDelivered();




			dtmVI.addRow(rowData);
		}
		tblLog.updateUI();
	}
}
