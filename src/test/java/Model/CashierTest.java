package Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import Enum.*;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

class CashierTest {
    @Test
    void SellTheProductToCustomersOnFirstComeFirstServeBasis() {
        Cashier cashier = new Cashier();

        Customer customerA = new Customer("Ben MarkA", "ben@gmail.com", "097636338", 21, Gender.Male, 10000.00, "Texas", Payment.Cash);
        Customer customerB = new Customer("Ben MarkB", "ben@gmail.com", "097636338", 21, Gender.Male, 10000.00, "Texas", Payment.Cash);
        Customer customerC = new Customer("Ben MarkC", "ben@gmail.com", "097636338", 21, Gender.Male, 10000.00, "Texas", Payment.Cash);

        Product product = new Product("Coca - Cola", 100.00, Category.Drinks, 5, 5, cashier);
        Product product1 = new Product("Pepsi", 100.00, Category.Drinks, 5, 5, cashier);
        Product product2 = new Product("Fanta", 100.00, Category.Drinks, 5, 5, cashier);

        customerA.addToCart(product);
        customerB.addToCart(product1);
        customerC.addToCart(product2);

        CustomerFIFOQueue fifoList = new CustomerFIFOQueue();
        fifoList.addCustomerToQueue(customerA);
        fifoList.addCustomerToQueue(customerB);
        fifoList.addCustomerToQueue(customerC);

        List<Customer> queueFIFO = fifoList.getQueueFIFO();

        String result = cashier.sellFromAnyQueue(queueFIFO);
        Assertions.assertEquals("Order has been successful Processed!", result);
    }


    @Test
    void SellTheProductToCustomersBasedOnNoOfQuantityPurchased() {
        Cashier cashier = new Cashier();

        Customer customerX = new Customer("Ben MarkX", "ben@gmail.com", "097636338", 21, Gender.Male, 10000.00, "Texas", Payment.Cash);
        Customer customerY = new Customer("Ben MarkY", "ben@gmail.com", "097636338", 21, Gender.Male, 10000.00, "Texas", Payment.Cash);
        Customer customerZ = new Customer("Ben MarkZ", "ben@gmail.com", "097636338", 21, Gender.Male, 10000.00, "Texas", Payment.Cash);

        Product product = new Product("Twix", 100.00, Category.Chocolates, 5, 5, cashier);
        Product product1 = new Product("Apple", 100.00, Category.Fruits, 5, 5, cashier);
        Product product2 = new Product("Spicy", 100.00, Category.Biscuits, 5, 5, cashier);

        customerY.addToCart(product, product1, product);
        customerZ.addToCart(product2);
        customerX.addToCart(product, product1, product2, product, product2);


        Queue<Customer> queue = new PriorityQueue<>(new CustomerQuantityComparator());
        queue.offer(customerX);
        queue.offer(customerY);
        queue.offer(customerZ);
        List<Customer> list = queue.stream().toList();


        String result = cashier.sellFromAnyQueue(list);
        Assertions.assertEquals("Order has been successful Processed!", result);
    }

    @Test
    void CheckTheProductQuantityAfterSales() {
        Cashier cashier = new Cashier();

        Customer customerA = new Customer("Ben MarkA", "ben@gmail.com", "097636338", 21, Gender.Male, 10000.00, "Texas", Payment.Cash);
        Customer customerB = new Customer("Ben MarkB", "ben@gmail.com", "097636338", 21, Gender.Male, 10000.00, "Texas", Payment.Cash);
        Customer customerC = new Customer("Ben MarkC", "ben@gmail.com", "097636338", 21, Gender.Male, 10000.00, "Texas", Payment.Cash);

        Product product = new Product("Twix", 100.00, Category.Chocolates, 10, 5, cashier);


        customerA.addToCart(product);
        customerB.addToCart(product);
        customerC.addToCart(product);


        Queue<Customer> queue = new PriorityQueue<>(new CustomerQuantityComparator());
        queue.offer(customerA);
        queue.offer(customerB);
        queue.offer(customerC);


        BlockingQueue<Customer> list = new LinkedBlockingQueue<>();
        while(!queue.isEmpty()) {
            list.offer(queue.poll());
        }

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Runnable runnable1 = new MyMultiThread(list);
        Runnable runnable2 = new MyMultiThread(list);
        Runnable runnable3 = new MyMultiThread(list);

        Assertions.assertEquals(product.getQuantity(),10);

        executorService.execute(runnable1);
        executorService.execute(runnable2);
        executorService.execute(runnable3);



        executorService.shutdown();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Integer result = product.getQuantity();


        Assertions.assertEquals(product.getQuantity(),7);
    }
}