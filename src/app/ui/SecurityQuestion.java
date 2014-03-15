package app.ui;

import java.awt.BorderLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import app.dao.user.ReadUserDao;
import app.user.dao.impl.ReadUserDaoImpl;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SecurityQuestion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSecAns;
	private JFrame loginFrame;
	private int tries ;
	
	/*
	* Launch the application.
	*/
	public  void securityQuestion() {
		try {
			SecurityQuestion dialog = new SecurityQuestion(loginFrame);
			
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setLocation(450, 250);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SecurityQuestion(final JFrame loginFrame) {
		
		
		
		this.loginFrame = loginFrame;
		
		
		setBounds(100, 100, 453, 303);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 153, 102));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblSecurityQuestion = new JLabel("Please answer the security question:");
		lblSecurityQuestion.setForeground(new Color(255, 255, 255));
		lblSecurityQuestion.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblSecurityQuestion.setBounds(47, 26, 333, 43);
		contentPanel.add(lblSecurityQuestion);

		Icon firstPicSecQ= new ImageIcon(Login.class.getResource("/app/resources/okbutton.jpg"));
		Icon secPicSecQ= new ImageIcon(Login.class.getResource("/app/resources/okbutton2.jpg"));

		txtSecAns = new JTextField();
		txtSecAns.setHorizontalAlignment(SwingConstants.CENTER);
		txtSecAns.setForeground(new Color(0, 128, 0));
		txtSecAns.setFont(new Font("Dialog", Font.PLAIN, 17));
		txtSecAns.setBounds(32, 139, 365, 35);
		contentPanel.add(txtSecAns);
		txtSecAns.setColumns(10);

		ReadUserDao myUser = new ReadUserDaoImpl();

		System.out.println("SQ: "+myUser.getUserInfo().getSecQues());

		JScrollPane scrpSecQues = new JScrollPane();
		scrpSecQues.setBounds(32, 66, 365, 62);
		contentPanel.add(scrpSecQues);
		
		JLabel lblSecQuesAns = new JLabel(myUser.getUserInfo().getSecQues());
		lblSecQuesAns.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecQuesAns.setBackground(Color.WHITE);
		lblSecQuesAns.setForeground(new Color(204, 0, 0));
		lblSecQuesAns.setFont(new Font("Dialog", Font.PLAIN, 20));
		scrpSecQues.setViewportView(lblSecQuesAns);
		
		JLabel lblOk = new JLabel("");
		lblOk.setIcon(new ImageIcon(SecurityQuestion.class.getResource("/app/resourceLatest/add.png")));
		lblOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				

				ReadUserDao myReadUserDao = new ReadUserDaoImpl();

				if(txtSecAns.getText().equals(myReadUserDao.getUserInfo().getAnswer())){
					dispose();
					ChangeUsername myChangeUsername = new ChangeUsername(loginFrame);
					myChangeUsername.changeAcc();


				}else{
					tries++;
					int tries1 = 5 - tries;
					JOptionPane.showMessageDialog(null,"Sorry! Wrong answer!! (maximum of tries is 5)"+" "+tries1+"  remaining" );
					if(tries == 5){
						System.exit(0);
					}
				}

				
				
			}
		});
		lblOk.setBounds(342, 193, 70, 61);
		contentPanel.add(lblOk);
	}
}
