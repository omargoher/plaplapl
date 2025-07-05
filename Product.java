import java.util.Date;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private Date expireDate;

    public Product(String name, double price, int quantity, Date expireDate){
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setExpireDate(expireDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new RuntimeException("Product Name cannot be empty.");
        }
        this.name = name.trim();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price < 0){
            throw new RuntimeException("Product Price cannot be less than 0.");
        }
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity < 0){
            throw new RuntimeException("Product Quantity cannot be less than 0.");
        }
        this.quantity = quantity;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        if(expireDate != null && expireDate.before(new Date())){
            throw new RuntimeException("Product Expire date cannot be in the past.");
        }
        this.expireDate = expireDate;
    }

    public boolean isExpirable(){
        return expireDate != null;
    }

    public boolean isShippable(){
        return this instanceof Shippable;
    }
}
