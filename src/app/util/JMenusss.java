package app.util;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseMotionAdapter;

public class JMenusss {

	private JFrame frame;
	JLabel lblItem;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JMenusss window = new JMenusss();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JMenusss() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JSeparator sptrJmenus = new JSeparator();
		sptrJmenus.setBackground(Color.WHITE);
		sptrJmenus.setForeground(Color.LIGHT_GRAY);
		sptrJmenus.setBounds(-26, 99, 1000, 12);
		frame.getContentPane().add(sptrJmenus);
		
		final JLabel lblHome = new JLabel("");
		lblHome.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				lblHome.setIcon(new ImageIcon(JMenusss.class.getResource("/app/resourcegif/home.gif")));
			}
		});
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblHome.setIcon(new ImageIcon(JMenusss.class.getResource("/app/resources/hoom.png")));
				lblItem.setVisible(false);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblHome.setIcon(new ImageIcon(JMenusss.class.getResource("/app/resources/Basic-Home-icon.png")));
				lblItem.setVisible(true);
			}
		});
		lblHome.setIcon(new ImageIcon(JMenusss.class.getResource("/app/resources/Basic-Home-icon.png")));
		lblHome.setBounds(10, 11, 88, 83);
		frame.getContentPane().add(lblHome);
		
		lblItem = new JLabel("");
		lblItem.setIcon(new ImageIcon(JMenusss.class.getResource("/app/resources/item.png")));
		lblItem.setBounds(99, 11, 106, 100);
		frame.getContentPane().add(lblItem);
		
		JLabel lblGroupManagement = new JLabel("");
		lblGroupManagement.setIcon(new ImageIcon(JMenusss.class.getResource("/app/resources/group.png")));
		lblGroupManagement.setBounds(194, 11, 80, 89);
		frame.getContentPane().add(lblGroupManagement);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(JMenusss.class.getResource("/app/resources/inventoryf.png")));
		label.setBounds(284, 11, 80, 89);
		frame.getContentPane().add(label);
		
		JLabel lblLogout = new JLabel("");
		lblLogout.setIcon(new ImageIcon(JMenusss.class.getResource("/app/resources/lock.png")));
		lblLogout.setBounds(903, 11, 71, 77);
		frame.getContentPane().add(lblLogout);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		menuBar.setBounds(363, 11, 97, 83);
		frame.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("");
		mnNewMenu.setBackground(Color.WHITE);
		mnNewMenu.setIcon(new ImageIcon(JMenusss.class.getResource("/app/resources/settings.png")));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmChangeUsername = new JMenuItem("Change Username");
		mntmChangeUsername.setBackground(Color.WHITE);
		mnNewMenu.add(mntmChangeUsername);
		
		JMenuItem mntmChangePassword = new JMenuItem("Change Password");
		mntmChangePassword.setBackground(Color.WHITE);
		mnNewMenu.add(mntmChangePassword);
		
		JMenuItem mntmChangeSecurityQuestion = new JMenuItem("Change Security Question");
		mntmChangeSecurityQuestion.setBackground(Color.WHITE);
		mnNewMenu.add(mntmChangeSecurityQuestion);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBounds(373, 177, 116, 77);
		frame.getContentPane().add(menuBar_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		menuBar_1.add(mntmNewMenuItem);
		
		
		
		final JPanel panel = new JPanel();
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
			}
		});
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println("Pumasok sa panel"+ e);
			}
		});
		
		
		
		panel.setBounds(64, 153, 287, 296);
		frame.getContentPane().add(panel);
		
		JLabel lblRosselle = new JLabel("rosselle");
		panel.add(lblRosselle);
		
		JLabel lblLbltry = new JLabel("");
		lblLbltry.setIcon(new ImageIcon(JMenusss.class.getResource("/app/resourcegif/homeasdasdasdasdasdasda.gif")));
		lblLbltry.setBounds(566, 193, 263, 157);
		frame.getContentPane().add(lblLbltry);
		frame.setBounds(100, 100, 1000, 544);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
