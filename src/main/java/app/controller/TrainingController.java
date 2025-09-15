package app.controller;

import app.domain.Training;
import app.exceptions.TrainingNotFoundException;
import app.exceptions.TrainingSaveException;
import app.exceptions.TrainingUpdateException;
import app.service.TrainingService;

import java.io.IOException;
import java.util.List;

public class TrainingController {

    private final TrainingService service;

    public TrainingController() throws IOException {
        service = new TrainingService();
    }

    public Training save(String title, double price, int durationInDays) throws TrainingSaveException, IOException {
        Training training = new Training(title, price, durationInDays);
        return service.save(training);
    }

    public List<Training> getAllActiveTrainings() throws IOException {
        return service.getAllActiveTrainings();
    }

    public Training getActiveTrainingById(int id) throws IOException, TrainingNotFoundException {
        return service.getActiveTrainingById(id);
    }

    public void update(int id, String title, double price, int durationInDays) throws TrainingUpdateException, IOException {
        Training training = new Training(id, title, price, durationInDays);
        service.update(training);
    }

    public void deleteById(int id) throws IOException, TrainingNotFoundException {
        service.deleteById(id);
    }

    public void deleteByTitle(String title) throws IOException, TrainingNotFoundException {
        service.deleteByTitle(title);
    }

    public void restoreById(int id) throws IOException, TrainingNotFoundException {
        service.restoreById(id);
    }

    public int getActiveTrainingsCount() throws IOException {
        return service.getActiveTrainingsCount();
    }

    public double getActiveTrainingsTotalCost() throws IOException {
        return service.getActiveTrainingsTotalCost();
    }

    public double getActiveTrainingsAveragePrice() throws IOException {
        return service.getActiveTrainingsAveragePrice();
    }

    public double getActiveTrainingsAverageDuration() throws IOException {
        return service.getActiveTrainingsAverageDuration();
    }
}
