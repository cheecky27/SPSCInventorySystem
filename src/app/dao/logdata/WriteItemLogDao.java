package app.dao.logdata;

import app.model.Item;

public interface WriteItemLogDao {
	public void writeIntoLog(Item items);
}
