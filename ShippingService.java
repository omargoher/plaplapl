import java.util.List;

public class ShippingService {
    public void shipNotice(List<CartItem> shippableItems){
        System.out.println("** Shipment notice **");
        double totalWeight = 0.0;
        for (CartItem item : shippableItems) {
            Shippable shippableItem = (Shippable) item.getProduct();
            double weight = shippableItem.getWeight() * item.getQuantity();
            totalWeight +=  weight;
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + " weight= " + weight + " kg");
        }
        System.out.println("Total package weight " + totalWeight + "kg");
    }

    public double calcFees(List<CartItem> shippableItems) {
        // 1kg = 30LE
        double totalWeight = 0.0;
        for (CartItem item : shippableItems) {
            Shippable shippableItem = (Shippable) item.getProduct();
            totalWeight += shippableItem.getWeight() * item.getQuantity();
        }
        return totalWeight * 30;
    }
}
