package app.dao.archive;

import java.util.List;

import app.model.Item;

public interface ReadIntoArchiveDao {
	List<Item> showArchive();
	void deleteRecordInArchive(Item selectedNewItem);
}
