package Interface;

import Model.Product;

import java.util.List;

public interface CustomerService {
     List<Product> buyProduct();

     public String getProductFromInventory(String name, Integer quantity);

}
