package Model;


import java.util.List;
import java.util.concurrent.BlockingQueue;

public class MyMultiThread implements Runnable{
    private BlockingQueue<Customer> customer;
    private Cashier cashier= new Cashier();

    public MyMultiThread(BlockingQueue<Customer> customer) {
        this.customer = customer;
    }

    @Override
    public void run() {

        while (true) {
            Customer customerOut = customer.poll();
            if (customerOut != null) {
                cashier.sellFromThread(customerOut);
                System.out.println(Thread.currentThread().getName() + " attended to " + customerOut.getFullName());
            } else {
                return;
            }

        }

    }

}
