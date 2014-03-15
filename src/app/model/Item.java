package app.model;

public class Item {

	private String itemID = "";
	private String itemDes ="";
	private String dateReturn = "";
	private String location = "";
	private String dateDelivered="";
	private String remarks = "";
	private String borrower = "";
	private String unitOfMeasurement = "";
	private int grpId= 0;
	private int quantity = 0;
	private int instock = 0;
	private int outstock = 0;
	private int itemsToBeBorrowed;
	private boolean wereYouSelected;
	private String dateDeleted= "";
	private String itemClassification = "";
	
	public String getDateDeleted() {
		return dateDeleted;
	}
	public void setDateDeleted(String dateDeleted) {
		this.dateDeleted = dateDeleted;
	}
	public boolean getWereYouSelected() {
		return wereYouSelected;
	}
	public void setWereYouSelected(boolean wereYouSelected) {
		this.wereYouSelected = wereYouSelected;
	}
	public int getItemsToBeBorrowed() {
		return itemsToBeBorrowed;
	}
	public void setItemsToBeBorrowed(int itemsToBeBorrowed) {
		this.itemsToBeBorrowed = itemsToBeBorrowed;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public String getItemDes() {
		return itemDes;
	}
	public void setItemDes(String itemDes) {
		this.itemDes = itemDes;
	}
	public String getDateReturn() {
		return dateReturn;
	}
	public void setDateReturn(String dateReturn) {
		this.dateReturn = dateReturn;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getInstock() {
		return instock;
	}
	public void setInstock(int instock) {
		this.instock = instock;
	}
	public int getOutstock() {
		return outstock;
	}
	public void setOutstock(int outstock) {
		this.outstock = outstock;
	}
	public String getDateDelivered() {
		return dateDelivered;
	}
	public void setDateDelivered(String dateDelivered) {
		this.dateDelivered = dateDelivered;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getBorrower() {
		return borrower;
	}
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
	public int getGrpId() {
		return grpId;
	}
	public void setGrpId(int grpId) {
		this.grpId = grpId;
	}
	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}
	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}
	public String getItemClassification() {
		return itemClassification;
	}
	public void setItemClassification(String itemClassification) {
		this.itemClassification = itemClassification;
	}

	
}
