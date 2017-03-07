import org.sql2o.*;
import java.util.*;

public class Product{
	
	private int id;
	private String name;
	private String description;
	public int price;
	public int quantity;
	
	public static final int MAX_INVENTORY = 10;
	public static final int MIN_INVENTORY = 0;
	
	public String getName(){
		return name
	}
	
	 public String getDescription() {
    return description;
  }

  public int getPrice() {
    return price;
  }

  public int getId() {
    return id;
  }
	
	
  public int getQuantity() {
    return quantity;
  }
	
	public void restock(){
		quantity = MAX_QUANTITY;
		try(Connection con = DB.sql2o.open()){
			String sql = "UPDATE products SET quantity = :quantity WHERE id = :id;";
			con.createQuery(sql)
				.addParameter("quantity",quantity)
				.addParameter("id", this.id)
				.throwOnMappingFailure(false)
				.executeUpdate();
		}
	}
	
	public void save(){
		try(Connection con = DB.sql2o.open()){
			String sql = "INSERT INTO products (name,description,price,quantity)"
		}
	
	}



}