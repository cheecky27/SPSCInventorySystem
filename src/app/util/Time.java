package app.util;

import java.text.SimpleDateFormat;

import javax.swing.JLabel;

public class Time {
	
	Thread clockThread = null;
	int hours = 0;
	int minutes = 0;
	int seconds = 0;
	static String timeString = "";

	public Time(final JLabel lblClock) {
		new Thread(new Runnable(){
			@Override
			public void run(){ 
				try{
					updateTime(lblClock);
				}
				catch (Exception ie){ 

				}
			}
		}).start();
	}


	@SuppressWarnings("static-access")
	public void updateTime(JLabel lblClock){
		try{
			while(true){
				lblClock.setText(new SimpleDateFormat("hh:mm:ss a").format(new java.util.Date()));
				Thread.currentThread().sleep(1000);
			}
		}
		catch (Exception e){
			System.out.println("Exception in Thread Sleep : "+e);
		}
		{

		}
		try{
			while(true){
				lblClock.setText(new SimpleDateFormat("hh:mm:ss a").format(new java.util.Date()));
				Thread.currentThread().sleep(1000);
			}
		}
		catch (Exception e){
			System.out.println("Exception in Thread Sleep : "+e);
		}
	}

}