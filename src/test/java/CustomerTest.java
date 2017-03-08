import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class CustomerTest {
  @Test
    public void constructor_customerInstantiatesCorrectly_true() {
      Customer customerOne = new Customer("Brian", "Brian@email.com");
      customerOne.save();
      assertTrue(customerOne instanceof Customer);
    }

    @Test
    public void getName_getsCorrectCustomerName_true() {
      Customer customerOne = new Customer("Brian", "Brian@email.com");
      customerOne.save();
      assertEquals("Brian", customerOne.getName());
    }

    @Test
    public void getEmail_getsCorrectCustomerEmail_true() {
      Customer customerOne = new Customer("Brian", "Brian@email.com");
      customerOne.save();
      assertEquals("Brian@email.com", customerOne.getEmail());
    }

    @Test
    public void save_savesIntoDatabase_true() {
      Customer customerOne = new Customer("Brian", "Brian@email.com");
      customerOne.save();
      assertEquals(true, Customer.all().get(0).equals(customerOne));
    }
    
    @Test
    public void all_returnsAllCustomers_true () {
      Customer customerOne = new Customer("Brian", "Brian@email.com");
      customerOne.save();
      Customer customerTwo = new Customer("ahmed", "norbixin@email.com");
      customerTwo.save();
      assertEquals(true, Customer.all().get(0).equals(customerOne));
      assertEquals(true, Customer.all().get(1).equals(customerTwo));
    }

    @Test
    public void equals_recognizesSameValues_true () {
      Customer customerOne = new Customer("Brian", "Brian@email.com");
      customerOne.save();
      Customer customerTwo = new Customer("Brian", "Brian@email.com");
      customerTwo.save();
      assertEquals(true, customerOne.equals(customerTwo));
    }

    @Test
    public void find_returnsCustomerWithSameId_true() {
      Customer customerOne = new Customer("Brian", "Brian@email.com");
      customerOne.save();
      Customer customerTwo = new Customer("ahmed", "norbixin@email.com");
      customerTwo.save();
      assertEquals(Customer.find(customerTwo.getId()).getName(), customerTwo.getName());
    }

    @Test
    public void update_updatesCustomer_true() {
      Customer customerTwo = new Customer("ahmed", "norbixin@email.com");
      customerTwo.save();
      customerTwo.update("ann", "ann@email.com");
      assertEquals("ann", Customer.find(customerTwo.getId()).getName());
      assertEquals("ann@email.com", Customer.find(customerTwo.getId()).getEmail());
    }

    @Test
    public void delete_deletesCustomer_null() {
      Customer customerTwo = new Customer("ahmed", "norbixin@email.com");
      customerTwo.save();
      int deletedId = customerTwo.getId();
      customerTwo.delete();
      assertEquals(null, Customer.find(deletedId));
    }

    @Test
    public void findName_findsCustomerByName_Brian() {
      Customer customerOne = new Customer("Brian", "Brian@email.com");
      customerOne.save();
      Customer customerTwo = new Customer("ahmed", "norbixin@email.com");
      customerTwo.save();
      assertEquals(customerOne, Customer.findName("Brian"));
    }
    

  }
