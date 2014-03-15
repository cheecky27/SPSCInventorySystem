package app.service.removeitemsfromnewitemstbl.impl;

import app.dao.newentry.ReadNewItemDao;
import app.dao.newentry.impl.ReadNewItemDaoImpl;
import app.model.Item;
import app.service.removeitemsfromnewitemstbl.RemoveItemsFromNewItemsTblService;
import app.ui.ItemManagement;

public class RemoveItemsfomNewItemsTblServiceImpl implements
RemoveItemsFromNewItemsTblService {

	@Override
	public void removeItemsFromNewItemsTbl(String[] itemIDsToRemove) {
		System.out.println("pumasok ako sa remove");
		Item myItemToRemove = new Item();
		ItemManagement myItemManagement = new ItemManagement();

		for(int x= 0; x< itemIDsToRemove.length; x++){
			System.out.println("pumasok ako sa for loop");
			myItemToRemove.setItemID(itemIDsToRemove[x]);
			
		}

	}

}
