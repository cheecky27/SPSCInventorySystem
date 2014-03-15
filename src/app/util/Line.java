package app.util;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import app.ui.GroupManagement;


public class Line extends javax.swing.JPanel {

	private final LineUser parent;


	public Line(LineUser user) {
		initComponents();
		
		this.parent = user;
		
	}


	private void initComponents() {
		
		field1 = new javax.swing.JTextField();
		field2 = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();

		
		field1.setPreferredSize(new Dimension (153,35));
		field2.setText("");
		field2.setPreferredSize(new Dimension(350,35));

		
		jButton1.setIcon ( new ImageIcon(GroupManagement.class.getResource("/app/resources/plussmall.png")));
		jButton1.setForeground(Color.BLACK);
		jButton1.setBackground(Color.WHITE);
		Border line = new LineBorder(null);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
		jButton1.setBorder(compound);
		jButton1.setOpaque(false);
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setIcon ( new ImageIcon(GroupManagement.class.getResource("/app/resources/minussmall.png")));
		jButton2.setForeground(Color.BLACK);
		jButton2.setBackground(Color.WHITE);
		Border line2 = new LineBorder(null);
		Border margin2 = new EmptyBorder(5, 15, 5, 15);
		Border compound2 = new CompoundBorder(line, margin);
		jButton2.setBorder(compound2);
		jButton2.setOpaque(false);
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		this.setBackground(Color.WHITE);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(field1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(field2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jButton1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jButton2)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(field1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(field2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton1)
								.addComponent(jButton2))
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
	}// </editor-fold>                        

	// addButton
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
		parent.addLine(new Line(parent));

	}                                        

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
		parent.removeLine(this);
	}          

	public String getText1(){
		return field1.getText();
	}
	public String getText2(){
		return field2.getText();
	}


	// Variables declaration - do not modify                     
	private javax.swing.JTextField field1;
	private javax.swing.JTextField field2;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	// End of variables declaration                   
}
