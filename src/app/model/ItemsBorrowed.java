package app.model;

public class ItemsBorrowed {

	private boolean returnedItem;
	private int quantityToBeReturned;
	private String itemID;
	private String itemDescription;
	private int quantityReturned;
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public boolean isReturnedItem() {
		return returnedItem;
	}
	public void setReturnedItem(boolean returnedItem) {
		this.returnedItem = returnedItem;
	}
	public int getQuantityToBeReturned() {
		return quantityToBeReturned;
	}
	public void setQuantityToBeReturned(int quantityToBeReturned) {
		this.quantityToBeReturned = quantityToBeReturned;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public int getQuantityReturned() {
		return quantityReturned;
	}
	public void setQuantityReturned(int quantityReturned) {
		this.quantityReturned = quantityReturned;
	}

}
