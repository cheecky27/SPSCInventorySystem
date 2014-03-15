package app.service.searchorsort;

import java.util.List;

import app.model.Item;

public interface InventorySorterAndSearcherService {

	List<Item> doSort(String howToSort);
}
