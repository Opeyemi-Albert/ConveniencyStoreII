package Model;

import java.util.ArrayList;
import java.util.List;

public class CustomerFIFOQueue{
  private  List<Customer> queueFIFO = new ArrayList<>();

    public List<Customer> getQueueFIFO() {
        return queueFIFO;
    }

    public void addCustomerToQueue(Customer customer) {
        queueFIFO.add(customer);
    }

    public Customer getNextCustomerInQueue(){
        if(queueFIFO.size()!=0){
        return queueFIFO.remove(0);
        }
        return null;
    }
}

