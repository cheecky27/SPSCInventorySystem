package app.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;


import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.border.LineBorder;

import java.awt.Font;




import app.dao.user.ReadUserDao;

import app.user.dao.impl.ReadUserDaoImpl;
import app.util.CenterWindow;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Login {

	JFrame frmLogin;
	private JTextField txtuserName;
	private JPasswordField txtPass;

	int	subok=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {


					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setExtendedState(Frame.MAXIMIZED_BOTH); 
		frmLogin.getContentPane().setBackground(Color.WHITE);
		frmLogin.getContentPane().setForeground(new Color(255, 255, 255));
		frmLogin.getContentPane().setPreferredSize(new Dimension(1365, 747));

		frmLogin.setVisible(true);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frmLogin.setLocationRelativeTo(null);
		CenterWindow myCenterWindoww = new CenterWindow();
		myCenterWindoww.doFullScreen(frmLogin);
		frmLogin.getContentPane().setLayout(null);

		Icon firstPicLogin= new ImageIcon(Login.class.getResource("/app/resources/okbutton.jpg"));
		Icon secPicLogin= new ImageIcon(Login.class.getResource("/app/resources/okbutton2.jpg"));

		JLabel lblForgotPassword = new JLabel("Forgot password?");
		lblForgotPassword.setForeground(Color.GRAY);
		lblForgotPassword.setBounds(666, 457, 114, 18);
		frmLogin.getContentPane().add(lblForgotPassword);

		JLabel lblClickHere = new JLabel("click here");
		lblClickHere.setBounds(790, 457, 56, 18);
		frmLogin.getContentPane().add(lblClickHere);
		lblClickHere.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override

			public void mouseClicked(MouseEvent arg0) {
				SecurityQuestion myQuestion = new SecurityQuestion(frmLogin);
				myQuestion.securityQuestion();
			}

		});

		lblClickHere.setForeground(new Color(0, 128, 0));

		txtPass = new JPasswordField();
		txtPass.setHorizontalAlignment(SwingConstants.CENTER);
		txtPass.setBounds(576, 300, 332, 58);
		frmLogin.getContentPane().add(txtPass);
		txtPass.setForeground(new Color(0, 128, 0));
		txtPass.setFont(new Font("Dialog", Font.PLAIN, 20));

		txtuserName = new JTextField();
		txtuserName.setHorizontalAlignment(SwingConstants.CENTER);
		txtuserName.setBounds(576, 221, 332, 58);
		frmLogin.getContentPane().add(txtuserName);
		txtuserName.setForeground(new Color(0, 128, 0));
		txtuserName.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtuserName.setColumns(10);

		JLabel lblBG = new JLabel("");
		lblBG.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				ReadUserDao myReadUserDao = new ReadUserDaoImpl();
				if(txtuserName.getText().equals(myReadUserDao.getUserInfo().getUserName()) && txtPass.getText().equals(myReadUserDao.getUserInfo().getPassWord())){
					frmLogin.dispose();
					new Welcome();
				}else{
					JOptionPane.showMessageDialog(null,"Sorry! Wrong username or password!!");
				}
			}
	
	});
		
				JLabel lblLogin = new JLabel("");
				lblLogin.setIcon(new ImageIcon(Login.class.getResource("/app/resourceLatest/btnLogin.jpg")));
				lblLogin.setBounds(584, 382, 367, 48);
				frmLogin.getContentPane().add(lblLogin);
		lblBG.setIcon(new ImageIcon(Login.class.getResource("/app/resourceLatest/LoginJ.jpg")));
		lblBG.setBounds(0, 0, 1365, 747);
		frmLogin.getContentPane().add(lblBG);
		txtPass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ReadUserDao myReadUserDao = new ReadUserDaoImpl();
				if(txtuserName.getText().equals(myReadUserDao.getUserInfo().getUserName()) && txtPass.getText().equals(myReadUserDao.getUserInfo().getPassWord())){
					frmLogin.dispose();
					new Welcome();
				}else{
					JOptionPane.showMessageDialog(null,"Sorry! Wrong username or password!!");
				}
			}
		});
		frmLogin.pack();
}
}