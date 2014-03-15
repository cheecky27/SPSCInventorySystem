package app.util;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



public class TextBoxCreator {

	private JFrame yourFrame= null;
	private JTextField origBox=null;
	private String tempBox= null;
	int count=0;

	public TextBoxCreator(final JFrame yourFrame){

		tempBox = "origBox";
		count = 0;
		this.yourFrame = yourFrame;


	}
	private void displayGUI()
	{
		
		origBox = new JTextField();
		origBox.setName(tempBox + count);
		count++;
		yourFrame.add(origBox);
		yourFrame.getContentPane().revalidate();
		yourFrame.repaint();


		yourFrame.pack();
		yourFrame.setLocationByPlatform(true);
		yourFrame.setVisible(true);
	}
	public void doRunIt(){
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new TextBoxCreator(yourFrame).displayGUI();
			}
		});
	}
}
