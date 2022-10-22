package Model;

import java.util.Comparator;


public class CustomerQuantityComparator implements Comparator<Customer> {


    @Override
    public int compare(Customer customer1, Customer customer2) {

        return customer2.getQuantity().compareTo(customer1.getQuantity());
    }


}
