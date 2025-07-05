import java.util.Date;

public class ShippableProduct extends Product implements Shippable{
    private double weight;

    public ShippableProduct(String name, double price, int quantity, Date expireDate, double weight) {
        super(name, price, quantity, expireDate);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
