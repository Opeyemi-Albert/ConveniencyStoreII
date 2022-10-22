package Model;
import Enum.*;
import Interface.CashierService;

public class Product {
    private String name;
    private Double price;
    private Category category;
    private volatile Integer quantity;
    private Integer discount;
    private StockStatus stockStatus;

    public Product(String name, Double price, Category category, Integer quantity, Integer discount, Cashier cashier) {
        if (cashier != null){
            this.name = name;
            this.price = price;
            this.category = category;
            this.quantity = quantity;
            this.discount = discount;
            cashier.getInventoryList().add(this);
            Cashier.setStockStatus(this);
        }
        else return;
    }

    public Product(String name, Double price, Category category, Integer quantity, Integer discount) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.discount = discount;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public synchronized Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        synchronized (this) {
            this.quantity = quantity;
            Cashier.setStockStatus(this);
        }

    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public StockStatus getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(StockStatus stockStatus) {
        this.stockStatus = stockStatus;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", quantityInStock=" + quantity +
                ", discount=" + discount +
                '}' + '\n';
    }
}
