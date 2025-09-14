package app.repository;


import app.domain.Client;
import app.domain.Training;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientRepository {
    private final File database;
    private final ObjectMapper mapper;
    private int maxId;

    public ClientRepository() throws IOException {
        database = new File("database/client.txt");
        mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<Client> clients = findAll();
        if (!clients.isEmpty()) {
            Client lastClient = clients.get(clients.size() - 1);
            maxId = lastClient.getId();
        }
    }

    public Client save(Client customer) throws IOException {
        customer.setId(++maxId);
        List<Client> customers = findAll();
        customers.add(customer);
        mapper.writeValue(database, customers);
        return customer;
    }

    public List<Client> findAll() throws IOException {
        try {
            Client[] clients = mapper.readValue(database, Client[].class);
            return new ArrayList<>(Arrays.asList(clients));
        } catch (MismatchedInputException e) {
            return new ArrayList<>();
        }
    }

    public Client findById(int id) throws IOException {
        return findAll()
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void update(Client client) throws IOException {
        int id = client.getId();
        String newName = client.getName();
        boolean active = client.isActive();
        List<Training> trainings = client.getTrainings();

        List<Client> clients = findAll();
        clients
                .stream()
                .filter(x -> x.getId() == id)
                .forEach(x -> {
                    x.setName(newName);
                    x.setActive(active);
                    x.setTrainings(trainings);
                });
        mapper.writeValue(database, clients);
    }

    public void deleteById(int id) throws IOException {
        List<Client> clients = findAll();
        clients.removeIf(x -> x.getId() == id);
        mapper.writeValue(database, clients);
    }
}

