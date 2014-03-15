package app.dao.archive;

import app.model.Item;

public interface WriteIntoArchiveDao {

	boolean doWriteInArchive(Item items);
}
