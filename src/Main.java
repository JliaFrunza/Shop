import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();

        store.addProduct(new Product("Телефон", "Samsung", 10000));
        store.addProduct(new Product("Ноутбук", "Apple", 50000));
        store.addProduct(new Product("Наушники", "Sony", 2000));

        List<Product> availableProducts = store.getAvailableProducts();
        System.out.println("Доступные товары:");
        for (Product product : availableProducts) {
            System.out.println(product);
        }

        List<Product> filteredProducts = store.filterProductsByManufacturer("Samsung");
        System.out.println("\nТовары производителя Samsung:");
        for (Product product : filteredProducts) {
            System.out.println(product);
        }

        User user = new User("Иван");

        user.addToCart(availableProducts.get(0));
        user.addToCart(availableProducts.get(1));

        Order order = user.checkout();
        System.out.println("\nЗаказ пользователя " + user.getName() + ":");
        for (Product product : order.getProducts()) {
            System.out.println(product.getName());
        }

        order.trackOrder();

        user.returnOrder(order);
    }
}

class Store {
    private List<Product> products;

    public Store() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getAvailableProducts() {
        return products;
    }

    public List<Product> filterProductsByManufacturer(String manufacturer) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getManufacturer().equals(manufacturer)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
}

class User {
    private String name;
    private List<Product> cart;

    public User(String name) {
        this.name = name;
        this.cart = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addToCart(Product product) {
        cart.add(product);
    }

    public Order checkout() {
        return new Order(cart);
    }

    public void returnOrder(Order order) {
        System.out.println("Заказ возвращен");
    }
}

class Product {
    private String name;
    private String manufacturer;
    private double price;

    public Product(String name, String manufacturer, double price) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public String toString() {
        return name + " - " + manufacturer + " - Цена: " + price;
    }
}

class Order {
    private List<Product> products;

    public Order(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void trackOrder() {
        System.out.println("Заказ отслеживается в системе доставки");
    }
}
