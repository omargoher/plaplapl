import java.util.Date;
import java.util.List;

public class CheckoutService {
    ShippingService shippingService;

    public CheckoutService(){
        this.shippingService = new ShippingService();
    }

    public void checkout(Customer customer, Cart cart){
        if(cart.getItems().isEmpty()){
            throw new RuntimeException("Cart is empty.");
        }

        if(cart.hasExpiredItems()){
            throw new RuntimeException("Cart has expired item.");
        }

        if(cart.hasOutOfStockItems()){
            throw new RuntimeException("Cart has out of stock items.");
        }

        double subTotal = calcSubTotal(cart);
        double shippingFees = calcShippingFees(cart.getShippableItems());
        double totalAmount = subTotal + shippingFees;

        shippingService.shipNotice(cart.getShippableItems());

        System.out.println("** Checkout receipt **");

        for (CartItem item : cart.getItems()){
            System.out.println(item.toString());
        }
        System.out.println("------------------------");
        System.out.println("Subtotal : " + subTotal);
        System.out.println("Shipping: " + shippingFees);
        System.out.println("Total amount: " + totalAmount);

    }

    private double calcSubTotal(Cart cart){
        double subTotal = 0.0;
        for (CartItem item : cart.getItems()){
            subTotal += item.getProduct().getPrice() * item.getQuantity();
        }
        return subTotal;
    }

    private double calcShippingFees(List<CartItem> shippableItems){
        return shippingService.calcFees(shippableItems);
    }
}
