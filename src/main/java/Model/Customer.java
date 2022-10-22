package Model;

import Enum.Payment;
import Enum.Gender;
import Interface.CustomerService;
import java.util.*;


public class Customer extends Person implements CustomerService {
    private Double walletAmount;
    private String location;
    private Payment payment;
    private Integer quantity = 0;
    private Map<Product, Integer> cart = new HashMap<>();
    private Double totalCostOfProductInCart = 0.00;


    public Customer(String fullName, String email, String phoneNumber, Integer age, Gender gender, Double walletAmount, String location, Payment payment) {
        super(fullName, email, phoneNumber, age, gender);
        this.walletAmount = walletAmount;
        this.location = location;
        this.payment = payment;
    }

    public Customer() {
    }

    public Double getWalletAmount() {
        return walletAmount;
    }

    public void setWalletAmount(Double walletAmount) {
        this.walletAmount = walletAmount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;

    }
    public Map<Product, Integer> getCart() {
        return cart;
    }

    public Product[] addToCart(Product... product) {
        for (Product item : product) {
            if (cart.containsKey(item)) {
                cart.replace(item, cart.get(item) + 1);
            } else {
                cart.put(item, 1);
            }
            totalCostOfProductInCart += item.getPrice();
            setQuantity(getQuantity()+1);

        }
        return product;
    }

    public Double getTotalCostOfProductInCart() {
        return totalCostOfProductInCart;
    }

    public void setTotalCostOfProductInCart(Double totalCostOfProductInCart) {
        this.totalCostOfProductInCart = totalCostOfProductInCart;
    }
    List<Product> itemsBought = new ArrayList<>();

    public List<Product> getItemsBought() {
        return itemsBought;
    }

    public void setItemsBought(List<Product> itemsBought) {
        this.itemsBought = itemsBought;
    }

    @Override
    public List<Product> buyProduct() {
//        System.out.println(this.getFullName()+" Yeah");
        if (walletAmount >= totalCostOfProductInCart) {

            for (Map.Entry<Product, Integer> item : getCart().entrySet()) {

                    if (item.getKey().getQuantity() >= item.getValue()) {
                        //update product stock quantity
                        synchronized (this) {
                            item.getKey().setQuantity(item.getKey().getQuantity() - item.getValue());
                        }
                        //update customer wallet
                        setWalletAmount(getWalletAmount() - item.getKey().getPrice() * item.getValue());
                        //update total cost of product in cart
                        setTotalCostOfProductInCart(getWalletAmount() - item.getKey().getPrice() * item.getValue());
                        //add product to itemsBought List
                        itemsBought.add(item.getKey());
                    } else {
                        System.out.println("This Product was not purchased" + item.getKey());
                    }
            }
            cart.clear();
            setQuantity(0);
            return itemsBought;
        } else {
            System.out.println("You have Insufficient Fund!");
        }
        return null;
    }
        @Override
        public String getProductFromInventory(String name, Integer quantity){
        //loop the inventory list and search through for a product with the given 'name'
        for(Product item: Cashier.inventoryList) {
            //Compare the item name with the parameter name
           if (item.getName().equalsIgnoreCase(name)){
               for (int i =0; i<quantity; i++){
                   addToCart(item);
               }
               return "Your product has been added to cart Successfully!";
            }
        }
        return "Your Product was not found.";
    }

}