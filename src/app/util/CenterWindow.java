package app.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

public class CenterWindow {

	public  void doCentreWindow(Window frameNamin) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();//ETO KINUHA ATA UNG SIZE NG SCREEN NG USER
		int x = (int) ((dimension.getWidth() - frameNamin.getWidth()) / 2); //DINIVIDE SA 2
		int y = (int) ((dimension.getHeight() - frameNamin.getHeight()) / 2);//GANUN DIN TO GAYA-GAYA
		frameNamin.setLocation(x, y); // ETO NAGSET YABANG NAMAN WAHAHAH!
	}
	
	public  void doFullScreen(Window frameNatin) {
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int xSize = ((int) tk.getScreenSize().getWidth());  
		int ySize = ((int) tk.getScreenSize().getHeight());  
		frameNatin.setSize(xSize,ySize);  
	}
}
