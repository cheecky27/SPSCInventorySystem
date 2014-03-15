package app.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import app.dao.group.ReadGroupsDao;
import app.dao.group.impl.ReadGroupDaoImpl;
import app.dao.selected.group.UpdateSelectedGroupDao;
import app.dao.selected.group.impl.UpdateSelectedGroupDaoImpl;
import app.model.Group;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;

public class UpdateGroup extends JDialog {

	private final JPanel cnpUpdateGroup = new JPanel();
	private JTextField txtProfessor;
	private JTextArea txtGrpMembers;
	private JTextField txtGroupName;
	private GroupManagement groupManagement;
	private JTextField txtGrpID;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 *
	 */
	public UpdateGroup(final Group group, final GroupManagement groupManagement) {
		this.groupManagement = groupManagement;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {

				txtGrpID.setText(Integer.toString(group.getGroupID()));
				txtProfessor.setText(group.getProfessor());
				txtGroupName.setText(group.getGroupName());
				txtGrpMembers.setText(group.getGroupMembers());

			}
		});
		setBounds(100, 100, 501, 486);
		getContentPane().setLayout(new BorderLayout());
		cnpUpdateGroup.setBackground(new Color(0, 153, 102));
		cnpUpdateGroup.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(cnpUpdateGroup, BorderLayout.CENTER);
		cnpUpdateGroup.setLayout(null);
		{
			JLabel lblProfessor = new JLabel("Professor :");
			lblProfessor.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
			lblProfessor.setBounds(10, 67, 126, 14);
			cnpUpdateGroup.add(lblProfessor);
		}
		{
			txtProfessor = new JTextField();
			txtProfessor.setBounds(121, 61, 171, 20);
			cnpUpdateGroup.add(txtProfessor);
			txtProfessor.setColumns(10);
		}
		{
			JLabel lblGroupMembers = new JLabel("Group Members:");
			lblGroupMembers.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
			lblGroupMembers.setBounds(10, 144, 126, 14);
			cnpUpdateGroup.add(lblGroupMembers);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(79, 169, 248, 170);
			cnpUpdateGroup.add(scrollPane);

			txtGrpMembers = new JTextArea();
			scrollPane.setColumnHeaderView(txtGrpMembers);
		}
		{
			JLabel lblGroupName = new JLabel("Group Name:");
			lblGroupName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
			lblGroupName.setBounds(10, 104, 126, 14);
			cnpUpdateGroup.add(lblGroupName);
		}
		{
			txtGroupName = new JTextField();
			txtGroupName.setBounds(121, 98, 191, 20);
			cnpUpdateGroup.add(txtGroupName);
			txtGroupName.setColumns(10);
		}
		{
			JLabel lblId = new JLabel("ID:");
			lblId.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
			lblId.setBounds(10, 31, 46, 14);
			cnpUpdateGroup.add(lblId);
		}
		{
			txtGrpID = new JTextField();
			txtGrpID.setEditable(false);
			txtGrpID.setBounds(120, 25, 86, 20);
			cnpUpdateGroup.add(txtGrpID);
			txtGrpID.setColumns(10);
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
						Group group = new Group();
						UpdateSelectedGroupDao updateGroups = new UpdateSelectedGroupDaoImpl();

						group.setGroupID(Integer.parseInt(txtGrpID.getText()));
						group.setProfessor(txtProfessor.getText());
						group.setGroupName(txtGroupName.getText());
						group.setGroupMembers(txtGrpMembers.getText());
						System.out.println("ito ha:"+group.getGroupName()+group.getProfessor()+group.getGroupMembers());

						updateGroups.UpdateGroup(group);

						JOptionPane.showMessageDialog(cnpUpdateGroup,"Succesfully Edited!");
						dispose();
						groupManagement.showGroupTable();
						
						System.out.println("ito ulit:"+group.getGroupName()+"\n"+group.getProfessor()+group.getGroupMembers());
						


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
