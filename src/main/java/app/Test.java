package app;

import app.domain.Client;
import app.domain.Training;
import app.exceptions.ClientNotFoundException;
import app.exceptions.ClientSaveException;
import app.repository.ClientRepository;
import app.repository.TrainingRepository;
import app.service.ClientService;
import app.service.TrainingService;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, ClientNotFoundException, ClientSaveException {
        TrainingRepository trainingRepository = new TrainingRepository();
        ClientRepository clientRepository = new ClientRepository();

        TrainingService trainingService = new TrainingService();
        ClientService clientService = new ClientService();

        Training training = new Training(1, "Yoga", 100.0, 10, true);
        trainingRepository.save(training);

        trainingRepository.findAll().forEach(System.out::println);

        Client client = new Client(1, "Mark");
        clientService.save(client);

        clientRepository.findAll().forEach(System.out::println);

        System.out.println(clientService.getAllActiveClients());

    }
}
