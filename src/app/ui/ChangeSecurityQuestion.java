package app.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import app.dao.user.ReadUserDao;
import app.dao.user.UpdateSecurityDao;

import app.model.User;
import app.user.dao.impl.ReadUserDaoImpl;
import app.user.dao.impl.UpdateUserSecurityDaoImpl;

import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ChangeSecurityQuestion extends JDialog {

	private final JPanel cnpChangeSecurity = new JPanel();
	private JTextField txtsecQuestion;
	private JTextField txtAnswer;
	private SecurityQuestion securityQuestion;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public ChangeSecurityQuestion(final User user, final SecurityQuestion securityQuestion) {
		this.securityQuestion = securityQuestion;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				ReadUserDao myUser = new ReadUserDaoImpl();
				txtsecQuestion.setText(myUser.getUserInfo().getSecQues());
				txtAnswer.setText(myUser.getUserInfo().getAnswer());
				
			}
		});
		setType(Type.POPUP);
		setBounds(100, 100, 379, 257);
		getContentPane().setLayout(new BorderLayout());
		cnpChangeSecurity.setBackground(new Color(0, 153, 102));
		cnpChangeSecurity.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(cnpChangeSecurity, BorderLayout.CENTER);
		cnpChangeSecurity.setLayout(null);
		
		JLabel lblSecurityQuestion = new JLabel("Security Question");
		lblSecurityQuestion.setForeground(new Color(255, 255, 255));
		lblSecurityQuestion.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblSecurityQuestion.setBounds(10, 11, 135, 29);
		cnpChangeSecurity.add(lblSecurityQuestion);
		
		txtsecQuestion = new JTextField();
		txtsecQuestion.setForeground(new Color(0, 128, 0));
		txtsecQuestion.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		txtsecQuestion.setBounds(10, 46, 299, 29);
		cnpChangeSecurity.add(txtsecQuestion);
		txtsecQuestion.setColumns(10);
		
		JLabel lblAnswer = new JLabel("Answer");
		lblAnswer.setForeground(new Color(255, 255, 255));
		lblAnswer.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblAnswer.setBounds(10, 86, 61, 25);
		cnpChangeSecurity.add(lblAnswer);
		
		txtAnswer = new JTextField();
		txtAnswer.setForeground(new Color(0, 128, 0));
		txtAnswer.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		txtAnswer.setBounds(10, 122, 299, 29);
		cnpChangeSecurity.add(txtAnswer);
		txtAnswer.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(0, 153, 102));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						User user = new User();
						UpdateSecurityDao updateSecurity = new UpdateUserSecurityDaoImpl();
						
						user.setSecQues(txtsecQuestion.getText());
						user.setAnswer(txtAnswer.getText());
						updateSecurity.updateSecurity(user);
						dispose();
						
						
					
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
