package Model;
import Enum.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void buyProductFromTheInventoryLIstWhenWalletAmountIsEnough() {
        Customer customer = new Customer("Ben Mark", "ben@gmail.com", "097636338",
                21, Gender.Male, 10000.00, "Texas", Payment.Cash);


        customer.getProductFromInventory("Apple", 4);
        customer.getProductFromInventory("tangerine", 2);
        customer.getProductFromInventory("pure heaven", 2);

        List<Product> result = customer.buyProduct();

        Assertions.assertEquals(customer.getItemsBought(), result);
    }

    @Test
    void doNotBuyProductFromTheInventoryLIstWhenWalletAmountIsNotEnough() {
        Customer customer = new Customer("Ben Mark", "ben@gmail.com", "097636338",
                21, Gender.Male, 100.00, "Texas", Payment.Cash);


        customer.getProductFromInventory("Apple", 4);
        customer.getProductFromInventory("tangerine", 2);
        customer.getProductFromInventory("pure heaven", 2);

        List<Product> result = customer.buyProduct();

        Assertions.assertEquals(customer.getItemsBought(), result);
    }


}