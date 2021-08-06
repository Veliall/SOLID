package ru.netology;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Igor Khristiuk
 * SOLID - Liskov substitution principle
 * Молоко является продуктом и расширяет дополнительными возможностями, например храня дату выпуска.
 * Молоко везде может выступать в качестве продукта.
 */
public class Milk extends Product {

    private final Calendar dateOfManufacture;

    public Milk(String name, double cost, Calendar dateOfManufacture) {
        super(name, cost);
        this.dateOfManufacture = dateOfManufacture;
    }

    public Calendar getDateOfManufacture() {
        return dateOfManufacture;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        return "Молоко = " + this.getName()  +
                ", стоимость = " + this.getCost()  +
                ", дата изготовления = " + dateFormat.format(getDateOfManufacture().getTime());
    }

}
