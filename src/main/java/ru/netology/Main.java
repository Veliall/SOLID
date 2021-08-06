package ru.netology;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * @author Igor Khristiuk
 * SOLID - Dependency inversion principle
 * При продаже мы фиксируем логером в консоль, при желании можно будет легко поменять на фиксацию в файл
 * просто добавим новую имплементацию класса Логгер и изменив конструктор.
 */
public class Main {

    public static void main(String[] args) {
        Market market = Market.getInstance();

        Product milkKaloriya = new Milk("Калория", 80, Calendar.getInstance());
        Product milkKaloriyaOld =
                new Milk("Калория", 80, new GregorianCalendar(2021, Calendar.AUGUST, 1));
        Product milkCow = new Milk("Коровка из Кореновки", 67, Calendar.getInstance());
        Product bread = new Product("Хлебушек", 50);
        Product meat = new Product("Мясо", 300);

        market.addProduct(meat, 20)
                .addProduct(bread, 30)
                .addProduct(milkCow, 40)
                .addProduct(milkKaloriya, 10)
                .addProduct(milkKaloriya, 10)
                .addProduct(milkKaloriyaOld, 20);

        User user = new User();
        System.out.println("Добро пожаловать в наш магазин\n" +
                "Вам доступно:");
        printMenu();

        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!input.equals("0")) {
            input = scanner.nextLine();
            switch (input) {
                case "1" -> System.out.println(market);
                case "2" -> {
                    try {
                        user.addProduct(searchProduct(scanner, market));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case "3" -> System.out.println(user.getProducts());
                case "4" -> {
                    try {
                        user.removeProduct(searchProduct(scanner, market));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case "5" -> {
                    Logger logger = new LoggerForSales();
                    logger.log("Продано:\n" + user.getProducts());
                    user.buyProducts(market);
                }
                case "6" -> {
                    try {
                        System.out.println((searchProduct(scanner, market)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case "7" -> {
                    try {
                        Product product = (searchProduct(scanner, market));
                        System.out.println("Введите оценку от 1 до 5 включительно");
                        product.addRating(Integer.parseInt(scanner.nextLine()));
                        System.out.println("Спасибо за ваше мнение! Средняя оценка товара равна");
                        System.out.println(product.rating());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
            printMenu();
        }
    }

    public static void printMenu() {
        System.out.println("1. Просмотреть список доступных товаров\n" +
                "2. Добавить товар в корзину\n" +
                "3. Просмотреть товары в корзине\n" +
                "4. Удалить товары из корзины\n" +
                "5. Купить товары из корзины\n" +
                "6. Выполнить поиск товара по названию\n" +
                "7. Оставить оценку от 1 до 5 к товару\n" +
                "0. Выход из программы");
    }

    //DRY
    public static Product searchProduct(Scanner scanner, Market market) throws Exception {
        System.out.println("Введите название продукта");
        String productName = scanner.nextLine();
        if (market.search(productName) != null) {
            return market.search(productName);
        } else {
            throw new Exception("Данный продукт не найден");
        }
    }
}
