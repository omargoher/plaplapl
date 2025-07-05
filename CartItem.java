public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        increaseQuantity(quantity);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int amount) {
        if (amount <= 0){
            throw new RuntimeException("Quantity must be greater than 0.");
        }
        quantity += amount;
    }

    public void decreaseQuantity(int amount) {
        if (amount <= 0){
            throw new RuntimeException("Quantity must be greater than 0.");
        }
        if(amount > quantity){
            quantity = 0;
        }
        quantity -= amount;
    }

    @Override
    public String toString() {
        return quantity + "x" + " " + product.getName() + " price = " + product.getPrice() * quantity;
    }
}
