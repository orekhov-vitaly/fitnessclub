package app.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client {
    private int id;
    private String name;
    private boolean active;
    private List<Training> trainings = new ArrayList<>();

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public Client(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && active == client.active && Objects.equals(name, client.name) && Objects.equals(trainings, client.trainings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, active, trainings);
    }

    @Override
    public String toString() {
        return String.format("Клиент: id - %d, имя - %s, активен - %b, список тренировок - %s", id, name, active, trainings);
    }
}
