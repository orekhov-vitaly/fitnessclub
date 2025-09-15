package app.domain;

import java.util.Objects;

public class Training {
    private int id;
    private String title;
    private double price;
    private int durationInDays;
    private boolean active;

    private final static String COLOR_BLACK = "\u001B[0m";

    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_RED = "\u001B[31m";
    public static final String COLOR_GREEN = "\u001B[32m";
    public static final String COLOR_YELLOW = "\u001B[33m";
    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_PURPLE = "\u001B[35m";
    public static final String COLOR_CYAN = "\u001B[36m";

    public static final String COLOR_WHITE = "\u001B[37m";

    public Training() {
    }

    public Training(String title, int durationInDays) {
        this.title = title;
        this.durationInDays = durationInDays;
    }

    public Training(String title, double price, int durationInDays) {
        this.title = title;
        this.price = price;
        this.durationInDays = durationInDays;
    }

    public Training(int id, int durationInDays) {
        this.id = id;
        this.durationInDays = durationInDays;
    }

    public Training(int id, String title, double price, int durationInDays, boolean active) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.durationInDays = durationInDays;
        this.active = active;
    }

    public Training(int id, String title, double price, int durationInDays) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.durationInDays = durationInDays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Training training = (Training) o;
        return id == training.id && Double.compare(price, training.price) == 0 && durationInDays == training.durationInDays && active == training.active && Objects.equals(title, training.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, durationInDays, active);
    }

    @Override
    public String toString() {
        return String.format(COLOR_BLUE + "Тренировки: id - %d, название - %s, цена - %.2f, длительность в днях - %d, активна - %b" + COLOR_RESET, id, title, price, durationInDays, active);
    }
}
