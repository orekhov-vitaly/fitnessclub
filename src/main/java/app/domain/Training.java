package app.domain;

import java.util.Objects;

public class Training {
    private int id;
    private String title;
    private double price;
    private int durationInDays;
    private boolean active;

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
        return "Training{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", durationInDays=" + durationInDays +
                ", active=" + active +
                '}';
    }
}
