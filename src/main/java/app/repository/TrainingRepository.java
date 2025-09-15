package app.repository;

import app.domain.Training;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TrainingRepository {
    private final File database;
    private final ObjectMapper mapper;
    private int maxId;


    public TrainingRepository() throws IOException {
        database = new File("database/training.txt");
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);


        List<Training> trainings = findAll();
        if (!trainings.isEmpty()) {
            Training lastTraining = trainings.get(trainings.size() - 1);
            maxId = lastTraining.getId();
        }
    }

    public Training save(Training training) throws IOException {
        training.setId(++maxId);
        List<Training> trainings = findAll();
        trainings.add(training);
        mapper.writeValue(database, trainings);
        return training;
    }

    public List<Training> findAll() throws IOException {
        try {
            Training[] trainings = mapper.readValue(database, Training[].class);
            return new ArrayList<>(Arrays.asList(trainings));
        } catch (MismatchedInputException e) {
            return new ArrayList<>();
        }
    }

    public Training findById(int id) throws IOException {
        return findAll()
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void update(Training training) throws IOException {
        int id = training.getId();
        String newTitle = training.getTitle();
        double newPrice = training.getPrice();
        int newDuration = training.getDurationInDays();
        boolean newActive = training.isActive();

        List<Training> trainings = findAll();
        trainings.stream()
                .filter(x -> x.getId() == id)
                .forEach(x -> {
                    x.setTitle(newTitle);
                    x.setPrice(newPrice);
                    x.setDurationInDays(newDuration);
                    x.setActive(newActive);
                });

        mapper.writeValue(database, trainings);
    }

    public void deleteById(int id) throws IOException {
        List<Training> trainings = findAll();
        trainings.removeIf(x -> x.getId() == id);
        mapper.writeValue(database, trainings);
    }
}