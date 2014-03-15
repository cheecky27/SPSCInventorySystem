package app.service.cmblclassification.impl;

import java.util.List;

import app.dao.itemclassification.impl.ReadFromItemClassDBDaoImpl;
import app.model.Item;

public class CmbClassificationServiceImpl {
	public String[] addDataToCmbClassification(){
		int containMe = 0;
		String[] dataForCmb = new String[countUnits()];
		ReadFromItemClassDBDaoImpl myItemClassification = new ReadFromItemClassDBDaoImpl();
		List<Item> itemClassList = myItemClassification.showItemClassification();
		for (Item myItemClass : itemClassList) {
			dataForCmb[containMe]=myItemClass.getItemClassification();
			containMe++;
		}
		return dataForCmb;
	}
	public int countUnits(){
		int myCount =0;
		ReadFromItemClassDBDaoImpl myItemClassification = new ReadFromItemClassDBDaoImpl();
		List<Item> itemClassList = myItemClassification.showItemClassification();
		for (Item myItemClass : itemClassList) {
			myItemClass.getItemClassification();
			myCount++;
		}
		return myCount;
	}
}
