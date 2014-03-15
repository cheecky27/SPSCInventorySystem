package app.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import app.dao.unitofmeasurement.ReadUnitOfMeasurementDao;
import app.dao.unitofmeasurement.WriteUnitOfMeasurementDao;
import app.dao.unitofmeasurement.impl.ReadUnitOfMeasurementDaoImpl;
import app.dao.unitofmeasurement.impl.WriteUnitOfMeasurementDaoImpl;
import app.model.Item;
import app.service.cmblocationunit.impl.CmbLocationUnitServiceImpl;

import java.awt.Color;
import java.awt.color.CMMException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class NewUnit extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNewUnit;
	private ItemManagement myItemManagement;
	/**
	 * Launch the application.
	 */
	public  void addNewUnit() {
		try {
			NewUnit dialog = new NewUnit(myItemManagement);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewUnit(final ItemManagement myItemManagement) {

		this.myItemManagement = myItemManagement;
		//System.out.println("anung class "+myItemManagement);
		setBounds(430, 350, 351, 166);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 153, 102));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewUnit = new JLabel("New Unit:");
		lblNewUnit.setForeground(new Color(255, 255, 255));
		lblNewUnit.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 23));
		lblNewUnit.setBounds(51, 42, 105, 34);
		contentPanel.add(lblNewUnit);

		txtNewUnit = new JTextField();
		txtNewUnit.setForeground(new Color(0, 128, 0));
		txtNewUnit.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 23));
		txtNewUnit.setBounds(178, 43, 105, 33);
		contentPanel.add(txtNewUnit);
		txtNewUnit.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(0, 153, 102));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					@SuppressWarnings("unchecked")
					public void actionPerformed(ActionEvent arg0) {
						Item myUnit = new Item();
						WriteUnitOfMeasurementDao myMeasurementdao = new WriteUnitOfMeasurementDaoImpl();
						ReadUnitOfMeasurementDao myDao = new ReadUnitOfMeasurementDaoImpl();
						myUnit.setUnitOfMeasurement(txtNewUnit.getText());

						if(myMeasurementdao.writeIntoDbUnits(myUnit)==false){
							JOptionPane.showMessageDialog(null,"Succesfully added new unit!!");
							CmbLocationUnitServiceImpl myCmbLocationServiceImpl = new CmbLocationUnitServiceImpl();
							myItemManagement.cmbUnitOfMeasurement= new JComboBox(myCmbLocationServiceImpl.addDataToCmbUnitOfMeasurement());


							dispose();
						}
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
