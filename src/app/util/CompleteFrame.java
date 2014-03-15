package app.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import app.model.Group;
import app.ui.GroupManagement;

public class CompleteFrame implements LineUser, ActionListener{

	private static final long serialVersionUID = 1L;

	GroupManagement myGroupManagement;

	private JPanel pane = new JPanel();
	private JFrame myFrame;
	private JButton submit;
	public CompleteFrame(JFrame myFrame){
		this.myFrame = myFrame;

		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		submit = new JButton("Submit");
		submit.setForeground(Color.BLACK);
		submit.setBackground(Color.WHITE);
		Border line = new LineBorder(null);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
		submit.setBorder(compound);
		submit.setOpaque(false);
		submit.addActionListener(this);

		myFrame.add(pane);

		pane.add(submit);
		submit.setBounds(0, 0, 200, 100);
		pane.add(new Line(this));                

		myFrame.pack();
		pane.setBackground(Color.white);
	}

	public  JPanel doReturnPanel(){ 
		return pane;
	}



	@Override
	public void removeLine(Line l) {
		pane.add(submit);
		pane.remove(l);
		myFrame.pack();
	}

	@Override
	public void addLine(Line l) {
		pane.add(l);
		myFrame.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StringBuilder b = new StringBuilder();
		Component[] comps =pane.getComponents();
		for (Component comp : comps){
			if ( comp instanceof Line){
				Line l = (Line)comp;
				b.append(l.getText1()).append("\n ").append(l.getText2()).append("\n");

			}
		}
		
		ArrayList<String> myGroups = new ArrayList<String>();

		myGroups.add(b.toString());
		myGroupManagement = new GroupManagement();
		myGroupManagement.doGetTheGroupMembers(myGroups);
	}
}