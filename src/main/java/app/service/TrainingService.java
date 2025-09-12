package app.service;

import app.domain.Training;
import app.exceptions.TrainingNotFoundException;
import app.exceptions.TrainingSaveException;
import app.exceptions.TrainingUpdateException;
import app.repository.TrainingRepository;

import java.io.IOException;
import java.util.List;

public class TrainingService {
    private final TrainingRepository repository;

    public TrainingService() throws IOException {
        repository = new TrainingRepository();
    }

    public Training save(Training training) throws TrainingSaveException {
        if (training == null) {
            throw new TrainingSaveException("Тренировка не может быть null");
        }

        String title = training.getTitle();
        if (title == null || title.trim().isEmpty()) {
            throw new TrainingSaveException("Название тренировки не может быть пустым");
        }

        if (training.getDurationInDays() <= 0) {
            throw new TrainingSaveException("Продолжительность тренировки должна быть положительной");
        }

        training.setActive(true);
        return repository.save(training);
    }

    public List<Training> getAllActiveTrainings() throws IOException {
        return repository.findAll()
                .stream()
                .filter(Training::isActive)
                .toList();
    }

    public Training getActiveTrainingById(int id) throws IOException, TrainingNotFoundException {
        Training training = repository.findById(id);

        if (training == null || !training.isActive()) {
            throw new TrainingNotFoundException(id);
        }

        return training;
    }

    public void update(Training training) throws TrainingUpdateException, IOException {
        if (training == null) {
            throw new TrainingUpdateException("Продукт не может быть null");
        }

        if (training.getPrice() <= 0) {
            throw new TrainingUpdateException("Цена продукта должна быть положительной");
        }

        training.setActive(true);
        repository.update(training);
    }

    public void deleteById(int id) throws IOException, TrainingNotFoundException {
        Training training = getActiveTrainingById(id);
        training.setActive(false);
        repository.update(training);
    }

    public void deleteByTitle(String title) throws IOException, TrainingNotFoundException {
        Training training = getAllActiveTrainings()
                .stream()
                .filter(x -> x.getTitle().equals(title))
                .peek(x -> x.setActive(false))
                .findFirst()
                .orElseThrow(
                        () -> new TrainingNotFoundException(title)
                );

        repository.update(training);
    }

    public void restoreById(int id) throws IOException, TrainingNotFoundException {
        Training training = repository.findById(id);

        if (training != null) {
            training.setActive(true);
            repository.update(training);
        } else {
            throw new TrainingNotFoundException(id);
        }
    }

    public int getActiveTrainingCount() throws IOException {
        return getAllActiveTrainings().size();
    }

    public double getActiveTrainingsTotalCost() throws IOException {
        return getAllActiveTrainings()
                .stream()
                .mapToDouble(Training::getPrice)
                .sum();
    }

    public double getActiveProductsAveragePrice() throws IOException {
        int productCount = getActiveProductsCount();

        if (productCount == 0) {
            return 0.0;
        }

        return getActiveProductsTotalCost() / productCount;
    }
}
