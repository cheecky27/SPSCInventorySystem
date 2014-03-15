package app.service.searchorsort.impl;

import java.util.List;

import app.model.Item;
import app.service.searchorsort.InventorySorterAndSearcherService;

public class InventorySorterAndSearcherServiceImpl implements InventorySorterAndSearcherService{

	@Override
	public List<Item> doSort(String howToSort) {
		
	
		if(howToSort.equals("Item Description")){

		}
		else if(howToSort.equals("Location")){

		}else if(howToSort.equals("Quantity")){

		}else if(howToSort.equals("Unit")){

		}else if(howToSort.equals("Date Created")){
			
		}else if(howToSort.equals("Instock")){
			
		}else if(howToSort.equals("Outstock")){
			
		}else if(howToSort.equals("Last Borrower")){
			
		}else if(howToSort.equals("Last Borrowed")){
			
		}


		return null;
	}
}
