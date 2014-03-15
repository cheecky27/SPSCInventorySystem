package app.dao.borroweditems;

import java.util.List;

import app.model.Group;
import app.model.Item;

public interface WriteBorrowedItemDao {
	void doWriteBorrowedItems( Group groupWhoBorrowed, Item borrowedItems);
}
