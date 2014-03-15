package app.dao.location;

import java.util.List;

import app.model.Item;

public interface ReadLocationDao {
	List<Item> getFromDbLocation();
}
