package Model;
import Enum.*;
import Interface.CashierService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Cashier extends Staff{
    private String isReceiptGenerated;
    public static List<Product> inventoryList = new ArrayList<>();

    public Cashier(String fullName, String email, String phoneNumber, Integer age, Gender gender, Double salary) {
        super(fullName, email, phoneNumber, age, gender, salary);
    }

    public Cashier() {
    }

    public List<Product> getInventoryList() {return inventoryList;}

    public void setInventoryList(Product product) {
        this.inventoryList.add(product);
    }
    public String getIsReceiptGenerated() {return isReceiptGenerated;}

    public void setIsReceiptGenerated(String isReceiptGenerated) {
        this.isReceiptGenerated = isReceiptGenerated;
    }
    public static List<Product> ImportProductFromCSV() throws IOException {
        List<String[]> string = new ArrayList<>();


        BufferedReader readFile = new BufferedReader(new FileReader("src/main/resources/ProductInTheStore.csv"));
        String line = "";

        while ((line=readFile.readLine())!=null) {
            String[] value= line.split(",");
            string.add(value);
        }

        for(int i=1; i<string.size(); i++){
            Product product = new Product();

            product.setName(string.get(i)[0]);
            product.setPrice(Double.parseDouble(string.get(i)[1]));
            product.setCategory(Category.valueOf(string.get(i)[2]));
            product.setQuantity(Integer.parseInt(string.get(i)[3]));
            product.setDiscount(Integer.parseInt(string.get(i)[4]));

            inventoryList.add(product);
        }
        return inventoryList;
    }

    public String sellProduct(Customer customer, Product... product){
        customer.addToCart(product);
        List<Product> listOfProductSold = customer.buyProduct();

        System.out.println(issueReceipt(customer, listOfProductSold));
        return "Order has been successful Processed!" ;
    }
    public String sellFromAnyQueue(List<Customer> customer){
        for (Customer cuz : customer){
            List<Product> listOfProductSold = cuz.buyProduct();
            System.out.println(issueReceipt(cuz, listOfProductSold));
        }
        return "Order has been successful Processed!" ;
    }
    public void sellFromThread(Customer customer){
        List<Product> listOfProductSold = customer.buyProduct();
    }
    public String issueReceipt(Customer customer, List<Product> product) {
        try {
            Double totalAmountSpent = 0.00;
            for (Product item : product) {
                totalAmountSpent += item.getPrice();
            }
        }catch (NullPointerException e){

        }
        return customer.getFullName() + "\n" + product;
    }
    static void setStockStatus(Product product){
        if (product.getQuantity() <= 0){
            product.setStockStatus(StockStatus.OUT_OF_STOCK);
        } else
            product.setStockStatus(StockStatus.AVAILABLE);
    }
}

