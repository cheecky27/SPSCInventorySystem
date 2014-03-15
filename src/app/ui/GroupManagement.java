package app.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;



import app.dao.allitems.impl.ReadAllItemsDaoImpl;
import app.dao.borroweditems.ReadBorrowedItemDao;
import app.dao.borroweditems.impl.ReadBorrowedItemsDaoImpl;
import app.dao.group.DeleteGroupDao;
import app.dao.group.ReadGroupsDao;
import app.dao.group.WriteGroupDao;
import app.dao.group.impl.DeleteGroupDaoImpl;
import app.dao.group.impl.WriteGroupDaoImpl;
import app.dao.group.impl.ReadGroupDaoImpl;
import app.dao.user.ReadUserDao;
import app.model.Group;
import app.model.Item;
import app.service.getvaluefromtxtfields.impl.JTxtFieldvalueSetter;
import app.user.dao.impl.ReadUserDaoImpl;
import app.util.CenterWindow;
import app.util.CompleteFrame;
import app.util.TextBoxCreator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;
import javax.swing.border.BevelBorder;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JToggleButton;

public class GroupManagement {

	private JFrame frmGM;
	private JTable tblGroup;
	private JTextField txtProf;
	private JTextField txtGrpName;
	private JTextField txtSearchGrp;
	private JTable tblGrpItemsToBeReturned;
	private String[] studIDFromUser= null;

	private boolean isValid;
	private boolean isDone;
	private GroupManagement groupManagement = null;
	int counter;

	private JPanel pnlAddGrp;

	private JPanel pnlGroupView ;
	int x;
	int y;
	int count;
	int lblCounter ;
	int countInWindowOpen;
	JPanel pnlTxtTxt;
	JScrollPane scrpTxtTxt;
	JLabel lblAddNewTF;
	JLabel lblRemoveTf;
	Collection<JTextField> myListOfStudentName = new ArrayList<JTextField>();
	Collection<JTextField> myListOfStudentNumber = new ArrayList<JTextField>();

	private JPanel pnlReturn;
	Collection<JTextField> myListOfTxtName = new ArrayList<JTextField>(); 
	Collection<JTextField> myListOfTxtNum = new ArrayList<JTextField>(); 
	JLabel lblSearchForA;
	JTextField eto ;
	JTextField eto1;
	private int countMe = 1;
	private int id;

	/**
	 * Launch the application.
	 */
	public  void groupManagement() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					GroupManagement window = new GroupManagement();
					window.frmGM.setVisible(true);




				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GroupManagement() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		CenterWindow myCenterWindow = new CenterWindow();
		frmGM = new JFrame();
		myCenterWindow.doCentreWindow(frmGM);
		frmGM.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				frmGM.setDefaultCloseOperation(frmGM.DO_NOTHING_ON_CLOSE);
				JOptionPane.showMessageDialog(null,"Please logout!");
			}
		});

		frmGM.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmGM.getContentPane().setBackground(new Color(255, 255, 255));
		frmGM.getContentPane().setForeground(new Color(255, 255, 255));
		frmGM.getContentPane().setPreferredSize(new Dimension(1365, 747));
		frmGM.getContentPane().setLayout(null);

		new JMenus(frmGM);


		frmGM.setLocationByPlatform(true);

		pnlReturn = new JPanel();
		pnlReturn.setBackground(new Color(102, 205, 170));
		pnlReturn.setBounds(812, 177, 503, 515);
		frmGM.getContentPane().add(pnlReturn);
		pnlReturn.setLayout(null);

		JLabel lblPendingItemsTo = new JLabel("Pending items to be returned:");
		lblPendingItemsTo.setBounds(158, 5, 212, 20);
		pnlReturn.add(lblPendingItemsTo);
		lblPendingItemsTo.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JScrollPane scrllpGrpItemsToBeReturned = new JScrollPane();
		scrllpGrpItemsToBeReturned.setBounds(10, 36, 483, 427);
		pnlReturn.add(scrllpGrpItemsToBeReturned);

		tblGrpItemsToBeReturned = new JTable();
		tblGrpItemsToBeReturned.setBackground(Color.WHITE);
		tblGrpItemsToBeReturned.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Return", "Quantity(Returned)", "ItemID", "Item Description", "Quantity"
				}
				));
		scrllpGrpItemsToBeReturned.setViewportView(tblGrpItemsToBeReturned);

		JButton btnUpdateGroup = new JButton("Update Group");
		btnUpdateGroup.setBounds(137, 468, 114, 36);
		pnlReturn.add(btnUpdateGroup);

		JButton btnDeleteGroup = new JButton("Delete Group");
		btnDeleteGroup.setBounds(273, 468, 114, 36);
		pnlReturn.add(btnDeleteGroup);

		pnlAddGrp = new JPanel();
		pnlAddGrp.setBounds(84, 176, 1233, 516);
		pnlAddGrp.setVisible(false);
		frmGM.getContentPane().add(pnlAddGrp);
		pnlAddGrp.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				countInWindowOpen = 1;
			}
		});
		pnlAddGrp.setBackground(new Color(102, 205, 170));
		pnlAddGrp.setLayout(null);

		JLabel lblProfessor = new JLabel("Professor:");
		lblProfessor.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lblProfessor.setBounds(10, 50, 96, 27);
		pnlAddGrp.add(lblProfessor);

		JLabel lblGroupName = new JLabel("Group Name:");
		lblGroupName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lblGroupName.setBounds(305, 43, 122, 46);
		pnlAddGrp.add(lblGroupName);

		JLabel lblGroupMembers = new JLabel("Group Members:");
		lblGroupMembers.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lblGroupMembers.setBounds(10, 100, 152, 46);
		pnlAddGrp.add(lblGroupMembers);

		txtProf = new JTextField();
		txtProf.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtProf.setBounds(98, 48, 189, 34);
		pnlAddGrp.add(txtProf);
		txtProf.setColumns(10);

		txtGrpName = new JTextField();
		txtGrpName.setBounds(437, 43, 208, 34);
		pnlAddGrp.add(txtGrpName);
		txtGrpName.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 95, 689, 2);
		pnlAddGrp.add(separator);

		JLabel lblStudentNumber = new JLabel("Student Number");
		lblStudentNumber.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblStudentNumber.setBounds(123, 145, 119, 24);
		pnlAddGrp.add(lblStudentNumber);

		JLabel lblStudentName = new JLabel("Student Name");
		lblStudentName.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblStudentName.setBounds(318, 144, 109, 27);
		pnlAddGrp.add(lblStudentName);

		JLabel lblAddGroup = new JLabel();
		lblAddGroup.setIcon(new ImageIcon(GroupManagement.class.getResource("/app/resources/plusbig.png")));
		lblAddGroup.setBounds(978, 321, 72, 81);
		pnlAddGrp.add(lblAddGroup);
		lblAddGroup.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {

				isDone = true;
				JTxtFieldvalueSetter myFieldvalueSetter = new JTxtFieldvalueSetter();
				for(JTextField myTxt1 : myListOfTxtNum){
					eto1 = myTxt1;

				}
				myFieldvalueSetter.doGetthevalueFromTF(myListOfTxtNum);
				for(JTextField myTxt : myListOfTxtName){
					eto = myTxt;

				}
				myFieldvalueSetter.doGetValueFromTxtName(myListOfTxtName);
				//setGroupMembers();

			}

		});

		JButton cancel = new JButton("Cancel");
		cancel.setBounds(1000, 400, 119, 52);
		pnlAddGrp.add(cancel);


		scrpTxtTxt = new JScrollPane();
		scrpTxtTxt.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrpTxtTxt.setBounds(54, 180, 567, 283);
		pnlAddGrp.add(scrpTxtTxt);
		pnlTxtTxt = new JPanel();

		CompleteFrame myCompleteFrame = new CompleteFrame(frmGM);
		pnlTxtTxt =myCompleteFrame.doReturnPanel();



		scrpTxtTxt.setViewportView(pnlTxtTxt);



		JLabel lblSwitch1 = new JLabel("");
		lblSwitch1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSearchForA.setVisible(false);
				txtSearchGrp.setVisible(false);
				pnlReturn.setVisible(false);
				pnlGroupView.setVisible(false);
				pnlAddGrp.setVisible(true);


			}
		});
		lblSwitch1.setIcon(new ImageIcon(GroupManagement.class.getResource("/app/resources/arrow2.png")));
		lblSwitch1.setBounds(637, 678, 58, 69);
		frmGM.getContentPane().add(lblSwitch1);

		pnlGroupView = new JPanel();
		pnlGroupView.setBounds(47, 166, 705, 484);
		frmGM.getContentPane().add(pnlGroupView);
		pnlGroupView.setBackground(new Color(102, 205, 170));
		pnlGroupView.setLayout(null);

		JScrollPane scrlpGrp = new JScrollPane();
		scrlpGrp.setBounds(10, 97, 689, 362);
		pnlGroupView.add(scrlpGrp);

		tblGroup = new JTable();
		tblGroup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {


			}
		});
		tblGroup.setBackground(Color.WHITE);
		tblGroup.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Group ID", "Professor", "Group Name", "Group Members"
				}
				));
		tblGroup.getColumnModel().getColumn(3).setPreferredWidth(136);
		scrlpGrp.setViewportView(tblGroup);


		txtSearchGrp = new JTextField();
		txtSearchGrp.setBounds(160, 45, 215, 34);
		pnlGroupView.add(txtSearchGrp);
		txtSearchGrp.setColumns(10);


		lblSearchForA = new JLabel("Search for a group :");
		lblSearchForA.setBounds(10, 41, 171, 34);
		pnlGroupView.add(lblSearchForA);
		lblSearchForA.setFont(new Font("Tahoma", Font.PLAIN, 18));

		final JLabel lblSwitch = new JLabel("");
		lblSwitch.setBounds(574, 678, 64, 69);
		frmGM.getContentPane().add(lblSwitch);
		lblSwitch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblSearchForA.setVisible(true);
				txtSearchGrp.setVisible(true);
				pnlGroupView.setVisible(true);
				pnlAddGrp.setVisible(false);
				pnlReturn.setVisible(true);
			}
		});
		lblSwitch.setIcon(new ImageIcon(GroupManagement.class.getResource("/app/resources/arrow1.png")));

		JLabel label = new JLabel("");
		label.setBounds(659, 667, 64, 69);
		frmGM.getContentPane().add(label);

		JLabel lblBG = new JLabel("");
		lblBG.setIcon(new ImageIcon(GroupManagement.class.getResource("/app/resourceLatest/GroupManagement.jpg")));
		lblBG.setBounds(0, 0, 1365, 747);
		frmGM.getContentPane().add(lblBG);
		btnDeleteGroup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel dtm = (DefaultTableModel) tblGroup.getModel();
				if(tblGroup.getSelectedRow()==-1){
					JOptionPane.showMessageDialog(null, "No Item to Remove", "No Selected Item", JOptionPane.ERROR_MESSAGE);
				}else{
					String sagot =JOptionPane.showInputDialog(null,"Please enter password to delete an item");

					ReadUserDao myReadUserDao = new ReadUserDaoImpl();
					if(sagot.equals(myReadUserDao.getUserInfo().getPassWord())){
						
							id = Integer.parseInt(dtm.getValueAt(tblGroup.getSelectedRow(), 0).toString());
						DeleteGroupDao delete = new DeleteGroupDaoImpl();
						delete.deleteGroup(id);
						System.out.println("nadelete?"+id);
						showGroupTable();
						
					}else{
						JOptionPane.showMessageDialog(null,"WRONG PASSWORD! YOU LITTLE INTRUDER!");
					}
				}
		
				}
			//}
		});
		btnUpdateGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(tblGroup.getSelectedRow() == -1){
					JOptionPane.showMessageDialog(null, "No Group to Update", "No Selected Group", JOptionPane.ERROR_MESSAGE);
				}else{
					UpdateGroup updateGroup = new UpdateGroup(selectedGroup(), groupManagement);
					updateGroup.setVisible(true);
					showGroupTable();
				}
				
			}
		});
		frmGM.pack();
		frmGM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		showGroupTable();
		//doShowBorrowedItems();
		frmGM.pack();
	}

	public Group setGroup(){
		Group myGroup = new Group();
		myGroup.setProfessor(txtProf.getText());
		myGroup.setGroupName(txtGrpName.getText());
		return myGroup;
	}
	public void showGroupTable(){
		DefaultTableModel dtm = (DefaultTableModel) tblGroup.getModel();
		dtm.getDataVector().removeAllElements();
		ReadGroupDaoImpl myReader = new ReadGroupDaoImpl();
		List<Group> groupList = myReader.showGroups();
		for (Group myGroups : groupList) {
			Object[] rowData = new Object[4];
			rowData[0] =myGroups.getGroupID();
			rowData[1] =myGroups.getProfessor();
			rowData[2] =myGroups.getGroupName();
			rowData[3] =myGroups.getGroupMembers();
			dtm.addRow(rowData);
		}
		tblGroup.updateUI();
	}



	public Group selectedGroup(){
		Group myGroup = new Group();
		DefaultTableModel dtm = (DefaultTableModel) tblGroup.getModel();
		String id = dtm.getValueAt(tblGroup.getSelectedRow(), 0).toString();
		String prof  = dtm.getValueAt(tblGroup.getSelectedRow(), 1).toString();
		String grpName = dtm.getValueAt(tblGroup.getSelectedRow(), 2).toString();
		String grpMembers= dtm.getValueAt(tblGroup.getSelectedRow(), 3).toString();

		myGroup.setProfessor(prof);
		myGroup.setGroupName(grpName);
		myGroup.setGroupID(Integer.parseInt(id));
		myGroup.setGroupMembers(grpMembers);
		return myGroup;
	}


	public void doGetTheGroupMembers(ArrayList<String> myGroups){
		Group myGroup2 = new Group();
		ReadGroupDaoImpl myReadGroupsDao = new ReadGroupDaoImpl();
		List<Group> myGroupFromDB= myReadGroupsDao.showGroups();


		for(String myGroup: myGroups){
			//checks if the database is empty
			if(myGroupFromDB.isEmpty()){
				isValid = false;

			}else{
				studIDFromUser	=myGroup.split("\n");
				//tests it the student id has a duplicate in the textfield
				for(int x = 0 ; x<studIDFromUser.length ; x+=2 ){
					for(int r = 1 ; r<studIDFromUser.length ; r+=2 ){
						countMe+=2;
						if(studIDFromUser[x].equals(studIDFromUser[r])){
							System.out.println("x" + studIDFromUser[x]+ "r"+ studIDFromUser[r]);
							JOptionPane.showMessageDialog(null,"Duplicate student number!");
							isValid = true;
						}else{
							isValid = false;

						}

					}

				}
				//Checks if the studentid has a duplicate in the DB
				for(Group myGroupp : myGroupFromDB){
					for(int x = 0 ; x<studIDFromUser.length ; x+=2 ){
						countMe+=2;
						if(myGroupp.getStudentId().equals(studIDFromUser[x])){
							JOptionPane.showMessageDialog(null,"Duplicate student number! Same student is already a part of another group.");
							isValid = false;
						}else{
							isValid = true;

						}
					}

				}



			}

		}


	}

	public void doShowBorrowedItems(){
		ReadBorrowedItemDao myBorrowedItemDao = new ReadBorrowedItemsDaoImpl();
		List<Item> myItem=myBorrowedItemDao.showItemsToBeReturned(selectedGroup().getGroupID());
		List<Item> myItems=myBorrowedItemDao.showQuantityToBeReturned();

		DefaultTableModel dtm = (DefaultTableModel) tblGrpItemsToBeReturned.getModel();
		dtm.getDataVector().removeAllElements();
		ReadAllItemsDaoImpl myReader = new ReadAllItemsDaoImpl();

		for (Item myBorrowedItems : myItem) {
			Object[] rowData = new Object[2];
			rowData[2] =myBorrowedItems.getItemID();
			rowData[3] =myBorrowedItems.getItemDes();

			dtm.addRow(rowData);

		}
		tblGrpItemsToBeReturned.updateUI();

	}
}

