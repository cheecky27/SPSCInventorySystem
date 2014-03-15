package app.dao.allitems;

import java.util.ArrayList;
import java.util.List;

import app.model.Item;

public interface ReadAllItemsDao {
	ArrayList<Item> showEverything();
	int countRecords();
}
