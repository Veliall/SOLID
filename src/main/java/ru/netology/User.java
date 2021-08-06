package ru.netology;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Igor Khristiuk
 */
public class User {

    private final String userID;
    private final List<Product> products;

    public User() {
        this.userID = UUID.randomUUID().toString();
        this.products = new ArrayList<>();
    }

    public String getUserID() {
        return userID;
    }

    public List<Product> getProducts() {
        return products;
    }

    public User addProduct(Product product) {
        this.products.add(product);
        return this;
    }

    public void buyProducts(Market market) {
        for (Product product : products) {
            market.reduceProduct(product, 1);
        }
        for (int i = 0; i < products.size(); i++) {
            products.remove(i);
        }
    }

    public List<Product> removeProduct(Product product) {
        this.products.remove(product);
        return products;
    }
}
