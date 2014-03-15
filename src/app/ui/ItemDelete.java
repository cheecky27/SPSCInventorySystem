package app.ui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import app.dao.archive.WriteIntoArchiveDao;
import app.dao.archive.impl.WriteIntoArchiveDaoImpl;
import app.dao.user.ReadUserDao;
import app.model.Item;
import app.user.dao.impl.ReadUserDaoImpl;
import app.util.CurrentDate;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ItemDelete extends JDialog {
	/**
	 * 
	 */

	private JPasswordField pwdPasswordconfirm;
	private JLabel lblWrongPassword; 
	private Item myItemToRemove;
	private JLabel lblGoToArchive;
	private JLabel lblConfirmDeletion ;
	private JLabel lblOk;
	private JPanel pnlConfirmDeletion;
	private JLabel lblCancel;
	private JLabel lblSuccessfull;
private ItemManagement itemManagement;
	/**
	 * Launch the application.
	 */
	public  void confirmDelete() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemDelete dialog = new ItemDelete(myItemToRemove, itemManagement);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public ItemDelete(final Item myItemToRemove, final ItemManagement itemManagement) {
		this.itemManagement = itemManagement;
		this.myItemToRemove = myItemToRemove;
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);

		final JLabel lblPleaseEnterPassword = new JLabel("Please enter password to move item to archive:");
		lblPleaseEnterPassword.setForeground(new Color(255, 255, 255));
		lblPleaseEnterPassword.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		lblPleaseEnterPassword.setBounds(31, 11, 268, 34);
		getContentPane().add(lblPleaseEnterPassword);


		pnlConfirmDeletion = new JPanel();
		pnlConfirmDeletion.setBounds(0, 0, 370, 270);
		pnlConfirmDeletion.setVisible(false);
		pnlConfirmDeletion.setBackground(new Color(0, 153, 102));
		getContentPane().add(pnlConfirmDeletion);
		pnlConfirmDeletion.setLayout(null);

		lblSuccessfull = new JLabel("Succesfully Deleted Apparatus!");
		lblSuccessfull.setForeground(new Color(255, 255, 255));
		lblSuccessfull.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
		lblSuccessfull.setVisible(false);
		lblSuccessfull.setBounds(50,70,250,70);
		pnlConfirmDeletion.add(lblSuccessfull);

		pwdPasswordconfirm = new JPasswordField();
		pwdPasswordconfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ReadUserDao myReadUserDao = new ReadUserDaoImpl();
				if(pwdPasswordconfirm.getText().equals(myReadUserDao.getUserInfo().getPassWord())){
					CurrentDate myCurrentDate = new CurrentDate();
					myItemToRemove.setDateDeleted(myCurrentDate.intoTheDBDate());

					if(myItemToRemove.getInstock() > 0){
						setBounds(445, 140, 370, 270);
						pnlConfirmDeletion.setVisible(true);
						lblPleaseEnterPassword.setVisible(false);
						pwdPasswordconfirm.setVisible(false);
						lblGoToArchive.setVisible(false);

						lblConfirmDeletion = new JLabel("<html><center>    \t\t\t There are still remaining stocks,<br>if you delete this apparatus the inventory record <br>will be permanently deleted. <br> Do you wish to proceed?<center></html>");
						lblConfirmDeletion.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
						lblConfirmDeletion.setBounds(10, -20, 500, 170);
						lblConfirmDeletion.setVisible(true);
						pnlConfirmDeletion.add(lblConfirmDeletion);

						lblOk = new JLabel();
						lblOk.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent arg0) {

								WriteIntoArchiveDao myWriteIntoArchive = new WriteIntoArchiveDaoImpl();
								boolean wasWritten =myWriteIntoArchive.doWriteInArchive(myItemToRemove);

								if(wasWritten){
									lblConfirmDeletion.setVisible(false);
									lblCancel.setVisible(false);
									lblOk.setVisible(false);
									lblPleaseEnterPassword.setVisible(false);


									lblSuccessfull.setVisible(true);
									pnlConfirmDeletion.add(lblSuccessfull);
									itemManagement.showInventoryListTable();
								dispose();
									
								}

							}
						});
						lblOk.setVisible(true);
						lblOk.setIcon(new ImageIcon(Archive.class.getResource("/app/resources/check.png")));
						lblOk.setBounds(70,125,70,70);
						pnlConfirmDeletion.add(lblOk);

						lblCancel = new JLabel();
						lblCancel.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent arg0) {
								dispose();
							}
						});
						lblCancel.setVisible(true);
						lblCancel.setIcon(new ImageIcon(Archive.class.getResource("/app/resources/x.png")));
						lblCancel.setBounds(230,125,70,70);
						pnlConfirmDeletion.add(lblCancel);

					}else if(myItemToRemove.getInstock() == 0){
						WriteIntoArchiveDao myWriteIntoArchive = new WriteIntoArchiveDaoImpl();
						boolean wasWritten =myWriteIntoArchive.doWriteInArchive(myItemToRemove);

						if(wasWritten){
							setBounds(445, 140, 370, 270);
							pnlConfirmDeletion.setVisible(true);
							lblPleaseEnterPassword.setVisible(false);
							pwdPasswordconfirm.setVisible(false);
							lblGoToArchive.setVisible(false);
							pnlConfirmDeletion.setVisible(true);
							lblSuccessfull.setVisible(true);
							pnlConfirmDeletion.add(lblSuccessfull);
							itemManagement.showInventoryListTable();
						}
					}

				}else{
					lblWrongPassword.setVisible(true);
				}
			}
		});
		pwdPasswordconfirm.setBounds(63, 56, 193, 26);
		getContentPane().add(pwdPasswordconfirm);

		lblGoToArchive = new JLabel("Go to archive ->\r\n");
		lblGoToArchive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Archive myArchive = new Archive(itemManagement);
				dispose();
				myArchive.Archive();
			}
		});
		lblGoToArchive.setForeground(new Color(0, 128, 0));
		lblGoToArchive.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 11));
		lblGoToArchive.setBounds(206, 108, 93, 26);
		getContentPane().add(lblGoToArchive);

		lblWrongPassword = new JLabel("Wrong Password!");
		lblWrongPassword.setVisible(false);
		lblWrongPassword.setForeground(Color.RED);
		lblWrongPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblWrongPassword.setBounds(118, 95, 93, 14);
		getContentPane().add(lblWrongPassword);
		setBounds(500, 200, 337, 183);

	}
}
