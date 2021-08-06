package ru.netology;

/**
 * @author Igor Khristiuk
 */
public class LoggerForSales implements Logger{
    @Override
    public void log(String msg) {
        System.out.println(msg);
    }
}
