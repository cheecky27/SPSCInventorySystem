package app.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import app.dao.allitems.impl.ReadAllItemsDaoImpl;
import app.dao.borroweditems.WriteBorrowedItemDao;
import app.dao.borroweditems.impl.WriteBorrowedItemsDaoImpl;
import app.model.Group;
import app.model.Item;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Summary extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Group yourGroup;
	private Item yourItem;
	private List<Item> borrowedItems;
	private JLabel lblGrpname;
	private JLabel lblgrpLeader;
	private JLabel lblProf;
	private JTable tblSummary;
	private JTextField txtGrpId;
	/**
	 * Launch the application.
	 */
	public  void Summary() {
		try {
			Summary dialog = new Summary(yourGroup ,  yourItem, borrowedItems);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Summary(final Group yourGroup, final Item yourItem, final List<Item> finalListOfBorrowedItems) {
		this.yourGroup = yourGroup;
		this.yourItem = yourItem;
		this.borrowedItems = finalListOfBorrowedItems;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				lblGrpname.setText(yourItem.getBorrower());
				lblgrpLeader.setText(yourGroup.getGroupLeader());
				lblProf.setText(yourGroup.getProfessor());
				String grpIDD = ""+ yourGroup.getGroupID();
				txtGrpId.setText(grpIDD);

			}
		});

		setBounds(380, 130, 588, 524);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 153, 102));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblSummary = new JLabel("Summary:");
		lblSummary.setForeground(new Color(255, 255, 255));
		lblSummary.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 30));
		lblSummary.setBounds(10, 11, 143, 61);
		contentPanel.add(lblSummary);

		JLabel lblGroupName = new JLabel("Group Name:");
		lblGroupName.setForeground(new Color(255, 255, 255));
		lblGroupName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblGroupName.setBounds(46, 115, 107, 37);
		contentPanel.add(lblGroupName);

		JLabel lblLeader = new JLabel("Group Leader:");
		lblLeader.setForeground(new Color(255, 255, 255));
		lblLeader.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblLeader.setBounds(309, 115, 107, 37);
		contentPanel.add(lblLeader);

		JLabel lblProfessor = new JLabel("Professor:");
		lblProfessor.setForeground(new Color(255, 255, 255));
		lblProfessor.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblProfessor.setBounds(46, 149, 107, 37);
		contentPanel.add(lblProfessor);

		JLabel lblItemsBorrowed = new JLabel("Items Borrowed:");
		lblItemsBorrowed.setForeground(new Color(255, 255, 255));
		lblItemsBorrowed.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblItemsBorrowed.setBounds(48, 197, 132, 37);
		contentPanel.add(lblItemsBorrowed);

		lblGrpname = new JLabel("");
		lblGrpname.setBounds(142, 125, 143, 23);
		contentPanel.add(lblGrpname);

		lblgrpLeader = new JLabel("");
		lblgrpLeader.setBounds(419, 129, 143, 23);
		contentPanel.add(lblgrpLeader);

		lblProf = new JLabel("");
		lblProf.setBounds(142, 163, 143, 23);
		contentPanel.add(lblProf);

		JLabel lblTotalans = new JLabel("");
		lblTotalans.setBounds(425, 397, 77, 23);
		contentPanel.add(lblTotalans);

		JScrollPane scrlpSummary = new JScrollPane();
		scrlpSummary.setBounds(142, 245, 360, 141);
		contentPanel.add(scrlpSummary);

		tblSummary = new JTable();
		scrlpSummary.setViewportView(tblSummary);
		tblSummary.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Item no", "Item Description", "Quantity"
				}
				) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
					Integer.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		JLabel lblGrpId = new JLabel("Group ID");
		lblGrpId.setForeground(new Color(255, 255, 255));
		lblGrpId.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblGrpId.setBounds(46, 83, 77, 37);
		contentPanel.add(lblGrpId);

		txtGrpId = new JTextField();
		txtGrpId.setEditable(false);
		txtGrpId.setBounds(154, 94, 86, 20);
		contentPanel.add(txtGrpId);
		txtGrpId.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(0, 153, 102));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						for(Item borrowedItemss : borrowedItems){
							WriteBorrowedItemDao myWriter = new WriteBorrowedItemsDaoImpl();
							myWriter.doWriteBorrowedItems(yourGroup, borrowedItemss);
							dispose();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		doShowSummary();
	}

	void doShowSummary(){
		DefaultTableModel dtm = (DefaultTableModel) tblSummary.getModel();


		for (Item borrowedItem : borrowedItems) {
			Object[] rowData = new Object[3];

			rowData[0] =borrowedItem.getItemID();
			rowData[1] = borrowedItem.getItemDes();
			rowData[2] = borrowedItem.getItemsToBeBorrowed();


			dtm.addRow(rowData);

		}
		tblSummary.updateUI();

	}
}
