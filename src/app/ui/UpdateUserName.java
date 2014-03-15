package app.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import app.dao.user.UpdateUserUserNameDao;
import app.model.User;
import app.user.dao.impl.UpdateUserUserNameDaoImpl;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Font;

public class UpdateUserName extends JDialog {

	private final JPanel cnpChangeUsername = new JPanel();
	private JTextField txtNewUsername;
	private JFrame menu;
	

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public UpdateUserName(final User user) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
			}
		});
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		cnpChangeUsername.setBackground(new Color(0, 153, 102));
		cnpChangeUsername.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(cnpChangeUsername, BorderLayout.CENTER);
		cnpChangeUsername.setLayout(null);
		{
			JLabel lblNewUsername = new JLabel("Please Enter your new Username");
			lblNewUsername.setForeground(new Color(255, 255, 255));
			lblNewUsername.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
			lblNewUsername.setBounds(25, 70, 297, 21);
			cnpChangeUsername.add(lblNewUsername);
		}
		{
			txtNewUsername = new JTextField();
			txtNewUsername.setBounds(25, 121, 384, 34);
			cnpChangeUsername.add(txtNewUsername);
			txtNewUsername.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(0, 153, 102));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						UpdateUserUserNameDao updateUsername = new UpdateUserUserNameDaoImpl();
						updateUsername.getUserUserName(txtNewUsername.getText());
						JOptionPane.showMessageDialog(cnpChangeUsername,"Succesfully changed your USERNAME!");
						dispose();
						Login logIn = new Login();
						logIn.frmLogin.setVisible(true);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
