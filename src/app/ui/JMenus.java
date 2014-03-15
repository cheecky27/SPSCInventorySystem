package app.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.WindowConstants;

import app.model.User;
import app.util.JMenusss;



import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class JMenus {
	private JFrame menuu;
	private SecurityQuestion securityQuestion;
	private User user;

	private JMenu mnAccount;

	public JMenus(JFrame menuu) {
		this.menuu = menuu;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {



		final JLabel lblHome = new JLabel("");
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Welcome myWelcome = new Welcome();
				menuu.dispose();
			}
		});
		lblHome.setIcon(new ImageIcon(JMenus.class.getResource("/app/resources/home-icon.png")));
		lblHome.setBounds(780, 4, 88, 83);
		menuu.getContentPane().add(lblHome);

		final JLabel lblItem = new JLabel("");
		lblItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				ItemManagement myItemManagement = new ItemManagement();
				myItemManagement.ItemManagement();
				menuu.dispose();
			}
		});
		lblItem.setIcon(new ImageIcon(JMenus.class.getResource("/app/resources/items.png")));
		lblItem.setBounds(860, 4, 88, 83);
		menuu.getContentPane().add(lblItem);

		final JLabel lblGroupManagement = new JLabel("");
		lblGroupManagement.setIcon(new ImageIcon(JMenus.class.getResource("/app/resources/group11.png")));
		lblGroupManagement.setBounds(940, 4, 88, 83);
		menuu.getContentPane().add(lblGroupManagement);

		lblGroupManagement.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				GroupManagement myGroupManagement = new GroupManagement();
				myGroupManagement.groupManagement();

				menuu.dispose();
			}
		});


		final JLabel lblInventory = new JLabel("");
		lblInventory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				IOStock myInventory = new IOStock();
				myInventory.InventoryWindow();
				menuu.dispose();
			}
		});
		lblInventory.setIcon(new ImageIcon(JMenus.class.getResource("/app/resources/IO.png")));
		lblInventory.setBounds(1020, 4, 88, 83);
		menuu.getContentPane().add(lblInventory);

		final JLabel lblLogout = new JLabel("");
		lblLogout.setIcon(new ImageIcon(JMenus.class.getResource("/app/resources/lock.png")));
		lblLogout.setBounds(1180, 4, 120, 83);
		menuu.getContentPane().add(lblLogout);
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

				int selectedOption = JOptionPane.showConfirmDialog(null,"You are about to logout, are you sure?","Choose",JOptionPane.YES_NO_OPTION); 
				if (selectedOption == JOptionPane.YES_OPTION) {
					Login window = new Login();
					window.frmLogin.setVisible(true);
					menuu.dispose();
				}
			}

		});

		JMenuBar mnbMenu = new JMenuBar();
		mnbMenu.setBorderPainted(false);
		mnbMenu.setBackground(new Color(0, 153, 102));
	//	mnbMenu.setBackground(Color.WHITE);
	//	mnbMenu.setBounds(1100, 6, 45, 55);


		final JMenu mnAccount = new JMenu();
	//	mnAccount.setBackground(Color.WHITE);
	//	mnAccount.setForeground(Color.WHITE);
		mnAccount.setIcon(new ImageIcon(JMenus.class.getResource("/app/resources/Settings-icon.png")));
		mnbMenu.setBounds(1100, 6, 65, 70);
		mnbMenu.add(mnAccount);
		
		
		menuu.getContentPane().add(mnbMenu);


		JMenuItem mntmChangeUsername = new JMenuItem("Change Username");
		mntmChangeUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				UpdateUserName updateUsername = new UpdateUserName(user);
				updateUsername.setVisible(true);


			}
		});

		//mntmChangeUsername.setBackground(Color.WHITE);
		mnAccount.add(mntmChangeUsername);

		JMenuItem mntmChangePassword = new JMenuItem("Change Password");
		mntmChangePassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				ChangeUsername changeUsername = new ChangeUsername(menuu);
				changeUsername.changeAcc();

			}
		});
		mntmChangePassword.setBackground(Color.WHITE);
		mnAccount.add(mntmChangePassword);

		JMenuItem mntmChangeSecurityQuestion = new JMenuItem("Change Security Question");
		mntmChangeSecurityQuestion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				ChangeSecurityQuestion changeSecurity = new ChangeSecurityQuestion(user, securityQuestion);
				changeSecurity.setVisible(true);
				changeSecurity.setLocationRelativeTo(null);
				changeSecurity.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				changeSecurity.setAlwaysOnTop(true);

			}
		});
		mntmChangeSecurityQuestion.setBackground(Color.WHITE);
		mnAccount.add(mntmChangeSecurityQuestion);






	}
}


