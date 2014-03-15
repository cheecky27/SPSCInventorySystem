package app.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;



import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import app.dao.allitems.ReadAllItemsDao;
import app.dao.allitems.impl.ReadAllItemsDaoImpl;
import app.dao.itemclassification.ReadFromItemClassDBDao;
import app.dao.itemclassification.impl.ReadFromItemClassDBDaoImpl;
import app.dao.logdata.WriteItemLogDao;
import app.dao.logdata.impl.WriteItemLogDaoImpl;
import app.dao.newentry.impl.ReadNewItemDaoImpl;
import app.dao.newentry.impl.WriteNewItemDaoImpl;


import app.location.dao.impl.ReadLocationDaoImpl;
import app.model.Group;
import app.model.Item;
import app.service.cmblclassification.impl.CmbClassificationServiceImpl;
import app.service.cmblocationunit.CmbLocationUnitService;
import app.service.cmblocationunit.impl.CmbLocationUnitServiceImpl;
import app.user.dao.impl.ReadUserDaoImpl;
import app.util.CurrentDate;
import app.util.ItemIDSetter;
import app.util.Test;
import app.util.Time;



import java.awt.Color;
import java.util.Calendar;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JProgressBar;

public class ItemManagement {

	JFrame IMFrame;
	private JTextField txtSearch;
	private JTextField txtItemDes;
	private JTextField txtQuantity;
	private JTextArea txtRemarks;
	public JComboBox cmbUnitOfMeasurement;
	private ItemManagement itemManagement= this;
	JPanel pnlSideBar;
	JLabel lblDate;
	private JTable tblAllItems;
	private JLabel time;
	private String selectedUnit;
	private String selectedLocation;
	private JComboBox cmbLocation ;
	boolean dumaan;
	boolean dumaan2;
	boolean dumaan3;
	JLabel lblLog;
	JLabel lblArchive;
	int rowCount;
	private JLabel lblCancel;
	private JLabel lblClassification;
	private JComboBox cmbClassification;
	private JScrollPane scrlpRemarks;
	private String code = "";
//	private Item itemForLog ;
	private JLabel lblBG;
	/**
	 * Launch the application.
	 */
	public  void ItemManagement() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {

					ItemManagement window = new ItemManagement();
					window.IMFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ItemManagement() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		IMFrame = new JFrame();
		IMFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				IMFrame.setDefaultCloseOperation(IMFrame.DO_NOTHING_ON_CLOSE);
				JOptionPane.showMessageDialog(null,"Please logout!");
			}
		});


		IMFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		IMFrame.getContentPane().setBackground(new Color(255, 255, 255));
		IMFrame.getContentPane().setForeground(new Color(255, 255, 255));
		IMFrame.getContentPane().setPreferredSize(new Dimension(1365, 747));

		IMFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		IMFrame.getContentPane().setLayout(null);
		JMenus myJMenus = new JMenus(IMFrame);
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String search= txtSearch.getText();
				showSearchNewListTable(search);
			}
		});

		txtSearch.setBounds(959, 220, 244, 32);
		IMFrame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);

		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setForeground(Color.WHITE);
		lblSearch.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblSearch.setBounds(873, 220, 76, 30);
		IMFrame.getContentPane().add(lblSearch);
		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
		Border line1 = new LineBorder(Color.BLACK);
		Border margin1 = new EmptyBorder(5, 15, 5, 15);
		Border compound1 = new CompoundBorder(line, margin);

		JTabbedPane tbdpItemManagement = new JTabbedPane(SwingConstants.TOP);
		tbdpItemManagement.setBorder(null);
		tbdpItemManagement.setBounds(103, 255, 1106, 397);
		IMFrame.getContentPane().add(tbdpItemManagement);

		JPanel pnlAllItems = new JPanel();
		pnlAllItems.setBackground(new Color(102, 205, 170));
		pnlAllItems.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				pnlSideBar.setVisible(true);
			}
		});


		tbdpItemManagement.addTab("All Items", null, pnlAllItems, null);
		pnlAllItems.setLayout(null);

		JScrollPane scrpAllItems = new JScrollPane();
		scrpAllItems.setBounds(26, 33, 1046, 244);
		pnlAllItems.add(scrpAllItems);

		tblAllItems = new JTable();
		tblAllItems.setBackground(Color.WHITE);
		tblAllItems.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Item ID", "Item Description", "Location", "Quantity", "Unit", "Date Created", "Remarks", "Instock", "Outstock", "Last Borrower", "Last Borrowed"
				}
				) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblAllItems.getColumnModel().getColumn(0).setPreferredWidth(52);
		tblAllItems.getColumnModel().getColumn(1).setPreferredWidth(145);
		tblAllItems.getColumnModel().getColumn(3).setPreferredWidth(54);
		tblAllItems.getColumnModel().getColumn(7).setPreferredWidth(48);
		tblAllItems.getColumnModel().getColumn(8).setPreferredWidth(56);
		tblAllItems.getColumnModel().getColumn(9).setPreferredWidth(90);
		tblAllItems.getColumnModel().getColumn(10).setPreferredWidth(87);
		scrpAllItems.setViewportView(tblAllItems);

		pnlSideBar = new JPanel();
		pnlSideBar.setBounds(451, 278, 220, 91);
		pnlAllItems.add(pnlSideBar);
		pnlSideBar.setBackground(new Color(102, 205, 170));
		pnlSideBar.setLayout(null);

		lblLog = new JLabel("");
		lblLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tblAllItems.getSelectedRow() == -1){
					JOptionPane.showMessageDialog(IMFrame, "No selected row");
				}
				else{
					UpdateItems ui = new UpdateItems(getSelectedItem(), itemManagement);
					ui.setVisible(true);
				}

			}
		});
		lblLog.setToolTipText("log");
		lblLog.setIcon(new ImageIcon(ItemManagement.class.getResource("/app/resources/iba.png")));
		lblLog.setBounds(23, 0, 81, 89);
		pnlSideBar.add(lblLog);

		lblArchive = new JLabel("");
		lblArchive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {


				if(tblAllItems.getSelectedRow()==-1){

					final JDialog myPrompt = new JDialog();
					myPrompt.setVisible(true);
					myPrompt.setBounds(450, 300, 400, 200);
					myPrompt.getContentPane().setBackground(Color.white);
					JLabel myLblPrompt = new JLabel("Please select an apparatus to delete!");
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

				}else{
					ItemDelete myItemDelete = new ItemDelete(getSelectedItem(), itemManagement);
					myItemDelete.confirmDelete();
				}
			}

		});
		lblArchive.setToolTipText("Archive");
		lblArchive.setIcon(new ImageIcon(ItemManagement.class.getResource("/app/resources/2.PNG")));
		lblArchive.setBounds(138, 0, 72, 89);
		pnlSideBar.add(lblArchive);



		JPanel pnlAddNewItem = new JPanel();
		pnlAddNewItem.setBackground(new Color(102, 205, 170));
		pnlAddNewItem.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				pnlSideBar.setVisible(false);
			}
		});
		tbdpItemManagement.addTab("Add New Item", null, pnlAddNewItem, null);
		pnlAddNewItem.setLayout(null);

		JLabel lblItemDes = new JLabel("Item Description: ");
		lblItemDes.setForeground(Color.BLACK);
		lblItemDes.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblItemDes.setBounds(76, 80, 159, 32);
		pnlAddNewItem.add(lblItemDes);

		txtItemDes = new JTextField();
		txtItemDes.setForeground(new Color(0, 100, 0));
		txtItemDes.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		txtItemDes.setColumns(10);
		txtItemDes.setBounds(245, 80, 236, 38);
		pnlAddNewItem.add(txtItemDes);

		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setForeground(Color.BLACK);
		lblQuantity.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblQuantity.setBounds(76, 143, 85, 26);
		pnlAddNewItem.add(lblQuantity);

		txtQuantity = new JTextField();
		txtQuantity.setForeground(new Color(0, 128, 0));
		txtQuantity.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(245, 139, 137, 38);
		pnlAddNewItem.add(txtQuantity);

		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setForeground(Color.BLACK);
		lblLocation.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblLocation.setBounds(82, 198, 111, 27);
		pnlAddNewItem.add(lblLocation);


		JLabel lblRemarks = new JLabel("Remarks:");
		lblRemarks.setForeground(Color.BLACK);
		lblRemarks.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblRemarks.setBounds(565, 140, 111, 32);
		pnlAddNewItem.add(lblRemarks);
		CmbLocationUnitServiceImpl myyCmbLocationServiceImpl = new CmbLocationUnitServiceImpl();
		cmbUnitOfMeasurement = new JComboBox(myyCmbLocationServiceImpl.addDataToCmbUnitOfMeasurement());
		cmbUnitOfMeasurement.setBackground(new Color(255, 255, 255));
		cmbUnitOfMeasurement.setForeground(new Color(0, 128, 0));
		cmbUnitOfMeasurement.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		cmbUnitOfMeasurement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmbUnitOfMeasurement = (JComboBox) e.getSource();
				selectedUnit =(String) cmbUnitOfMeasurement.getSelectedItem();
				dumaan = true;
			}
		});
		cmbUnitOfMeasurement.setBounds(392, 139, 89, 38);
		pnlAddNewItem.add(cmbUnitOfMeasurement);


		CmbLocationUnitServiceImpl myCmbLocationServiceImpl = new CmbLocationUnitServiceImpl();
		cmbLocation = new JComboBox(myCmbLocationServiceImpl.addDataToCmbLocation());
		cmbLocation.setBackground(new Color(255, 255, 255));
		cmbLocation.setForeground(new Color(0, 128, 0));
		cmbLocation.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		cmbLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmbLocation = (JComboBox) e.getSource();
				selectedLocation= (String) cmbLocation.getSelectedItem();
				dumaan2 = true;

			}
		});
		cmbLocation.setBounds(242, 211, 289, 38);
		pnlAddNewItem.add(cmbLocation);

		JLabel lblAddNewItem = new JLabel("");
		lblAddNewItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//	Scanner scan = new Scanner(txtItemDes.getText());
				confirmItem();
				WriteNewItemDaoImpl myDbImpl = new WriteNewItemDaoImpl();
			//	itemForLog = setItemValue();
				boolean result =myDbImpl.writeIntoInventory(setItemValue());
			
				/*	Item myItem = new Item();
				int lolo = 0;
				try{
					myItem.setQuantity(Integer.parseInt(txtQuantity.getText()));
				}catch(NumberFormatException e){
					lolo = 1;
				}
				if(txtRemarks.getText().trim().isEmpty()||txtItemDes.getText().trim().isEmpty() || txtQuantity.getText().trim().isEmpty() || lolo == 1 ){
					JOptionPane.showMessageDialog(IMFrame,"No Input!, Please enter a number!");
				}*/

			if(result){
					JOptionPane.showMessageDialog(null,"Succesfully added new item");

				}
				showInventoryListTable();
				setNull();
			}
		});
		lblAddNewItem.setIcon(new ImageIcon(ItemManagement.class.getResource("/app/resources/plusbig.png")));
		lblAddNewItem.setBounds(942, 294, 64, 70);
		pnlAddNewItem.add(lblAddNewItem);

		lblCancel = new JLabel("");
		lblCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setNull();
			}
		});
		lblCancel.setIcon(new ImageIcon(ItemManagement.class.getResource("/app/resources/x.png")));
		lblCancel.setBounds(1016, 294, 75, 70);
		pnlAddNewItem.add(lblCancel);

		lblClassification = new JLabel("Classification:");
		lblClassification.setForeground(Color.BLACK);
		lblClassification.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblClassification.setBounds(565, 79, 137, 35);
		pnlAddNewItem.add(lblClassification);


		CmbClassificationServiceImpl myClassificationServiceImpl = new CmbClassificationServiceImpl();
		cmbClassification = new JComboBox(myClassificationServiceImpl.addDataToCmbClassification());
		cmbClassification.setForeground(new Color(0, 128, 0));
		cmbClassification.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		cmbClassification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cmbClassification = (JComboBox)e.getSource();
				String idCode = (String) cmbClassification.getSelectedItem();
				ReadFromItemClassDBDao myReader = new ReadFromItemClassDBDaoImpl();
				code =myReader.showItemCodeClassification(idCode);
				dumaan3 = true;
			}
		});

		cmbClassification.setBackground(Color.WHITE);
		cmbClassification.setBounds(699, 79, 289, 38);
		pnlAddNewItem.add(cmbClassification);

		scrlpRemarks = new JScrollPane();
		scrlpRemarks.setBounds(701, 146, 287, 101);
		pnlAddNewItem.add(scrlpRemarks);

		txtRemarks = new JTextArea();
		scrlpRemarks.setViewportView(txtRemarks);
		txtRemarks.setForeground(new Color(0, 128, 0));
		txtRemarks.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		txtRemarks.setBorder(line);
		txtRemarks.setLineWrap(true);





		showInventoryListTable();
		IMFrame.pack();
		Test mytest = new Test(IMFrame, txtSearch);

		lblBG = new JLabel("");
		lblBG.setIcon(new ImageIcon(ItemManagement.class.getResource("/app/resourceLatest/itemManagement.jpg")));
		lblBG.setBounds(0, 0, 1365, 747);
		IMFrame.getContentPane().add(lblBG);

	}

	public Item setItemValue(){
		CurrentDate myCurrentDate = new CurrentDate();
		Item myItem1 = new Item();

		if(dumaan3){
			ItemIDSetter myItemIdSetter = new ItemIDSetter();
			myItem1.setItemID(myItemIdSetter.doSetTheItemId(code));
		}else{

			code ="FCG";
			ItemIDSetter myItemIdSetter = new ItemIDSetter();
			myItem1.setItemID(myItemIdSetter.doSetTheItemId(code));

		}


		myItem1.setItemDes(txtItemDes.getText());
		if(dumaan2){
			myItem1.setLocation(selectedLocation);
		}else{
			myItem1.setLocation("cabinet 1");
		}
		if(dumaan){
			myItem1.setUnitOfMeasurement(selectedUnit);
		}else{

			myItem1.setUnitOfMeasurement("cm");
		}

		myItem1.setQuantity(Integer.parseInt(txtQuantity.getText()));

		myItem1.setRemarks(txtRemarks.getText());

		myItem1.setDateDelivered(myCurrentDate.intoTheDBDate());


		return myItem1;
	}
	public void confirmItem(){
		Item myItem = new Item();
		int lolo = 0;
		try{
			myItem.setQuantity(Integer.parseInt(txtQuantity.getText()));
		}catch(NumberFormatException e){
			lolo = 1;
		}
		if(txtRemarks.getText().isEmpty()||txtItemDes.getText().isEmpty() || txtQuantity.getText().isEmpty() || lolo == 1 ){
			JOptionPane.showMessageDialog(IMFrame,"No Input!, Please enter a number!");
		}else{
			setItemValue();
		}

	}
	public void setNull(){ 
		txtItemDes.setText("");
		txtQuantity.setText("");
		txtRemarks.setText("");
	}



	public void showInventoryListTable(){

		DefaultTableModel dtmVI = (DefaultTableModel) tblAllItems.getModel();
		dtmVI.getDataVector().removeAllElements();

		ReadAllItemsDao myReader = new ReadAllItemsDaoImpl();
		List<Item> inventoryList = myReader.showEverything();
		for (Item myInventoryItems : inventoryList) {
			Object[] rowData = new Object[11];


			rowData[0] =myInventoryItems.getItemID();
			rowData[1] =myInventoryItems.getItemDes();
			rowData[2] =myInventoryItems.getLocation();
			rowData[3] =myInventoryItems.getQuantity();
			rowData[4] =myInventoryItems.getUnitOfMeasurement();
			rowData[5] =myInventoryItems.getDateDelivered();
			rowData[6] =myInventoryItems.getRemarks();
			rowData[7] =myInventoryItems.getInstock();
			rowData[8] =myInventoryItems.getOutstock();
			rowData[9] =myInventoryItems.getBorrower();
			rowData[10] =myInventoryItems.getDateReturn();


			dtmVI.addRow(rowData);
		}
	}

	public void showSearchNewListTable(String search){

		DefaultTableModel dtm = (DefaultTableModel) tblAllItems.getModel();
		dtm.getDataVector().removeAllElements();
		ReadNewItemDaoImpl myReader = new ReadNewItemDaoImpl();
		List<Item> newItemList = myReader.showSearchedItems(search);
		for (Item myNewItems : newItemList) {

			Object[] rowData = new Object[11];


			rowData[0] =myNewItems.getItemID();
			rowData[1] =myNewItems.getItemDes();
			rowData[2] =myNewItems.getLocation();
			rowData[3] =myNewItems.getQuantity();
			rowData[4] =myNewItems.getUnitOfMeasurement();
			rowData[5] =myNewItems.getDateDelivered();
			rowData[6] =myNewItems.getRemarks();
			rowData[7] =myNewItems.getInstock();
			rowData[8] =myNewItems.getOutstock();
			rowData[9] =myNewItems.getBorrower();
			rowData[10] =myNewItems.getDateReturn();

			dtm.addRow(rowData);
		}
		tblAllItems.updateUI();
	}
	public Item getSelectedItem(){
		Item selectedItem = new Item();
		DefaultTableModel dtm = (DefaultTableModel) tblAllItems.getModel();
		String itemID  = dtm.getValueAt(tblAllItems.getSelectedRow(), 0).toString();
		String itemDes = dtm.getValueAt(tblAllItems.getSelectedRow(), 1).toString();
		String location= dtm.getValueAt(tblAllItems.getSelectedRow(), 2).toString();
		int quantity = (int) dtm.getValueAt(tblAllItems.getSelectedRow(), 3);

		String unit = dtm.getValueAt(tblAllItems.getSelectedRow(),4).toString();
		String dateCreated = dtm.getValueAt(tblAllItems.getSelectedRow(), 5).toString();

		String remarks=dtm.getValueAt(tblAllItems.getSelectedRow(),6).toString();
		int instock = (int)dtm.getValueAt(tblAllItems.getSelectedRow(),7);
		int outstock = (int) dtm.getValueAt(tblAllItems.getSelectedRow(),8);
		//	String lastBorrower= dtm.getValueAt(tblAllItems.getSelectedRow(),9).toString();
		//String lastBorrowed=dtm.getValueAt(tblAllItems.getSelectedRow(),10).toString();

		selectedItem.setItemID(itemID);
		selectedItem.setItemDes(itemDes);
		selectedItem.setLocation(location);
		selectedItem.setQuantity(quantity);
		selectedItem.setUnitOfMeasurement(unit);
		selectedItem.setDateDelivered(dateCreated);
		selectedItem.setRemarks(remarks);
		selectedItem.setInstock(instock);
		selectedItem.setOutstock(outstock);
		//selectedItem.setBorrower(lastBorrower);
		//selectedItem.setDateDelivered(lastBorrowed);



		return selectedItem;
	}
}

