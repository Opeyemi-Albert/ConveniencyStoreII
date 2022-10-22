package Model;
import Enum.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

       Cashier cashier = new Cashier();

        Customer customerA= new Customer("Ben MarkA", "ben@gmail.com", "097636338", 21, Gender.Male, 10000.00,"Texas", Payment.Cash);
        Customer customerB= new Customer("Ben MarkB", "ben@gmail.com", "097636338", 21, Gender.Male, 10000.00,"Texas", Payment.Cash);
        Customer customerC= new Customer("Ben MarkC", "ben@gmail.com", "097636338", 21, Gender.Male, 10000.00,"Texas", Payment.Cash);
        Customer customerD= new Customer("Ben MarkD", "ben@gmail.com", "097636338", 21, Gender.Male, 10000.00,"Texas", Payment.Cash);
        Customer customerE= new Customer("Ben MarkE", "ben@gmail.com", "097636338", 21, Gender.Male, 10000.00,"Texas", Payment.Cash);
        Customer customerF= new Customer("Ben MarkF", "ben@gmail.com", "097636338", 21, Gender.Male, 10000.00,"Texas", Payment.Cash);
        Customer customerG= new Customer("Ben MarkG", "ben@gmail.com", "097636338", 21, Gender.Male, 10000.00,"Texas", Payment.Cash);
        Customer customerH= new Customer("Ben MarkH", "ben@gmail.com", "097636338", 21, Gender.Male, 10000.00,"Texas", Payment.Cash);
        Customer customerI= new Customer("Ben MarkI", "ben@gmail.com", "097636338", 21, Gender.Male, 10000.00,"Texas", Payment.Cash);
        Customer customerJ= new Customer("Ben MarkJ", "ben@gmail.com", "097636338", 21, Gender.Male, 10000.00,"Texas", Payment.Cash);

        Product product = new Product("Coca - Cola", 100.00, Category.Drinks, 20, 5, cashier );
        Product product1 = new Product("Pepsi", 100.00, Category.Drinks, 20, 5, cashier );
        Product product2 = new Product("Fanta", 100.00, Category.Drinks, 20, 5, cashier );


        customerA.addToCart(product);
        customerB.addToCart(product);
        customerC.addToCart(product);
        customerD.addToCart(product);
        customerE.addToCart(product);
        customerF.addToCart(product);
        customerG.addToCart(product);
        customerH.addToCart(product);
        customerI.addToCart(product);
        customerJ.addToCart(product);

        Queue<Customer> queue = new PriorityQueue<> (new CustomerQuantityComparator());

        queue.offer(customerA);
        queue.offer(customerB);
        queue.offer(customerC);
        queue.offer(customerD);
        queue.offer(customerE);
        queue.offer(customerF);
        queue.offer(customerG);
        queue.offer(customerH);
        queue.offer(customerI);
        queue.offer(customerJ);


        BlockingQueue<Customer> list = new LinkedBlockingQueue<>();

        while(!queue.isEmpty()) {
         list.offer(queue.poll());
        }


        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Runnable runnable1 = new MyMultiThread(list);
        Runnable runnable2 = new MyMultiThread(list);
        Runnable runnable3 = new MyMultiThread(list);
        Runnable runnable4 = new MyMultiThread(list);

        executorService.execute(runnable1);
        executorService.execute(runnable2);
        executorService.execute(runnable3);
        executorService.execute(runnable4);


        executorService.shutdown();


        Thread.sleep(2000);
        System.out.println(product.getQuantity());


    }
}