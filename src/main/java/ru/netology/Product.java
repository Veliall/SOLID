package ru.netology;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Igor Khristiuk
 */
public class Product implements Rating {

    private final String productID;
    private String name;
    private double cost;
    private List<Integer> ratingList;

    public Product(String name, double cost) {
        this.productID = UUID.randomUUID().toString();
        this.name = name;
        this.cost = cost;
        this.ratingList = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productID.equals(product.productID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getProductID() {
        return productID;
    }

    @Override
    public String toString() {
        return "Название продукта = " + name +
                ", стоимость = " + cost;
    }

    @Override
    public void addRating(int grade) {
        ratingList.add(grade);
    }

    @Override
    public double rating() {
        double sumGrades = 0.0;
        for (Integer integer : ratingList) {
            sumGrades += integer;
        }

        return sumGrades / ratingList.size();
    }
}
