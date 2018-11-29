package shoppingcart;

public class ShoppingCartItems {
	
	int itemID;
	int itemCategoryID;
	String itemName;
	float unitPrice;
	int quantity;
	
	/**
	 * 
	 */
	public ShoppingCartItems() {
		super();
	}

	/**
	 * @param itemID
	 * @param itemCategoryID
	 * @param itemName
	 * @param unitPrice
	 * @param quantity
	 */
	public ShoppingCartItems(int itemID, int itemCategoryID, String itemName, float unitPrice, int quantity) {
		super();
		this.itemID = itemID;
		this.itemCategoryID = itemCategoryID;
		this.itemName = itemName;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}

	/**
	 * @return the itemID
	 */
	public int getItemID() {
		return itemID;
	}

	/**
	 * @param itemID the itemID to set
	 */
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	/**
	 * @return the itemCategoryID
	 */
	public int getItemCategoryID() {
		return itemCategoryID;
	}

	/**
	 * @param itemCategoryID the itemCategoryID to set
	 */
	public void setItemCategoryID(int itemCategoryID) {
		this.itemCategoryID = itemCategoryID;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the unitPrice
	 */
	public float getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/*@Override
	public String toString() {
		return "ShoppingCart [itemID=" + itemID + ", itemCategoryID="
				+ itemCategoryID + ", itemName=" + itemName + ", unitPrice="
				+ unitPrice + ", quantity=" + quantity + "]";
	}*/
}

