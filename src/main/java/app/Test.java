package app;

import app.domain.Client;
import app.domain.Training;
import app.repository.ClientRepository;
import app.repository.TrainingRepository;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        TrainingRepository trainingRepository = new TrainingRepository();
        ClientRepository clientRepository = new ClientRepository();

        Training training = new Training(1, "Yoga", 100.0, 10, true);
        trainingRepository.save(training);

        trainingRepository.findAll().forEach(System.out::println);

        Client client = new Client(1, "Mark");
        clientRepository.save(client);

        clientRepository.findAll().forEach(System.out::println);
    }
}
