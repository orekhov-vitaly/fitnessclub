package app.exceptions;

public class ClientNotFoundException extends Exception{
    public ClientNotFoundException(int id) {
        super(String.format("Клиент с идентификатором %d не найден", id));
    }

    public ClientNotFoundException(String name) {
        super(String.format("Клиент с именем %s не найден", name));
    }
}
