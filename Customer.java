public class Customer {
    private String name;
    private double balance;
    private Cart cart;

    public Customer(String name, double balance) {
        setName(name);
        addToBalance(balance);
        cart = new Cart();
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

    public double getBalance() {
        return balance;
    }

    public void addToBalance(double amount) {
        if (amount < 0) {
            throw new RuntimeException("Amount to add must be positive.");
        }
        balance += amount;
    }

    public void subtractFromBalance(double amount) {
        if (amount < 0) {
            throw new RuntimeException("Amount to subtract must be positive.");
        }
        if (amount > balance) {
            throw new RuntimeException("Insufficient balance.");
        }
        balance -= amount;
    }

    public void addProduct(Product product, int quantity){
        cart.addProduct(product, quantity);
    }

    public Cart getCart(){
        return cart;
    }
}
