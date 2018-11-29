package shoppingcart;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BillProcessing {
	
	private static final String CATEGORIES_DATA_FILEPATH = "JSONData\\Categories.json";
	private static final String FLAT_DISCOUNT_SLABS_DATA_FILEPATH = "JSONData\\FlatDiscountSlabs.json";
	private static final String SHOPPING_CART_DATA_FILEPATH = "JSONData\\ShoppingCart.json";
	private static final String CATEGORIES_FIELD = "Categories";
	private static final String CATEGORY_FIELD = "Category";
	private static final String FLAT_DISCOUNT_SLABS_FIELD = "FlatDiscountSlabs";
	private static final String SLAB_FIELD = "Slab";
	private static final String SHOPPING_CART_FIELD = "ShoppingCart";
	private static final String ITEM_ID_FIELD = "itemID";
	private static final String ITEM_CATEGORY_ID_FIELD = "itemCategoryID";
	private static final String ITEM_NAME_FIELD = "itemName";
	private static final String UNIT_PRICE_FIELD = "unitPrice";
	private static final String QUANTITY_FIELD = "quantity";
	private static final String CATEGORY_ID_FIELD = "id";
	private static final String CATEGORY_NAME_FIELD = "name";
	private static final String RANGE_MIN_FIELD = "RangeMin";
	private static final String RANGE_MAX_FIELD = "RangeMax";
	private static final String DISC_PERC_FIELD = "discPerc";

	Map<Integer,Categories> categoriesMap = new HashMap<Integer, Categories>();
	List<FlatDiscountSlabs> flatDiscountSlabsList = new ArrayList<FlatDiscountSlabs>();
	List<ShoppingCartItems> shoppingCartItemsList = new ArrayList<ShoppingCartItems>();
		
	/**
	 *This method will read the JSON Files 
	 */
	public void jsonReader(String filePath) throws IOException, ParseException  {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(filePath));

		JSONObject jsonObject =  (JSONObject) obj;
		if (filePath.equals(CATEGORIES_DATA_FILEPATH)) {
			readCategories(jsonObject);
		}
		else if (filePath.equals(FLAT_DISCOUNT_SLABS_DATA_FILEPATH)) {
			readFlatDiscountSlabs(jsonObject);
		}
		else if (filePath.equals(SHOPPING_CART_DATA_FILEPATH)) {
			readShoppingCart(jsonObject);
		}
	}

	/**
	 *This method will parse JSON data for Shopping Cart and stores it in ArrayList
	 */
	public void readShoppingCart (JSONObject jobj) {
		JSONArray jsonArray = (JSONArray)jobj.get(SHOPPING_CART_FIELD);
		for (int i = 0; i <jsonArray.size(); i++) {
			ShoppingCartItems shoppingCart = new ShoppingCartItems();
			JSONObject obj= (JSONObject)jsonArray.get(i);
			shoppingCart.setItemID(Integer.parseInt((String)obj.get(ITEM_ID_FIELD)));
			shoppingCart.setItemCategoryID(Integer.parseInt((String)obj.get(ITEM_CATEGORY_ID_FIELD)));
			shoppingCart.setItemName((String)obj.get(ITEM_NAME_FIELD));
			shoppingCart.setUnitPrice(Float.parseFloat((String)obj.get(UNIT_PRICE_FIELD)));
			shoppingCart.setQuantity(Integer.parseInt((String)obj.get(QUANTITY_FIELD)));
			shoppingCartItemsList.add(shoppingCart);
		}
	}

	/**
	 *This method will parse JSON data for Categories and stores it in HashMap
	 */
	private  void readCategories(JSONObject jobj) {
		JSONObject job1 = (JSONObject) jobj.get(CATEGORIES_FIELD);
		JSONArray jsonArray = (JSONArray)job1.get(CATEGORY_FIELD);
		for (int i = 0; i <jsonArray.size(); i++) {
			Categories category = new Categories();
			JSONObject obj= (JSONObject)jsonArray.get(i);
			category.setId(Integer.parseInt((String)obj.get(CATEGORY_ID_FIELD)));
			category.setName((String)obj.get(CATEGORY_NAME_FIELD));
			category.setDiscPerc(Float.parseFloat((String)obj.get(DISC_PERC_FIELD)));
			categoriesMap.put(category.getId(), category);
		}
	}
	
	/**
	 *This method will parse JSON data for Flat Discount Slabs and stores it in ArrayList
	 */
	private void readFlatDiscountSlabs(JSONObject jobj) {
		JSONObject job1 = (JSONObject) jobj.get(FLAT_DISCOUNT_SLABS_FIELD);
		JSONArray jsonArray = (JSONArray) job1.get(SLAB_FIELD);
		for (int i = 0; i <jsonArray.size(); i++) {
			FlatDiscountSlabs slab = new FlatDiscountSlabs();
			JSONObject obj= (JSONObject)jsonArray.get(i);
			slab.setRangeMin(Integer.parseInt((String)obj.get(RANGE_MIN_FIELD)));
			slab.setRangeMax(Integer.parseInt((String)obj.get(RANGE_MAX_FIELD)));
			slab.setDiscPerc(Float.parseFloat((String)obj.get(DISC_PERC_FIELD)));
			flatDiscountSlabsList.add(slab);
		}
	}

	/**
	 *This method will calculate the Grand Total, Total Discount, Net Bill Amount 
	 *and generates the bill for shopping cart
	 */
	public float calculateBillAmount() {
		float totalCartValue = 0;
		float totalDiscount = 0;

		System.out.println("----------------------------------------------shopping bill-------------------------------------");
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.format("%32s%9s%20s%10s%20s\n", "Item", "| Qty", "| Price(per Unit)", "| Disc", "| Net Amount ");		
		System.out.println("------------------------------------------------------------------------------------------------");
		for(ShoppingCartItems shoppingCartItem : shoppingCartItemsList){
			Categories itemCategory = categoriesMap.get(shoppingCartItem.getItemCategoryID());
			float discount = (shoppingCartItem.getUnitPrice() * shoppingCartItem.getQuantity() * 
					((float) itemCategory.getDiscPerc()) / 100);
			float itemTotal = (shoppingCartItem.getUnitPrice() * shoppingCartItem.getQuantity()) - discount;
			totalCartValue = totalCartValue + itemTotal;
			totalDiscount = totalDiscount + discount;
			System.out.format("%36s%s%7d%s%20f%s%12f%s%10f\n",shoppingCartItem.getItemName(), "|" , shoppingCartItem.getQuantity(), "|"
			, shoppingCartItem.getUnitPrice(), "|"
			, discount , "|", itemTotal);
			/*System.out.println(shoppingCartItem.getItemName() + shoppingCartItem.getQuantity()
					+ shoppingCartItem.getUnitPrice()
					+ discount + itemTotal);*/
			System.out.println("------------------------------------------------------------------------------------------------");			
		}
		
		for (FlatDiscountSlabs flatDiscountSlab : flatDiscountSlabsList)
		{
			if (flatDiscountSlab.getRangeMin() < totalCartValue	&& flatDiscountSlab.getRangeMax() > totalCartValue) {
				float slabDiscount = totalCartValue	* ((float) flatDiscountSlab.getDiscPerc() / 100);
				totalCartValue = totalCartValue	- slabDiscount;
				totalDiscount = totalDiscount + slabDiscount;
				break;
			}
		}
		System.out.println("		Grand Total:		Rs " + (totalCartValue + totalDiscount));
		System.out.println("		Applicable Discount:	Rs "+totalDiscount);
		System.out.println("		Net Bill Amount:	Rs "+totalCartValue);
		System.out.println("------------------------------------------------------------------------------------------------");
		return totalCartValue;
	}
	
	/**
	 *This method will return the categoriesMap
	 */
	public Map<Integer, Categories> getCategoriesMap() {
		return categoriesMap;
	}

	/**
	 *This method will return the shoppingCartItemsList
	 */
	public List<ShoppingCartItems> getShoppingCartItemsList() {
		return shoppingCartItemsList;
	}

	/**
	 *This method will return the flatDiscountSlabsList
	 */
	public List<FlatDiscountSlabs> getflatDiscountSlabsList() {
		return flatDiscountSlabsList;
	}

	
	public static void main(String args[]) {
		BillProcessing billProcess = new BillProcessing();
		
		try {
			billProcess.jsonReader(CATEGORIES_DATA_FILEPATH);
			billProcess.jsonReader(FLAT_DISCOUNT_SLABS_DATA_FILEPATH);
			billProcess.jsonReader(SHOPPING_CART_DATA_FILEPATH);
		} catch (IOException | ParseException ex) {
			ex.printStackTrace();
		}
		
		billProcess.calculateBillAmount();
	}
}

