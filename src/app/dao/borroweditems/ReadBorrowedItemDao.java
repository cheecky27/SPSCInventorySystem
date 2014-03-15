package app.dao.borroweditems;

import java.util.List;

import app.model.Item;

public interface ReadBorrowedItemDao {
	List<Item> showItemsToBeReturned(int i);
	List<Item> showQuantityToBeReturned();
}
