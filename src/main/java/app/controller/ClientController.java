package app.controller;

import app.domain.Client;
import app.exceptions.ClientNotFoundException;
import app.exceptions.ClientSaveException;
import app.exceptions.ClientUpdateException;
import app.exceptions.TrainingNotFoundException;
import app.service.ClientService;

import java.io.IOException;
import java.util.List;

public class ClientController {

    private final ClientService service;

    public ClientController() throws IOException {
        service = new ClientService();
    }

    public Client save(String name) throws IOException, ClientSaveException {
        Client client = new Client(name);
        return service.save(client);
    }

    public List<Client> getAllActiveClients() throws IOException {
        return service.getAllActiveClients();
    }

    public Client getActiveClientById(int id) throws IOException, ClientNotFoundException {
        return service.getActiveClientById(id);
    }

    public void update(int id, String name) throws ClientUpdateException, IOException {
        Client client = new Client(id, name);
        service.update(client);
    }

    public void deleteById(int id) throws IOException, ClientNotFoundException {
        service.deleteById(id);
    }

    public void deleteByName(String name) throws IOException, ClientNotFoundException {
        service.deleteByName(name);
    }

    public void restoreById(int id) throws IOException, ClientNotFoundException {
        service.restoreById(id);
    }

    public int getActiveClientsCount() throws IOException {
        return service.getActiveClientsCount();
    }

    public double getClientTrainingsTotalPrice(int id) throws IOException, ClientNotFoundException {
        return service.getClientTrainingsTotalPrice(id);
    }

    public double getClientTrainingsAveragePrice(int id) throws IOException, ClientNotFoundException {
        return service.getClientTrainingsAveragePrice(id);
    }

    public void addTrainingToClient(int clientId, int trainingId) throws IOException, ClientNotFoundException, TrainingNotFoundException {
        service.addTrainingToClient(clientId, trainingId);
    }

    public void removeTrainingFromClient(int clientId, int traningId) throws IOException, ClientNotFoundException, TrainingNotFoundException {
        service.removeTrainingFromClient(clientId, traningId);
    }

    public void clearClientTrainings(int id) throws IOException, ClientNotFoundException {
        service.clearClientTrainings(id);
    }
}
