import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Calendar;
import java.sql.Timestamp;

public class TransactionTest{
	
 @Rule
  public DatabaseRule database = new DatabaseRule();
	

	
	@Test
  public void constructor_transactionInstantiatesCorrectly_true() {
   	Product productOne = new Product("Panadol", "pain-killer", 15);
    productOne.save();	
 		Customer customerOne = new Customer("Andrew", "andrew@email.com");
    customerOne.save();
		Transaction transactionOne = new Transaction(productOne.getId(), customerOne.getId());
    transactionOne.save();
    assertTrue(transactionOne instanceof Transaction);
  }
	
	@Test
	public void getters_getsCorrectGetters_true(){
   Product productOne = new Product("Panadol", "pain-killer", 15);
    productOne.save();	
 		Customer customerOne = new Customer("Andrew", "andrew@email.com");
    customerOne.save();
		Transaction transactionOne = new Transaction(productOne.getId(), customerOne.getId());
    transactionOne.save();
		assertEquals(customerOne.getId(),transactionOne.getCustomerId());
		assertEquals(productOne.getId(), transactionOne.getProductId());
		assertEquals(productOne.getPrice(), transactionOne.getSalePrice());
	}
	
	@Test
	public void save_saveIntoDb_true(){
		Product productOne = new Product("Panadol", "pain-killer", 15);
    productOne.save();	
 		Customer customerOne = new Customer("Andrew", "andrew@email.com");
    customerOne.save();
		Transaction transactionOne = new Transaction(productOne.getId(), customerOne.getId());
    transactionOne.save();
		assertEquals(true, Transaction.all().get(0).equals(transactionOne));
	}
	
	@Test
	public void all_returnsAllClothing_true(){
		Product productOne = new Product("Panadol", "pain-killer", 15);
    productOne.save();
		Product productTwo = new Product("flu-gone", "flu-meds", 15);
    productTwo.save();
    Customer customerOne = new Customer("Andrew", "andrew@email.com");
    customerOne.save();
    Customer customerTwo = new Customer("Yusuf", "andrew@email.com");
    customerTwo.save();
    Transaction transactionOne = new Transaction(productOne.getId(), customerOne.getId());
    transactionOne.save();
    Transaction transactionTwo = new Transaction(productTwo.getId(), customerTwo.getId());
    transactionTwo.save();
		assertEquals(true, Transaction.all().get(0).equals(transactionOne));
    assertEquals(true, Transaction.all().get(1).equals(transactionTwo));
		
	}
	
	@Test
	public void equals_recognizesSameValues_true(){
		Product productOne = new Product("Panadol", "pain-killer", 15);
    productOne.save();
		Product productTwo = new Product("flu-gone", "flu-meds", 15);
    productTwo.save();
    Customer customerOne = new Customer("Andrew", "andrew@email.com");
    customerOne.save();
    Transaction transactionOne = new Transaction(productOne.getId(), customerOne.getId());
    transactionOne.save();
    Transaction transactionTwo = new Transaction(productOne.getId(), customerOne.getId());
    transactionTwo.save();
		assertEquals(true, transactionOne.equals(transactionTwo));
	}
	
	@Test
	public void find_returnProductsWithSameId_true(){
		Product productOne = new Product("Panadol", "pain-killer", 15);
    productOne.save();
		Product productTwo = new Product("flu-gone", "flu-meds", 15);
    productTwo.save();
    Customer customerOne = new Customer("Andrew", "andrew@email.com");
    customerOne.save();
    Customer customerTwo = new Customer("Yusuf", "andrew@email.com");
    customerTwo.save();
    Transaction transactionOne = new Transaction(productOne.getId(), customerOne.getId());
    transactionOne.save();
    Transaction transactionTwo = new Transaction(productTwo.getId(), customerTwo.getId());
    transactionTwo.save();
		assertEquals(Transaction.find(transactionTwo.getId()),transactionTwo);
		
	}
	
	@Test
	public void delete_deletesTransaction_null(){
		Product productOne = new Product("Panadol", "pain-killer", 15);
    productOne.save();	
 		Customer customerOne = new Customer("Andrew", "andrew@email.com");
    customerOne.save();
		Transaction transactionOne = new Transaction(productOne.getId(), customerOne.getId());
    transactionOne.save();
		int deletedId = transactionOne.getId();
		transactionOne.delete();
		assertEquals(null,Transaction.find(deletedId));
		
	}
	
	@Test
	public void findMonthlyTransactions_returnsCorrectTransactions_true(){
		Product productOne = new Product("Panadol", "pain-killer", 15);
    productOne.save();	
 		Customer customerOne = new Customer("Andrew", "andrew@email.com");
    customerOne.save();
		Transaction transactionOne = new Transaction(productOne.getId(), customerOne.getId());
    transactionOne.save();
		assertTrue(Transaction.findMonthlyTransactions().contains(transactionOne));
	}
	
	@Test
	public void sumMonthlySales_returnsTotalSalesForMonth_75(){
		Product productOne = new Product("Panadol", "pain-killer", 15);
    productOne.save();	
 		Customer customerOne = new Customer("Andrew", "andrew@email.com");
    customerOne.save();
		Transaction transactionOne = new Transaction(productOne.getId(), customerOne.getId());
    transactionOne.save();
		assertEquals((Integer) 15, (Integer) Transaction.sumMonthlySales());
	}
	
	
}