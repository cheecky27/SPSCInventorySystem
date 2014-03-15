package app.util;

import app.dao.allitems.ReadAllItemsDao;
import app.dao.allitems.impl.ReadAllItemsDaoImpl;
import app.dao.itemclassification.ReadFromItemClassDBDao;
import app.dao.itemclassification.impl.ReadFromItemClassDBDaoImpl;

public class ItemIDSetter {

	public String doSetTheItemId(String code){
		String itemId = "";
		
		ReadAllItemsDao myItems = new ReadAllItemsDaoImpl();
		CurrentDate myCurrentDate = new CurrentDate();
		itemId = code+ myCurrentDate.showDateToday()+myItems.countRecords();
		
		System.out.println("The final id: "+ itemId);
		
		return itemId;
	}
}
