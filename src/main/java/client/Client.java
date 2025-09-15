package client;

import app.controller.ClientController;
import app.controller.TrainingController;

import java.util.Scanner;

public class Client {
    private static ClientController clientController;
    private static TrainingController trainingController;
    private static Scanner scanner;

    private final static String COLOR_BLACK = "\u001B[0m";

    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_RED = "\u001B[31m";
    public static final String COLOR_GREEN = "\u001B[32m";
    public static final String COLOR_YELLOW = "\u001B[33m";
    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_PURPLE = "\u001B[35m";
    public static final String COLOR_CYAN = "\u001B[36m";

    public static final String COLOR_WHITE = "\u001B[37m";

    public static void main(String[] args) {
        try {
            clientController = new ClientController();
            trainingController = new TrainingController();
            scanner = new Scanner(System.in);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        while (true) {
            System.out.println(COLOR_GREEN + "Добро пожаловать в спортзал");
            System.out.println("============= v 1.0 =========" + COLOR_RESET);
            System.out.println("Выберите желаемую операцию");
            System.out.println("1 - операции с тренировками");
            System.out.println("2 - операции с клиентами");
            System.out.println("0 - выход");

            String input = scanner.nextLine();

            switch (input){
                case "1":
                    trainingOperations();
                    break;
                case "2":
                    clientOperations();
                    break;
                case "0":
                    return;
                default:
                    System.out.println(COLOR_YELLOW + "Некорректный ввод!" + COLOR_RESET);
            }
        }
    }

    private static void trainingOperations() {
        while (true) {
            try {
                System.out.println("Выберите желаемую операцию с тренировками:");
                System.out.println("1 - создать новую тренировку");
                System.out.println("2 - получить все тренировки");
                System.out.println("3 - получить тренировку по идентификатору");
                System.out.println("4 - изменить тренировку");
                System.out.println("5 - удалить тренировку по идентификатору");
                System.out.println("6 - удалить тренировку по названию");
                System.out.println("7 - восстановить тренировку по идентификатору");
                System.out.println("8 - получить количество тренировок");
                System.out.println("9 - получить суммарную стоимость всех тренировок");
                System.out.println("10 - получить среднюю стоимость тренировок");
                System.out.println("0 - выход");

                String input = scanner.nextLine();

                switch (input) {
                    case "1":
                        System.out.println("Введите название тренировки:");
                        String title = scanner.nextLine();
                        System.out.println("Введите цену тренировки:");
                        double price = Double.parseDouble(scanner.nextLine());
                        System.out.println("Введите длительность тренировки:");
                        int duration = Integer.parseInt(scanner.nextLine());
                        System.out.println(trainingController.save(title, price, duration));
                        break;
                    case "2":
                        trainingController.getAllActiveTrainings().forEach(System.out::println);
                        break;
                    case "3":
                        System.out.println("Введите идентификатор тренировки:");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println(trainingController.getActiveTrainingById(id));
                        break;
                    case "4":
                        System.out.println("Введите идентификатор тренировки:");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Введите новое название тренировки:");
                        title = scanner.nextLine();
                        System.out.println("Введите новую цену тренировки:");
                        price = Double.parseDouble(scanner.nextLine());
                        System.out.println("Введите новую длительность тренировки:");
                        duration = Integer.parseInt(scanner.nextLine());
                        trainingController.update(id, title, price, duration);
                        System.out.println(trainingController.getActiveTrainingById(id));
                        break;
                    case "5":
                        System.out.println("Введите идентификатор тренировки:");
                        id = Integer.parseInt(scanner.nextLine());
                        trainingController.deleteById(id);
                        break;
                    case "6":
                        System.out.println("Введите название тренировки:");
                        title = scanner.nextLine();
                        trainingController.deleteByTitle(title);
                        break;
                    case "7":
                        System.out.println("Введите идентификатор тренировки:");
                        id = Integer.parseInt(scanner.nextLine());
                        trainingController.restoreById(id);
                        break;
                    case "8":
                        System.out.println("Количество тренировок: " + trainingController.getActiveTrainingsCount());
                        break;
                    case "9":
                        System.out.println("Суммарная стоимость всех тренировок: " + trainingController.getActiveTrainingsTotalCost());
                        break;
                    case "10":
                        System.out.println("Средняя стоимость тренировок: " + trainingController.getActiveTrainingsAveragePrice());
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println(COLOR_YELLOW + "Некорректный ввод!" + COLOR_RESET);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void clientOperations() {
        while (true) {
            try {
                System.out.println("Выберите желаемую операцию с клиентами:");
                System.out.println("1 - создать нового клиента");
                System.out.println("2 - получить всех клиентов");
                System.out.println("3 - получить клиента по идентификатору");
                System.out.println("4 - изменить клиента");
                System.out.println("5 - удалить клиента по идентификатору");
                System.out.println("6 - удалить клиента по имени");
                System.out.println("7 - восстановить клиента по идентификатору");
                System.out.println("8 - получить количество клиентов");
                System.out.println("9 - получить стоимость тренировок клиента");
                System.out.println("10 - получить среднюю стоимость тренировок клиента");
                System.out.println("11 - добавить тренировку клиенту");
                System.out.println("12 - удалить тренировку у клиента");
                System.out.println("13 - очистить тренировки у клиента");
                System.out.println("0 - выход");

                String input = scanner.nextLine();

                switch (input) {
                    case "1":
                        System.out.println("Введите имя нового клиента:");
                        String name = scanner.nextLine();
                        clientController.save(name);
                        break;
                    case "2":
                        clientController.getAllActiveClients().forEach(System.out::println);
                        break;
                    case "3":
                        System.out.println("Введите идентификатор клиента:");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println(clientController.getActiveClientById(id));
                        break;
                    case "4":
                        System.out.println("Введите идентификатор клиента:");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Введите имя клиента:");
                        name = scanner.nextLine();
                        clientController.update(id, name);
                        break;
                    case "5":
                        System.out.println("Введите идентификатор клиента:");
                        id = Integer.parseInt(scanner.nextLine());
                        clientController.deleteById(id);
                        break;
                    case "6":
                        System.out.println("Введите имя клиента:");
                        name = scanner.nextLine();
                        clientController.deleteByName(name);
                        break;
                    case "7":
                        System.out.println("Введите идентификатор клиента:");
                        id = Integer.parseInt(scanner.nextLine());
                        clientController.restoreById(id);
                        break;
                    case "8":
                        System.out.println("Количество клиентов: " + clientController.getActiveClientsCount());
                        break;
                    case "9":
                        System.out.println("Введите идентификатор клиента:");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Стоимость тренировок клиента: " + clientController.getClientTrainingsTotalPrice(id));
                        break;
                    case "10":
                        System.out.println("Введите идентификатор клиента:");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Средняя стоимость тренировок клиента: " + clientController.getClientTrainingsAveragePrice(id));
                        break;
                    case "11":
                        System.out.println("Введите идентификатор клиента:");
                        int clientId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Введите идентификатор тренировки:");
                        int trainingId = Integer.parseInt(scanner.nextLine());
                        clientController.addTrainingToClient(clientId, trainingId);
                        break;
                    case "12":
                        System.out.println("Введите идентификатор клиента:");
                        clientId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Введите идентификатор тренировки:");
                        trainingId = Integer.parseInt(scanner.nextLine());
                        clientController.removeTrainingFromClient(clientId,trainingId);
                        break;
                    case "13":
                        System.out.println("Введите идентификатор клиента:");
                        id = Integer.parseInt(scanner.nextLine());
                        clientController.clearClientTrainings(id);
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println(COLOR_YELLOW + "Некорректный ввод!" + COLOR_RESET);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
