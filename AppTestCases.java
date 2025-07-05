import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class AppTestCases {
    public static void testCustomerCases() {
        System.out.println("\n Customer Tests");

        // Case 1: Valid Customer
        try {
            Customer c1 = new Customer("Omar", 100);
            System.out.println("Cases1:Created customer " + c1.getName());
        } catch (Exception e) {
            System.out.println("Case1: " + e.getMessage());
        }

        // Case 2: Empty name
        try {
            Customer c2 = new Customer("   ", 50);
            System.out.println("Case2: Should have faild");
        } catch (Exception e) {
            System.out.println("Case2: " + e.getMessage());
        }

        // Case3: Add negative balance
        try {
            Customer c3 = new Customer("Omar", 100);
            c3.addToBalance(-50);
            System.out.println("Case3: Should have failed");
        } catch (Exception e) {
            System.out.println("Case3: " + e.getMessage());
        }

        // Case4: Subtract > balance
        try {
            Customer c4 = new Customer("Omar", 100);
            c4.subtractFromBalance(150);
            System.out.println("Case4: Should have failed");
        } catch (Exception e) {
            System.out.println("Case4: " + e.getMessage());
        }

        // Case5: Valid subtract
        try {
            Customer c5 = new Customer("Omar", 200);
            c5.subtractFromBalance(50);
            System.out.println("Case5: Balance after subtract = " + c5.getBalance());
        } catch (Exception e) {
            System.out.println("Case5: " + e.getMessage());
        }
    }

    public static void testProductCases() {
        System.out.println("\n Product Tests");

        // Case1: valid product
        try {
            Product p1 = new Product("TV", 10, 5, null);
            System.out.println("Case1: Created product " + p1.getName());
        } catch (Exception e) {
            System.out.println("Case1: " + e.getMessage());
        }

        // Case2: Create product with empty name
        try {
            Product p2 = new Product("", 10, 5, null);
            System.out.println("Case2: Should have failed");
        } catch (Exception e) {
            System.out.println("Case2: " + e.getMessage());
        }

        // Case3: Create product with negative price
        try {
            Product p3 = new Product("TV", -5, 5, null);
            System.out.println("case3: Should have failed");
        } catch (Exception e) {
            System.out.println("case3: " + e.getMessage());
        }

        // Case4: Create product with negative quantity
        try {
            Product p4 = new Product("TV", 5, -3, null);
            System.out.println("Case4: Should have failed");
        } catch (Exception e) {
            System.out.println("Case4: " + e.getMessage());
        }

        // Case5: Set expire date in the past
        try {
            Date past = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
            Product p5 = new Product("Milk", 10, 5, past);
            System.out.println("Case5: Should have failed");
        } catch (Exception e) {
            System.out.println("Case5: " + e.getMessage());
        }

        // Case6: Create non-expirable product
        try {
            Product p6 = new Product("Milk", 15, 2, null);
            System.out.println("Case6: isExpirable = " + p6.isExpirable());
        } catch (Exception e) {
            System.out.println("Case6: " + e.getMessage());
        }
    }

    public static void testShippableProductCases() {
        System.out.println("\n ShippableProduct Tests");

        // Case1: Create valid shippable product
        try {
            Product p = new ShippableProduct("TV", 300, 1, null, 15);
            Shippable shippableProduct = (Shippable) p;
            System.out.println("Case1: Weight = " + shippableProduct.getWeight());
        } catch (Exception e) {
            System.out.println("Case1: " + e.getMessage());
        }
    }

    public static void testCartCases() {
        System.out.println("\nCart Tests");

        // Case1: increase the same product
        try {
            Cart cart = new Cart();
            Product p1 = new Product("TV", 5, 3, null);
            Product p2 = new Product("Phone", 7, 5, null);
            cart.addProduct(p1, 2);
            cart.addProduct(p2, 3);
            cart.addProduct(p1, 1); // Should increase p1 quantity to 3
            System.out.println("Case1: Items count = " + cart.getItems().size() + " (expected 2)");

            // Check p1 quantity
            for (CartItem item : cart.getItems()) {
                if (item.getProduct().equals(p1)) {
                    System.out.println("Case1: TV quantity = " + item.getQuantity() + " (expected 3)");
                }
            }
        } catch (Exception e) {
            System.out.println("Case1: " + e.getMessage());
        }
    }

    public static void testCartItemCases() {
        System.out.println("\n CartItem Tests");

        // Case1: Increase quantity normally
        try {
            Product p = new Product("TV", 3, 10, null);
            CartItem item = new CartItem(p, 2);
            item.increaseQuantity(3);
            System.out.println("Case1: Quantity after increase = " + item.getQuantity() + " (expected 5)");
        } catch (Exception e) {
            System.out.println("Case1: " + e.getMessage());
        }

    }

    public static void testShippingServiceCases() {
        System.out.println("\nShippingService Tests");

        // Case1: Calculate fees and print shipping notice
        try {
            Product p = new ShippableProduct("Chair", 100, 5, null, 2);
            CartItem item = new CartItem(p, 2);
            List<CartItem> items = List.of(item);
            ShippingService service = new ShippingService();
            double fees = service.calcFees(items);
            System.out.println("Case1: Shipping Fees = " + fees + " (expected 120)");
            service.shipNotice(items);
        } catch (Exception e) {
            System.out.println("Case1: " + e.getMessage());
        }
    }

    public static void testCheckoutServiceCases() {
        System.out.println("\nCheckoutService Tests");

        // Case1: valid Checkout
        try {
            Customer c = new Customer("Omar", 1000);
            Product p1 = new Product("Book", 50, 10, null);
            Product p2 = new ShippableProduct("Laptop", 300, 2, null, 5);
            c.addProduct(p1, 2);
            c.addProduct(p2, 1);
            CheckoutService checkout = new CheckoutService();
            checkout.checkout(c, c.getCart());
            System.out.println("Case1: Checkout succeeded");
        } catch (Exception e) {
            System.out.println("Case1: " + e.getMessage());
        }

        // Case2: Empty Cart
        try {
            Customer c = new Customer("Omar", 1000);
            CheckoutService checkout = new CheckoutService();
            checkout.checkout(c, c.getCart());
            System.out.println("Case2: Should have failed");
        } catch (Exception e) {
            System.out.println("Case2: " + e.getMessage());
        }

        // Case3: product is expired
        try {
            Customer c = new Customer("Omar", 1000);
            Cart cart = c.getCart();
            Date past = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
            Product expired = new Product("Milk", 5, 5, past);
            cart.addProduct(expired, 1);
            CheckoutService checkout = new CheckoutService();
            checkout.checkout(c, cart);
            System.out.println("Case3: Should have failed due to expired items");
        } catch (Exception e) {
            System.out.println("Case3: " + e.getMessage());
        }

        // Case4: Out Of Stock item
        try {
            Customer c = new Customer("Omar", 1000);
            Cart cart = c.getCart();
            Product p = new Product("TV", 5, 1, null);
            cart.addProduct(p, 2);
            CheckoutService checkout = new CheckoutService();
            checkout.checkout(c, cart);
            System.out.println("Case4: Should have failed");
        } catch (Exception e) {
            System.out.println("Case4: " + e.getMessage());
        }
    }
}
