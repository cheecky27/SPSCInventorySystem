package app.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import app.util.CurrentDate;
import app.util.Time;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.Window.Type;


public class Welcome {

	private JFrame frmWelcome;
	JPanel pnlMenu;
	

	/**
	 * Launch the application.
	 */
	public  void welcome() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Welcome() {
		initialize();

	}  


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWelcome = new JFrame();
		frmWelcome.setBackground(Color.WHITE);
		frmWelcome.setUndecorated(true);
		frmWelcome.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				frmWelcome.setDefaultCloseOperation(frmWelcome.DO_NOTHING_ON_CLOSE);
				JOptionPane.showMessageDialog(null,"Please logout!");
			}
		});
		
		
		
		frmWelcome.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmWelcome.getContentPane().setBackground(new Color(255, 255, 255));
		frmWelcome.getContentPane().setForeground(new Color(255, 255, 255));
	
		frmWelcome.getContentPane().setPreferredSize(new Dimension(1365, 780));
		frmWelcome.setVisible(true);

		new JMenus(frmWelcome);



		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		new CompoundBorder(line, margin);

		new LineBorder(Color.BLACK);
		new EmptyBorder(5, 15, 5, 15);
		new CompoundBorder(line, margin);
		new LineBorder(Color.BLACK);
		new EmptyBorder(5, 15, 5, 15);
		new CompoundBorder(line, margin);
		frmWelcome.getContentPane().setLayout(null);
		
				final JLabel lbldate = new JLabel("");
				lbldate.setBounds(917, 623, 480, 75);
				lbldate.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 35));
				lbldate.addPropertyChangeListener(new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent evt) {
						CurrentDate myCurrent = new CurrentDate();
						lbldate.setText(myCurrent.showCompleteDateToday());
					}
				});
				frmWelcome.getContentPane().add(lbldate);
		
				JLabel lbltime = new JLabel("");
				lbltime.setBounds(1053, 688, 259, 49);
				lbltime.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 30));
				frmWelcome.getContentPane().add(lbltime);
				new Time(lbltime);

		JPanel pnlMenu = new JPanel();
		pnlMenu.setBounds(-15, -24, 1430, 967);
		pnlMenu.setBackground(new Color(0, 153, 102));
		frmWelcome.getContentPane().add(pnlMenu);
		pnlMenu.setLayout(null);
		
		JLabel lblHome = new JLabel("Home!");
		lblHome.setForeground(new Color(255, 255, 255));
		lblHome.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 37));
		lblHome.setBounds(280, 64, 121, 37);
		pnlMenu.add(lblHome);
		
		JLabel lblWelcomeHome = new JLabel("Welcome ");
		lblWelcomeHome.setForeground(new Color(255, 255, 255));
		lblWelcomeHome.setFont(new Font("Segoe UI", Font.BOLD, 47));
		lblWelcomeHome.setBounds(65, 53, 356, 49);
		pnlMenu.add(lblWelcomeHome);
		
		JLabel lblTree = new JLabel("");
		lblTree.setIcon(new ImageIcon(Welcome.class.getResource("/app/resourceLatest/tree.png")));
		lblTree.setBounds(538, 134, 292, 520);
		pnlMenu.add(lblTree);
		
	
		frmWelcome.pack();
	}
}
