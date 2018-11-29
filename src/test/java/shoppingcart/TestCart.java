package shoppingcart;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import junit.framework.Assert;
import shoppingcart.BillProcessing;

public class TestCart {
	
	private BillProcessing billprocess;
	
	@Rule
	public final ExpectedException exc = ExpectedException.none();
	
	@Before
	public void init(){
		billprocess = new BillProcessing();
	}
	
	@Test
	public void testReadCategories() throws IOException, ParseException{
		billprocess.jsonReader("JSONData\\Categories.json");
		
		Assert.assertEquals("BabyProducts",billprocess.getCategoriesMap().get(4).getName());
		Assert.assertEquals(10.0,billprocess.getCategoriesMap().get(4).getDiscPerc(),1);
				
	}
	
	@Test
	public void testReadFlatDiscountSlabs() throws IOException, ParseException{
		billprocess.jsonReader("JSONData\\FlatDiscountSlabs.json");
		
		Assert.assertEquals(3001,billprocess.getflatDiscountSlabsList().get(1).getRangeMin());
		Assert.assertEquals(7000,billprocess.getflatDiscountSlabsList().get(1).getRangeMax());
		Assert.assertEquals(4.0,billprocess.getflatDiscountSlabsList().get(1).getDiscPerc(),1);
		
	}
	
	@Test
	public void testReadShoppingCart() throws IOException, ParseException{
		billprocess.jsonReader("JSONData\\ShoppingCart.json");
		
		Assert.assertEquals("Organic Tomatoes",billprocess.getShoppingCartItemsList().get(2).getItemName());
		Assert.assertEquals(2,billprocess.getShoppingCartItemsList().get(2).getQuantity());
		
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testFileNotFoundException() throws IOException, ParseException{
		billprocess.jsonReader("demo.json");
	}
	
	@Test
	public void testCalculateBillAmount() throws IOException, ParseException{
		billprocess.jsonReader("JSONData\\Categories.json");
		billprocess.jsonReader("JSONData\\FlatDiscountSlabs.json");
		billprocess.jsonReader("JSONData\\ShoppingCart.json");
		
		Assert.assertEquals("Bill Amount check",1601.516,billprocess.calculateBillAmount(),1);
	
	}
}
