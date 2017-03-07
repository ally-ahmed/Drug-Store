import org.sql2o.*;
import java.util.List;

public class Customer{
int id;
String name;
String email;


public Customer(String name, String email ){
  this.name = name;
  this. email = email;

}

public String getName(){
  return name;
}

public String getEmail(){
  return email;
}


public int getId(){
  return id;
}

public void save(){
  try(Connection con = DB.sql2o.open()){
    String sql = "INSERT INTO customers (name, email) VALUES (:name, :email)";
    this.id = (int) con.createQuery(sql,true)
    .addParameter("name", this.name)
    .addParameter("email", this.email)
    .executeUpdate()
    .getKey();
  }
}

public static List <Customer> all(){
  try(Connection con = DB.sql2o.open()){
    String sql = "SELECT * From customers";
    return con.createQuery(sql)
    .executeAndFetch(Customer.class);
  }
}
public static Customer find(int id) {
  try(Connection con = DB.sql2o.open()){
    String sql = "SELECT * From customers WHERE id = :id";
    Customer customer = con.createQuery(sql)
    .addParameter("id", id)
    .executeAndFetchFirst(Customer.class);
  return customer;

  }
}
public static Customer findName(String name){
  try( Connection con = DB.sql2o.open()){
    String sql = "SELECT * From customers WHERE name = :name";
    Customer customer = con.createQuery(sql)
    .addParameter("name", name)
    .executeAndFetchFirst(Customer.class);
    return customer;
  }
}
public void update(String name, String email ){
  try(Connection con = DB.sql2o.open()){
    String sql = "UPDATE customers SET name = :name, email = :email WHERE id = :id";
    con.createQuery(sql)
    .addParameter("name", name)
    .addParameter("email", email)
    .addParameter("id", this.id)
    .executeUpdate();

  }
}
@Override
public boolean equals(Object otherCustomer){
  if(!(otherCustomer instanceof Customer)){
    return false;
  }else{
    Customer newCustomer = (Customer) otherCustomer;
    return this.getName().equals(newCustomer.getName())&&
           this.getEmail().equals(newCustomer.getEmail());


  }
}
public void delete(){
  try(Connection con = DB.sql2o.open()){
    String sql = "DELETE FROM customers WHERE id =:id";
    con.createQuery(sql)
    .addParameter("id", id)
    .executeUpdate();
  }
}
}
