import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;


public class ProductTest {
  
	@Rule
	public DatabaseRule database = new DatabaseRule();
	
	Product newProduct = new Product("flu-gone", "flu-meds", 15);
	Product anotherProduct = new Product("flu-gone", "flu-meds", 15);
	Product firstProduct = new Product("panadol", "pain-killer", 20);
	
	@Test
	public void product_productInstantiatesCorrectly_true()
	{
		assertEquals(true, newProduct instanceof Product);
	}
	
	@Test
	public void getters_getCorrectValue_true(){
		assertEquals("flu-gone", newProduct.getName());
		assertEquals("flu-meds", newProduct.getDescription());
		assertEquals(15, newProduct.getPrice());
		assertEquals(0, newProduct.getQuantity());	
	}
	
	@Test
	public void equals_returnsTrueIfattributesAreSame_true(){
		assertTrue(newProduct.equals(anotherProduct));
	}
	
	@Test
	public void save_savesIntoDatabase_true(){
		newProduct.save();
		assertEquals(true, Product.all().get(0).equals(newProduct));
	}
	
	@Test
	public void all_returnsAllProducts_true(){
		newProduct.save();
		firstProduct.save();
		assertEquals(true, Product.all().get(0).equals(newProduct));
		assertEquals(true, Product.all().get(1).equals(firstProduct));
	}
	
	@Test
	public void save_assignsIdToObject(){
		newProduct.save();
		Product savedProduct = Product.all().get(0);
		assertEquals(newProduct.getId(),savedProduct.getId());
	}
	
	@Test
	public void find_returnsProductWithSameId_true(){
		newProduct.save();
		anotherProduct.save();
		assertEquals(Product.find(anotherProduct.getId()),anotherProduct);
	}
	
	@Test
	public void update_updatesHardware_true(){
		newProduct.save();
		newProduct.update("panadol", "pain-killer", 7);
		assertEquals("panadol",Product.find(newProduct.getId()).getName());
		assertEquals("pain-killer",Product.find(newProduct.getId()).getDescription());
		assertEquals(7,Product.find(newProduct.getId()).getPrice());
		
	}
	
	@Test
	public void delete_deletesHardware_null(){
		newProduct.save();
		int deletedId = newProduct.getId();
		newProduct.delete();
		assertEquals(null, Product.find(deletedId));
	}
		
//	 @Test(expected = UnsupportedOperationException.class)
//     public void inventory_throwsExceptionIfInventoryLevelIsAtMinValue(){
//			Product newProduct = new Product("flu-gone", "flu-meds", 15);
//       for(int i = Product.MIN_QUANTITY; i <= (Product.MAX_QUANTITY); i++){
//         newProduct.depleteQuantity();
//       }
//    }
		
		
}



