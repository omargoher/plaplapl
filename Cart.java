import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addProduct (Product product, int quantity){
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                item.increaseQuantity(quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems(){
        return items;
    }

    public boolean hasExpiredItems(){
        for (CartItem item : items){
            if(item.getProduct().isExpirable() && item.getProduct().getExpireDate().before(new Date())){
                return true;
            }
        }
        return false;
    }

    public boolean hasOutOfStockItems(){
        for (CartItem item : items){
            if(item.getProduct().getQuantity() < item.getQuantity()){
                return true;
            }
        }
        return false;
    }

    public List<CartItem> getShippableItems(){
        List<CartItem> shippableItems = new ArrayList<>();
        for (CartItem item: items) {
            if (item.getProduct() instanceof Shippable) {
                shippableItems.add(item);
            }
        }
        return shippableItems;
    }
}
