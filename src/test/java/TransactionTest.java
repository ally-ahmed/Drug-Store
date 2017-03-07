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
    Clothing productOne = new Clothing("T-shirt", "100% cotton blend", 15);
    productOne.save();
    Customer customerOne = new Customer("Andrew", "andrew@email.com", "2270 Portland Pl. Portland, OR 97210");
    customerOne.save();
    Transaction transactionOne = new Transaction(productOne.getId(), customerOne.getId());
    transactionOne.save();
    assertTrue(transactionOne instanceof Transaction);
  }
	
}