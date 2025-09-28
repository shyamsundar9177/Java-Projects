import java.util.*;

// Product class
class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }
}

// Abstract Discount class
abstract class Discount {
    public abstract double apply(List<Product> products);
}

// FestiveDiscount subclass
class FestiveDiscount extends Discount {
    @Override
    public double apply(List<Product> products) {
        double total = 0;
        for (Product p : products) {
            total += p.getTotalPrice();
        }
        return total * 0.90; // 10% off
    }
}

// BulkDiscount subclass
class BulkDiscount extends Discount {
    @Override
    public double apply(List<Product> products) {
        double total = 0;
        for (Product p : products) {
            if (p.getQuantity() > 5) {
                total += p.getTotalPrice() * 0.80; // 20% off
            } else {
                total += p.getTotalPrice();
            }
        }
        return total;
    }
}

// Payment class
class Payment {
    public void pay(double amount) {
        System.out.printf("Total Amount Payable: %.2f%n", amount);
    }
}

// Main class
public class ProductDiscount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        List<Product> products = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            double price = sc.nextDouble();
            int quantity = sc.nextInt();
            products.add(new Product(name, price, quantity));
        }

        sc.nextLine(); // consume newline
        String discountType = sc.nextLine().trim().toLowerCase();

        Discount discount;
        if (discountType.equals("festive")) {
            discount = new FestiveDiscount();
        } else if (discountType.equals("bulk")) {
            discount = new BulkDiscount();
        } else {
            // Anonymous Discount with no reduction
            discount = new Discount() {
                @Override
                public double apply(List<Product> products) {
                    double total = 0;
                    for (Product p : products) {
                        total += p.getTotalPrice();
                    }
                    return total;
                }
            };
        }

        // Output products
        for (Product p : products) {
            System.out.println("Product: " + p.getName() +
                    ", Price: " + p.getPrice() +
                    ", Quantity: " + p.getQuantity());
        }

        // Apply discount and pay
        double finalAmount = discount.apply(products);
        Payment payment = new Payment();
        payment.pay(finalAmount);

        sc.close();
    }
}
