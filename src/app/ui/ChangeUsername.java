package app.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import app.dao.user.ReadUserDao;
import app.dao.user.UpdateUserPasswordDao;
import app.user.dao.impl.ReadUserDaoImpl;
import app.user.dao.impl.UpdateUserPasswordDaoImpl;
import app.util.CenterWindow;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.WindowConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class ChangeUsername {

	private JFrame changeframe;
	private JFrame loginFrame = null;
	private JPasswordField pwdEPassword;
	private JPasswordField pwdNewPass;
	/**
	 * Launch the application.
	 */
	public  void changeAcc() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ChangeUsername window = new ChangeUsername(loginFrame);
					window.changeframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChangeUsername(JFrame loginFrame) {
		this.loginFrame = loginFrame;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		changeframe = new JFrame();
		changeframe.getContentPane().setBackground(new Color(0, 153, 102));
		changeframe.setAutoRequestFocus(false);
		changeframe.setResizable(false);
		changeframe.setBounds(100, 100, 450, 300);
		changeframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		CenterWindow myCenterWindow = new CenterWindow();
		myCenterWindow.doCentreWindow(changeframe);
		changeframe.getContentPane().setLayout(null);

		JLabel lblEnterNewPassword = new JLabel("Enter new password:");
		lblEnterNewPassword.setForeground(new Color(255, 255, 255));
		lblEnterNewPassword.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 17));
		lblEnterNewPassword.setBounds(26, 78, 176, 23);
		changeframe.getContentPane().add(lblEnterNewPassword);

		JLabel lblReenterPassword = new JLabel("Re-enter password:");
		lblReenterPassword.setForeground(new Color(255, 255, 255));
		lblReenterPassword.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 17));
		lblReenterPassword.setBounds(26, 126, 176, 20);
		changeframe.getContentPane().add(lblReenterPassword);

		Icon firstPicChange= new ImageIcon(Login.class.getResource("/app/resources/okbutton.jpg"));
		Icon secPicChange= new ImageIcon(Login.class.getResource("/app/resources/okbutton2.jpg"));
		
		pwdEPassword = new JPasswordField();
		pwdEPassword.setHorizontalAlignment(SwingConstants.CENTER);
		pwdEPassword.setForeground(new Color(0, 128, 0));
		pwdEPassword.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		pwdEPassword.setBounds(222, 71, 184, 30);
		changeframe.getContentPane().add(pwdEPassword);
		
		pwdNewPass = new JPasswordField();
		pwdNewPass.setHorizontalAlignment(SwingConstants.CENTER);
		pwdNewPass.setForeground(new Color(0, 128, 0));
		pwdNewPass.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		pwdNewPass.setBounds(222, 118, 184, 30);
		changeframe.getContentPane().add(pwdNewPass);
		
		JLabel lblNewPass = new JLabel("");
		lblNewPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReadUserDao myDao = new ReadUserDaoImpl();
				UpdateUserPasswordDao updatePassword = new UpdateUserPasswordDaoImpl();
			//	System.out.println(pwdEPassword.getPassword(), pwdNewPass.getPassword());
				
				if(pwdEPassword.getText().equals("") && (pwdNewPass.getText()).equals("")){
					JOptionPane.showMessageDialog(changeframe,"Please enter new password!");
					
				}
				
				else if(pwdEPassword.getText().equals(pwdNewPass.getText())){
					updatePassword.getUserPassword(pwdEPassword.getText());
					JOptionPane.showMessageDialog(changeframe,"Succesfully changed password!");
					changeframe.dispose();
					loginFrame.dispose();
					Login myLogin = new Login();
					myLogin.frmLogin.dispose();
				    Welcome myWelcome = new Welcome();
				  	
				}
				else {
					JOptionPane.showMessageDialog(changeframe, "Password does not match!");
				}
			}
		});
		lblNewPass.setIcon(new ImageIcon(ChangeUsername.class.getResource("/app/resourceLatest/add.png")));
		lblNewPass.setBounds(233, 183, 69, 78);
		changeframe.getContentPane().add(lblNewPass);
		
		JLabel lblCancel = new JLabel("");
		lblCancel.setIcon(new ImageIcon(ChangeUsername.class.getResource("/app/resourceLatest/Close-icon.png")));
		lblCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				changeframe.dispose();
			}
		});
		
		
		lblNewPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReadUserDao myDao = new ReadUserDaoImpl();
				UpdateUserPasswordDao updatePassword = new UpdateUserPasswordDaoImpl();
			//	System.out.println(pwdEPassword.getPassword(), pwdNewPass.getPassword());
				
				if(pwdEPassword.getText().equals("") && (pwdNewPass.getText()).equals("")){
					JOptionPane.showMessageDialog(changeframe,"Please enter new password!");
					
				}
				
				else if(pwdEPassword.getText().equals(pwdNewPass.getText())){
					updatePassword.getUserPassword(pwdEPassword.getText());
					JOptionPane.showMessageDialog(changeframe,"Succesfully changed password!");
					changeframe.dispose();
					loginFrame.dispose();
					Login myLogin = new Login();
					myLogin.frmLogin.dispose();
				    Welcome myWelcome = new Welcome();
				  	
				}
				else {
					JOptionPane.showMessageDialog(changeframe, "Password does not match!");
				}
			}
		});
		lblCancel.setBounds(325, 183, 69, 78);
		changeframe.getContentPane().add(lblCancel);
	}
}
