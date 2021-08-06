package ru.netology;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Igor Khristiuk
 * SOLID - Single responsibility principle
 * Отдельный класс магазина , который хранит данные о количестве определённого товара.
 * Технически это можно было бы хранить и в самом классе продукта, но было бы не очень логично.
 */
public class Market {

    private final HashMap<Product, Integer> productsList;
    private static Market market = null;

    private Market() {
        this.productsList = new HashMap<>();
    }

    public static Market getInstance() {
        if (market == null) market = new Market();
        return market;
    }

    public Market addProduct(Product product, int count) {
        if (!productsList.containsKey(product)) {
            productsList.put(product, count);
        } else {
            int value = productsList.get(product);
            value += count;
            productsList.put(product, value);
        }
        return this;
    }

    public Market reduceProduct(Product product, int count) {
        if (!productsList.containsKey(product)) {
            throw new IllegalArgumentException("Такого товара не существует");
        } else {
            int value = productsList.get(product);
            if (value < count) {
                throw new IllegalArgumentException("Такого количества товара нет в наличии");
            }
            value -= count;
            productsList.put(product, value);
        }
        return this;
    }

    public Product search(String name) {
        for (Product product : productsList.keySet()) {
            if (product.getName().equalsIgnoreCase(name)) return product;
        }
        return null;
    }

    //    Open-Close principle метод на вход принимает коллекцию из любых объектов реализущих интерфейс "рейтинг"
//     Если у нас добавится какой-то товар не являющийся Product (по неизвестным причинам), но реализующий интерфейс
//    переделывать метод фильтрации нам не придётся
    public List<Rating> filterByRating(List<Rating> list, double rating) {
        return productsList.keySet()
                .stream()
                .filter(x -> x.rating() >= rating)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Product, Integer> entry : productsList.entrySet()) {
            stringBuilder.append(entry.getKey())
                    .append(" в количестве ")
                    .append(entry.getValue())
                    .append(" штук")
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
