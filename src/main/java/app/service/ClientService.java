package app.service;

import app.domain.Client;
import app.domain.Training;
import app.exceptions.ClientNotFoundException;
import app.exceptions.ClientSaveException;
import app.exceptions.ClientUpdateException;
import app.exceptions.TrainingNotFoundException;
import app.repository.ClientRepository;

import java.io.IOException;
import java.util.List;

public class ClientService {
    private final ClientRepository repository;
    private final TrainingService trainingService;

    public ClientService() throws IOException {
        repository = new ClientRepository();
        trainingService = new TrainingService();
    }

    public Client save(Client client) throws ClientSaveException, IOException {
        if (client == null) {
            throw new ClientSaveException("Клиент не может быть null");
        }

        String name = client.getName();
        if (name == null || name.trim().isEmpty()) {
            throw new ClientSaveException("Имя клиента не может быть пустым");
        }

        client.setActive(true);
        return repository.save(client);
    }

    public List<Client> getAllActiveClients() throws IOException {
        return repository.findAll()
                .stream()
                .filter(Client::isActive)
                .toList();
    }

    public Client getActiveClientById(int id) throws IOException, ClientNotFoundException {
        Client client = repository.findById(id);

        if (client == null || !client.isActive()) {
            throw new ClientNotFoundException(id);
        }

        return client;
    }

    public void update(Client client) throws ClientUpdateException, IOException {
        if (client == null) {
            throw new ClientUpdateException("Клиент не может быть null");
        }

        String name = client.getName();
        if (name == null || name.trim().isEmpty()) {
            throw new ClientUpdateException("Имя клиента не может быть пустым");
        }

        client.setActive(true);
        repository.update(client);
    }

    public void deleteById(int id) throws IOException, ClientNotFoundException {
        Client client = getActiveClientById(id);
        client.setActive(false);
        repository.update(client);
    }

    public void deleteByName(String name) throws IOException, ClientNotFoundException {
        Client client = getAllActiveClients()
                .stream()
                .filter(x -> x.getName().equals(name))
                .peek(x -> x.setActive(false))
                .findFirst()
                .orElseThrow(
                        () -> new ClientNotFoundException(name)
                );
        repository.update(client);
    }

    public void restoreById(int id) throws IOException, ClientNotFoundException {
        Client client = repository.findById(id);

        if (client != null) {
            client.setActive(true);
            repository.update(client);
        } else {
            throw new ClientNotFoundException(id);
        }
    }

    public int getActiveClientsCount() throws IOException {
        return getAllActiveClients().size();
    }

    public double getClientTrainingsTotalPrice(int id) throws IOException, ClientNotFoundException {
        return getActiveClientById(id).getTrainings()
                .stream()
                .filter(Training::isActive)
                .mapToDouble(Training::getPrice)
                .sum();
    }

    public double getClientTrainingsAveragePrice(int id) throws IOException, ClientNotFoundException {
        return getActiveClientById(id)
                .getTrainings()
                .stream()
                .filter(Training::isActive)
                .mapToDouble(Training::getPrice)
                .average()
                .orElse(0.0);
    }

    public void addTrainingToClient(int clientId, int trainingId) throws IOException, ClientNotFoundException, TrainingNotFoundException {
        Client client = getActiveClientById(clientId);
        Training training = trainingService.getActiveTrainingById(trainingId);
        client.getTrainings().add(training);
        repository.update(client);
    }

    public void removeTrainingFromClient(int clientId, int productId) throws IOException, ClientNotFoundException, TrainingNotFoundException {
        Client client = getActiveClientById(clientId);
        Training training = trainingService.getActiveTrainingById(productId);
        client.getTrainings().remove(training);
        repository.update(client);
    }

    public void clearClientTrainings(int id) throws IOException, ClientNotFoundException {
        Client client = getActiveClientById(id);
        client.getTrainings().clear();
        repository.update(client);
    }
}
