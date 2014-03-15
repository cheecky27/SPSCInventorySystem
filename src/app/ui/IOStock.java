package app.ui;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import app.dao.allitems.UpdateInventoryItemsDao;
import app.dao.allitems.impl.ReadAllItemsDaoImpl;
import app.dao.allitems.impl.UpdateInventoryItemsDaoImpl;
import app.dao.group.impl.ReadGroupDaoImpl;


import app.dao.selected.group.SelectedGroupDao;
import app.dao.selected.group.impl.SelectedGroupDaoImpl;
import app.model.Group;
import app.model.Item;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import app.service.removeitemsfromnewitemstbl.RemoveItemsFromNewItemsTblService;
import app.service.removeitemsfromnewitemstbl.impl.RemoveItemsfomNewItemsTblServiceImpl;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class IOStock{

	private JFrame inventoryframe;
	private JTable tblInventory;
	private JCheckBox chckbxBorrow;

	private JTextField txtUProf;
	private JTable tblMembers;
	private JTable tblItemBorrowed;
	private JComboBox<?> cmbGroups;
	private Boolean wasAbleToChooseAGroup = false;
	private Boolean wasAbleToChooseALeader;
	private String[] mem=null;
	private String[] studId=null;
	private String selectedGroup= "";
	private List<Item> borrowedItems = new ArrayList<Item>(); 
	private Item selectedQuantity;
	private int grpId;
	/**
	 * Launch the application.
	 */
	public  void InventoryWindow() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					IOStock window = new IOStock();
					window.inventoryframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IOStock() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {


		inventoryframe = new JFrame();

		inventoryframe.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				inventoryframe.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				JOptionPane.showMessageDialog(null,"Please logout!");
			}
		});


		inventoryframe.setExtendedState(Frame.MAXIMIZED_BOTH);
		inventoryframe.getContentPane().setBackground(new Color(255, 255, 255));
		inventoryframe.getContentPane().setForeground(new Color(255, 255, 255));
		inventoryframe.getContentPane().setPreferredSize(new Dimension(1365, 747));

		inventoryframe.getContentPane().setLayout(null);

		new JMenus(inventoryframe);
		JScrollPane scrlpBorrow = new JScrollPane();
		scrlpBorrow.setBounds(63, 368, 714, 295);
		inventoryframe.getContentPane().add(scrlpBorrow);
		tblInventory = new JTable();




		tblInventory.setForeground(new Color(0, 128, 0));
		tblInventory.setBackground(Color.WHITE);
		tblInventory.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		tblInventory.setShowVerticalLines(false);
		tblInventory.setShowHorizontalLines(false);
		tblInventory.setShowGrid(false);
		tblInventory.setFillsViewportHeight(true);
		tblInventory.setRowHeight(25);




		//	ImageIcon imageicon = new ImageIcon("E:\\Studies\\3rd Year 2nd Sem\\Software Engineering\\lagayan.png");

		//	lblAddToList = new JLabel(imageicon);
		tblInventory.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"ItemID", "Item Description", "Quantity", "InStock", "OutStock"
				}
				) {
			Class[] columnTypes = new Class[] {
					String.class, String.class, Integer.class, Integer.class, Integer.class
			};
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblInventory.getColumnModel().getColumn(1).setPreferredWidth(135);
		scrlpBorrow.setViewportView(tblInventory);

		cmbGroups = new JComboBox<Object>(addDataToCombo());
		cmbGroups.setForeground(new Color(107, 142, 35));
		cmbGroups.setBackground(new Color(255, 255, 255));
		cmbGroups.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cmbGroups = (JComboBox<?>) e.getSource();
				wasAbleToChooseAGroup = true;
				cmbGroups.setBackground(Color.GREEN);
				selectedGroup =(String) cmbGroups.getSelectedItem();
				getSelectedGroupInDb();

			}
		});

		cmbGroups.setBounds(156, 111, 280, 41);
		inventoryframe.getContentPane().add(cmbGroups);

		JLabel lblSelectGroup = new JLabel("Select group:");
		lblSelectGroup.setForeground(Color.WHITE);
		lblSelectGroup.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectGroup.setBounds(63, 116, 83, 27);
		inventoryframe.getContentPane().add(lblSelectGroup);

		JLabel lblProfessor = new JLabel("Professor:");
		lblProfessor.setForeground(Color.WHITE);
		lblProfessor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProfessor.setBounds(63, 160, 62, 35);
		inventoryframe.getContentPane().add(lblProfessor);

		JLabel lblMembers = new JLabel("Members:");
		lblMembers.setForeground(Color.WHITE);
		lblMembers.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMembers.setBounds(63, 231, 67, 27);
		inventoryframe.getContentPane().add(lblMembers);

		txtUProf = new JTextField();
		txtUProf.setForeground(new Color(0, 128, 0));
		txtUProf.setBackground(new Color(255, 255, 255));
		txtUProf.setEditable(false);
		txtUProf.setBounds(156, 169, 280, 20);
		inventoryframe.getContentPane().add(txtUProf);
		txtUProf.setColumns(10);

		JScrollPane scrpMembers = new JScrollPane();
		scrpMembers.setBounds(156, 232, 273, 100);
		inventoryframe.getContentPane().add(scrpMembers);

		tblMembers = new JTable();
		tblMembers.setForeground(new Color(0, 128, 0));
		tblMembers.setBackground(new Color(255, 255, 255));
		tblMembers.setShowVerticalLines(false);
		tblMembers.setShowHorizontalLines(false);
		tblMembers.setShowGrid(false);
		tblMembers.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"StudNo", "StudName"
				}
				) {
			boolean[] columnEditables = new boolean[] {
					false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrpMembers.setViewportView(tblMembers);

		JScrollPane scrlpListBorrowed = new JScrollPane();
		scrlpListBorrowed.setBounds(911, 368, 280, 295);
		inventoryframe.getContentPane().add(scrlpListBorrowed);

		tblItemBorrowed = new JTable();
		tblItemBorrowed.setForeground(new Color(0, 100, 0));
		tblItemBorrowed.setBackground(Color.green);
		tblItemBorrowed.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Item Number", "Item Description", "Quantity"
				}
				) {
			boolean[] columnEditables = new boolean[] {
					false, false, true
			};
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblItemBorrowed.getColumnModel().getColumn(0).setPreferredWidth(74);
		tblItemBorrowed.getColumnModel().getColumn(1).setPreferredWidth(133);
		tblItemBorrowed.getColumnModel().getColumn(2).setPreferredWidth(73);
		tblItemBorrowed.setShowVerticalLines(false);
		tblItemBorrowed.setShowHorizontalLines(false);
		tblItemBorrowed.setShowGrid(false);
		scrlpListBorrowed.setViewportView(tblItemBorrowed);



		JButton btnBorrow = new JButton("Borrow");
		btnBorrow.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnBorrow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				
				getSelectedMember();
				if(wasAbleToChooseAGroup && wasAbleToChooseALeader){
					JOptionPane.showMessageDialog(null,"Succesfully borrowed items!");
					Summary mySummary = new Summary(getSelectedMember(), getQuantity(), borrowedItems);
					mySummary.Summary();
					
					showInventoryTable();
				}else{
					JOptionPane.showMessageDialog(null, "Please select a group/leader !");
					cmbGroups.setBackground(Color.RED);
				}
			}
		});
		btnBorrow.setBounds(1204, 501, 114, 38);
		inventoryframe.getContentPane().add(btnBorrow);

		JLabel lblSelectAStudent = new JLabel("*Select a student leader");
		lblSelectAStudent.setForeground(Color.BLUE);
		lblSelectAStudent.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSelectAStudent.setBounds(151, 200, 166, 27);
		inventoryframe.getContentPane().add(lblSelectAStudent);
		
		JLabel lblAddItem = new JLabel("");
		lblAddItem.setToolTipText("Add Item");
		lblAddItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				doUpdateTblItemsToBeBorrowed(getSelectedItem());
			}
		});
		lblAddItem.setIcon(new ImageIcon("C:\\Users\\rosselle\\Desktop\\add.png"));
		lblAddItem.setBounds(812, 429, 67, 74);
		inventoryframe.getContentPane().add(lblAddItem);
		
		JLabel lblRemoveItem = new JLabel("");
		lblRemoveItem.setToolTipText("Remove Item");
		lblRemoveItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) tblItemBorrowed.getModel();
				if(dtm.getRowCount() >0){
					dtm.removeRow(tblItemBorrowed.getSelectedRow());
				}else{
					JOptionPane.showMessageDialog(inventoryframe,"No item to remove!");
				}
			}
		});
		lblRemoveItem.setIcon(new ImageIcon("C:\\Users\\rosselle\\Desktop\\Trash-2-icon.png"));
		lblRemoveItem.setBounds(812, 528, 67, 66);
		inventoryframe.getContentPane().add(lblRemoveItem);
		
		JLabel lblBG = new JLabel("");
		lblBG.setIcon(new ImageIcon(IOStock.class.getResource("/app/resourceLatest/InOutStockManagement.jpg")));
		lblBG.setBounds(0, 0, 1365, 747);
		inventoryframe.getContentPane().add(lblBG);
		chckbxBorrow = new JCheckBox("borrow");
		chckbxBorrow.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxBorrow.setBounds(360, 63, 97, 23);


		showInventoryTable();
		inventoryframe.pack();
	}

	public void getSelectedGroupInDb(){

		showGroupMembers();
	}
	public void showGroupMembers(){
		DefaultTableModel dtm = (DefaultTableModel) tblMembers.getModel();

		dtm.getDataVector().removeAllElements();
		SelectedGroupDao myReader = new SelectedGroupDaoImpl();
		List<Group> memberList = myReader.getSelectedGroupInfo(selectedGroup);
		for (Group myMembers : memberList) {
			Object[] rowData = new Object[3];
			mem =myMembers.getGroupMembers().split(",");
			studId=myMembers.getStudentId().split(",");

			for(int x = 0; x<mem.length ; x++){
				rowData[0]=studId[x];
				rowData[1] = mem[x];
				dtm.addRow(rowData);
			}
			txtUProf.setText(myMembers.getProfessor());
			grpId = myMembers.getGroupID();
		}
		tblMembers.updateUI();
	}

	public void showInventoryTable(){
		DefaultTableModel dtm = (DefaultTableModel) tblInventory.getModel();
		dtm.getDataVector().removeAllElements();
		ReadAllItemsDaoImpl myReader = new ReadAllItemsDaoImpl();
		List<Item> newAllItemList = myReader.showEverything();
		for (Item myNewItems : newAllItemList) {
			Object[] rowData = new Object[5];
			rowData[0] =myNewItems.getItemID();
			rowData[1] =myNewItems.getItemDes();
			rowData[2] =myNewItems.getQuantity();
			rowData[3] =myNewItems.getInstock();
			rowData[4] =myNewItems.getOutstock();
			dtm.addRow(rowData);

		}
		tblInventory.updateUI();
	}

	public void doUpdateTblItemsToBeBorrowed(List<Item> borrowedItems){
		DefaultTableModel dtm = (DefaultTableModel) tblItemBorrowed.getModel();
		Object[] rowData = new Object[2];
		for (Item myNewItems : borrowedItems) {
			rowData[0]=myNewItems.getItemID();
			rowData[1]=myNewItems.getItemDes();
			for(int x = 0 ; x < tblItemBorrowed.getRowCount(); x++){
				int value = x;
				String myTableValue =  dtm.getValueAt(value, 0).toString();
				String yourSelectedValue = myNewItems.getItemID();
				if(myTableValue.equals(yourSelectedValue) ){
					JOptionPane.showMessageDialog(null, "Item already selected!");
					doUpdateTblItemsToBeBorrowed(null);
				}
			}
			dtm.addRow(rowData);
		}
		tblItemBorrowed.updateUI();
	}

	public String[] addDataToCombo(){
		int containMe = 0;
		String[] dataForCmb = new String[countGroups()];
		ReadGroupDaoImpl myReader = new ReadGroupDaoImpl();
		List<Group> groupList = myReader.showGroups();
		for (Group myGroups : groupList) {
			dataForCmb[containMe]=myGroups.getGroupName();
			containMe++;
		}
		return dataForCmb;
	}

	public int countGroups(){
		int myCount =0;
		ReadGroupDaoImpl myReader = new ReadGroupDaoImpl();
		List<Group> groupList = myReader.showGroups();
		for (Group myGroups : groupList) {
			myGroups.getGroupName();
			myCount++;
		}
		return myCount;
	}

	public List<Item> getSelectedItem(){
		Item selectedItems = new Item();
		DefaultTableModel dtm = (DefaultTableModel) tblInventory.getModel();
		List<Item> borrowedItems = new ArrayList<Item>();
		if(tblInventory.getSelectedRow() == -1){
			JOptionPane.showMessageDialog(null,"Please select item!");
		}else{

			String itemID  =dtm.getValueAt(tblInventory.getSelectedRow(), 0).toString();
			String itemDes = dtm.getValueAt(tblInventory.getSelectedRow(), 1).toString();
			int quantity = (int) dtm.getValueAt(tblInventory.getSelectedRow(), 2);
			int instock = (int)dtm.getValueAt(tblInventory.getSelectedRow(), 3);
			int outstock= (int)dtm.getValueAt(tblInventory.getSelectedRow(), 4);
			selectedItems.setItemID(itemID);
			selectedItems.setItemDes(itemDes);
			selectedItems.setQuantity(quantity);
			selectedItems.setInstock(instock);
			selectedItems.setOutstock(outstock);
			borrowedItems.add(selectedItems);
		}
		return borrowedItems;
	}

	public Group getSelectedMember(){
		Group selectedMember = new Group();
		DefaultTableModel dtm = (DefaultTableModel) tblMembers.getModel();
		if(tblMembers.getSelectedRow() == -1){
			wasAbleToChooseALeader = false;
			JOptionPane.showMessageDialog(null, "Please select a group leader");
			tblMembers.setBackground(Color.red);
		}else{
			wasAbleToChooseALeader = true;
			String studentID = dtm.getValueAt(tblMembers.getSelectedRow(), 0).toString();
			String studentName= dtm.getValueAt(tblMembers.getSelectedRow(), 1).toString();
			selectedMember.setStudentId(studentID);
			selectedMember.setGroupLeader(studentName);
			selectedMember.setProfessor(txtUProf.getText());
			selectedMember.setGroupID(grpId);

		}	
		return selectedMember;
	}


	public Item getQuantity(){



		Item updateInventory = new Item();
		DefaultTableModel dtm = (DefaultTableModel) tblItemBorrowed.getModel();
		ReadAllItemsDaoImpl myInventoryItemsDaoImpl = new ReadAllItemsDaoImpl();


		String[] idArray = new String[dtm.getRowCount()];
		String itemID;
		List<Item> myItemList;
		for(int x = 0 ; x <dtm.getRowCount(); x++){

			selectedQuantity = new Item();
			selectedQuantity.setBorrower((String) cmbGroups.getSelectedItem());
			selectedQuantity.setItemID( dtm.getValueAt(x, 0).toString());
			selectedQuantity.setItemsToBeBorrowed(Integer.parseInt( dtm.getValueAt(x, 2).toString())); 
			selectedQuantity.setItemDes( dtm.getValueAt(x, 1).toString());
			System.out.println("Yung mga items: " +selectedQuantity.getItemDes());
			idArray[x] = selectedQuantity.getItemID();
			itemID =selectedQuantity.getItemID();

			borrowedItems.add(selectedQuantity);

			int quantityOfItemsToBeBorrowed = selectedQuantity.getItemsToBeBorrowed();
			myItemList =  myInventoryItemsDaoImpl.showEverything();

			for (Item myNewItems : myItemList) {

				if(itemID.equals(myNewItems.getItemID())){
					if(quantityOfItemsToBeBorrowed> myNewItems.getInstock()){
						JOptionPane.showMessageDialog(null,"Sorry, the quantity that you typed in is greater than the number of instocks!");
						break;
					}else{
						int	quantity =myNewItems.getQuantity();
						int	instock1 = myNewItems.getInstock();
						int	outstock = myNewItems.getOutstock();
						int instock;

						if(instock1 == 0){

							instock = quantity- quantityOfItemsToBeBorrowed; 
							outstock = quantity - instock;
							updateInventory.setInstock(instock);
							updateInventory.setOutstock(outstock);
							updateInventory.setItemID(selectedQuantity.getItemID());
							UpdateInventoryItemsDao myInventoryItemsDao = new UpdateInventoryItemsDaoImpl();
							myInventoryItemsDao.updateInventory(updateInventory);
						}else{
							instock = instock1- quantityOfItemsToBeBorrowed;
							outstock =quantity - instock;
							updateInventory.setInstock(instock);
							updateInventory.setOutstock(outstock);
							updateInventory.setItemID(selectedQuantity.getItemID());
							updateInventory.setBorrower(selectedGroup);
							UpdateInventoryItemsDao myInventoryItemsDao = new UpdateInventoryItemsDaoImpl();
							myInventoryItemsDao.updateInventory(updateInventory);
						}
					}
				}

			}


		}


		return selectedQuantity;

	}
}