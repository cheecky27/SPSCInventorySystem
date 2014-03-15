package app.service.getvaluefromtxtfields.impl;

import java.util.Collection;
import java.util.List;

import javax.swing.JTextField;

public class JTxtFieldvalueSetter {

	public void doGetthevalueFromTF(Collection<JTextField> txtStudentNumber){

		for(JTextField theTxtStudNum : txtStudentNumber){
			System.out.println("Lamang ng mga textbox: "+ theTxtStudNum.getText());
		}


	}
	public void doGetValueFromTxtName(Collection<JTextField> txtStudentName){
		for(JTextField theTxtStudName : txtStudentName){
			System.out.println("Lamang ng mga textbox: "+ theTxtStudName.getText());
		}
	}
}
