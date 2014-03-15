package app.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

import app.dao.allitems.ReadAllItemsDao;
import app.dao.allitems.impl.ReadAllItemsDaoImpl;
import app.dao.archive.ReadIntoArchiveDao;
import app.dao.archive.impl.ReadIntoArchiveDaoImpl;
import app.dao.newentry.WriteNewItemDao;
import app.dao.newentry.impl.WriteNewItemDaoImpl;
import app.model.Item;
import app.util.CurrentDate;

import javax.swing.JInternalFrame;
import javax.swing.JToggleButton;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Archive extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tblArchive;
	private ItemManagement myItemManagement;

	/**
	 * Launch the application.
	 */
	public  void Archive() {
		try {
			Archive dialog = new Archive(myItemManagement);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Archive(final ItemManagement myItemManagement) {
		this.myItemManagement = myItemManagement;

		setBounds(400, 100, 591, 538);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 153, 102));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblArchive = new JLabel("Archive");
		lblArchive.setForeground(new Color(255, 255, 255));
		lblArchive.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblArchive.setBounds(28, 23, 88, 54);
		contentPanel.add(lblArchive);

		JPanel pnlArchive = new JPanel();
		pnlArchive.setBackground(new Color(0, 153, 102));
		pnlArchive.setBounds(20, 103, 545, 369);
		contentPanel.add(pnlArchive);
		pnlArchive.setLayout(null);

		JScrollPane scrlpArchive = new JScrollPane();
		scrlpArchive.setBounds(10, 8, 525, 242);
		pnlArchive.add(scrlpArchive);

		tblArchive = new JTable();
		tblArchive.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Item No", "Item description", "Quantity", "Unit", "Location", "Date Deleted"
				}
				) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblArchive.getColumnModel().getColumn(1).setPreferredWidth(157);
		scrlpArchive.setViewportView(tblArchive);

		JLabel lblRestore = new JLabel("");
		lblRestore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ReadIntoArchiveDao myArchiveDao = new ReadIntoArchiveDaoImpl();
				myArchiveDao.deleteRecordInArchive(getSelectedRow());
				WriteNewItemDao myItemDao = new WriteNewItemDaoImpl();
				boolean wasWritten =	myItemDao.writeIntoInventory(getSelectedRow());
				if(wasWritten){
					myItemManagement.showInventoryListTable();
					final JDialog myPrompt = new JDialog();
					myPrompt.setVisible(true);
					myPrompt.setBounds(450, 300, 400, 200);
					myPrompt.getContentPane().setBackground(Color.white);
					JLabel myLblPrompt = new JLabel("Apparatus succesfully restored!");
					myLblPrompt.setVisible(true);
					myLblPrompt.setFont(new Font("Segoe UI", Font.PLAIN, 20));
					myLblPrompt.setBounds(450, 200, 20, 10 );
					myPrompt.getContentPane().add(myLblPrompt);

					myPrompt.addKeyListener(new KeyAdapter() {


						@Override
						public void keyPressed(KeyEvent arg0) {
							myPrompt.dispose();

						}
					});

				}
			}
		});
		lblRestore.setBounds(457, 261, 78, 87);
		pnlArchive.add(lblRestore);
		lblRestore.setToolTipText("Restore\r\n");
		lblRestore.setIcon(new ImageIcon(Archive.class.getResource("/app/resourceLatest/Folders-OS-System-Restore-Metro-icon.png")));

		showRemovedItemsTable();
	}

	public void showRemovedItemsTable(){

		DefaultTableModel dtmVI = (DefaultTableModel) tblArchive.getModel();
		dtmVI.getDataVector().removeAllElements();

		ReadIntoArchiveDao myArchive = new ReadIntoArchiveDaoImpl();
		List<Item> archiveList = myArchive.showArchive();
		for (Item archiveItems : archiveList) {
			Object[] rowData = new Object[6];


			rowData[0] =archiveItems.getItemID();
			rowData[1] =archiveItems.getItemDes();
			rowData[2] =archiveItems.getQuantity();
			rowData[3] =archiveItems.getUnitOfMeasurement();
			rowData[4] =archiveItems.getLocation();
			rowData[5] =archiveItems.getDateDeleted();




			dtmVI.addRow(rowData);
		}
	}
	public Item getSelectedRow(){
		Item itemToBeRestored = new Item();
		DefaultTableModel dtm = (DefaultTableModel) tblArchive.getModel();
		String itemID  = dtm.getValueAt(tblArchive.getSelectedRow(), 0).toString();
		String itemDes = dtm.getValueAt(tblArchive.getSelectedRow(), 1).toString();
		int quantity = (int) dtm.getValueAt(tblArchive.getSelectedRow(), 2);
		String unit = dtm.getValueAt(tblArchive.getSelectedRow(),3).toString();
		String location= dtm.getValueAt(tblArchive.getSelectedRow(), 4).toString();
		String dateDeleted = dtm.getValueAt(tblArchive.getSelectedRow(), 5).toString();
		itemToBeRestored.setItemID(itemID);
		itemToBeRestored.setItemDes(itemDes);
		itemToBeRestored.setQuantity(quantity);
		itemToBeRestored.setUnitOfMeasurement(unit);
		itemToBeRestored.setLocation(location);
		CurrentDate myCurrentDate = new CurrentDate();
		itemToBeRestored.setDateDelivered(myCurrentDate.intoTheDBDate());
		return itemToBeRestored;
	}
}
