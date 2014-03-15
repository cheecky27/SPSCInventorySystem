package app.dao.logdata;

import java.util.List;

import app.model.Item;

public interface ReadItemLogDao {
	List<Item> showListOfLogs(String ungId);
}
