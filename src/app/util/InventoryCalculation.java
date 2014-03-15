package app.util;

import java.util.List;

import app.dao.allitems.impl.ReadAllItemsDaoImpl;

import app.model.Item;

public class InventoryCalculation {
	
	public void doSetInstock(Item quantityToBeBorrowed){
		
		ReadAllItemsDaoImpl myReader = new ReadAllItemsDaoImpl();
		List<Item> newAllItemList = myReader.showEverything();
		int quantity = 0;
		int instock =0;
		int outstock=0;
		for (Item myNewItems : newAllItemList) {
			quantity =myNewItems.getQuantity();
			instock = myNewItems.getInstock();
			outstock = myNewItems.getOutstock();
	
		}
		
		
	}

}
