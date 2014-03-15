package app.dao.itemclassification;

import java.util.List;

import app.model.Item;

public interface ReadFromItemClassDBDao {
	List<Item> showItemClassification() ;
	String showItemCodeClassification(String itemClassName) ;
}
