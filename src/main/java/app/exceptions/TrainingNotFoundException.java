package app.exceptions;

public class TrainingNotFoundException extends Exception{
    public TrainingNotFoundException(int id) {
        super(String.format("Тренировка с идентификатором %d не найдена", id));
    }

    public TrainingNotFoundException(String title) {
        super(String.format("Тренировка с названием %s не найдена", title));
    }
}
