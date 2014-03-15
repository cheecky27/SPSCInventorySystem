package app.dao.newentry;

import java.util.List;

import app.model.Item;

public interface ReadNewItemDao {

	List<Item> showItems();
	List<Item> deleteStudent(Item selectedNewItem);
	int getForeignKeyOfInventory();
	List<Item> showSearchedItems(String search);
}
