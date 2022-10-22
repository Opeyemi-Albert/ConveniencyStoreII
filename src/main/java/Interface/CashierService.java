package Interface;
import Enum.*;
import Model.Customer;
import Model.Product;

import java.io.IOException;
import java.util.List;

public interface CashierService {

   String sellProduct(Customer customer, Product... products);

   String issueReceipt(Customer customer, List<Product> listOfProductSold);

   String importProductFromCSV(String path) throws IOException;


}
